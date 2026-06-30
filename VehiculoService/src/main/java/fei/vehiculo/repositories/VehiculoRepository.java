package fei.vehiculo.repositories;

import fei.vehiculo.model.Vehiculo;
import java.util.List;
import org.apache.ibatis.annotations.*;

public interface VehiculoRepository {

    //Buscar Vehiculo
    @Select("SELECT * FROM vehiculo WHERE idUsuario = #{idUsuario}")
    @Results({
        @Result(property = "idVehiculo", column = "idVehiculo"),
        @Result(property = "idUsuario", column = "idUsuario"),
        @Result(property = "claveVehiculo", column = "claveVehiculo"),
        @Result(property = "idModelo", column = "idModelo"),
        @Result(property = "placa", column = "placa"),
        @Result(property = "color", column = "color"),
        @Result(property = "anio", column = "anio"),
        @Result(property = "descripcion", column = "descripcion")
    })
    List<Vehiculo> findByUserId(@Param("idUsuario") int idUsuario);

    @Select("SELECT COUNT(*) FROM vehiculo WHERE placa = #{placa}")
    int countByPlaca(@Param("placa") String placa);

   //Registrar Vehiculo
    @Insert("INSERT INTO vehiculo (idUsuario, claveVehiculo, idModelo, placa, color, anio, descripcion) " +
            "VALUES (#{idUsuario}, #{claveVehiculo}, #{idModelo}, #{placa}, #{color}, #{anio}, #{descripcion})")
    @Options(useGeneratedKeys = true, keyProperty = "idVehiculo")
    int save(Vehiculo vehiculo);

    @Select("SELECT * FROM vehiculo WHERE idVehiculo = #{idVehiculo}")
    Vehiculo findById(@Param("idVehiculo") int idVehiculo);

    //EDITAR VEHÍCULO
    @Update("UPDATE vehiculo SET placa = #{placa}, color = #{color}, idModelo = #{idModelo}, " +
            "anio = #{anio}, claveVehiculo = #{claveVehiculo}, descripcion = #{descripcion} " +
            "WHERE idVehiculo = #{idVehiculo}")
    int update(Vehiculo vehiculo);

    //ELIMINAR REGISTRO FISICAMENTE
     
    @Delete("DELETE FROM vehiculo WHERE idVehiculo = #{idVehiculo}")
    int delete(@Param("idVehiculo") int idVehiculo);

    public void updateStatus(int idVehiculo, String bitStatus);
}
