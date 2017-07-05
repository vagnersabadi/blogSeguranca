<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"%>

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
            <strong><h5><i>Seus Usuários</i></h5></strong>
            <div class="divider col s8 m6 l6"></div>
        </div>
        <div class="center-align"><p>${msg}<p><br><br></div>
        <table class="striped">
            <thead>
            <tr>
                <th>Name</th>
                <th>Ativado</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${usuariosCadastrados}" var="u">
            <tr>
                <td><c:out value=" ${u.nomeUsuario}"/></td>
                <c:if test = "${u.ativo == true}"><td>Ativo</td></c:if>
                <c:if test = "${u.ativo == false}"><td>Desativado</td></c:if>
                    <td>
                        <!--<input type="button" onclick="document.location='ativaUsuario.html?id=${u.idUsuario}'"
                               value="Ativa/Desativa">-->
                            <button  class='col s12 btn waves-effect blue' type="submit"
                                     onclick="document.location='ativaUsuario.html?id=${u.idUsuario}'">Ativa||Desativa
                            </button>
                    </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
