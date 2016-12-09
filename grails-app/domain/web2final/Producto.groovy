package web2final

class Producto {
    String nombre
    String descripcion
    Float precio
    Integer existencia
    byte[] imagen

    static constraints = {
        nombre(blank: false)
        descripcion(blank: false)
        precio(min: 0.01f)
        existencia(min: 0)
        imagen(nullable: true, blank: true, maxSize: 1024 * 1024 * 16)
    }

}
