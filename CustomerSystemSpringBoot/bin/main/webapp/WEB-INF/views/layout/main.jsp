// filepath: src/main/webapp/WEB-INF/views/layout/main.jsp
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ include file="/WEB-INF/views/layout/header.jsp" %>
            <div class="content">
                <jsp:include page="${body}" />
            </div>
            <%@ include file="/WEB-INF/views/layout/footer.jsp" %>