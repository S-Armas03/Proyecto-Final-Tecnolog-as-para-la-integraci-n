package fei.parkingservice.services;

import fei.parkingservice.dto.EntradaRequestDTO;
import fei.parkingservice.dto.EntradaResponseDTO;
import fei.parkingservice.dto.SalidaRequestDTO;
import fei.parkingservice.dto.SalidaResponseDTO;
import fei.parkingservice.dto.VehiculoInfoDTO;
import fei.parkingservice.model.EspacioEstacionamiento;
import fei.parkingservice.model.Movimiento;
import fei.parkingservice.repository.EspacioEstacionamientoRepository;
import fei.parkingservice.repository.MovimientoRepository;
import fei.parkingservice.utils.JwtUtil;
import io.jsonwebtoken.Claims;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ParkingService {

   private static final int MAX_VEHICULOS_DENTRO_POR_USUARIO = 2;

    private final EspacioEstacionamientoRepository espacioRepository = new EspacioEstacionamientoRepository();
    private final MovimientoRepository movimientoRepository = new MovimientoRepository();

    public List<EspacioEstacionamiento> consultarEspaciosDisponibles() {
        return espacioRepository.listarDisponibles();
    }
    
   public SalidaResponseDTO registrarSalida(SalidaRequestDTO request, String token) {
    validarDatosSalida(request);

    Integer idUsuario = obtenerIdUsuarioDesdeToken(token);
    String placa = request.getPlaca().trim().toUpperCase();

    List<VehiculoInfoDTO> vehiculos = VehicleServiceClient.obtenerVehiculosPorUsuario(idUsuario, token);

    if (vehiculos == null || vehiculos.isEmpty()) {
        throw new IllegalArgumentException("El usuario no tiene vehículos registrados");
    }

    VehiculoInfoDTO vehiculo = buscarVehiculoDelUsuario(vehiculos, placa, idUsuario);

    if (!Boolean.TRUE.equals(vehiculo.getEstatus())) {
        throw new IllegalArgumentException("El vehículo no está activo");
    }

    Movimiento movimientoActivo = movimientoRepository.buscarActivoPorVehiculo(vehiculo.getIdVehiculo());

    if (movimientoActivo == null) {
        throw new IllegalArgumentException("El vehículo no tiene una entrada activa");
    }

    EspacioEstacionamiento espacio = espacioRepository.buscarPorId(movimientoActivo.getIdEspacio());

    if (espacio == null) {
        throw new IllegalArgumentException("No se encontró el espacio asignado al movimiento");
    }

    Timestamp ahora = new Timestamp(System.currentTimeMillis());
    Timestamp tiempoSalida = request.getTiempoSalida() != null ? request.getTiempoSalida() : ahora;
    Timestamp tiempoActualizacion = request.getTiempoActualizacion() != null ? request.getTiempoActualizacion() : ahora;

    long diferenciaMs = tiempoSalida.getTime() - movimientoActivo.getTiempoEntrada().getTime();

    if (diferenciaMs <= 0) {
        throw new IllegalArgumentException("El tiempo de salida debe ser mayor al tiempo de entrada");
    }

    int minutosEstacionado = (int) Math.ceil(diferenciaMs / 60000.0);
    int horasCobradas = (int) Math.ceil(minutosEstacionado / 60.0);

    if (horasCobradas <= 0) {
        horasCobradas = 1;
    }

    BigDecimal costoTotal = movimientoActivo.getTarifaHora()
            .multiply(BigDecimal.valueOf(horasCobradas));

    movimientoActivo.setTiempoSalida(tiempoSalida);
    movimientoActivo.setTiempoActualizacion(tiempoActualizacion);
    movimientoActivo.setMinutosEstacionado(minutosEstacionado);
    movimientoActivo.setHorasCobradas(horasCobradas);
    movimientoActivo.setCostoTotal(costoTotal);

    Movimiento movimientoActualizado = movimientoRepository.registrarSalida(movimientoActivo);

    if (movimientoActualizado == null) {
        throw new RuntimeException("No se pudo registrar la salida");
    }

    return new SalidaResponseDTO(
            movimientoActualizado.getIdMovimiento(),
            movimientoActualizado.getTiempoEntrada(),
            movimientoActualizado.getTiempoSalida(),
            espacio.getClaveEspacio(),
            movimientoActualizado.getTarifaHora(),
            movimientoActualizado.getCostoTotal(),
            movimientoActualizado.getHorasCobradas()
    );
}

private void validarDatosSalida(SalidaRequestDTO request) {
    if (request == null) {
        throw new IllegalArgumentException("El cuerpo de la solicitud es obligatorio");
    }

    if (request.getClaveUsuario() == null || request.getClaveUsuario().isBlank()) {
        throw new IllegalArgumentException("La claveUsuario es obligatoria");
    }

    if (request.getPlaca() == null || request.getPlaca().isBlank()) {
        throw new IllegalArgumentException("La placa es obligatoria");
    }
}
    
    public EntradaResponseDTO registrarEntrada(EntradaRequestDTO request, String token) {
        validarDatosEntrada(request);

        Integer idUsuario = obtenerIdUsuarioDesdeToken(token);
        String placa = request.getPlaca().trim().toUpperCase();

        List<VehiculoInfoDTO> vehiculos = VehicleServiceClient.obtenerVehiculosPorUsuario(idUsuario, token);

        if (vehiculos == null || vehiculos.isEmpty()) {
            throw new IllegalArgumentException("El usuario no tiene vehículos registrados");
        }

        VehiculoInfoDTO vehiculo = buscarVehiculoDelUsuario(vehiculos, placa, idUsuario);

        if (!Boolean.TRUE.equals(vehiculo.getEstatus())) {
            throw new IllegalArgumentException("El vehículo no está activo");
        }

        Movimiento movimientoActivo = movimientoRepository.buscarActivoPorVehiculo(vehiculo.getIdVehiculo());

        if (movimientoActivo != null) {
            throw new IllegalArgumentException("El vehículo ya se encuentra dentro del estacionamiento");
        }

        List<Integer> idsVehiculosActivos = obtenerIdsVehiculosActivos(vehiculos);
        int vehiculosDentro = movimientoRepository.contarActivosPorVehiculos(idsVehiculosActivos);

        if (vehiculosDentro >= MAX_VEHICULOS_DENTRO_POR_USUARIO) {
            throw new IllegalArgumentException("El usuario ya tiene el máximo de 2 vehículos dentro del estacionamiento");
        }

        EspacioEstacionamiento espacio = espacioRepository.buscarPorId(request.getIdEspacio());

        if (espacio == null) {
            throw new IllegalArgumentException("El espacio indicado no existe");
        }

        if (!Boolean.TRUE.equals(espacio.getEstatus())) {
            throw new IllegalArgumentException("El espacio indicado no está activo");
        }

        if (Boolean.TRUE.equals(espacio.getOcupado())) {
            throw new IllegalArgumentException("El espacio indicado ya está ocupado");
        }

        Timestamp ahora = new Timestamp(System.currentTimeMillis());

        Movimiento movimiento = new Movimiento();
        movimiento.setIdVehiculo(vehiculo.getIdVehiculo());
        movimiento.setTiempoEntrada(request.getTiempoEntrada() != null ? request.getTiempoEntrada() : ahora);
        movimiento.setTiempoCreacion(request.getTiempoCreacion() != null ? request.getTiempoCreacion() : ahora);
        movimiento.setTarifaHora(request.getTarifa());
        movimiento.setIdEspacio(request.getIdEspacio());

        Movimiento movimientoCreado = movimientoRepository.registrarEntrada(movimiento);

        if (movimientoCreado == null || movimientoCreado.getIdMovimiento() == null) {
            throw new RuntimeException("No se pudo registrar la entrada");
        }

        return new EntradaResponseDTO(
                movimientoCreado.getIdMovimiento(),
                movimientoCreado.getTiempoEntrada(),
                espacio.getClaveEspacio(),
                movimientoCreado.getTarifaHora()
        );
    }

    private void validarDatosEntrada(EntradaRequestDTO request) {
        if (request == null) {
            throw new IllegalArgumentException("El cuerpo de la solicitud es obligatorio");
        }

        if (request.getClaveUsuario() == null || request.getClaveUsuario().isBlank()) {
            throw new IllegalArgumentException("La claveUsuario es obligatoria");
        }

        if (request.getPlaca() == null || request.getPlaca().isBlank()) {
            throw new IllegalArgumentException("La placa es obligatoria");
        }

        if (request.getTarifa() == null || request.getTarifa().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("La tarifa es obligatoria y debe ser mayor a 0");
        }

        if (request.getIdEspacio() == null) {
            throw new IllegalArgumentException("El idEspacio es obligatorio");
        }
    }

    private Integer obtenerIdUsuarioDesdeToken(String token) {
        try {
            Claims claims = JwtUtil.validarToken(token);
            Integer idUsuario = JwtUtil.obtenerIdUsuario(claims);

            if (idUsuario == null) {
                throw new IllegalArgumentException("El token no contiene idUsuario");
            }

            return idUsuario;
        } catch (Exception ex) {
            throw new IllegalArgumentException("Token inválido");
        }
    }

    private VehiculoInfoDTO buscarVehiculoDelUsuario(List<VehiculoInfoDTO> vehiculos, String placa, Integer idUsuario) {
        return vehiculos.stream()
                .filter(v -> v.getPlaca() != null && placa.equalsIgnoreCase(v.getPlaca().trim()))
                .filter(v -> v.getIdUsuario() == null || v.getIdUsuario().equals(idUsuario))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("La placa no corresponde a un vehículo del usuario"));
    }

    private List<Integer> obtenerIdsVehiculosActivos(List<VehiculoInfoDTO> vehiculos) {
        List<Integer> ids = new ArrayList<>();

        for (VehiculoInfoDTO vehiculo : vehiculos) {
            if (vehiculo.getIdVehiculo() != null && Boolean.TRUE.equals(vehiculo.getEstatus())) {
                ids.add(vehiculo.getIdVehiculo());
            }
        }

        return ids;
    }
}