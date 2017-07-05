<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page language="java" contentType="text/html; charset=utf-8"%>

<html>
<head>
    <title>POST</title>
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
            <strong><h5><i>Cadastro Post</i></h5></strong>
            <div class="divider col s8 m6 l6"></div>
        </div>
        <br>
        <form:form action="cadastro-post.html" method="post" commandName="posts">
            <div class="row">
                Titulo:<form:input path="tituloPost" data-length="20"/>
            </div>
            <div  class="row">
                Post:<form:input path="textoPost" class="materialize-textarea" data-length="250"/>
            </div>
                <div class="right">
                <button class="btn waves-effect waves-rigth blue darken-1" type="submit" name="form">Salvar
                    <i class="material-icons right">send</i>
                </button>
            </div>
        </form:form>
        <p>${msg}<p><br>

    </div>
</div>
</body>
</html>
