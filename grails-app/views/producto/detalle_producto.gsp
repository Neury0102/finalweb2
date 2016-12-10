<%@ page import="web2final.TipoUsuario" %>
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
                    <div class="row">
                        <div class="col col-md-2">
                            <g:if test="${session.usuario.tipo in [TipoUsuario.CLIENTE_CONSUMIDOR_FINAL, TipoUsuario.CLIENTE_EMPRESA, TipoUsuario.CLIENTE_PERSONA_FISICA]}">
                                <a href="/producto/catalogo" class="btn btn-primary btn-large">Ver lista</a>
                            </g:if>
                            <g:else>
                                <a href="/producto/index" class="btn btn-primary btn-large">Ver lista</a>
                            </g:else>
                        </div>

                        <g:if test="${session.usuario}">
                            <g:if test="${session.usuario.tipo in [TipoUsuario.CLIENTE_CONSUMIDOR_FINAL, TipoUsuario.CLIENTE_EMPRESA, TipoUsuario.CLIENTE_PERSONA_FISICA]}">
                                <div class="col col-md-6 col-md-offset-1">
                                    <div class="alert alert-warning">
                                        <form action="/carrito/agregar" method="post">
                                            <input type="hidden" name="id_producto" value="${producto.id}" />
                                            <g:field type="number" name="cantidad" min="1" />
                                            &nbsp;
                                            <button type="submit" name="crear" class="btn btn-success btn-large">
                                                Agregar a carrito
                                                <i class="fa fa-cart-plus" aria-hidden="true"></i>
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </g:if>
                        </g:if>
                    </div>
                    <div class="row">
                        <div class="jumbotron">
                            <div class="row">
                                <div class="col col-md-7">
                                    <img class="thumbnail"
                                         src="data:image/jpeg;base64,${producto.imagen?.encodeBase64()}"
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
        </div>
    </body>
</html>
