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

        session.carrito[id_producto] = ["producto": p, "cantidad": cantidad]

        flash.message = "Producto ${p.nombre} agregado al carrito!"
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

    def procesar(String direccion) {
        println direccion

        redirect(action: 'ver')
    }
}
