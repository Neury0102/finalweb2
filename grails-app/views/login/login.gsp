<!DOCTYPE html>
<html>
<head>
    <link href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" rel="stylesheet">
    <g:set var="entityName" value="${message(code: 'departamento.label', default: 'Departamento')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>

<div class="container" style="width: 20%; margin-top: 10%">
    <div class="panel panel-info">
        <div class="panel-heading">Iniciar Sesi&oacute;n</div>
        <div class="panel-body">
            <g:if test="${flash.message}">
                <div class="alert alert-danger">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    ${flash.message}
                </div>
            </g:if>
            <g:form resource="${this.departamento}" method="DELETE">

                <div>
                    <label for="username">Usuario</label>
                    <input type="text" name="email" id="username" class="form-control" required autofocus>
                </div>
                <div>
                    <label for="password">Password</label>
                    <input type="password" name="password" id="password" class="form-control" required>
                </div>
                <br>
                <g:actionSubmit class="btn btn-info" value="Autenticar" />
            </g:form>
        </div>
    </div>

</div>
</body>
</html>
