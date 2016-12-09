package web2final

import grails.transaction.Transactional

class LoginController {

    def usuarioService

    def login(){

    }

    def autenticar(){

        String email = params.get("email")
        String password = params.get("password")

        if(usuarioService.autenticar(email, password)) {
            session.setAttribute("usuario", Usuario.findByEmail(email))
            session.setAttribute("carrito", [:])

            redirect(url:"/")
        }
        else {
            flash.message = "Credenciales no validas"
            redirect(action: "login")
        }

    }

    def cerrar_sesion() {
        session.invalidate()
        redirect (action: "login")
    }
}
