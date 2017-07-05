<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page language="java" contentType="text/html; charset=utf-8"%>

<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >

<script>
    $(".dropdown-button").dropdown();
</script>

<!-- Dropdown Structure -->
<ul id="dropdownPost" class="dropdown-content">
    <li><a href="criarPosts.html">Criar Posts</a></li>
    <li class="divider"></li>
    <li><a href="meusPosts.html">Meus Posts</a></li>
    <li class="divider"></li>
    <li><a href="todosPosts.html">Todos Posts</a></li>

</ul>
<!-- Dropdown Structure -->
<ul id="dropdownUser" class="dropdown-content">
    <li><a href="GerenciaUsuariosCriar.html">Criar User</a></li>
    <li class="divider"></li>
    <li><a href="GerenciaUsuariosVer.html">Ver Users</a></li>

</ul>
<!-- Dropdown Structure -->
<ul id="dropdownSair" class="dropdown-content">
    <li><a href="logout.html">Sair</a></li>

</ul>
<div class="navbar-fixed">

<nav>
    <div class="nav-wrapper z-depth-4 blue">
        <a href="paginaAdm.html" class="brand-logo center">
            <strong><i>Blog</i></strong>
        </a>
        <ul id="nav-mobile " class="right hide-on-med-and-down">
            <li><a class="dropdown-button" href="#!" data-activates="dropdownUser">User<i class="material-icons right">arrow_drop_down</i></a></li>
            <li><a class="dropdown-button" href="#!" data-activates="dropdownPost">Posts<i class="material-icons right">arrow_drop_down</i></a></li>
            <li><a class="dropdown-button" href="#!" data-activates="dropdownSair">: <c:out value="${usuario.nomeUsuario}" escapeXml="true" /> :<i class="material-icons right">arrow_drop_down</i></a></li>
        </ul>
    </div>
</nav>
</div>
