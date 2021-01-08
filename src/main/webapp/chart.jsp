<%
    if ( request.getSession().getAttribute("id_user") == null) {
        response.sendRedirect("/");
        return;
    }
%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Управление</title>
    <link rel="icon" type="image/png" href="https://i.ibb.co/9Wtc3rG/image1.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <style>
        <%@include file='/styles/s.css' %>
        <%=
               (request.getSession().getAttribute("access").toString().equals("1"))? "":"#pr {display:none;}"
        %>
    </style>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        <%=request.getAttribute("script")%>
    </script>
</head>
<body>
<div class="row">
    <div class="center-align test" style="border-radius: unset; padding: unset; height: 50px">
        <form action="${pageContext.request.contextPath}/start">
            <input type="submit" value="Назад" class="test col l2 offset-l9 hoverable" style="padding: 15px">
        </form>
    </div>
    </div>
<div class="row">
    <div class="col s10 offset-s1 l10 offset-l1">
        <div id="chart">
        </div>
    </div>
</div>
</body>
</html>
