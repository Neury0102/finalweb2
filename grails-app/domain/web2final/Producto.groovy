package web2final

class Producto {
    String nombre
    String descripcion
    Float precio
    Integer existencia
    static hasMany = [imagenes: Imagen, productos: Producto]
    static belongsTo = Factura
    static constraints = {
    }

}
