<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<script src="resources/js/common.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<H3 class="p-5" style="color:#0f6674">Выписки (движения)</H3>
<div class="container">
    <p></p>     <p></p>
    <a class="btn btn-info mr-1" href="${pageContext.request.contextPath}bankstatements?action=update&id=0&name=''">Загрузить выписку из файла</a></p>
    <table class="table table-striped" id="datatableforsort">
        <thead>
        <tr>
            <th onclick="getSort()">ID</th>
            <th>Дата регистрации</th>
            <th>Описание</th>
            <th>Валюта выписки</th>
            <th>Сумма</th>
            <th>Удалить</th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.bankstatements}" var="bankstatement">
            <jsp:useBean id="bankstatement" type="ru.java.springbootwebjsp.model.BankStatements"/>
            <tr iddel="${bankstatement.id}">
                <td>${bankstatement.id}</td>
                <td><fmt:formatDate value="${bankstatement.registered}" pattern="dd-MMMM-yyyy hh:mm"/></td>
                <td>${bankstatement.description}</td>
                <td>${bankstatement.typeCurrency}</td>
                <td>${bankstatement.value}</td>
                <td><a style="color:#286bec" onclick='delelerow(
                        "${pageContext.request.contextPath}/bankstatements?action=delete&id=${bankstatement.id}",
                        "${pageContext.request.contextPath}/bankstatements"
                        )'><span class="fa fa-remove"></span></a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>