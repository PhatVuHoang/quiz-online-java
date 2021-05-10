<%-- 
    Document   : detail
    Created on : Feb 7, 2021, 11:11:00 AM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>detail Page</title>
        <style>
            span{
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <a href="history.jsp">Back</a>
        <c:forEach var="item" items="${sessionScope.LISTDETAIL}">
            <p>Question: <span>${item.question}</span></p>
            <p>Answer correct: <span>${item.correctAns}</span></p>
            <c:if test="${item.correctAns eq item.userAns}">
                <p>Your answer: <span style="background: green; color: white">${item.userAns}</span></p>
                </c:if>
                <c:if test="${item.correctAns ne item.userAns}">
                <p>Your answer: <span style="background: red; color: white">${item.userAns}</span></p>
                </c:if>
            </c:forEach>
    </body>
</html>
