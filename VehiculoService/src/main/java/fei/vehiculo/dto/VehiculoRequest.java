package fei.vehiculo.dto; 

public class VehiculoRequest {
    
    private int idUsuario;
    private int idModelo;
    private String placa;
    private String color;
    private String anio;
    private String descripcion;

    public VehiculoRequest() {
    }

    public VehiculoRequest(int idUsuario, int idModelo, String placa, String color, String anio, String descripcion) {
        this.idUsuario = idUsuario;
        this.idModelo = idModelo;
        this.placa = placa;
        this.color = color;
        this.anio = anio;
        this.descripcion = descripcion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}