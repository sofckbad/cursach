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
    <title>Управление</title>
    <link rel="icon" type="image/png" href="https://i.ibb.co/9Wtc3rG/image1.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <style>
        <%@include file='/styles/s.css' %>
        <%=
               (request.getSession().getAttribute("access").toString().equals("1"))? "":".priority {display:none;}"
        %>
    </style>
</head>
<body>
    <div class="row">
        <div class="center-align test" style="border-radius: unset; padding: unset; height: 50px"><form action="${pageContext.request.contextPath}/exit"><input type="submit" value="Выход" style="padding: 15px" class="test col l2 offset-l9 hoverable"></form></div>
    </div>
    <div class="row priority">
        <div class="col s2 l2 center-align offset-s1 offset-l1">
            <div class="z-depth-5 myBlock hoverable">
                <div class="test">
                    <p>Добавить пользователя</p>
                </div>
                <div class="cont">
                    <form method="POST" action="${pageContext.request.contextPath}/db">
                        <label>Имя<input required autocomplete="off" type="text" name="name"></label>
                        <label>Пароль<input required autocomplete="off" type="password" name="password"></label>
                        <label>Доступ<input required autocomplete="off" type="number" min="0" max="1" step="1" name="access"></label>
                        <label><input style="visibility: hidden" type="text" name="request" value="0"></label>
                        <input type="submit" value="Добавить" class="test">
                        <br>
                    </form>
                </div>
            </div>
        </div>
        <div class="col s2 l2 center-align">
            <div class="z-depth-5 myBlock hoverable">
                <div class="test">
                    <p>Удалить пользователя</p>
                </div>
                <div class="cont">
                    <form method="POST" action="${pageContext.request.contextPath}/db">
                        <label>Имя<input required autocomplete="off" type="text" name="name"></label>
                        <label>Пароль<input type="password" name="password" disabled></label>
                        <label>Доступ<input type="text" name="access" disabled></label>
                        <label><input style="visibility: hidden" type="text" name="request" value="1"></label>
                        <input type="submit" value="Удалить" class="test">
                        <br>
                    </form>
                </div>
            </div>
        </div>
        <div class="col s2 l2 center-align">
            <div class="z-depth-5 myBlock hoverable">
                <div class="test">
                    <p>Лист всех пользователей</p>
                </div>
                <div class="cont">
                    <form method="POST" action="${pageContext.request.contextPath}/db">
                        <label>Имя<input disabled="disabled" type="text" name="name"></label>
                        <label>Пароль<input disabled="disabled" type="password" name="password"></label>
                        <label>Доступ<input disabled="disabled" type="text" name="access"></label>
                        <label><input style="visibility: hidden" type="text" name="request" value="2"></label>
                        <input type="submit" value="Таблица" class="test">
                        <br>
                    </form>
                </div>
            </div>
        </div>
        <div class="col s2 l2 center-align">
            <div class="z-depth-5 myBlock hoverable">
                <div class="test">
                    <p>Информация о пользователе</p>
                </div>
                <div class="cont">
                    <form method="POST" action="${pageContext.request.contextPath}/db">
                        <label>Имя<input autocomplete="off" type="text" name="name" required></label>
                        <label>Пароль<input  type="password" name="password" disabled></label>
                        <label>Доступ<input autocomplete="off" type="text" name="access" disabled></label>
                        <label><input style="visibility: hidden" type="text" name="request" value="3"></label>
                        <input type="submit" value="Информация" class="test">
                        <br>
                    </form>
                </div>
            </div>
        </div>
        <div class="col s2 l2 center-align">
            <div class="z-depth-5 myBlock hoverable">
                <div class="test">
                    <p>Изменить информацию</p>
                </div>
                <div class="cont">
                    <form method="POST" action="${pageContext.request.contextPath}/db">
                    <label>Имя -> новое имя<br>
                        <input style="width: 49%" autocomplete="off" type="text" name="name" required>
                        <input style="width: 49%" autocomplete="off" type="text" name="newName">
                    </label>
                    <label>Пароль -> Новый пароль<br>
                        <input style="width: 49%" autocomplete="off" type="password" name="password" disabled>
                        <input style="width: 49%" autocomplete="off" type="password" name="newPassword">
                    </label>
                    <label>Доступ -> Новый доступ<br>
                        <input style="width: 49%" autocomplete="off"  type="number" min="0" max="1" step="1"  name="access" disabled>
                        <input style="width: 49%" autocomplete="off"  type="number" min="0" max="1" step="1"  name="newAccess">
                    </label>
                    <label><input style="visibility: hidden" type="text" name="request" value="4"></label>
                    <input type="submit" value="Изменить" class="test">
                    <br>
                </form>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col s2 center-align offset-s1">
            <div class="z-depth-5 myBlock hoverable">
                <div class="test">
                    <p>Добавить сервис</p>
                </div>
                <div class="cont">
                    <form method="POST" action="${pageContext.request.contextPath}/db">
                        <label>Название<input required autocomplete="off" type="text" name="service"></label>
                        <label>Стоимость<input required autocomplete="off"  type="number" min="100" max="500" step="10"  name="cost"></label>
                        <label>Описание<input required autocomplete="off" type="text" name="description"></label>
                        <label><input style="visibility: hidden" type="text" name="request" value="5"></label>
                        <input type="submit" value="Добавить" class="test">
                        <br>
                    </form>
                </div>
            </div>
        </div>
        <div class="col s2 center-align">
            <div class="z-depth-5 myBlock hoverable">
                <div class="test">
                    <p>Удалить сервис</p>
                </div>
                <div class="cont">
                    <form method="POST" action="${pageContext.request.contextPath}/db">
                        <label>Название<input autocomplete="off" name="service" required type="text"></label>
                        <label>Стоимость<input disabled name="cost" type="text"></label>
                        <label>Описание<input disabled name="description" type="text"></label>
                        <label><input style="visibility: hidden" type="text" name="request" value="6"></label>
                        <input type="submit" value="Удалить" class="test">
                        <br>
                    </form>
                </div>
            </div>
        </div>
        <div class="col s2 center-align">
            <div class="z-depth-5 myBlock hoverable">
                <div class="test">
                    <p>Лист всех услуг</p>
                </div>
                <div class="cont">
                    <form method="POST" action="${pageContext.request.contextPath}/db">
                        <label>Название<input disabled="disabled" type="text" name="service"></label>
                        <label>Стоимость<input disabled="disabled" type="text" name="cost"></label>
                        <label>Описание<input disabled="disabled" type="text" name="description"></label>
                        <label><input style="visibility: hidden" type="text" name="request" value="7"></label>
                        <input type="submit" value="Таблица" class="test">
                        <br>
                    </form>
                </div>
            </div>
        </div>
        <div class="col s2 center-align">
            <div class="z-depth-5 myBlock hoverable">
                <div class="test">
                    <p>Информация об услуге</p>
                </div>
                <div class="cont">
                    <form method="POST" action="${pageContext.request.contextPath}/db">
                        <label>Название<input autocomplete="off" type="text" name="service"></label>
                        <label>Стоимость<input disabled="disabled" type="text" name="cost"></label>
                        <label>Описание<input disabled="disabled" type="text" name="description"></label>
                        <label><input style="visibility: hidden" type="text" name="request" value="8"></label>
                        <input type="submit" value="Информация" class="test">
                        <br>
                    </form>
                </div>
            </div>
        </div>
        <div class="col s2 center-align">
            <div class="z-depth-5 myBlock hoverable">
                <div class="test">
                    <p>Изменить услугу</p>
                </div>
                <div class="cont">
                    <form method="POST" action="${pageContext.request.contextPath}/db">
                        <label>Название -> новое название<br>
                            <input style="width: 49%" autocomplete="off" type="text" name="service" required>
                            <input style="width: 49%" autocomplete="off" type="text" name="newService">
                        </label>
                        <label>Стоимость -> Новая стоимость<br>
                            <input style="width: 49%" type="number" min="100" max="500" step="10" name="cost" disabled>
                            <input style="width: 49%" type="number" min="100" max="500" step="10" name="newCost">
                        </label>
                        <label>Описание -> новое Описание<br>
                            <input style="width: 49%" autocomplete="off" type="text" name="description" disabled>
                            <input style="width: 49%" autocomplete="off" type="text" name="newDescription">
                        </label>
                        <label><input style="visibility: hidden" type="text" name="request" value="9"></label>
                        <input type="submit" value="Изменить" class="test">
                        <br>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col s2 center-align offset-s1">
        <div class="z-depth-5 myBlock hoverable">
                <div class="test">
                    <p>Добавить запись</p>
                </div>
                <div class="cont">
                    <form method="POST" action="${pageContext.request.contextPath}/db">
                        <label>Сервис<input required autocomplete="off" type="text" name="service"></label>
                        <label>Количество<input required autocomplete="off"   type="number" min="1" max="10" step="1"   name="count"></label>
                        <label>Работник<input required autocomplete="off" type="text" name="user"></label>
                        <label>Дата<input required autocomplete="off" type="datetime-local" name="date"></label>
                        <label>Имя<input required autocomplete="off" type="text" name="name"></label>
                        <label>номер<input required autocomplete="off" type="tel" pattern="375[0-9]{9}" name="number"></label>
                        <label><input style="visibility: hidden" type="text" name="request" value="a"></label>
                        <input type="submit" value="Добавить" class="test">
                        <br>
                    </form>
                </div>
            </div>
        </div>
        <div class="col s2 center-align">
            <div class="z-depth-5 myBlock hoverable">
                <div class="test">
                    <p>Удалить запись</p>
                </div>
                <div class="cont">
                    <form method="POST" action="${pageContext.request.contextPath}/db">
                        <label>Сервис<input required autocomplete="off" type="text" name="service" ></label>
                        <label>Количество<input required autocomplete="off" type="text" name="count" disabled></label>
                        <label>Работник<input required autocomplete="off" type="text" name="user" disabled></label>
                        <label>Дата<input required autocomplete="off" type="date" name="date"></label>
                        <label>Имя<input required autocomplete="off" type="text" name="name" disabled></label>
                        <label>номер<input required autocomplete="off" type="tel" pattern="375[0-9]{9}" name="number"></label>
                        <label><input style="visibility: hidden" type="text" name="request" value="b"></label>
                        <input type="submit" value="Удалить" class="test">
                        <br>
                    </form>
                </div>
            </div>
        </div>
        <div class="col s2 center-align">
            <div class="z-depth-5 myBlock hoverable">
                <div class="test">
                    <p>все записи</p>
                </div>
                <div class="cont">
                    <form method="POST" action="${pageContext.request.contextPath}/db">
                        <label>Сервис<input required autocomplete="off" type="text" name="service" disabled></label>
                        <label>Количество<input required autocomplete="off" type="text" name="count" disabled></label>
                        <label>Работник<input required autocomplete="off" type="text" name="user" disabled></label>
                        <label>Дата<input required autocomplete="off" type="datetime-local" name="date" disabled></label>
                        <label>Имя<input required autocomplete="off" type="text" name="name" disabled></label>
                        <label>номер<input required autocomplete="off" type="tel" pattern="375[0-9]{9}" name="number" disabled></label>
                        <label><input style="visibility: hidden" type="text" name="request" value="c"></label>
                        <input type="submit" value="Таблица" class="test">
                        <br>
                    </form>
                </div>
            </div>
        </div>
        <div class="col s2 center-align">
            <div class="z-depth-5 myBlock hoverable">
                <div class="test">
                    <p>Информация об записи</p>
                </div>
                <div class="cont">
                    <form method="POST" action="${pageContext.request.contextPath}/db">
                        <label>Сервис<input required autocomplete="off" type="text" name="service"></label>
                        <label>Количество<input required autocomplete="off" type="text" name="count" disabled></label>
                        <label>Работник<input required autocomplete="off" type="text" name="user" disabled></label>
                        <label>Дата<input required autocomplete="off" type="datetime-local" name="date" disabled></label>
                        <label>Имя<input required autocomplete="off" type="text" name="name" disabled></label>
                        <label>номер<input required autocomplete="off" type="tel" pattern="375[0-9]{9}" name="number"></label>
                        <label><input style="visibility: hidden" type="text" name="request" value="d"></label>
                        <input type="submit" value="Информация" class="test">
                        <br>
                    </form>
                </div>
            </div>
        </div>
        <div class="col s2 center-align">
            <div class="z-depth-5 myBlock hoverable">
                <div class="test">
                    <p>Изменить запись</p>
                </div>
                <div class="cont">
                    <form method="POST" action="${pageContext.request.contextPath}/db">
                        <label>Сервис -> новый сервис<br>
                            <input style="width: 49%" autocomplete="off" type="text" name="service" required>
                            <input style="width: 49%" autocomplete="off" type="text" name="newService">
                        </label>
                        <label>Новое количество<br>
                            <input autocomplete="off"   type="number" min="1" max="10" step="1"   name="newCount">
                        </label>
                        <label>Новый работник<br>
                            <input autocomplete="off" type="text" name="newUser">
                        </label>
                        <label>Дата -> Новая дата<br>
                            <input style="width: 49%" autocomplete="off" type="date" name="date" required>
                            <input style="width: 49%" autocomplete="off" type="date" name="newDate">
                        </label>
                        <label>Имя<br>
                            <input autocomplete="off" type="text" name="name" required>
                        </label>
                        <label>номер<br>
                            <input autocomplete="off" type="tel" pattern="375[0-9]{9}" name="number" required>
                        </label>
                        <label><input style="visibility: hidden" type="text" name="request" value="e"></label>
                        <input type="submit" value="Изменить" class="test">
                        <br>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="row priority">
        <div class="col s2 center-align offset-s1">
            <div class="z-depth-5 myBlock hoverable">
                <div class="test">
                    <p>Заказы за день</p>
                </div>
                <div class="cont">
                    <form method="POST" action="${pageContext.request.contextPath}/db">
                        <label><input autocomplete="off" type="date" name="date"></label>
                        <label><input style="visibility: hidden" type="text" name="request" value="f"></label>
                        <input type="submit" value="Скачать" class="test">
                        <br>
                    </form>
                </div>
            </div>
        </div>
        <div class="col s2 center-align">
            <div class="z-depth-5 myBlock hoverable">
                <div class="test">
                    <p>Кол-во заказов</p>
                </div>
                <div class="cont">
                    <form method="POST" action="${pageContext.request.contextPath}/db">
                        <label><input autocomplete="off" type="date" name="date"></label>
                        <label><input style="visibility: hidden" type="text" name="request" value="g"></label>
                        <input type="submit" value="Calendar chart" class="test">
                        <br>
                    </form>
                </div>
            </div>
        </div>
        <div class="col s2 center-align">
            <div class="z-depth-5 myBlock hoverable">
                <div class="test">
                    <p>Прибыль</p>
                </div>
                <div class="cont">
                    <form method="POST" action="${pageContext.request.contextPath}/db">
                        <label><input autocomplete="off" type="date" name="date"></label>
                        <label><input style="visibility: hidden" type="text" name="request" value="h"></label>
                        <input type="submit" value="Calendar chart" class="test">
                        <br>
                    </form>
                </div>
            </div>
        </div>
        <div class="col s2 center-align">
            <div class="z-depth-5 myBlock hoverable">
                <div class="test">
                    <p>Кол-во заказов</p>
                </div>
                <div class="cont">
                    <form method="POST" action="${pageContext.request.contextPath}/db">
                        <label><input autocomplete="off" type="date" name="date" required></label>
                        <label><input style="visibility: hidden" type="text" name="request" value="i"></label>
                        <input type="submit" value="Line chart" class="test">
                        <br>
                    </form>
                </div>
            </div>
        </div>
        <div class="col s2 center-align">
            <div class="z-depth-5 myBlock hoverable">
                <div class="test">
                    <p>Прибыль</p>
                </div>
                <div class="cont">
                    <form method="POST" action="${pageContext.request.contextPath}/db">
                        <label><input autocomplete="off" type="date" name="date" required></label>
                        <label><input style="visibility: hidden" type="text" name="request" value="j"></label>
                        <input type="submit" value="Line chart" class="test">
                        <br>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>