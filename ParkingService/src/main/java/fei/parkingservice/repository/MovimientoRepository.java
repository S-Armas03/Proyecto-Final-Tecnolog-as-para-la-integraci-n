/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fei.parkingservice.repository;

import fei.parkingservice.config.MyBatisUtil;
import fei.parkingservice.model.Movimiento;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author sergg
 */
public class MovimientoRepository {
        public Movimiento registrarEntrada(Movimiento movimiento) {
        SqlSession session = null;

        try {
            session = MyBatisUtil.getSession();

            int espacioActualizado = session.update(
                    "EspacioEstacionamiento.ocuparSiDisponible",
                    movimiento.getIdEspacio()
            );

            if (espacioActualizado == 0) {
                session.rollback();
                return null;
            }

            session.insert("Movimiento.registrarEntrada", movimiento);
            session.commit();

            return movimiento;

        } catch (Exception ex) {
            if (session != null) {
                session.rollback();
            }
            ex.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    public Movimiento registrarSalida(Movimiento movimiento) {
    SqlSession session = null;

    try {
        session = MyBatisUtil.getSession();

        session.update("Movimiento.registrarSalida", movimiento);

        session.update(
                "EspacioEstacionamiento.liberarEspacio",
                movimiento.getIdEspacio()
        );

        session.commit();

        return movimiento;

    } catch (Exception ex) {
        if (session != null) {
            session.rollback();
        }
        ex.printStackTrace();
        return null;
    } finally {
        if (session != null) {
            session.close();
        }
    }
}

    public Movimiento buscarActivoPorVehiculo(Integer idVehiculo) {
        SqlSession session = null;

        try {
            session = MyBatisUtil.getSession();
            return session.selectOne("Movimiento.buscarActivoPorVehiculo", idVehiculo);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public int contarActivosPorVehiculos(List<Integer> idsVehiculos) {
        if (idsVehiculos == null || idsVehiculos.isEmpty()) {
            return 0;
        }

        SqlSession session = null;

        try {
            session = MyBatisUtil.getSession();
            Integer total = session.selectOne("Movimiento.contarActivosPorVehiculos", idsVehiculos);
            return total != null ? total : 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
