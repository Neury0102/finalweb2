package web2final


class FacturaProducto implements Serializable {

    Factura factura
    Producto producto
    int cantidad

    static FacturaProducto get(long estudianteId, long grupoAsignaturaId) {
        find("from FacturaProducto  where factura.id = :estudianteId and producto.id = :grupoAsignaturaId", [estudianteId: estudianteId, grupoAsignaturaId: grupoAsignaturaId])
    }

    static FacturaProducto create(Factura estudiante1, Producto grupoAsignatura1, Integer cantidad){
        println estudiante1
        println grupoAsignatura1
        println cantidad
        new FacturaProducto(factura: estudiante1, producto: grupoAsignatura1, cantidad:cantidad).save(flush: true);
    }

    static boolean remove(Factura estudiante1, Producto grupoAsignatura1){
        FacturaProducto instancia = FacturaProducto.findByFacturaAndProducto(estudiante1,grupoAsignatura1);
        if(!instancia){
            return false;
        }

        instancia.delete(flush: true);
        return true;
    }

    static constraints = {
    }

    /**
     * Creando la entidad e indicando que el id será
     * la unificacion de los campos estudiantes y grupoAsignatura
     */
    static mapping = {
        table 'factura_producto'
        id(composite: ['producto', 'factura'])
        version(false) //eliminando el campo de versiones...
    }
}