<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

<head>
    <title>Eduinq project</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/4.5.6/css/ionicons.min.css">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="css/style.css">

</head>

<body class="img js-fullheight" style="background-image: url(images/bg.jpg);">
    <section class="ftco-section">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6 text-center mb-5">
                    <h2 class="heading-section">Enter a new Password</h2>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-md-6 col-lg-4">
                    <div class="login-wrap p-0">
                     <div class="alert-container">
                     </div>
                        <form action="passReset" class="regform" method="get">
                            <input type="hidden" name="alert" id="alert" value="${alert}" />
                            <input type="hidden" name="userid" id="userid" value="<%= request.getParameter("userid") %>" />
                             <input type="hidden" name="email" id="email" value="<%= request.getParameter("email") %>" />
                            <div class="form-group">
                                <input name="pass" id="password-field1" type="password" class="form-control" placeholder="Password" autocomplete="off" required>
                                <span toggle="#password-field1" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                            </div>
                            <div class="form-group">
                                <input name="conpass" id="confirm-password-field" type="password" class="form-control" placeholder="Confirm Password" autocomplete="off" required>
                                <span toggle="#confirm-password-field" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="form-control btn btn-primary submit px-3">Sign Up</button>
                            </div>
                        </form>
                        <p class="w-100 text-center" style="font-size: 12px;">Remember your password? <a href="login.jsp" style="color: #fbceb5;">Sign in</a></p>
                    <p class="w-100 text-center" style="font-size: 12px;">Don't have an account? <a href="signup.jsp" style="color: #fbceb5;">Sign up</a></p>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <script src="js/jquery.min.js"></script>
    <script src="js/popper.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/main.js"></script>

</body>

</html>
