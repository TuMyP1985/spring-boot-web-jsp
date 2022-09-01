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
<p></p>
<p></p>
<p></p>

<div class="container">
    <span class="text-body">Тестирование ученика 3 класса, на знание предметов.</span>
    <p></p>
    <div class="alineGorizont">
        <p><a href="${pageContext.request.contextPath}/exammathematic">Математика...</a></p>
        <p><a href="${pageContext.request.contextPath}/examenglish">Английский язык...</a></p>
        <p><a href="${pageContext.request.contextPath}/examrussian">Русский язык...</a></p>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>







