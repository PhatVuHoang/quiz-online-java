<%-- 
    Document   : login
    Created on : Jan 29, 2021, 1:49:17 PM
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
            @import url('https://fonts.googleapis.com/css?family=Montserrat:400,500,600,700|Poppins:400,500&display=swap');
            *{
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                user-select: none;
            }
            .bg-img{
                background: url('https://wallpaperaccess.com/full/521111.jpg');
                height: 100vh;
                background-size: cover;
                background-position: center;
            }
            .bg-img:after{
                position: absolute;
                content: '';
                top: 0;
                left: 0;
                height: 100%;
                width: 100%;
                background: rgba(0,0,0,0.7);
            }
            .content{
                position: absolute;
                top: 50%;
                left: 50%;
                z-index: 999;
                text-align: center;
                padding: 60px 32px;
                width: 370px;
                transform: translate(-50%,-50%);
                background: rgba(255,255,255,0.04);
                box-shadow: -1px 4px 28px 0px rgba(0,0,0,0.75);
            }
            .content header{
                color: white;
                font-size: 33px;
                font-weight: 600;
                margin: 0 0 35px 0;
                font-family: 'Montserrat',sans-serif;
            }
            .field{
                position: relative;
                height: 45px;
                width: 100%;
                display: flex;
                background: rgba(255,255,255,0.94);
            }
            .field span{
                color: #222;
                width: 40px;
                line-height: 45px;
            }
            .field input{
                height: 100%;
                width: 100%;
                background: transparent;
                border: none;
                outline: none;
                color: #222;
                font-size: 16px;
                font-family: 'Poppins',sans-serif;
            }
            .space{
                margin: 16px 0;
            }
            .show{
                position: absolute;
                right: 13px;
                font-size: 13px;
                font-weight: 700;
                color: #222;
                display: none;
                cursor: pointer;
                font-family: 'Montserrat',sans-serif;
            }
            .qs{
                color: white;
            }
            .pass-key:valid ~ .show{
                display: block;
            }
            .pass{
                text-align: left;
                margin: 10px 0;
            }
            .pass a{
                color: white;
                text-decoration: none;
                font-family: 'Poppins',sans-serif;
            }
            .pass:hover a{
                text-decoration: underline;
            }
            .field input[type="submit"]{
                background: #3498db;
                border: 1px solid #2691d9;
                color: white;
                font-size: 18px;
                letter-spacing: 1px;
                font-weight: 600;
                cursor: pointer;
                font-family: 'Montserrat',sans-serif;
            }
            .field input[type="submit"]:hover{
                background: #2691d9;
            }
            .signup{
                margin: 10px 0;
            }
            .login{
                color: white;
                margin: 20px 0;
                font-family: 'Poppins',sans-serif;
            }
            .links{
                display: flex;
                cursor: pointer;
                color: white;
                margin: 0 0 20px 0;
            }
            .links i{
                font-size: 17px;
            }
            i span{
                margin-left: 8px;
                font-weight: 500;
                letter-spacing: 1px;
                font-size: 16px;
                font-family: 'Poppins',sans-serif;
            }
            .signup{
                font-size: 15px;
                color: white;
                font-family: 'Poppins',sans-serif;
            }
            .signup a{
                color: #3498db;
                text-decoration: none;
            }
            .signup a:hover{
                text-decoration: underline;
            }
        </style>
        <title>Login Page</title>
    </head>
    <body>
        <div class="bg-img">
            <div class="content">
                <header>Login</header>
                <form action="login" method="POST">
                    <p style="color: red;">${requestScope.INVALIDLOGIN}</p>
                    <div class="field">
                        <span class="fa fa-user"></span>
                        <input name="txtEmail" type="text" required placeholder="Email" value="${param.txtEmail}"/>
                    </div>
                    <div class="field space">
                        <span class="fa fa-lock"></span>
                        <input type="password" name="txtPassword" placeholder="Password" value="" />
                    </div>
                    <div class="field">
                        <input type="submit" value="LOGIN" />
                    </div>
                </form>
                <div class="signup">
                    <span class="qs">Don't have account?</span>
                    <a href="signup.jsp">Sign Up</a>
                </div>  
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        <script>
            const pass_field = document.querySelector(".pass-key");
            const showBtn = document.querySelector(".show");
            showBtn.addEventListener("click", function () {
                if (pass_field.type === "password") {
                    pass_field.type = "text";
                    showBtn.textContent = "HIDE";
                    showBtn.style.color = "#3498db";
                } else {
                    pass_field.type = "password";
                    showBtn.textContent = "SHOW";
                    showBtn.style.color = "#222";
                }
            });
        </script>
    </body>
</html>
