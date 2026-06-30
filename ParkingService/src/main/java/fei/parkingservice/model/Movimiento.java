/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fei.parkingservice.model;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 *
 * @author sergg
 */
public class Movimiento {

    private Integer idMovimiento;
    private Integer idVehiculo;
    private Timestamp tiempoEntrada;
    private Timestamp tiempoSalida;
    private Integer minutosEstacionado;
    private Integer horasCobradas;
    private BigDecimal costoTotal;
    private BigDecimal tarifaHora;
    private Timestamp tiempoCreacion;
    private Timestamp tiempoActualizacion;
    private Integer idEspacio;

 
    private String claveEspacio;
    private String tipoEspacio;

    public Movimiento() {
    }

    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
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

    public Integer getMinutosEstacionado() {
        return minutosEstacionado;
    }

    public void setMinutosEstacionado(Integer minutosEstacionado) {
        this.minutosEstacionado = minutosEstacionado;
    }

    public Integer getHorasCobradas() {
        return horasCobradas;
    }

    public void setHorasCobradas(Integer horasCobradas) {
        this.horasCobradas = horasCobradas;
    }

    public BigDecimal getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(BigDecimal costoTotal) {
        this.costoTotal = costoTotal;
    }

    public BigDecimal getTarifaHora() {
        return tarifaHora;
    }

    public void setTarifaHora(BigDecimal tarifaHora) {
        this.tarifaHora = tarifaHora;
    }

    public Timestamp getTiempoCreacion() {
        return tiempoCreacion;
    }

    public void setTiempoCreacion(Timestamp tiempoCreacion) {
        this.tiempoCreacion = tiempoCreacion;
    }

    public Timestamp getTiempoActualizacion() {
        return tiempoActualizacion;
    }

    public void setTiempoActualizacion(Timestamp tiempoActualizacion) {
        this.tiempoActualizacion = tiempoActualizacion;
    }

    public Integer getIdEspacio() {
        return idEspacio;
    }

    public void setIdEspacio(Integer idEspacio) {
        this.idEspacio = idEspacio;
    }

    public String getClaveEspacio() {
        return claveEspacio;
    }

    public void setClaveEspacio(String claveEspacio) {
        this.claveEspacio = claveEspacio;
    }

    public String getTipoEspacio() {
        return tipoEspacio;
    }

    public void setTipoEspacio(String tipoEspacio) {
        this.tipoEspacio = tipoEspacio;
    }
}

