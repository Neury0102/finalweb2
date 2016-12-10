package web2final

import grails.converters.JSON
import org.springframework.web.multipart.MultipartFile

class CarritoController {

    def ver() {
        def model = [:]

        if(!session.carrito) {
            session.carrito = [:]
        }

        Map carrito = session.carrito
        List productos = []

        carrito.each { k, v ->
            productos << carrito[k]
        }

        model["productos"] = productos

        render(view: 'ver_carrito', model: model)
    }

    def agregar(Integer id_producto, Integer cantidad) {

        Producto p = Producto.findById(id_producto)

        if(!session.carrito) {
            session.carrito = [:]
        }

        if(p.existencia >= cantidad) {
            session.carrito[id_producto] = ["producto": p, "cantidad": cantidad]

            flash.message = "Producto ${p.nombre} agregado al carrito!"
        }
        else {
            flash.error = "Producto ${p.nombre} No tiene suficiente existencia!"
        }
        redirect(controller: 'producto', action: 'catalogo')
    }

    def quitar(Integer id_producto) {

        if(!session.carrito) {
            session.carrito = [:]
        }

        flash.message = "Producto removido del carrito"
        session.carrito.remove(id_producto)

        redirect(action: 'ver')
    }

    def procesar() {
        Float montoTotal = 0.0

        if(!session.carrito) {
            session.carrito = [:]
        }

        session.direccion = params.direccion

        // calcular monto total
        for(item in session.carrito) {
            Producto producto = (Producto)item.value["producto"]
            Integer cantidad  = (Integer)item.value["cantidad"]

            montoTotal += producto.precio * cantidad
        }

        forward(controller: 'payPal', action: 'iniciar_pago', params: ['total': montoTotal])
    }

    def recibo_compra() {
        if(params.correcto) {
            // crear facturas
            Map carrito = (Map)session.carrito

            Factura f = new Factura()
            f.cliente = (Usuario)session.usuario
            f.comprobante = "x"
            f.despachada = false
            f.fecha = new Date()
            f.direccion = session.direccion
            f.save(flush: true)

            for(item in carrito) {
                Producto p = (Producto)item.value["producto"]
                Integer c  = (Integer)item.value["cantidad"]

                f.addProducto(p, c)
            }
            session.removeAttribute("direccion")
            session.removeAttribute("carrito")

            // mandar a buscar reportes
            render(view: 'recibo', model: ["factura": f, "productos": f.productos])
            // forward(controller: 'producto', action: 'catalogo', params: ['correcto':true])
        }
        else {
            redirect(url: "/")
        }
    }
}
