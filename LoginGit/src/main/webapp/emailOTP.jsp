
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
                <h2 class="heading-section">OTP Verification</h2>
            </div>
        </div>
        <div class="row justify-content-center">
            <div class="col-md-6 col-lg-4">
                <div class="login-wrap p-0">
                    <div class="mb-4 text-center">
                    <div class="alert-container">
                    </div>
                        <h5 class="text-white">Please enter the one-time password <br> to verify your account</h5>
                        <div>
                            <span>A code has been sent to</span> <small>${email}</small>
                        </div>
                        <form action="verifyOTP" method="post"> <!-- Add the form tag here -->
                        
                        		<input type="hidden" name="email" id="email" value="${email}" />
	                            <input type="hidden" name="user" id="user" value="${user}" />
	                            <input type="hidden" name="pass" id="pass" value="${pass}" />
	                            <input type="hidden" name="phone" id="phone" value="${phone}" />
	                            <input type="hidden" name="userid" id="userid" value="${userid}" />
	                            <input type="hidden" name="alert" id="alert" value="${alert}" />
                        
                            <div id="otp" class="inputs d-flex flex-row justify-content-center mt-2">
                                <div class="form-group">
                                    <input class="m-2 text-center form-control rounded" type="text" name="first" id="first" maxlength="1" />
                                </div>
                                <div class="form-group">
                                    <input class="m-2 text-center form-control rounded" type="text" name="second" id="second" maxlength="1" />
                                </div>
                                <div class="form-group">
                                    <input class="m-2 text-center form-control rounded" type="text" name="third" id="third" maxlength="1" />
                                </div>
                                <div class="form-group">
                                    <input class="m-2 text-center form-control rounded" type="text" name="fourth" id="fourth" maxlength="1" />
                                </div>
                                <div class="form-group">
                                    <input class="m-2 text-center form-control rounded" type="text" name="fifth" id="fifth" maxlength="1" />
                                </div>
                                <div class="form-group">
                                    <input class="m-2 text-center form-control rounded" type="text" name="sixth" id="sixth" maxlength="1" />
                                </div>
                            </div>
                            <div class="mt-4 form-group">
                                <button type="submit" class="form-control btn btn-primary validate px-3">Validate</button>
                            </div>
                        </form> <!-- Close the form tag here -->
                    </div>
                     <div class="content d-flex justify-content-center align-items-center">
                         <span>Didn't get the code? <a href="verifyOTP?email=${email}&user=${user}&pass=${pass}&phone=${phone}&userid=${userid}" class="text-decoration-none ms-3">Resend</a></span>
                     </div>
                </div>
            </div>
        </div>
    </div>
</section>


    <script src="js/jquery.min.js"></script>
    <script src="js/popper.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/main.js"></script>
	<script src="js/join_inputs.jquery.min.js"></script>

</body>

</html>
