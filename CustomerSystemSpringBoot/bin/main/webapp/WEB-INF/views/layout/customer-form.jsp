// filepath: src/main/webapp/WEB-INF/views/customer-form.jsp
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <c:set var="title" value="Customer Form" />
            <c:set var="body" value="/WEB-INF/views/customer-form-content.jsp" />
            <%@ include file="/WEB-INF/views/layout/main.jsp" %>