package web2final

class Usuario {

    String email
    String password
    String nombre
    String apellido
    TipoUsuario tipo

    static constraints = {
        nombre(blank: false)
        apellido(blank: false)
        email(blank: false, unique: true)
        password(blank: false)
        tipo(blank: false)
    }
}
