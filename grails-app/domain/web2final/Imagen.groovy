package web2final

class Imagen {
    static belongsTo = [producto: Producto]
    byte[] contenido
    static constraints = {
    }
}
