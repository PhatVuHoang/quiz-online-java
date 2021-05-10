<%-- 
    Document   : takequiz
    Created on : Feb 5, 2021, 4:29:08 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Quiz Online</title>
    </head>
    <body>
            <div class="content my-5" style="display: flex;">
                <div class="col-3">
                    <div class="px-2 py-3 my-5" style="border: 1px solid black">
                        <p>
                            Time: <span style="font-weight: bold"></span>
                        </p>
                        <table>
                            <tr>
                                <td>
                                    <form action="http://www.google.fr">
                                        <input
                                            class="btn"
                                            id="time"
                                            type="hidden"
                                            formtarget="_blank"
                                            value=""
                                            />
                                    </form>
                                </td>
                                <td>${sessionScope.HOUR}:${sessionScope.MINUTE}:${sessionScope.SECOND}</td>
                            </tr>
                        </table>

                        <p>Student: <span style="font-weight: bold">${sessionScope.FULLNAME}</span></p>
                        <p>Subject: <span style="font-weight: bold">${sessionScope.SUBJECT}</span></p>
                    </div>
                </div>
                <form action="takeQuiz" method="POST">
                    <div class="col-9">
                        <div class="container py-5">
                            <p>${sessionScope.QUESTDISPLAY.question}</p>
                            <input type="hidden" name="txtQuestion" value="${sessionScope.QUESTDISPLAY}" />
                            <input type="hidden" name="txtQuest" value="${sessionScope.QUESTDISPLAY.id}" />
                            <input type="radio"  onclick="Click()" <c:if test="${sessionScope.USERANS eq sessionScope.QUESTDISPLAY.op1}">checked</c:if> name="Option" value="${sessionScope.QUESTDISPLAY.op1}" /> ${sessionScope.QUESTDISPLAY.op1}<br/>
                            <input type="radio"  onclick="Click()" <c:if test="${sessionScope.USERANS eq sessionScope.QUESTDISPLAY.op2}">checked</c:if>  name="Option" value="${sessionScope.QUESTDISPLAY.op2}" /> ${sessionScope.QUESTDISPLAY.op2}<br/>
                            <input type="radio"  onclick="Click()" <c:if test="${sessionScope.USERANS eq sessionScope.QUESTDISPLAY.op3}">checked</c:if>  name="Option" value="${sessionScope.QUESTDISPLAY.op3}" /> ${sessionScope.QUESTDISPLAY.op3}<br/>
                            <input type="radio"  onclick="Click()" <c:if test="${sessionScope.USERANS eq sessionScope.QUESTDISPLAY.op4}">checked</c:if>  name="Option" value="${sessionScope.QUESTDISPLAY.op4}" /> ${sessionScope.QUESTDISPLAY.op4}
                            </div>
                        <c:forEach var="i" begin="1" end="20">
                            <a class="btn btn-<c:if test="${sessionScope.INDEX eq i}">dark</c:if> px-2" href="quiz?index=${i}">${i}</a>
                        </c:forEach>
                    </div>
                    <input style="display: none;" id="button" type="submit" value="Submit" />
                </form>
            </div>
            <form action="submit">
                <br/><input id="finish" class="btn btn-success my-2" type="submit" value="Finish Exam" />
            </form>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
        <script>
                                function Click() {
                                    document.getElementById('button').click();
                                }
                                ;

                                function toTimeString(seconds) {
                                    return (new Date(seconds * 1000)).toUTCString().match(/(\d\d:\d\d:\d\d)/)[0];
                                }

                                function startTimer() {
                                    var nextElem = $(this).parents('td').next();
                                    var duration = nextElem.text();
                                    var a = duration.split(':');
                                    var seconds = (+a[0]) * 60 * 60 + (+a[1]) * 60 + (+a[2]);
                                    setInterval(function () {
                                        seconds--;
                                        if (seconds >= 0) {
                                            nextElem.html(toTimeString(seconds));
                                        }
                                        if (seconds <= 20) {
                                            nextElem.css('color', 'red');
                                        }
                                        if (seconds === 0) {
                                            document.getElementById('finish').click();
                                            clearInterval(seconds);
                                        }
                                    }, 1000);
                                }
                                $('#time').on('click', startTimer);
                                jQuery(function () {
                                    jQuery('#time').click();
                                });

        </script>
    </body>
</html>
