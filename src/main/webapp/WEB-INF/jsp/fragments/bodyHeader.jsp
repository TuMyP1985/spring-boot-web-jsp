<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<nav class="navbar navbar-dark bg-dark py-0">
    <div class="container">
        <a href="accounts" class="navbar-brand"><img src="resources/images/account-card.png"> Банковские счета</a>
        <form class="form-inline my-2">
            <a class="btn btn-info mr-1" href="usersajax">Пользователи (заполн. ajax)</a>
            <a class="btn btn-info mr-1" href="users">Пользователи</a>
            <a class="btn btn-info mr-1" href="accounts">Банковкские счета</a>
            <a class="btn btn-info mr-1" href="bankstatements">Выписки (движение)</a>
            <a class="btn btn-info mr-1" href="richvalues">Ценные бумаги</a>
            <a class="btn btn-info mr-1" href="examination">Пров.</a>
            <a class="btn btn-info mr-1" href="convector">Конвектор</a>
            <a class="btn btn-primary" href="">
                <span class="fa fa-sign-in"></span>
            </a>
        </form>
    </div>
</nav>
