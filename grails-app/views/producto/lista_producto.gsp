<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>Crear Producto</title>
    </head>
    <body>
        <div class="container" role="main">
            <div class="row">
                <div class="col col-md-12">
                    <h1>Productos</h1>
                    <br>
                    <g:if test="${flash.message}">
                        <div class="alert alert-info" role="status">${flash.message}</div>
                        <br>
                    </g:if>
                    <g:if test="${flash.error}">
                        <div class="alert alert-danger" role="status">${flash.error}</div>
                        <br>
                    </g:if>
                    <div class="row">
                        <g:each in="${productos}" var="prod">
                            <div class="col-sm-6 col-md-4">
                                <div class="thumbnail">
                                    <img class="producto_thumbnail_img"
                                         src="data:image/jpeg;base64,${prod.imagen.encodeBase64()}"
                                         style="height: 100px"
                                    />
                                    <div class="caption">
                                        <h3>${prod.nombre}</h3>
                                        <p>RD$ ${prod.precio}</p>
                                        <p>
                                            <a href="/producto/detail/${prod.id}" class="btn btn-success" style="text-decoration: none">
                                                <i class="fa fa-eye"></i>
                                            </a>
                                            <g:if test="${accion == "crud"}">
                                                <a href="/producto/edit/${prod.id}" class="btn btn-primary" style="text-decoration: none">
                                                    <i class="fa fa-pencil-square-o"></i>
                                                </a>
                                                <a href="/producto/remove/${prod.id}" onclick="return confirm('Seguro?')" class="btn btn-danger" style="text-decoration: none">
                                                    <i class="fa fa-trash"></i>
                                                </a>
                                            </g:if>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </g:each>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
