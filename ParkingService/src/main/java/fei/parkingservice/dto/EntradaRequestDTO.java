package fei.parkingservice.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class EntradaRequestDTO {

    private String claveUsuario;
    private String placa;
    private Timestamp tiempoEntrada;
    private Timestamp tiempoCreacion;
    private BigDecimal tarifa;
    private Integer idEspacio;

    public EntradaRequestDTO() {
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

    public Timestamp getTiempoEntrada() {
        return tiempoEntrada;
    }

    public void setTiempoEntrada(Timestamp tiempoEntrada) {
        this.tiempoEntrada = tiempoEntrada;
    }

    public Timestamp getTiempoCreacion() {
        return tiempoCreacion;
    }

    public void setTiempoCreacion(Timestamp tiempoCreacion) {
        this.tiempoCreacion = tiempoCreacion;
    }

    public BigDecimal getTarifa() {
        return tarifa;
    }

    public void setTarifa(BigDecimal tarifa) {
        this.tarifa = tarifa;
    }

    public Integer getIdEspacio() {
        return idEspacio;
    }

    public void setIdEspacio(Integer idEspacio) {
        this.idEspacio = idEspacio;
    }
}