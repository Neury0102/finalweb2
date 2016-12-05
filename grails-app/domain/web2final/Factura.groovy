package web2final

class Factura {
    Boolean despachada;
    Date fecha;
    String comprobante;
    static hasMany = [productos: Producto]
    static constraints = {
    }
    Set<Producto> getProductos(){
        FacturaProducto.findAllByFactura(this).collect {it.producto} as Set
    }

    FacturaProducto addProducto(Producto producto, Integer cantidad){
        FacturaProducto.create(this,producto,cantidad)
    }


}
