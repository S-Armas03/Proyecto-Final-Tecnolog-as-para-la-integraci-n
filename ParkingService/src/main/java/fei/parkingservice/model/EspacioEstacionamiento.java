/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fei.parkingservice.model;

import java.io.Serializable;

/**
 *
 * @author sergg
 */
public class EspacioEstacionamiento implements Serializable {
    private Integer idEspacio;
    private String claveEspacio;
    private String tipo;
    private Boolean ocupado;
    private Boolean estatus;

    public EspacioEstacionamiento(Integer idEspacio, String claveEspacio, String tipo, Boolean ocupado, Boolean estatus) {
        this.idEspacio = idEspacio;
        this.claveEspacio = claveEspacio;
        this.tipo = tipo;
        this.ocupado = ocupado;
        this.estatus = estatus;
    }

    public EspacioEstacionamiento() {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getOcupado() {
        return ocupado;
    }

    public void setOcupado(Boolean ocupado) {
        this.ocupado = ocupado;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }
}
  
