<%-- 
    Document   : search
    Created on : Jan 29, 2021, 1:49:56 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css" integrity="sha512-HK5fgLBL+xu6dm/Ii3z4xhlSUyZgTT9tuc/hSrtw6uzJOvgRr2a9jyxxT1ely+B+xFAmJKVSTbpM/CuL7qxO8w==" crossorigin="anonymous" />
        <style>
            @import url('https://fonts.googleapis.com/css?family=Roboto');

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

            .question{
                display: grid;
                grid-template-columns: repeat(4,1fr);
                margin: 30px 150px;

            }
            .content{
                margin: 10px;
            }
            h2{
                text-align: center;
                font-size: 50px;
                color: #5161ce;
                font-weight: bold;
            }
            .question__content{
                margin: 10px 20px;
                padding: 10px;
                border: 1px solid white;
                border-radius: 5px;
                transition: .3s;
            }
            .question__content:hover{
                box-shadow:0 0 7px black;
                transition: .3s;
            }
            .question__content span{
                color: #5161ce;
                font-weight: bold;
            }
            button:focus{
                outline: none;
            }
            .button{
                padding: 8px;
                background: none;
                color: #5161ce;
                border: 2px solid #5161ce;
                border-radius: 5px;
            }
            .button:hover{
                border: 2px solid gray;
                background: #5161ce;
                color: white;
            }
            .delete{
                padding: 8px;
                background: red;
                color: white;
                border: 2px solid red;
                border-radius: 5px;
            }
            .pageNum{
                display: flex;
                justify-content: center;
            }

            .page{
                display: block;
                width: 20px;
                height: 20px;
                border: 1px solid black;
                border-radius: 50%; 
                margin-right: 10px;
                text-align: center;
                line-height: 20px;
                color: black;

            }
            .page:hover{
                color: white;
                background: black;
                text-decoration: none;
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
        <title>Search Page</title>
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
                    <li class="nav-item active">
                        <a class="nav-link" href="showAll"><i class="fa fa-search"></i>Find Question</a>
                    </li>
                    <li class="nav-item">
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
                <a href="logout">Logout</a>
            </div>
            <div style="display: flex;" class="right">
                <form class="form-inline ml-2" action="search" method="POST">
                    <input class="form-control mr-sm-2" name="txtSearch" type="search" placeholder="Search" aria-label="Search" value="${sessionScope.SEARCHVALUE}">
                    <button class="btn my-2 my-sm-0 btn__search" type="submit">Search</button>
                </form>
                <form class="form-inline ml-2" action="searchSubject" method="POST">
                    <c:if test="${not empty sessionScope.SUBJECTS}">
                        <select name="cbSubjects" class="form-control mr-sm-2">
                            <c:forEach var="subject" items="${sessionScope.SUBJECTS}">
                                <option <c:if test="${sessionScope.SUBJECTVALUE eq subject.key}">selected</c:if> >${subject.key}</option>
                            </c:forEach>

                        </select>
                        <button class="btn my-2 my-sm-0 btn__search" type="submit">Search</button>
                    </c:if>
                </form>
                <form class="form-inline ml-2" action="searchStatus" method="POST">
                    <select name="cbStatus" class="form-control mr-sm-2">
                        <option>activate</option>
                        <option>deactivate</option>
                    </select>
                    <button class="btn my-2 my-sm-0 btn__search" type="submit">Search</button>
                </form>
            </div>
        </nav>
        <form action="showAll">
            <input class="button" type="submit" value="Show all list" />
        </form>
        <div class="content">
            <c:if test="${not empty sessionScope.LISTQUEST}">
                <h2>${sessionScope.NAMESUBPAGE}</h2>
                <div class="question">
                    <c:forEach var="quest" items="${sessionScope.LISTQUEST}">
                        <form action="delete" method="POST">
                            <div class="question__content">
                                <span>Question:</span>
                                <p>${quest.question}</p>
                                <input type="hidden" name="txtQuest" value="${quest.question}" />
                                <p><span>option 1:</span> ${quest.op1}</p>
                                <p><span>option 2:</span> ${quest.op2}</p>
                                <p><span>option 3:</span> ${quest.op3}</p>
                                <p><span>option 4:</span> ${quest.op4}</p>
                                <p><span>answer:</span> ${quest.ans}</p>
                                <c:if test="${quest.status eq true}">
                                    <p><span>Status:</span> activate</p>
                                </c:if>
                                <c:if test="${quest.status eq false}">
                                    <p>Status: deactivate</p>
                                </c:if>
                                <c:if test="${quest.status eq false}">
                                    <input type="submit" class="delete" disabled value="Deleted" />
                                </c:if>
                                <c:if test="${quest.status eq true}">
                                    <input type="hidden" name="searchValue" value="${param.txtSearch}" />
                                    <input type="hidden" name="subject" value="${param.cbSubjects}" />
                                    <input type="hidden" name="status" value="${param.cbStatus}" />
                                    <input type="hidden" name="indexPage" value="${param.index}" />
                                    <input type="hidden" name="indexPageQuest" value="${param.indexQuest}" />

                                    <input type="submit" class="button" value="Delete" />
                                </c:if>
                            </div>
                        </form>
                    </c:forEach>
                </div>
            </c:if>
            <c:if test="${empty sessionScope.LISTQUEST}">
                <h1 class="text-center text-secondary">NO QUESTION IS MATCHED!</h1>
            </c:if>
            <c:if test="${not empty sessionScope.SEARCHVALUE}">
                <div class="pageNum">
                    <c:if test="${sessionScope.NUMPAGE > 1}">
                        <c:forEach var="page" begin="1" end="${sessionScope.NUMPAGE}">
                            <a class="page" href="search?index=${page}">${page}</a>
                        </c:forEach>
                    </c:if>
                </div>
            </c:if>
            <c:if test="${not empty sessionScope.SUBJECTVALUE}">
                <div class="pageNum">
                    <c:if test="${sessionScope.NUMPAGEQUEST > 1}">
                        <c:forEach var="pageQuest" begin="1" end="${sessionScope.NUMPAGEQUEST}">
                            <a class="page" href="searchSubject?indexQuest=${pageQuest}">${pageQuest}</a>
                        </c:forEach>
                    </c:if>
                </div>
            </c:if>
            <c:if test="${not empty sessionScope.STATUSVALUE}">
                <div class="pageNum">
                    <c:if test="${sessionScope.NUMPAGE > 1}">
                        <c:forEach var="page" begin="1" end="${sessionScope.NUMPAGE}">
                            <a class="page" href="searchStatus?index=${page}">${page}</a>
                        </c:forEach>
                    </c:if>
                </div>
            </c:if>
            <c:if test="${not empty sessionScope.SHOWALL}">
                <div class="pageNum">
                    <c:if test="${sessionScope.NUMPAGE > 1}">
                        <c:forEach var="page" begin="1" end="${sessionScope.NUMPAGE}">
                            <a class="page" href="showAll?index=${page}">${page}</a>
                        </c:forEach>
                    </c:if>
                </div>
            </c:if>
            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
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
