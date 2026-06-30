package fei.parkingservice.services;

import fei.parkingservice.dto.UsuarioInfoDTO;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
/**
 *
 * @author sergg
 */
public class UserServiceClient {

    private static final String USER_SERVICE_URL =
            System.getenv().getOrDefault("USER_SERVICE_URL","http://172.25.52.136:6060");

    public static UsuarioInfoDTO obtenerUsuarioPorClave(String claveUsuario, String token) {
        Client client = ClientBuilder.newClient();
        try {
            WebTarget target = client.target(USER_SERVICE_URL)
                    .path("sicae/user/api/clave/{claveUsuario}")
                    .resolveTemplate("claveUsuario", claveUsuario);

            Response response = target.request(MediaType.APPLICATION_JSON)
                    .header("Authorization", "Bearer " + token)
                    .get();

            if (response.getStatus() != 200) {
                return null;
            }
            return response.readEntity(UsuarioInfoDTO.class);
        } catch (Exception ex) {
            System.out.println("Error al conectar con UserService: " + ex.getMessage());
            return null;
        } finally {
            client.close();
        }
    }
}