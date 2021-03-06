<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page language="java" contentType="text/html; charset=utf-8"%>

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
<%@ include file="navBarUser.jsp" %>
<div class="container">
	<div class="row">
		<div class="row">
			<strong><h5><i>Todos os seus Posts</i></h5></strong>
			<div class="divider col s8 m6 l6"></div>
		</div>
		<div class="container">
			<div class="row">
				<div class="account-wall">
					<c:forEach items="${postsUsuario}" var="p">
						<div class="col s12">
							<div class="card blue darken-1 z-depth-5">
								<div class="card-content white-text">
									<strong><span class="card-title">${p.tituloPost}</span></strong>
									<p>${p.textoPost}</p>
									Data: <strong>${p.dataPost}</strong>
									</p>
								</div>
								<div class="card-action white-text">
									<div class="row">
										<div class="right">
											<form action="verPost.html" method="POST">
												<button type="submit" value="${p.idPost}" name="id"
														class="btn waves-effect waves-light green">
													Ver mais
												</button>
											</form>
										</div>
										<div class="right">
											<form action="alterarPost.html" method="POST">
												<button type="submit" value="${p.idPost}" name="id"
														class="btn waves-effect waves-light yellow">
													Alterar
												</button>
											</form>
										</div>
										<div class="right">
											<form action="deletaPost.html" method="POST">
												<button type="submit" value="${p.idPost}" name="id"
														class="btn waves-effect waves-light red">
													Excluir
												</button>
											</form>
										</div>
									</div>
									<!--<a href="verPost.html?id=${p.idPost}">Comentar</a>
									<a href='alterarPost.html?id=${p.idPost}'> Alterar</a>
									<a href='deletaPost.html?id=${p.idPost}' > Excluir</a>-->
								</div>
							</div>
						</div>
					</c:forEach>

				</div>
			</div>
		</div>
	</div>
</body>
</html>
