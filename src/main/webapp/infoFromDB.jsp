<%
    if ( request.getSession().getAttribute("id_user") == null) {
        response.sendRedirect("/");
        return;
    }
%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE HTML>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Таблица</title>
    <link rel="icon" type="image/png" href="https://i.ibb.co/9Wtc3rG/image1.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <style>
        <%@include file='/styles/s.css' %>

        a#go-up{
            position: fixed;
            border-radius: 50%;
            width: 50px;
            height: 50px;
            bottom: 40px;
            left: 80px;
            z-index: 100;
            -webkit-filter: opacity(.4);
            filter: opacity(.4);
            display : none;
            transition: .2s ease-out;
        }
        a#go-up:hover{
            -webkit-filter: opacity(.8);
            filter: opacity(.8);
        }
    </style>
</head>
<body>
    <div class="row" id="top">
        <div class="center-align test" style="border-radius: unset; padding: unset; height: 50px">
            <form action="${pageContext.request.contextPath}/start">
                <input type="submit" value="Назад" class="test col l2 offset-l9 hoverable" style="padding: 15px">
            </form>
        </div>
    </div>
    <script>
        window.addEventListener("scroll", function () {
            document.getElementById("go-up").style.display = (window.scrollY > 100)?"block":"none";
        });
    </script>
    <a id="go-up" class="test" href="#top">GO TOP</a>
    <%= request.getSession().getAttribute("table").toString()%>
</body>
</html>
