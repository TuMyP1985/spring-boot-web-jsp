<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<H3 class="p-5" style="color:#0f6674">Добавление выписки</H3>

    <div class="container">

        <form id="target" method="post" action="${pageContext.request.contextPath}/bankstatements/bankstatementForm" enctype="multipart/form-data">
            <input type="file" id="file" name="file" onchange="form.submit()">
        </form>
        <script>
            // alert("Hello1");
            $('#file').change(function () {
                $('#target').submit();
            });
        </script>

        <form method="post" action="${pageContext.request.contextPath}/bankstatements">
            <table>
                <tbody>
                <td>Счет:</td>
                <td><select  name="accountid" class="form-control mx-3">
                    <c:forEach items="${requestScope.listAccount}" var="account">
                        <jsp:useBean id="account" type="ru.java.springbootwebjsp.model.Account"/>
                        <option value=${account.id} ${account.id == dataFromFile.accountid ? 'selected' : ''}>
                                ${account.number}; ${account.description}; ${account.typeCurrency}</option>
                    </c:forEach>
                </select></td>
                </tbody>
            </table>

            <p></p>

            <table>
                <tbody>
                <tr>
                    <td>Описание:</td>
                    <td><input type="text" value="${dataFromFile.description}" name="description" required></td>
                </tr>
                <tr>
                    <td>Сумма:</td>
                    <td><input type="number" step=".01" value="${dataFromFile.value}" name="value" required></td>
                </tr>
                </tbody>
            </table>
            <p></p>
            <p style="color:#ff0000"> ${dataFromFile.error} </p>
            <p></p>
            <button class="btn btn-info mr-1" type="submit">Save</button>
            <button class="btn btn-info mr-1" onclick="window.history.back()" type="button">Cancel</button>
        </form>
    </div>



<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
