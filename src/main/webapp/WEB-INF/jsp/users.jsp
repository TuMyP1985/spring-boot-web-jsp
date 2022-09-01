<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<script src="resources/js/common.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<H3 class="p-5" style="color:#0f6674">Пользователи</H3>
<div class="container">
    <p></p>     <p></p>
    <a class="btn btn-info mr-1" href="${pageContext.request.contextPath}users?action=update&id=0&name=''">Добавить пользователя</a></p>
    <table class="table table-striped" id="datatable">
        <thead>
        <tr>
            <th>ID</th>
            <th>ФИО</th>
            <th>Обновить</th>
            <th>Удалить</th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.users}" var="user">
            <jsp:useBean id="user" type="ru.java.springbootwebjsp.model.User"/>
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td><a href="${pageContext.request.contextPath}/users?action=update&id=${user.id}"><span class="fa fa-pencil"></span></a></td>
                <td><a style="color:#286bec" onclick='delelerow(
                        "${pageContext.request.contextPath}/users?action=delete&id=${user.id}",
                        "${pageContext.request.contextPath}/users"
                        )'><span class="fa fa-remove"></span></a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>