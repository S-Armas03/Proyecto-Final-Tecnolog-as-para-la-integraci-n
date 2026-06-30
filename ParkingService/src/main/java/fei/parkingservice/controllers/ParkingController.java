/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fei.parkingservice.controllers;

import fei.parkingservice.dto.EntradaRequestDTO;
import fei.parkingservice.dto.EntradaResponseDTO;
import fei.parkingservice.dto.SalidaRequestDTO;
import fei.parkingservice.dto.SalidaResponseDTO;
import fei.parkingservice.model.EspacioEstacionamiento;
import fei.parkingservice.services.ParkingService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author sergg
 */

@Path("/parking")
public class ParkingController {

    private final ParkingService parkingService = new ParkingService();
    
    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public Response test() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "OK");
        response.put("message", "ParkingService funcionando correctamente");
        return Response.ok(response).build();
    }

    @POST
    @Path("/entrada")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarEntrada(EntradaRequestDTO request,@HeaderParam("Authorization") String authHeader) {
        try {
            String token = extraerToken(authHeader);
            EntradaResponseDTO response = parkingService.registrarEntrada(request, token);
            return Response.ok(response).build();
        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ex.getMessage())
                    .build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error interno al registrar la entrada")
                    .build();
        }
    }

    @PUT
    @Path("/salida")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarSalida(SalidaRequestDTO request,@HeaderParam("Authorization") String authHeader) {
        try {
            String token = extraerToken(authHeader);
            SalidaResponseDTO response = parkingService.registrarSalida(request, token);
            return Response.ok(response).build();
        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ex.getMessage())
                    .build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error interno al registrar la salida")
                    .build();
        }
    }

    @GET
    @Path("/espacios")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarEspacios() {
        try {
            List<EspacioEstacionamiento> espacios = parkingService.consultarEspaciosDisponibles();
            return Response.ok(espacios).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error interno al consultar espacios")
                    .build();
        }
    }

    private String extraerToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Token de autenticación no proporcionado");
        }
        return authHeader.substring("Bearer ".length()).trim();
    }
}