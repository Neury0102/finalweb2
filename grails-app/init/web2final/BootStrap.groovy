package web2final

class BootStrap {

    def usuarioService

    def init = { servletContext ->
        new Usuario(email:"eg@eg.com",password:"password").save()
        def factura = new Factura(fecha: new Date(),despachada: true,comprobante: "asdfs")
        def producto =  new Producto(nombre: 'sfds', existencia: 40, descripcion: 'asdfasdf', precio: 24.5)
        usuarioService.crear_admin()

        factura.save()
        producto.save()
        println(producto)
        factura.addProducto(producto,4)

        println factura.getProductos()
    }

    def destroy = {
    }
}
