<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8" %>

<html>
<head>
    <title>USUARIO</title>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <%@ include file="../../_css.jsp" %>
    <%@ include file="../../_js.jsp" %>
    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>
<%@ include file="navBarAdm.jsp" %>
<div class="container">
    <div class="row">
        <div class="row">
            <strong><h5><i>Cadastro Usuário</i></h5></strong>
            <div class="divider col s8 m6 l6"></div>
        </div>
        <br>
        <form:form action="cadastro-usuario.html" method="post" commandName="usuarios">
            Nome:<form:input path="nomeUsuario"/><br><br>
            Senha:<form:password path="senhaUsuario"/><br><br>
            Login:<form:input path="loginUsuario"/><br><br>
            <div class="right">
                <button class="btn waves-effect waves-rigth blue darken-1" type="submit" name="form">Salvar
                    <i class="material-icons right">send</i>
                </button>
            </div>
        </form:form>
        <div class="center-align"><p><c:out value="${msg}" escapeXml="true"/><p></div>
    </div>
</div>
</body>
</html>
