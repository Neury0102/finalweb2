package web2final

class FacturaController {

    def index() { }


    def ver_despachar(){

    }

    def despachar(){

        def factura = Factura.findById(params.factura)
        factura.despachada = true;
        factura.save()

        redirect action: "ver_despachar"
    }
}
