package es.masanz.ut6.arkanoid.service;

import es.masanz.ut6.arkanoid.dao.NivelDao;
import es.masanz.ut6.arkanoid.model.Nivel;

public class NivelService {

    public static Nivel obtenerNivel(int id) {
        // TODO 02: Buscar y devolver el nivel en caso de existir - DONE (CREO)
        if (NivelDao.obtenerNivel(id) != null) return NivelDao.obtenerNivel(id);
        return null;
    }
}
