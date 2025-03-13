// filepath: src/main/webapp/WEB-INF/views/layout/header.jsp
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <html>

    <head>
        <title>
            <c:out value="${title}" />
        </title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
    </head>

    <body>
        <header>
            <h1>My Spring Boot Application</h1>
            <nav>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/customer/list">Customers</a></li>
                    <li><a href="${pageContext.request.contextPath}/info">App Info</a></li>
                </ul>
            </nav>
        </header>