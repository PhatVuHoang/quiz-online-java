<%-- 
    Document   : accessdenied
    Created on : Jan 31, 2021, 6:24:17 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <style>
            h1{
                font-size: 70px;
                color: red;
            }
            p{
                font-size: 30px;
            }
            .container{
                text-align: center;
                padding-top: 100px;
            }
        </style>
        <title>Access Denied</title>
    </head>
    <body>
        <div class="container">
            <h1>WARNING!</h1>
            <p>You don't allow access this page</p>
            <c:if test="${empty sessionScope.PERMISSION}">
                <a href="main.jsp">Go back Quiz Online</a>
            </c:if>
            <c:if test="${not empty sessionScope.PERMISSION}">
                <a href="search.jsp">Go back Quiz Online Admin</a>
            </c:if>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    </body>
</html>
