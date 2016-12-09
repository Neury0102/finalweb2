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

        flash.message = "Producto <b>removido</b> del carrito"
        session.carrito.remove(id_producto)

        redirect(action: 'ver')
    }

    def procesar() {
        Float montoTotal = 0.0

        if(!session.carrito) {
            session.carrito = [:]
        }

        // calcular monto total
        for(item in session.carrito) {
            Producto producto = (Producto)item.value["producto"]
            Integer cantidad  = (Integer)item.value["cantidad"]

            montoTotal += producto.precio * cantidad
        }

        forward(controller: 'payPal', action: 'iniciar_pago', params: ['total': montoTotal])
    }
}
