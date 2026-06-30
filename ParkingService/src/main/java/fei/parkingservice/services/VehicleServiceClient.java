package fei.parkingservice.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fei.parkingservice.dto.VehiculoInfoDTO;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

public class VehicleServiceClient {

    private static final String VEHICLE_SERVICE_URL =
            System.getenv().getOrDefault("VEHICLE_SERVICE_URL", "http://172.25.52.190:8080");

    public static List<VehiculoInfoDTO> obtenerVehiculosPorUsuario(Integer idUsuario, String token) {
        Client client = ClientBuilder.newClient();

        try {
            WebTarget target = client.target(VEHICLE_SERVICE_URL)
                    .path("/VehicleService/api/vehicle/user/{idUsuario}")
                    .resolveTemplate("idUsuario", idUsuario);

            Response response = target.request(MediaType.APPLICATION_JSON)
                    .header("Authorization", "Bearer " + token)
                    .get();

            String body = response.readEntity(String.class);

            if (response.getStatus() != 200) {
                System.out.println("VehicleService respondió con status: " + response.getStatus());
                System.out.println("Respuesta VehicleService: " + body);
                return new ArrayList<>();
            }

            ObjectMapper mapper = new ObjectMapper();

            List<VehiculoInfoDTO> vehiculos = mapper.readValue(body,
                    new TypeReference<List<VehiculoInfoDTO>>() {}
            );

            return vehiculos != null ? vehiculos : new ArrayList<>();

        } catch (Exception ex) {
            System.out.println("Error al conectar con VehicleService: " + ex.getMessage());
            ex.printStackTrace();
            return new ArrayList<>();
        } finally {
            client.close();
        }
    }
}