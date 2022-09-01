<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<script src="resources/js/exam.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<H3 class="p-5" style="color:#0f6674">Тестирование</H3>

<div class="container">
    <form method="post" action="${pageContext.request.contextPath}/exammathematic">
        <jsp:include page="fragments/examBodyHeader.jsp"/>
        <i style="color:#336b2f">Попробуем умножить цифры</i>
        <table class="table table-striped" id="datatableexamination">
            <thead>
            <tr>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${requestScope.exams}" var="exam" varStatus="status">
                <jsp:useBean id="exam" type="ru.java.springbootwebjsp.model.Exam"/>
                <tr data-exam-error="${exam.error}">
                    <td>вопрос № ${status.index+1}:</td>
                    <td>${exam.val1}</td>
                    <td>*</td>
                    <td>${exam.val2}</td>
                    <td>=</td>
                    <td>
                        <input type="number" style='width: 60px' value=${exam.result} name="${status.index}"
                               required>
                    </td>

                </tr>
            </c:forEach>
        </table>
    </form>

</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>







