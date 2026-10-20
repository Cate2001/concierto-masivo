package com.cate.SGCM.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Almacén CRUD en memoria, parametrizado por entidad e identificador.
 *
 * Un único repositorio genérico sustituye a un repositorio por entidad. Guarda los registros en un
 * {@code Map} indexado por su identificador, de modo que buscar y eliminar son O(1) promedio y no
 * un recorrido, y listar respeta el orden de las claves del mapa. Al ser una interfaz de
 * almacenamiento aislada, sustituir el {@code HashMap} por JDBC o JPA no obliga a tocar
 * {@code services}.
 *
 * @param <T>  tipo de la entidad almacenada
 * @param <ID> tipo del identificador que la entidad usa como clave
 */
public class RepositorioGenerico <T, ID>{
    private final Map<ID, T> almacen = new HashMap<>();

    /**
     * Guarda o reemplaza el registro asociado a un id.
     *
     * @param id     identificador del registro
     * @param entidad entidad a guardar
     * @return el registro que estaba asociado al id, o {@code null} si no había ninguno
     */
    public T guardar(ID id, T entidad) {
        return almacen.put(id, entidad);
    }

    /**
     * Recupera el registro asociado a un id.
     *
     * @param id identificador del registro
     * @return el registro, o {@code null} si no existe
     */
    public T buscarRegistro(ID id) {
        return almacen.get(id);
    }

    /**
     * Devuelve todos los registros almacenados.
     *
     * @return copia de los registros; modificarla no afecta al repositorio
     */
    public List<T> listarRegistros() {
        return new ArrayList<>(almacen.values());
    }

    /**
     * Elimina el registro asociado a un id.
     *
     * @param id identificador del registro
     * @return el registro eliminado, o {@code null} si no existía
     */
    public T eliminarRegistro(ID id) {
        return almacen.remove(id);
    }

}
