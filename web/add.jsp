<%-- 
    Document   : add
    Created on : Jan 30, 2021, 9:33:54 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css" integrity="sha512-HK5fgLBL+xu6dm/Ii3z4xhlSUyZgTT9tuc/hSrtw6uzJOvgRr2a9jyxxT1ely+B+xFAmJKVSTbpM/CuL7qxO8w==" crossorigin="anonymous" />
        <style>
            @import url('https://fonts.googleapis.com/css?family=Roboto');
            .question{
                width: 70%;
                margin: auto;
                margin-top: 50px;
                padding: 30px;
                border: 2px solid white;
                border-radius: 10px;
                box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.384);
            }
            textarea{
                resize: none;
            }

            body{
                font-family: 'Roboto', sans-serif;
            }
            * {
                margin: 0;
                padding: 0;
            }
            i {
                margin-right: 10px;
            }
            /*----------multi-level-accordian-menu------------*/
            .navbar-logo{
                padding: 15px;
                color: #fff;
            }
            .navbar-brand span{
                font-size: 10px;
            }
            .navbar-mainbg{
                background-color: #5161ce;
                padding: 0px;
            }
            #navbarSupportedContent{
                overflow: hidden;
                position: relative;
            }
            #navbarSupportedContent ul{
                padding: 0px;
                margin: 0px;
            }
            #navbarSupportedContent ul li a i{
                margin-right: 10px;
            }
            #navbarSupportedContent li {
                list-style-type: none;
                float: left;
            }
            #navbarSupportedContent ul li a{
                color: rgba(255,255,255,0.5);
                text-decoration: none;
                font-size: 15px;
                display: block;
                padding: 20px 20px;
                transition-duration:0.6s;
                transition-timing-function: cubic-bezier(0.68, -0.55, 0.265, 1.55);
                position: relative;
            }
            #navbarSupportedContent>ul>li.active>a{
                color: #5161ce;
                background-color: transparent;
                transition: all 0.7s;
            }
            #navbarSupportedContent a:not(:only-child):after {
                content: "\f105";
                position: absolute;
                right: 20px;
                top: 10px;
                font-size: 14px;
                font-family: "Font Awesome 5 Free";
                display: inline-block;
                padding-right: 3px;
                vertical-align: middle;
                font-weight: 900;
                transition: 0.5s;
            }
            #navbarSupportedContent .active>a:not(:only-child):after {
                transform: rotate(90deg);
            }
            .hori-selector{
                display:inline-block;
                position:absolute;
                height: 100%;
                top: 0px;
                left: 0px;
                transition-duration:0.6s;
                transition-timing-function: cubic-bezier(0.68, -0.55, 0.265, 1.55);
                background-color: #fff;
                border-top-left-radius: 15px;
                border-top-right-radius: 15px;
                margin-top: 10px;
            }
            .hori-selector .right,
            .hori-selector .left{
                position: absolute;
                width: 25px;
                height: 25px;
                background-color: #fff;
                bottom: 10px;
            }
            .hori-selector .right{
                right: -25px;
            }
            .hori-selector .left{
                left: -25px;
            }
            .hori-selector .right:before,
            .hori-selector .left:before{
                content: '';
                position: absolute;
                width: 50px;
                height: 50px;
                border-radius: 50%;
                background-color: #5161ce;
            }
            .hori-selector .right:before{
                bottom: 0;
                right: -25px;
            }
            .hori-selector .left:before{
                bottom: 0;
                left: -25px;
            }
            .search__admin button{
                color: #5161ce;
                border: 2px solid #5161ce;
            }
            .search__admin button:hover{
                color: white;
                border: 2px solid #5161ce;
                background-color: #5161ce;
            }
            .search__admin{
                display: flex;
                justify-content: space-between;
                margin: 20px;
            }



            @media (max-width: 991px){
                #navbarSupportedContent ul li a{
                    padding: 12px 30px;
                }
                .hori-selector{
                    margin-top: 0px;
                    margin-left: 10px;
                    border-radius: 0;
                    border-top-left-radius: 25px;
                    border-bottom-left-radius: 25px;
                }
                .hori-selector .left,
                .hori-selector .right{
                    right: 10px;
                }
                .hori-selector .left{
                    top: -25px;
                    left: auto;
                }
                .hori-selector .right{
                    bottom: -25px;
                }
                .hori-selector .left:before{
                    left: -25px;
                    top: -25px;
                }
                .hori-selector .right:before{
                    bottom: -25px;
                    left: -25px;
                }
            }
        </style>
        <title>Add Question</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-mainbg">
            <a class="navbar-brand navbar-logo" href="#">Quiz Online <span>Admin</span></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <i class="fas fa-bars text-white"></i>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto">
                    <div class="hori-selector"><div class="left"></div><div class="right"></div></div>
                    <li class="nav-item">
                        <a class="nav-link" href="showAll"><i class="fa fa-search"></i>Find Question</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="add.jsp"><i class="fa fa-pen"></i>Add Question</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="showAllUpdate"><i class="fa fa-wrench"></i>Update Question</a>
                    </li>
                </ul>
            </div>
        </nav>
        <nav class="search__admin">
            <div class="left">
                <span>Welcome, ${sessionScope.FULLNAME}</span>
                <a href="Logout">Logout</a>
            </div>
        </nav>

        <div class="question">
            <form action="addQuest" method="POST">
                <font color="red">${requestScope.ADDERROR.subjectEmpty}</font>
                <div class="form-group">
                    <select class="form-control" name="cbSubject">
                        <option>--Subject--</option>
                        <c:if test="${not empty sessionScope.SUBJECTS}">
                            <c:forEach var="subject" items="${sessionScope.SUBJECTS}">
                                <option>${subject.key}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                </div>
                <div class="form-group">
                    <font color="red">${requestScope.ADDERROR.questionEmpty}</font>
                    <textarea class="form-control" name="txtQuestion" id="exampleFormControlTextarea1" rows="3" placeholder="Question"><c:if test="${requestScope.QUEST != null}">${requestScope.QUEST}</c:if></textarea>
                </div>
                <font color="red">${requestScope.ADDERROR.opDuplicate}</font>
                <div class="form-group">
                    <font color="red">${requestScope.ADDERROR.op1Empty}</font>
                    <input type="text" class="form-control" name="txtOption1" id="exampleFormControlInput1" placeholder="Option 1" value="${requestScope.OP1}">
                </div>
                <div class="form-group">
                    <font color="red">${requestScope.ADDERROR.op2Empty}</font>
                    <input type="text" class="form-control" name="txtOption2" id="exampleFormControlInput1" placeholder="Option 2" value="${requestScope.OP2}">
                </div>
                <div class="form-group">
                    <font color="red">${requestScope.ADDERROR.op3Empty}</font>
                    <input type="text" class="form-control" name="txtOption3" id="exampleFormControlInput1" placeholder="Option 3" value="${requestScope.OP3}">
                </div>
                <div class="form-group">
                    <font color="red">${requestScope.ADDERROR.op4Empty}</font>
                    <input type="text" class="form-control" name="txtOption4" id="exampleFormControlInput1" placeholder="Option 4" value="${requestScope.OP4}">
                </div>
                <div class="form-group">
                    <font color="red">${requestScope.ADDERROR.ansEmpty}</font>
                    <font color="red">${requestScope.ADDERROR.ansNotMatch}</font><br/>
                    <label for="exampleFormControlInput1">Correct Answer</label>
                    <input type="text" class="form-control" name="txtAnswer" id="exampleFormControlInput1" placeholder="Answer" value="${sessionScope.ANS}">
                </div>
                <input class="btn btn-success"  type="submit" value="+ Add Question">
            </form>
        </div>
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script>
            // ---------Responsive-navbar-active-animation-----------
            function test() {
                var tabsNewAnim = $('#navbarSupportedContent');
                var selectorNewAnim = $('#navbarSupportedContent').find('li').length;
                var activeItemNewAnim = tabsNewAnim.find('.active');
                var activeWidthNewAnimHeight = activeItemNewAnim.innerHeight();
                var activeWidthNewAnimWidth = activeItemNewAnim.innerWidth();
                var itemPosNewAnimTop = activeItemNewAnim.position();
                var itemPosNewAnimLeft = activeItemNewAnim.position();
                $(".hori-selector").css({
                    "top": itemPosNewAnimTop.top + "px",
                    "left": itemPosNewAnimLeft.left + "px",
                    "height": activeWidthNewAnimHeight + "px",
                    "width": activeWidthNewAnimWidth + "px"
                });
                $("#navbarSupportedContent").on("click", "li", function (e) {
                    $('#navbarSupportedContent ul li').removeClass("active");
                    $(this).addClass('active');
                    var activeWidthNewAnimHeight = $(this).innerHeight();
                    var activeWidthNewAnimWidth = $(this).innerWidth();
                    var itemPosNewAnimTop = $(this).position();
                    var itemPosNewAnimLeft = $(this).position();
                    $(".hori-selector").css({
                        "top": itemPosNewAnimTop.top + "px",
                        "left": itemPosNewAnimLeft.left + "px",
                        "height": activeWidthNewAnimHeight + "px",
                        "width": activeWidthNewAnimWidth + "px"
                    });
                });
            }
            $(document).ready(function () {
                setTimeout(function () {
                    test();
                });
            });
            $(window).on('resize', function () {
                setTimeout(function () {
                    test();
                }, 500);
            });
            $(".navbar-toggler").click(function () {
                setTimeout(function () {
                    test();
                });
            });
        </script>
    </body>
</html>
