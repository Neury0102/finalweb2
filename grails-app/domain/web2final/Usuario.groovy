package web2final

class Usuario {

    String email
    String password
    String nombre
    String apellido
    String tipo
    String departamento
    String tipo_fiscal

    String toString()
    { "$email" }

    static constraints = {
        email(email:true)
        password(blank:false, password:true)
    }
}
