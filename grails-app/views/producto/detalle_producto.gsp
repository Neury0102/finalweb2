<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>Crear Producto</title>
    </head>
    <body>
        <div class="container" role="main">
            <div class="row">
                <div class="col col-md-10 col-md-offset-1">
                    <br>
                    <a href="/producto/index" class="btn btn-primary btn-large">Ver lista</a>
                    <br>
                    <br>
                    <div class="jumbotron">
                        <div class="row">
                            <div class="col col-md-7">
                                <img class="thumbnail"
                                     src="data:image/jpeg;base64,${producto.imagen.encodeBase64()}"
                                     style="width: 100%"
                                />
                            </div>
                            <div class="col col-md-5">
                                <h1>${producto.nombre}</h1>
                                <p><b>${producto.descripcion}</b></p>
                                <p>RD$ <span class="badge">${producto.precio}</span></p>
                                <p>Quedan: <span class="badge">${producto.existencia}</span></p>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
