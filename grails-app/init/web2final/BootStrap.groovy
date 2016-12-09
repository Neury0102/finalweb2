package web2final

class BootStrap {

    def usuarioService

    def init = { servletContext ->
        Factura factura = new Factura(fecha: new Date(),despachada: true,comprobante: "asdfs")
        Producto producto =  new Producto(nombre: 'sfds', existencia: 40, descripcion: 'asdfasdf', precio: 24.5)

        usuarioService.crear_admin()

        producto.save()
//        factura.cliente = Usuario.findByEmail("admin@admin.com")
        factura.save()
        println(producto)
//        factura.addProducto(producto, 4)
//        println factura.getProductos()
    }

    def destroy = {
    }
}
