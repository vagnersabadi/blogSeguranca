<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page language="java" contentType="text/html; charset=utf-8"%>

<html>
<head>
    <title>Login Usuário</title>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <%@ include file="../../_css.jsp" %>
    <%@ include file="../../_js.jsp" %>

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!--Import jQuery before materialize.js-->
    <script>
        $(document).ready(function () {
            $('.carousel').carousel();
        });
    </script>
</head>
<body>

<div class="navbar-fixed">

    <nav>
        <div class="nav-wrapper z-depth-4 blue">
            <a href="paginaAdm.html" class="brand-logo center">
                <strong><i>Blog</i></strong>
            </a>
        </div>
    </nav>
</div>


<div class="container ">
    <div class="row">
        <center>
        <div class="section"></div>
            <div class="z-depth-3 grey lighten-4 row" style="width:300px;  border: 10px solid #EEE;">
                <form class="col s12 " method="post" action="login.html">
                    <div class='row'>
                        <div class='col s12'>
                        </div>
                    </div>
                    <div class='row'>
                        <div class='input-field col s12'>
                            <input class='validate' type="text" name="login" id="login" required autofocus/>
                            <label for="login">Nome</label>
                        </div>
                    </div>
                    <div class='row'>
                        <div class='input-field col s12'>
                            <input class='validate' type='password' name='senha' id='senha' required autofocus/>
                            <label for='senha'>Senha</label>
                        </div>
                    </div>
                    <br/>
                        <div class='row'>
                            <button type='submit' class='col s12 btn btn-large waves-effect blue' name="form">Entrar
                            </button>
                        </div>
                </form>
            </div>
            <p>${msg}</p>
        </center>
    </div>
    <div class="row center">
        <strong><h5><i>Últimos 10 posts</i></h5></strong>
    </div>
    <div class="row">
        <div class="carousel">
        <c:forEach items="${posts}" var="post">
                <div class=" carousel-item col s12 m8 l6 blue ">
                    <h5 class="header"><c:out value="${post.tituloPost}" escapeXml="true"/></h5>
                    <div class="card horizontal blue lighten-3">
                        <div class="card-stacked ">
                            <div class="card-content ">
                                <p><c:out value="${fn:substring(post.textoPost, 0,199)}" escapeXml="true"/> ...</p>
                                <p>Data: <c:out value="${post.dataPost}" escapeXml="true"/></p>
                                <p>Autor: <c:out value="${post.usuario.nomeUsuario}" escapeXml="true"/></p>
                            </div>
                            <div class="card-action">
                                <!--<a href="verPost.html?id=${post.idPost}">Ver Mais</a>-->
                                <form action="verPost.html" method="POST">
                                    <button type="submit" value="${post.idPost}" name="id" class="btn btn-default">
                                        Ver mais
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
            </div>
        </c:forEach>
    </div>
    </div>
    <div class="row">
    <div class="fixed-action-btn horizontal click-to-toggle">
        <a class="btn-floating btn-large blue tooltipped" href="todosPosts.html" data-position="left" data-delay="50" data-tooltip="Ver posts antigos"> <i class="material-icons">menu</i></a>
    </div>
    </div>
    </div>
</body>
</html>
