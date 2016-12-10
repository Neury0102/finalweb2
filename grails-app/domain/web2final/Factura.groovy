package web2final

class Factura {
    Boolean despachada;
    Date fecha;
    String comprobante;
    String direccion
    static hasMany = [productos: Producto]
    static belongsTo = [cliente: Usuario ]
    static constraints = {
    }
    Set<Producto> getProductos(){
        FacturaProducto.findAllByFactura(this).collect {it.producto} as Set
    }

    FacturaProducto addProducto(Producto producto, Integer cantidad){
        FacturaProducto.create(this,producto,cantidad)
    }

    Float total(){
        def items = FacturaProducto.findByFactura(this)
        float total = 0
        for(FacturaProducto fp: items){
            total+= fp.cantidad*fp.producto.precio
        }
        total
    }


    String generarRNC(){
        def result = "A0100100102" +  String.format("%08d", Factura.count());
        this.comprobante = result

    }


}
