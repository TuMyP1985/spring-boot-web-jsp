<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<jsp:useBean id="selectedUser" type="ru.java.springbootwebjsp.model.User" scope="request"/>
<jsp:useBean id="selectedCurrency" type="java.lang.String" scope="request"/>
<H3 class="p-5" style="color:#0f6674">Редактирование счета (${selectedUser.name})</H3>

    <div class="container">
        <form method="post" action="accounts">
            <input type="hidden" name="idaccount" value="${account.id}">
            <table style=${account.id==null||account.id==0?'':'visibility:hidden'}>
                <tbody>
                <td>Пользователь:</td>
                <td><select name="selectedUserId" class="form-control mx-3">
                    <c:forEach items="${requestScope.users}" var="user">
                        <jsp:useBean id="user" type="ru.java.springbootwebjsp.model.User"/>
                        <option value=${user.id} ${user.id == selectedUser.id ? 'selected' : ''}>${user.name}</option>
                    </c:forEach>
                </select></td>
                </tbody>
            </table>


            <table>
                <tbody>
                <td>Выберите вал.:</td>
                <td><select name="typeCurrency" class="form-control mx-3">
                    <c:forEach items="${requestScope.listCurrency}" var="currency">
                        <jsp:useBean id="currency" type="java.lang.String"/>
                        <option value=${currency} ${selectedCurrency.equals(currency) ? 'selected' : ''}>${currency}</option>
                    </c:forEach>
                </select></td>
                </tbody>
            </table>
            <p></p>

            <table>
                <tbody>
                <tr>
                    <td>Номер счета:</td>
                    <td><input type="number" value="${account.number}" name="number" required></td>
                </tr>
                <tr>
                    <td>Описание:</td>
                    <td><input type="text" value="${account.description}" name="description" required></td>
                </tr>
                <tr>
                    <td>Сумма на счете:</td>
                    <td><input type="number" step=".01" value="${account.value}" name="value" required></td>
                </tr>
                </tbody>
            </table>
            <p></p>
            <button class="btn btn-info mr-1" type="submit">Save</button>
            <button class="btn btn-info mr-1" onclick="window.history.back()" type="button">Cancel</button>
        </form>
    </div>



<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
