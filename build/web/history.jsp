<%-- 
    Document   : history
    Created on : Feb 7, 2021, 12:37:03 AM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>History Page</title>
    </head>
    <body>
        <a href="main.jsp">Back</a>
        <form action="searchHistory">
            <select name="cbSub">
                <c:forEach var="sub" items="${sessionScope.LISTSUB}">
                    <option>${sub}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Search" />
        </form>
        <h1>${sessionScope.NAMESUBHISTORY}</h1>
        <c:if test="${not empty sessionScope.LISTHIST}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Date take quiz</th>
                        <th>Score</th>
                        <th>View detail</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="list" items="${sessionScope.LISTHIST}">

                        <tr>
                            <td>${list.date}</td>
                            <td>${list.score}</td>
                    <form action="viewDetail" method="POST">
                        <input type="hidden" name="txtIdHistory" value="${list.id}" />
                        <td> <input type="submit" value="view detail" /> </td>
                    </form>
                </tr>

            </c:forEach>
        </tbody>
    </table>

</c:if>
<c:if test="${empty sessionScope.LISTHIST}">
    <h1>RESULT NOT FOUND</h1>
</c:if>
<c:if test="${sessionScope.HISTORYPAGE > 1}">
    <c:forEach var="page" begin="1" end="${sessionScope.HISTORYPAGE}">
        <a href="viewHistory?index=${page}">${page}</a>
    </c:forEach>
</c:if>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</body>
</html>
