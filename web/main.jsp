<%-- 
    Document   : main
    Created on : Jan 31, 2021, 5:19:48 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Bootstrap CSS -->
        <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
            integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
            crossorigin="anonymous"
            />
        <style>
            body{
                background-image: url(https://pubnative.net/wp-content/uploads/2019/08/PubNative-Web-Background-Design.png);
            }
            h1 {
                font-weight: bold;
                color: blueviolet;
            }
            button {
                border: 2px solid blueviolet;
                background: none;
                color: blueviolet;
                padding: 6px;
                border-radius: 3px;
                letter-spacing: 1px;
                font-weight: bold;
                transition: 0.3s;
            }
            button:hover {
                color: white;
                background: blueviolet;
                transition: 0.3s;
            }
            .welcome {
                font-size: 20px;
                margin-left: 50px;
                margin-top: 20px;
            }
            .welcome span {
                color: blueviolet;
                font-weight: bold;
            }
        </style>
        <title>Main Page</title>
    </head>
    <body>
        <div class="welcome">
            Welcome,<span>${sessionScope.FULLNAME}</span>
            <a href="logout">Log out</a>
            <a href="viewHistory">View History</a>
        </div>
        <div style="padding-top: 15%" class="container">
            <h1>Welcome to Quiz Online</h1>
            <form action="quiz">
                <div class="form-group d-flex mt-5">
                    <div class="search col-10">
                        <select class="form-control" name="cbSubject" id="subjectbox">
                            <option disabled>Select subject</option>
                            <c:if test="${not empty sessionScope.SUBJECTS}">
                                <c:forEach var="subject" items="${sessionScope.SUBJECTS}">
                                    <option>
                                        ${subject.key}
                                    </option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>
                    <div class="btnTakeQuiz col-2">
                        <button>Quiz Now ></button>
                    </div>
                </div>
            </form>
            <!--            <div class="content__question">
                            <p style="color: #495057; letter-spacing: 1px; font-size: 20px;">
                                Time:
            
                            </p>
                        </div>-->
        </div>
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script
            src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"
        ></script>

        <!--        <script>
                    document.getElementById('subjectbox').onclick = function () {
                        var subject = document.getElementById('time').value;
                        console.log(subject);
                    };
                </script>-->
    </body>
</html>
