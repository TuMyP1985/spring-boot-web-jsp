<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<H3 class="p-5" style="color:#0f6674">Редактирование пользователя</H3>
<div class="container">
    <form method="post" action="users">
        <input type="hidden" name="id" value="${user.id}">
        <dd>ФИО: <input type="text" value="${user.name}" name="name" required></dd>
        <p></p>
        <button class="btn btn-info mr-1" type="submit">Save</button>
        <button class="btn btn-info mr-1" onclick="window.history.back()" type="button">Cancel</button>
    </form>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
