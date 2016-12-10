package web2final

class BootStrap {

    def usuarioService

    def init = { servletContext ->
        usuarioService.crear_admin()
//        Factura factura = new Factura(fecha: new Date(),despachada: true,comprobante: "asdfs")
//        Producto producto =  new Producto(nombre: 'sfds', existencia: 40, descripcion: 'asdfasdf', precio: 24.5)
//
//        producto.save(failOnError: true)
//        factura.cliente = Usuario.findByEmail("admin@admin.com")
//        factura.save(failOnError: true)
//        factura.addProducto(producto, 4)
//        println factura.getProductos()
    }

    def destroy = {
    }
}
