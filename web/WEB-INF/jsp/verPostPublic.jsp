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
<nav>
    <div class="nav-wrapper z-depth-4 blue">
        <ul id="nav-mobile " class="left hide-on-med-and-down">
            <li><a href="login.html">Voltar ao Login</a></li>
        </ul>
    </div>
</nav>
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
                        <strong><span class="card-title"><c:out value="${post.tituloPost}" escapeXml="true"/></span></strong>
                        <p><c:out value="${post.textoPost}" escapeXml="true"/></p>
                        <p><strong>Data: <c:out value="${post.dataPost}" escapeXml="true"/></strong></p>
                        <p><strong>Autor: <c:out value="${post.usuario.nomeUsuario}" escapeXml="true"/></strong></p>
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
    </div>
</div>

</body>
</html>
