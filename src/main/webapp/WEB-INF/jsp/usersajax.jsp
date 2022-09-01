<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>

<script src="resources/js/common.js" defer></script>
<script src="resources/js/users.js" defer></script>

<jsp:include page="fragments/bodyHeader.jsp"/>
<H3 class="p-5" style="color:#0f6674">Пользователи (заполнение ajax)</H3>
<div class="container">
    <p></p>     <p></p>
    <table class="table table-striped" id="datatable">
        <thead>
        <tr>
            <th>ID</th>
            <th>ФИО</th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.users}" var="user">
            <jsp:useBean id="user" type="ru.java.springbootwebjsp.model.User"/>
            <tr iddel="${user.id}">
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td><a class="delete"><span class="fa fa-remove"></span></a></td>
<%--                <td><a onclick="deleteRow(${user.id})"><span class="fa fa-remove"></span></a></td>--%>
                    <%--<td><a onclick="deleteRow(${user.id})"><span class="fa fa-remove"></span></a></td>--%>
            </tr>
        </c:forEach>
    </table>
</div>



<%--

<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Добавление пользователя</h4>
                <button type="button" class="close" data-dismiss="modal" onclick="closeNoty()">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="name" class="col-form-label">ФИО</label>
                        <input type="text" class="form-control" id="name" name="name"
                               placeholder="name"/>">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeNoty()">
                    <span class="fa fa-close"></span>
                    Отмена
                </button>
                <button type="button" class="btn btn-primary" onclick="save()">
                    <span class="fa fa-check"></span>
                    Сохранить
                </button>
            </div>
        </div>
    </div>
</div>
--%>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>