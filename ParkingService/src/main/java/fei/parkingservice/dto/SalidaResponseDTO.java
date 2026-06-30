/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fei.parkingservice.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *
 * @author sergg
 */
public class SalidaResponseDTO {

    private Integer idMovimiento;
    private Timestamp tiempoEntrada;
    private Timestamp tiempoSalida;
    private String espacioAsignado;
    private BigDecimal tarifaHora;
    private BigDecimal costoTotal;
    private Integer horasCobradas;

    public SalidaResponseDTO() {
    }

    public SalidaResponseDTO(Integer idMovimiento, Timestamp tiempoEntrada, Timestamp tiempoSalida,
            String espacioAsignado, BigDecimal tarifaHora, BigDecimal costoTotal, Integer horasCobradas) {
        this.idMovimiento = idMovimiento;
        this.tiempoEntrada = tiempoEntrada;
        this.tiempoSalida = tiempoSalida;
        this.espacioAsignado = espacioAsignado;
        this.tarifaHora = tarifaHora;
        this.costoTotal = costoTotal;
        this.horasCobradas = horasCobradas;
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

    public Timestamp getTiempoSalida() {
        return tiempoSalida;
    }

    public void setTiempoSalida(Timestamp tiempoSalida) {
        this.tiempoSalida = tiempoSalida;
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

    public BigDecimal getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(BigDecimal costoTotal) {
        this.costoTotal = costoTotal;
    }

    public Integer getHorasCobradas() {
        return horasCobradas;
    }

    public void setHorasCobradas(Integer horasCobradas) {
        this.horasCobradas = horasCobradas;
    }
}
