package web2final

import grails.converters.JSON
import org.springframework.web.multipart.MultipartFile

class ProductoController {

    def index() {
        def model = [:]

        model["productos"] = Producto.findAll()
        model["accion"] = "crud"

        render(view: 'lista_producto', model: model)
    }

    def catalogo() {
        def model = [:]

        model["productos"] = Producto.findAll()
        model["accion"] = "catalogo"

        render(view: 'lista_producto', model: model)
    }

    def detail(Integer id) {
        def model = [:]

        model["producto"] = Producto.findById(id)
        model["accion"] = "Detalles de Producto"

        render(view: 'detalle_producto', model: model)
    }

    def remove(Integer id) {

        Producto p = Producto.findById(id)
        String prod_name = p.nombre

        p.delete()

        flash.message = "${prod_name} fue eliminado con exito"
        redirect(action: 'index')
    }

    def create() {
        def model = [:]

        model["accion"] = "Creando Nuevo Producto"

        render(view: 'form_producto', model: model)
    }

    def edit(Integer id) {
        def model = [:]

        Producto p = Producto.findById(id)

        model["producto"] = p

        render(view: 'form_producto', model: model)
    }

    def processForm(Integer id) {

        MultipartFile imagen = request.getFile('imagen')
        Producto target = Producto.findById(id)

        if(target) {
            if(!imagen.empty) {
                target.imagen = imagen.bytes
            }

            target.nombre = params.nombre
            target.descripcion = params.descripcion
            target.precio = params.precio as Float
            target.existencia = params.existencia as Integer
        }
        else {
            target = new Producto(params)
            target.imagen = imagen.bytes
        }

        target.validate()
        if(!target.hasErrors()) {
            target.save(flush: true)

            // declarar mensajes de exito
            flash.mensaje = "Operacion realizada con exito!"
        }
        else {
            // declarar mensajes de error
            flash.error = "Ocurrio un error al manipular producto..."
        }

        redirect(action: 'index')
    }

    def addToCart(Integer id_target, Integer cantidad) {

        def res = new Expando()

        res.target = id_target
        res.cantidad = cantidad

        print res

        redirect(action: 'catalogo')
    }
}
