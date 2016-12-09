package web2final

import grails.transaction.Transactional

class LoginController {

    def usuarioService
    def login(){
    }

    def autenticar(){

        String email = params.get("email")
        String password = params.get("password")

        println "fuck"
        if(usuarioService.autenticar(email,password)){
            session.setAttribute("email", Usuario.findByEmail(email))
            redirect(url:"/")
        }
        else{
            redirect(action: "login")
            flash.message = "Credenciales no validas"
        }

    }

    def cerrar_sesion(){
        session.invalidate()
        redirect (action: "login")
    }



}
