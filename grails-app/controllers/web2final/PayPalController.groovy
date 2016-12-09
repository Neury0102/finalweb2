package web2final

import com.paypal.api.payments.Links
import com.paypal.base.Constants
import groovy.json.JsonSlurper

class PayPalController {
    def paypalService
    def index() {

        def factura = new Factura(fecha: new Date(),despachada: true,comprobante: "asdfs", cliente: Usuario.findById(1), direccion: "asfdasfd")
        def producto =  new Producto(nombre: 'sfds', existencia: 40, descripcion: 'asdfasdf', precio: 24.5, imagen: "sdfs".bytes)

        factura.save(failOnError: true)
        producto.save(failOnError: true)
        factura.addProducto(producto,2)
        println factura
    }

    def iniciar_pago() {

        String clientId = "AWxsZATqfKoYSB0HhBfbr4-_UAtEe6iNRAqolK8nEz5jqEy4TrT-yuqwdvf0Osu2NE_Mq7-1cuyLa2QK"
        String clientSecret = "ENhbn3HcISNCvAIzlVPWklfSWMvvGXLmGiTMPJj9aDM6065u4WZVNqk92BgqXe9r04KKB0Np9Gdq6OXe"
        String endpoint = "https://api.sandbox.paypal.com"
        Map sdkConfig = [(Constants.CLIENT_ID)    : clientId,
                         (Constants.CLIENT_SECRET): clientSecret,
                         (Constants.ENDPOINT)     : endpoint]
        def accessToken = paypalService.getAccessToken(clientId, clientSecret, sdkConfig)
        def apiContext = paypalService.getAPIContext(accessToken, sdkConfig)

        def details = paypalService.createDetails(subtotal: "12.50")
        def amount = paypalService.createAmount(currency: "USD", total: params.total, details: details)

        def transaction = paypalService.createTransaction(amount: amount, description: "your description", details: details)
        def transactions = [transaction]

        def payer = paypalService.createPayer(paymentMethod: 'paypal')
        def cancelUrl = "http://localhost:8080/carrito/ver"
        def returnUrl = "http://localhost:8080/payPal/realizar_pago"

        def redirectUrls = paypalService.createRedirectUrls(cancelUrl: cancelUrl, returnUrl: returnUrl)

        def payment
        try {
            // create the paypal payment
            payment = paypalService.createPayment(
                    payer: payer, intent: 'sale',
                    transactionList: transactions,
                    redirectUrls: redirectUrls,
                    apiContext: apiContext)
        }
        catch (e) {
            flash.message = "Could not complete the transaction because: ${e.message ?: ''}"
            redirect controller: 'bill', action: "show", id: params.refId
            return
        }

        def approvalUrl = ""
        def retUrl = ""
        // retrieve links from returned paypal object
        for (Links links in payment?.links) {
            if (links?.rel == 'approval_url') {
                approvalUrl = links.href
            }
            if (links?.rel == 'return_url') {
                retUrl = links.href
            }
        }

        redirect url: approvalUrl ?: '/', method: 'POST'
    }

    def realizar_pago() {

        if(params.paymentId && params.payerId) {

            String clientId = "AWxsZATqfKoYSB0HhBfbr4-_UAtEe6iNRAqolK8nEz5jqEy4TrT-yuqwdvf0Osu2NE_Mq7-1cuyLa2QK"
            String clientSecret = "ENhbn3HcISNCvAIzlVPWklfSWMvvGXLmGiTMPJj9aDM6065u4WZVNqk92BgqXe9r04KKB0Np9Gdq6OXe"
            String endpoint = "https://api.sandbox.paypal.com"
            Map sdkConfig = [:] //= grailsApplication.config.paypal.sdkConfig//[mode: 'live']
            //sdkConfig['grant-type'] = "client_credentials"
            sdkConfig[Constants.CLIENT_ID] = clientId
            sdkConfig[Constants.CLIENT_SECRET] = clientSecret
            sdkConfig[Constants.ENDPOINT] = endpoint
            def accessToken = paypalService.getAccessToken(clientId, clientSecret, sdkConfig)
            def apiContext = paypalService.getAPIContext(accessToken, sdkConfig)
            //the paypal website will add params to the call to your app. Eg. PayerId, PaymentId
            // you will use the params to 'execute' the payment
            def paypalPayment = paypalService.createPaymentExecution(paymentId: params.paymentId, payerId: params.PayerID, apiContext)

            def map = new JsonSlurper().parseText(paypalPayment.toString())
            println map

            redirect url: "http://localhost:8080"
        }
        else {
            redirect url: "http://localhost:8080"
        }
    }
}
