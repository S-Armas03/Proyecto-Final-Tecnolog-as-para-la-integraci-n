package fei.parkingservice.dto;
/**
 *
 * @author sergg
 */
public class UsuarioInfoDTO {
    private Integer idUsuario;
    private String claveUsuario;
    private Boolean estatus;

    public UsuarioInfoDTO() {}

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public String getClaveUsuario() { return claveUsuario; }
    public void setClaveUsuario(String claveUsuario) { this.claveUsuario = claveUsuario; }

    public Boolean getEstatus() { return estatus; }
    public void setEstatus(Boolean estatus) { this.estatus = estatus; }
}