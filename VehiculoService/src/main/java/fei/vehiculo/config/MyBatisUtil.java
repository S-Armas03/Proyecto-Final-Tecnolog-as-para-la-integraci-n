package fei.vehiculo.config; // <-- Paquete actualizado a español

import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
    private static final String RESOURCE = "mybatis-config.xml";
    private static final String ENVIRONMENT = "development";
    private static final SqlSessionFactory sqlSessionFactory;

    static {
        try {
            // Lee el archivo de configuración de MyBatis desde src/main/resources
            Reader reader = Resources.getResourceAsReader(RESOURCE);
            
            // Inicializa la factoría usando el patrón Singleton
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, ENVIRONMENT);
        } catch (IOException ex) {
            System.err.println("[MyBatisUtil] Error al inicializar el SqlSessionFactory: " + ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException("No se pudo cargar la configuración de MyBatis.", ex);
        }
    }

    // Retorna una sesión abierta directamente
    public static SqlSession getSession() {
        if (sqlSessionFactory != null) {
            return sqlSessionFactory.openSession();
        }
        System.err.println("[MyBatisUtil] El SqlSessionFactory es nulo. Revisa tu archivo mybatis-config.xml");
        return null;
    }

    // Retorna la factoría con el tipo de dato correcto para tu VehiculoService
    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
