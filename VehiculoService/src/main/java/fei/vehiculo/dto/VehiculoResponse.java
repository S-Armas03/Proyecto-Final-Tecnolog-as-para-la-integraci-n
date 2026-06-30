package fei.vehiculo.dto;

public class VehiculoResponse {
    
    private int idVehiculo;
    private int idUsuario;
    private int idModelo;
    private String nombreModelo; 
    private int idMarca;
    private String nombreMarca;  
    private String placa;
    private String color;
    private String anio;
    private String descripcion;
    private String estatus;

    public VehiculoResponse() {
    }

    public VehiculoResponse(int idVehiculo, int idUsuario, int idModelo, String nombreModelo, 
                            int idMarca, String nombreMarca, String placa, String color, 
                            String anio, String descripcion, String estatus) {
        this.idVehiculo = idVehiculo;
        this.idUsuario = idUsuario;
        this.idModelo = idModelo;
        this.nombreModelo = nombreModelo;
        this.idMarca = idMarca;
        this.nombreMarca = nombreMarca;
        this.placa = placa;
        this.color = color;
        this.anio = anio;
        this.descripcion = descripcion;
        this.estatus = estatus;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
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

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
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

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}