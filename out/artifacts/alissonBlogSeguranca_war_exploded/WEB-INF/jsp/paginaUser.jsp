<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page language="java" contentType="text/html; charset=utf-8"%>

<html>
<head>
    <title>PAGINA</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <%@ include file="../../_css.jsp" %>
    <%@ include file="../../_js.jsp" %>
    <!--Let browser know website is optimized for mobile-->
</head>
<body>
<%@ include file="navBarUser.jsp" %>
<div class="container">
    <div class="row">
        <div class="account-wall">
            <div class="center-align"><p>${msg}</p></div>
            <blockquote class="z-depth-5" style="border-left-color: #2196f3">
                <br>
                <h5>TRABALHO – Mapeamento Objeto-Relacional e Segurança</h5>
                        <p>
                            Desenvolva uma aplicação Web em Java utilizando-se de todos os padrões de
                            projeto já discutidos em aula como DAO, MVC, Singletons, etc. A aplicação será do tipo
                            “blog”, e em linhas gerais, permite aos usuários registrados colocarem posts, bem
                            como a qualquer usuário comentar os posts. O trabalho será avaliado conforme o
                            cumprimento dos seguintes requisitos:
                        </p>
                        <p>
                            -> Permitir o cadastro de posts aos usuários devidamente cadastrados e
                            identificados;
                        </p>
                        <p>
                            -> Posts somente podem ser removidos ou alterados pelo usuário administrador
                            do blog ou pelo usuário que o cadastrou;
                        </p>
                        <p>-> Somente o usuário administrador poderá cadastrar outros usuários;
                        </p>
                        <p>-> A tela inicial do blog mostra apenas o título, a data de cadastro e os 200
                            primeiros caracteres de cada um dos 10 últimos posts;
                        </p>
                        <p>-> Na mesma tela inicial o usuário poderá acessar o link “posts antigos”, de onde a
                            tela passará a exibir todos os posts e não apenas os 10 últimos;
                        </p>
                        <p>->Ao clicar sobre o post o usuário poderá visualizar o post completo bem como os
                            comentários dos usuários, além de poder postar o seu comentário;
                        </p>
                        ->Deverá ser utilizada a ferramenta de mapeamento objeto-relacional para
                        persistência dos dados;
                        </p>
                        <p>->O site deverá ser imune às principais vulnerabilidades estudadas na Unidade 3
                            da disciplina;
                        </p>
            </blockquote>
        </div>
    </div>
</div>
</body>
</html>
