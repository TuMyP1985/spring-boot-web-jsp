<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.springbootwebjsp.ru/functions" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<script src="resources/js/common.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<H3 class="p-5" style="color:#0f6674">Банковские счета</H3>
<div class="container">
    <a class="btn btn-info mr-1" href="${pageContext.request.contextPath}accounts?action=update&id=0&name=''">Добавить банковский счет</a>
    <p></p>
    <form method="post" action="accounts/setusercurrentcurrency" class="form-inline">
        <div class="alineGorizont">
            <table>
                <tbody>
                <td>Пользователь:</td>
                <td>
                    <select onchange="this.form.submit()" name="userId" class="form-control mx-3">
                        <c:forEach items="${requestScope.users}" var="user">
                            <jsp:useBean id="user" type="ru.java.springbootwebjsp.model.User"/>
                            <option value=${user.id} ${user.id == selectedUserId ? 'selected' : ''}>${user.name}</option>
                        </c:forEach>
                    </select>
                </td>
                </tbody>
            </table>
        </div>
        <div class="alineGorizont">
            <table>
                <tbody>
                <td>Валюта:</td>
                <td>
                    <select onchange="this.form.submit()" name="typecurrencyselected" class="form-control mx-3">
                        <c:forEach items="${requestScope.listCurrency}" var="currency">
                            <jsp:useBean id="currency" type="java.lang.String"/>
                            <option value=${currency} ${currency == selectedCurrency ? 'selected' : ''}>${currency}</option>
                        </c:forEach>
                    </select>
                </td>
                </tbody>
            </table>
        </div>

        <div class="alineGorizont">
            <table>
                <td nowrap>
                    <a href="${pageContext.request.contextPath}/reportpdf?user=${user.id}&currency=${currency}">Пакетное формирование выписок.......</a>
                </td>
                <td nowrap>
                    <input type="checkbox" id="outcheck" onchange="onChangeVisible_Accounts()"> (выгрузить в json)
                </td>
                <td nowrap>
                    <button hidden="true" id="output_bn" class="btn btn-outline-secondary" onclick="outJson_Accounts(true)">выгрузить в JSON в папку</button>
                </td>

            </table>
        </div>

    </form>

    <table class="table table-striped" id="datatable">
        <thead>
        <tr>
            <th hidden="true" id="output_th">Выгрузить</th>
            <th>Номер счета</th>
            <th>Дата регистрации</th>
            <th>Описание</th>
            <th>Валюта счета</th>
            <th>Сумма на счете</th>
            <th>Обновить</th>
            <th>Удалить</th>
            <th>Конв.(руб)</th>
            <th>Форм.(pdf)</th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.accounts}" var="account">
            <jsp:useBean id="account" type="ru.java.springbootwebjsp.model.Account"/>
            <tr id="${account.id}">
                <td hidden="true" class="output_td"><input type="checkbox"></td>
                <td>${account.number}</td>
                <td><fmt:formatDate value="${account.registered}" pattern="dd-MMMM-yyyy hh:mm"/></td>
                <td>${account.description}</td>
                <td>${account.typeCurrency}</td>
                <td align="right">${fn:MyFormatDigital(account.value, 2)}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/accounts?action=update&id=${account.id}"><span class="fa fa-pencil"></span></a>
                </td>
                <td><a style="color:#286bec" onclick='delelerow(
                        "${pageContext.request.contextPath}/accounts?action=delete&id=${account.id}",
                        "${pageContext.request.contextPath}/accounts"
                        )'><span class="fa fa-remove"></span></a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/accounts?action=gerRate&id=${account.id}"><span class="fa fa-address-card"></span></a>
                </td>
                <td >
                    <a href="${pageContext.request.contextPath}/reportpdf?text=${account.id}"><span class="fa fa-address-book-o"></span></a>
                </td>
            </tr>
        </c:forEach>
        <p style="color:#0f6674">${rate}</p>
    </table>
</div>


<jsp:include page="fragments/footer.jsp"/>
</body>
</html>