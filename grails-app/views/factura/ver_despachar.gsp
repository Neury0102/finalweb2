<%@ page import="web2final.Factura" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" xmlns="http://www.w3.org/1999/html">
<head>
    <!-- Bootstrap -->
    <link href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" rel="stylesheet">
    <meta name="layout" content="main" />
    <title>Despachar</title>
</head>
<body>
<div class="container" id="contenedorCrearUsuario">

    <div class = "panel panel-default">
        <div class = "panel-body">
            <h1>Pedidos por Despachar</h1>
            <hr>

            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Numero Factura</th>
                    <th>Cliente</th>
                    <th>Direccion</th>
                    <th>Accion</th>
                </tr>
                </thead>
                <g:each var="factura" in="${web2final.Factura.findByDespachada(false)}">
                    <tr>
                        <td>${factura.id}</td>
                        <td>${factura.cliente.nombre} ${factura.cliente.apellido} </td>
                        <td>${factura.direccion}</td>
                        <td><span id="submit-devolver"  class="btn btn-primary"  data-toggle="modal" data-target="#myModal${factura.id}">Despachar</span></td>
                        <div class="modal fade" id="myModal${factura.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <g:form action="despachar">
                                        <div class = "panel panel-default">
                                            <div class = "panel-body">
                                                <label>¿Seguro deseas marcar este pedido como despachado?</label>

                                                <g:hiddenField value="${factura.id}" name="factura" />
                                                <br>
                                                <g:actionSubmit class="btn btn-info" value="Despachar" />
                                                <button type="button" class="btn btn-danger" data-dismiss="modal">Volver</button>

                                            </div>
                                        </div>

                                    </g:form>
                                </div>
                            </div>
                        </div>
                    </tr>
                </g:each>

            </tbody>
            </table>
            <br>
        </div>
    </div>

</div>
<script src="/webjars/jquery/3.1.0/jquery.min.js"></script>
<script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.js"></script>

</body>
</html>