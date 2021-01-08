<%
    if (request.getCookies() != null)
        for (Cookie c: request.getCookies())
            if (c.getName().equals("login")) {
                response.sendRedirect("/check");
                return;
            }
%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Вход</title>
    <link rel="icon" type="image/png" href="https://i.ibb.co/9Wtc3rG/image1.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <style>
        <%@include file='/styles/s.css' %>
    </style>
</head>
<body>
    
    <div class="row">
        <div class="col s12 l12 center-align test"  style="border-radius: unset; height: 50px"></div>
    </div>
    <div class="row">
        <form method="post" action="${pageContext.request.contextPath}/check" class="col l4 s8 offset-l4 offset-s2">
            <label>login:<input required autocomplete="off" id="1" type="text" name="login"></label><br/>
            <label>password:<input required id="2" type="password" name="password"></label><br/>
            <input type="submit" class="test" value="Войти">
        </form>
    </div>
</body>
</html>