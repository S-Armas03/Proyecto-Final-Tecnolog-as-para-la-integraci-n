package fei.vehiculo.controllers;
import fei.vehiculo.model.Vehiculo; 
import fei.vehiculo.services.VehiculoService; 
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/vehiculo") // URL base: /api/vehiculo
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VehiculoController {

    private final VehiculoService vehiculoService = new VehiculoService();

    /**
     * 1. BUSCAR VEHÍCULOS POR USUARIO
     * GET -> /api/vehiculo/usuario/{idUsuario}
     */
    @GET
    @Path("/usuario/{idUsuario}")
    public Response obtenerVehiculosPorUsuario(@PathParam("idUsuario") int idUsuario) {
        try {
            if (idUsuario <= 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"message\":\"El idUsuario proporcionado no es válido.\"}")
                        .build();
            }
            
            List<Vehiculo> lista = vehiculoService.obtenerVehiculosPorUsuario(idUsuario);
            return Response.ok(lista).build();
            
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error al consultar los vehículos: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    /**
     * 2. REGISTRAR VEHÍCULO
     * POST -> /api/vehiculo/registrar
     */
    @POST
    @Path("/registrar")
    public Response registrarVehiculo(Vehiculo vehiculo) {
        try {
            if (vehiculo == null || vehiculo.getPlaca() == null || vehiculo.getPlaca().trim().isEmpty() || vehiculo.getIdUsuario() == 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"message\":\"Faltan datos obligatorios para el registro (placa o idUsuario).\"}")
                        .build();
            }
            vehiculoService.registrarVehiculo(vehiculo);
            
            return Response.status(Response.Status.CREATED)
                    .entity("{\"message\":\"Vehículo registrado correctamente en el sistema.\"}")
                    .build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"message\":\"" + e.getMessage() + "\"}")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error interno en el servidor: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    /**
     * 3. EDITAR VEHÍCULO
     * PUT -> /api/vehiculo/editar
     */
    @PUT
    @Path("/editar")
    public Response editarVehiculo(Vehiculo vehiculo) {
        try {
            if (vehiculo == null || vehiculo.getIdVehiculo() == 0 || vehiculo.getIdUsuario() == 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"message\":\"Identificadores de vehículo y usuario obligatorios para editar.\"}")
                        .build();
            }

            vehiculoService.editarVehiculo(vehiculo);
            
            return Response.ok("{\"message\":\"Vehículo actualizado correctamente.\"}")
                    .build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"message\":\"" + e.getMessage() + "\"}")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error al actualizar el vehículo: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    /**
     * 4. ELIMINAR VEHÍCULO (BORRADO FÍSICO)
     * DELETE -> /api/vehiculo/eliminar/{idVehiculo}
     */
    @DELETE
    @Path("/eliminar/{idVehiculo}")
    public Response eliminarVehiculo(@PathParam("idVehiculo") int idVehiculo) {
        try {
            if (idVehiculo <= 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"message\":\"El idVehiculo proporcionado no es válido.\"}")
                        .build();
            }

            vehiculoService.eliminarVehiculo(idVehiculo);
            
            return Response.ok("{\"message\":\"Vehículo eliminado correctamente del sistema.\"}")
                    .build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"message\":\"" + e.getMessage() + "\"}")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error al eliminar el vehículo: " + e.getMessage() + "\"}")
                    .build();
        }
    }
}