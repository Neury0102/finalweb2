<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>Carrito de Compra</title>
    </head>
    <body>
        <div class="container" role="main">
            <div class="row">
                <div class="col col-md-12">
                    <br>
                    <a href="/producto/catalogo" target="" class="btn btn-primary btn-large">
                        Seguir comprando
                    </a>
                    <br>
                    <div class="jumbotron">
                        <p>
                            <a href="/reporting/factura_reporte?factura=${factura.id}" target="_blank" class="btn btn-primary btn-large">
                                <i class="fa fa-file-pdf-o"></i>
                            </a>
                        </p>
                        <h1>Reporte de factura: ${factura.comprobante}</h1>
                        <p><b>${factura.fecha}</b></p>
                        <p>Direccion: ${factura.direccion}</p>
                        <p>A nombre de: ${factura.cliente.nombre}</p>
                        <p>Monto: RD$ <span class="badge">${factura.total()}</span></p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
