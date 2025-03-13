// filepath: src/main/webapp/WEB-INF/views/customer-form-content.jsp
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <h2>Customer Form</h2>
    <form:form modelAttribute="customerDTO" method="post">
        <form:hidden path="id" />
        <div>
            <form:label path="name">Name:</form:label>
            <form:input path="name" />
            <form:errors path="name" cssClass="error" />
        </div>
        <div>
            <form:label path="sex">Sex:</form:label>
            <form:input path="sex" />
            <form:errors path="sex" cssClass="error" />
        </div>
        <div>
            <form:label path="birthday">Birthday:</form:label>
            <form:input path="birthday" type="date" />
            <form:errors path="birthday" cssClass="error" />
        </div>
        <div>
            <input type="submit" value="Submit" />
        </div>
    </form:form>