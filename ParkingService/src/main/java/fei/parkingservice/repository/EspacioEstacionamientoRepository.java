/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fei.parkingservice.repository;

import fei.parkingservice.config.MyBatisUtil;
import fei.parkingservice.model.EspacioEstacionamiento;
import org.apache.ibatis.session.SqlSession;
import java.util.HashMap;
import java.util.List;
/**
 *
 * @author sergg
 */
public class EspacioEstacionamientoRepository {

    public EspacioEstacionamiento buscarPorId(Integer idEspacio) {
        SqlSession session = null;
        EspacioEstacionamiento espacio = null;
        try {
            session = MyBatisUtil.getSession();
            espacio = session.selectOne("EspacioEstacionamiento.buscarPorId", idEspacio);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return espacio;
    }

    public List<EspacioEstacionamiento> listarDisponibles() {
        SqlSession session = null;
        List<EspacioEstacionamiento> espacios = null;
        try {
            session = MyBatisUtil.getSession();
            espacios = session.selectList("EspacioEstacionamiento.listarDisponibles");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return espacios;
    }

    public boolean actualizarOcupado(Integer idEspacio, boolean ocupado) {
        SqlSession session = null;
        int filas = 0;
        try {
            session = MyBatisUtil.getSession();
            HashMap<String, Object> params = new HashMap<>();
            params.put("idEspacio", idEspacio);
            params.put("ocupado", ocupado);
            filas = session.update("EspacioEstacionamiento.actualizarOcupado", params);
            session.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return filas > 0;
    }
}
