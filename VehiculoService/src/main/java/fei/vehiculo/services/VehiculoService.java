package fei.vehiculo.services;

import fei.vehiculo.config.MyBatisUtil;
import fei.vehiculo.model.Vehiculo;
import fei.vehiculo.repositories.VehiculoRepository;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

public class VehiculoService {

    // BUSCAR VEHÍCULOS POR USUARIO
    public List<Vehiculo> obtenerVehiculosPorUsuario(int idUsuario) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            VehiculoRepository repository = session.getMapper(VehiculoRepository.class);
            return repository.findByUserId(idUsuario);
        }
    }

    // REGISTRAR VEHÍCULO
    public void registrarVehiculo(Vehiculo vehiculo) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            VehiculoRepository repository = session.getMapper(VehiculoRepository.class);
            if (repository.countByPlaca(vehiculo.getPlaca()) > 0) {
                throw new IllegalArgumentException("La placa " + vehiculo.getPlaca() + " ya está registrada en el sistema.");
            }

            repository.save(vehiculo);
            session.commit(); 
        }
    }

    // EDITAR VEHÍCULO
    public void editarVehiculo(Vehiculo vehiculo) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            VehiculoRepository repository = session.getMapper(VehiculoRepository.class);
            if (repository.findById(vehiculo.getIdVehiculo()) == null) {
                throw new IllegalArgumentException("El vehículo con ID " + vehiculo.getIdVehiculo() + " no existe.");
            }

            repository.update(vehiculo);
            session.commit();
        }
    }

    // CAMBIAR ESTATUS
    public void cambiarEstatusVehiculo(int idVehiculo, int idUsuario, String estatus) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            VehiculoRepository repository = session.getMapper(VehiculoRepository.class);

            String bitStatus = (estatus.equalsIgnoreCase("activo") || estatus.equals("1")) ? "1" : "0";

            repository.updateStatus(idVehiculo, bitStatus);
            session.commit();
        }
    }
    
    //Eliminar Vehiculo
    public void eliminarVehiculo(int idVehiculo) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            VehiculoRepository repository = session.getMapper(VehiculoRepository.class);

            // Validar si existe antes de borrar
            if (repository.findById(idVehiculo) == null) {
                throw new IllegalArgumentException("El vehículo con ID " + idVehiculo + " no existe.");
            }

            repository.delete(idVehiculo);
            session.commit();
        }
    }
}