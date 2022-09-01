<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<p>Конвектор</p>

<body>
<nav class="navbar navbar-dark bg-dark py-0">
    <div class="container">
        <a href="accounts" class="navbar-brand"><img src="resources/images/account-card.png"> Банковские счета</a>
    </div>
</nav>

<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
    * {
        box-sizing: border-box;
    }

    /* Create two equal columns that floats next to each other */
    .column {
        float: left;
        width: 50%;
        padding: 10px;
        height: 500px;
    }

    .textarea {
        float: left;
        width: 90%;

        height: 300px;
    }

    /* Clear floats after the columns */
    .row:after {
        content: "";
        display: table;
        clear: both;
    }
</style>
<h2>Получаем JSON из XML файла/текста</h2>

<div class="row">
    <form method="post" action="convert">
        <div class="column" style="background-color:#aaa;">
            <h2>Получить json из текста</h2>
            <p>Введите xml текст:</p>
            <p><input type="submit" value="Получить json из xml текста"></p>
            <p><textarea class="textarea" required name="text_xml">

        <?xml version="1.0" encoding="UTF-8"?>
          <PropertyList>
            <Property>
              <Name>CommandTimeout</Name>
              <Value>60</Value>
              <Description>Setting the timeout(in seconds)</Description>
              <DefaultValue></DefaultValue>
            </Property>
            <Property>
              <Name>Address</Name>
              <Value>0.0.0.0</Value>
              <Description>ip:port</Description>
              <DefaultValue></DefaultValue>
            </Property>
          </PropertyList>

    </textarea></p>
        </div>
    </form>
    <form method="post" action="convertFile" enctype="multipart/form-data">
        <div class="column" style="background-color:#bbb;">
            <h2>Получить json из файла</h2>
            <label for="file">Выберите xml файл: </label>
            <input type="file" id="file" name="file">
            <p>
                <button>Получить json из xml файла</button>
            </p>
        </div>
    </form>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>







