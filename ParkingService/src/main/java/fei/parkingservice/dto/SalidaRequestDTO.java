package fei.parkingservice.dto;

import java.sql.Timestamp;
/**
 *
 * @author sergg
 */
public class SalidaRequestDTO {

    private String claveUsuario;
    private String placa;
    private Timestamp tiempoSalida;
    private Timestamp tiempoActualizacion;

    public SalidaRequestDTO() {
    }

    public String getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Timestamp getTiempoSalida() {
        return tiempoSalida;
    }

    public void setTiempoSalida(Timestamp tiempoSalida) {
        this.tiempoSalida = tiempoSalida;
    }

    public Timestamp getTiempoActualizacion() {
        return tiempoActualizacion;
    }

    public void setTiempoActualizacion(Timestamp tiempoActualizacion) {
        this.tiempoActualizacion = tiempoActualizacion;
    }
}