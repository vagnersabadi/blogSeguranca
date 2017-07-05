<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8" %>

<html>
<head>
    <title>POST</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <%@ include file="../../_css.jsp" %>
    <%@ include file="../../_js.jsp" %>

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!--Import jQuery before materialize.js-->
</head>
<body>
<%@ include file="navBarAdm.jsp" %>
<div class="container">
    <div class="row">
        <div class="row">
            <strong><h5><i>Comentar Post</i></h5></strong>
            <div class="divider col s8 m6 l6"></div>
        </div>
        <div class="account-wall">
            <div class="col s12">
                <div class="card blue darken-1 z-depth-5">
                    <div class="card-content white-text">
                        <strong><span class="card-title">${post.tituloPost}</span></strong>
                        <p>${post.textoPost}</p>
                        <p>
                            Data: <strong>${post.dataPost}</strong>
                        </p>
                        <p>
                            Autor: <strong>${post.usuario.nomeUsuario}</strong>
                        </p>
                    </div>

                </div>
            </div>
        </div>
        <strong><h5><i>Comentários</i></h5></strong>
        <div class="account-wall">
            <c:forEach items="${comentariosPost}" var="cp">
                <div class="col s12">
                    <div class="card  blue lighten-3 z-depth-3">
                        <div class="card-content white-text">
                            <p>${cp.textoComentario}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="divider col s12"></div>

        <br>
        <form:form action="cadastro-comentario.html" method="post" commandName="comentarios">
            <!--Titulo:<//form:input path="tituloComentario"/><br><br>-->
            Comentar:
            <form:textarea path="textoComentario" class="materialize-textarea" data-length="300"/></textarea>
            <br>
            <br>
            <div class="right">
                <button class="btn waves-effect waves-rigth blue darken-1"
                        type="submit" value="${post.idPost}" name="idPost">Enviar
                    <i class="material-icons right">send</i>
                </button>
            </div>
        </form:form>
    </div>
</div>

</body>
</html>
