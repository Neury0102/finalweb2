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
                    <h1>${accion}</h1>
                    <br>
                    <g:if test="${flash.error}">
                        <div class="alert alert-danger" role="status">${flash.error}</div>
                        <br>
                    </g:if>
                    <g:form action="processForm" method="post" enctype="multipart/form-data">
                        <g:field type="hidden" name="id" value="${producto?.id}" />
                        <div class="form-group">
                            <label for="nombre">Nombre</label>
                            <g:field id="nombre" type="text" name="nombre" value="${producto?.nombre}" />
                        </div>
                        <div class="form-group">
                            <label for="desc">Descripcion</label>
                            <g:field id="desc" type="text" name="descripcion" value="${producto?.descripcion}" />
                        </div>
                        <div class="form-group">
                            <label for="desc">Precio</label>
                            <g:field type="text" name="precio" value="${producto?.precio}" />
                        </div>
                        <div class="form-group">
                            <label for="desc">Existencia</label>
                            <g:field type="number" name="existencia" value="${producto?.existencia}" />
                        </div>
                        <div class="form-group">
                            <label for="foto">Imagen</label>
                            <g:if test="${producto?.imagen}">
                                <img class="thumbnail"
                                     src="data:image/jpeg;base64,${producto.imagen.encodeBase64()}"
                                     alt="No hay imagen" style="height: 200px" />
                            </g:if>

                            <g:field id="foto" type="file" name="imagen" />
                        </div>

                        <g:submitButton name="Crear" class="btn btn-info" />
                    </g:form>
                </div>
            </div>
        </div>
    </body>
</html>
