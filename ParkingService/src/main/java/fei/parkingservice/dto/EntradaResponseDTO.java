/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fei.parkingservice.dto;

/**
 *
 * @author sergg
 */
import java.math.BigDecimal;
import java.sql.Timestamp;

public class EntradaResponseDTO {

    private Integer idMovimiento;
    private Timestamp tiempoEntrada;
    private String espacioAsignado;
    private BigDecimal tarifaHora;

    public EntradaResponseDTO() {
    }

    public EntradaResponseDTO(Integer idMovimiento, Timestamp tiempoEntrada,
            String espacioAsignado, BigDecimal tarifaHora) {
        this.idMovimiento = idMovimiento;
        this.tiempoEntrada = tiempoEntrada;
        this.espacioAsignado = espacioAsignado;
        this.tarifaHora = tarifaHora;
    }

    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Timestamp getTiempoEntrada() {
        return tiempoEntrada;
    }

    public void setTiempoEntrada(Timestamp tiempoEntrada) {
        this.tiempoEntrada = tiempoEntrada;
    }

    public String getEspacioAsignado() {
        return espacioAsignado;
    }

    public void setEspacioAsignado(String espacioAsignado) {
        this.espacioAsignado = espacioAsignado;
    }

    public BigDecimal getTarifaHora() {
        return tarifaHora;
    }

    public void setTarifaHora(BigDecimal tarifaHora) {
        this.tarifaHora = tarifaHora;
    }
}