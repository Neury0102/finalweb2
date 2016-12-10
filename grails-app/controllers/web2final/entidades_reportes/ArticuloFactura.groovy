package web2final.entidades_reportes

import web2final.Factura
import web2final.FacturaProducto
import web2final.Producto

/**
 * Created by saleta on 12/6/2016.
 */
class ArticuloFactura {

    Integer idArticulo
    String nombreArticulo
    Integer cantidad
    Float costoUnidad
    Float precio

    static ArrayList<ArticuloFactura> getArticulosFactura(Factura factura){
        def listaRet = new ArrayList()
        def listaFP = FacturaProducto.findAllByFactura(factura)
        for(FacturaProducto fp: listaFP){
            ArticuloFactura af = new ArticuloFactura();
            af.idArticulo = fp.producto.id
            af.nombreArticulo = fp.producto.nombre
            af.cantidad = fp.cantidad
            af.costoUnidad = fp.producto.precio
            af.precio = af.costoUnidad*af.cantidad
            listaRet.add(af)
        }
       return listaRet
    }
}
