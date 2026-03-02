package es.masanz.ut6.arkanoid.dao;

import es.masanz.ut6.arkanoid.database.ConnectionManager;
import es.masanz.ut6.arkanoid.model.Ladrillo;
import es.masanz.ut6.arkanoid.model.Nivel;

import java.util.List;

public class NivelDao {

    public static Nivel obtenerNivel(int id){
        // TODO 01: Buscar y devolver el nivel en caso de existir - DONE

        String sql = "SELECT id, filas, columnas, ladrillos, siguiente_nivel FROM nivel WHERE id = ?";
        Object[] params = {};
        Object[][] resul = ConnectionManager.ejecutarSelectSQL(sql, params);
        if (resul != null && resul.length > 0){
            Nivel nivel = new Nivel();
            nivel.setId((Integer) resul[0][1]);
            nivel.setFilas((Integer) resul[0][2]);
            nivel.setColumnas((Integer) resul[0][3]);
            nivel.setLadrillos((List<Ladrillo>) resul[0][4]);
            nivel.setSiguienteNivel((Integer) resul[0][5]);

            return nivel;
        }
        return null;
    }


}
