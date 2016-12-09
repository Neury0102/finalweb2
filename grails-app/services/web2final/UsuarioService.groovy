package web2final

import grails.transaction.Transactional

@Transactional
class UsuarioService {

    def crear_admin(){
        if(Usuario.count() <= 0){
            Usuario admin = new Usuario()
            admin.email = "admin@admin.com"
            admin.nombre = "Administrador"
            admin.apellido = "Administrador"
            admin.password = "admin"
            admin.tipo = TipoUsuario.ADMIN

            admin.save(flush: true)

            print admin.errors
            println "usuario creado"
        }
    }

    def autenticar( email, password){
        Usuario u = Usuario.findByEmail(email)
        u != null && u.password == password

    }

    def serviceMethod() {
    }
}
