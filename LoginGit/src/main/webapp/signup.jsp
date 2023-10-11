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
                    <h2 class="heading-section">Eduinq Registration</h2>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-md-6 col-lg-4">
                    <div class="login-wrap p-0">
                        <h3 class="mb-4 text-center">Create an Account</h3>
                     <div class="alert-container">
                     </div>
                        <form action="signup" class="regform" method="post">
                            <input type="hidden" name="alert" id="alert" value="${alert}" />
                            <div class="form-group">
                                <input name="user" id="username-field" type="text" class="form-control" placeholder="Username" required>
                            </div>
                            <div class="form-group">
                                <input name="mail" id="email-field" type="email" class="form-control" placeholder="Email" required>
                            </div>
                            <div class="form-group">
								<input name="phone" id="phone-number-field" type="tel" class="form-control" placeholder="Phone Number" pattern="[0-9]{10}" required>
							</div>
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
                            <div class="form-group d-md-flex">
                                <div class="w-100 text-md-center">
                                    <label class="checkbox-wrap checkbox-primary">I agree to the terms and conditions
                                        <input name="check" type="checkbox" required>
                                        <span class="checkmark"></span>
                                    </label>
                                </div>
                                
                                <!-- Trigger the modal with a button -->
								<div class="terms-arrow">
									<span class="fa fa-arrow-up arrow-icon"></span>
								</div>
								
								<!-- Modal -->
								<div id="myModal" class="modal fade" role="dialog">
								  <div class="modal-dialog">
								
								    <!-- Modal content -->
								    <div class="modal-content">
								      <div class="modal-header">
								        <h5 class="modal-title text-md-center">Terms and Conditions</h5>
								        <button type="button" class="close" data-dismiss="modal">&times;</button>
								      </div>
								      <div class="modal-body">
								        <p class="w-100 text-md-center text-dark">
										Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus nec ullamcorper lectus. 
										Suspendisse at fermentum tellus, vel tempor arcu. In nec ipsum a velit ullamcorper sodales.
									  </p>
									  <p class="w-100 text-md-center text-dark">
										Fusce et metus ullamcorper, blandit mauris vel, fringilla nisi. Sed nec arcu eu nibh 
										cursus dictum. Quisque bibendum rhoncus mi ac venenatis.
									  </p>
								      </div>
								      <div class="modal-footer">
								        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
								      </div>
								    </div> 
								
								  </div>
								</div>
								
								
								
                            </div>
                        </form>
                        <p class="w-100 text-center" style="font-size: 12px;"> Have an account? <a href="login.jsp" style="color: #fbceb5;">Sign-in instead</a></p>
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
