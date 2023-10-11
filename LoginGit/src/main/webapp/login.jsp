<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" import="com.login.Globals" %>
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
					<h2 class="heading-section">Eduinq Login</h2>
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="col-md-6 col-lg-4">
					<div class="login-wrap p-0">
		      	<h3 class="mb-4 text-center">Have an account?</h3>
		      	<div class="alert-container">
		      	</div>
		      	<form action="login" class="logform" method="post">
		      	<input type="hidden" name="alert" id="alert" value="${alert}" />
		      		<div class="form-group">
		      			<input name="user" id="username-field" type="text" class="form-control" placeholder="Username or Email" required>
		      		</div>
	            <div class="form-group">
	              <input name="pass" id="password-field" type="password" class="form-control" placeholder="Password" autocomplete="off" required>
	              <span toggle="#password-field" class="fa fa-fw fa-eye field-icon toggle-password"></span>
	            </div>
	            <div class="form-group">
	            	<button type="submit" class="form-control btn btn-primary submit px-3">Sign In</button>
	            </div>
	            <div class="form-group d-md-flex">
	            	<div class="w-50">
		            	<label class="checkbox-wrap checkbox-primary">Remember Me
									  <input name="check" type="checkbox" checked>
									  <span class="checkmark"></span>
									</label>
								</div>
								<div class="w-50 text-md-right">
									<a href="passReset.jsp" style="color: #fff">Forgot Password</a>
								</div>
	            </div>
	          </form>
	          <p class="w-100 text-center">&mdash; Or Sign In With &mdash;</p>
	          <div class="social d-flex text-center">
	          	<a href="#" class="px-2 py-2 mr-md-1 rounded"><span class="ion-logo-whatsapp mr-2"></span> WhatsApp</a>
	          	<a href=" https://accounts.google.com/o/oauth2/auth?scope=profile&redirect_uri=${Globals.Website}/Login/login/google&response_type=code&client_id=${Globals.Client_ID}&approval_prompt=force" class="px-2 py-2 mr-md-1 rounded"><span class="ion-logo-google mr-2"></span> Google</a>
			  </div>
	          	<p class="w-100 text-center" style="font-size: 12px;"> Don't have an account? <a href="signup.jsp" style="color: #fbceb5;">Sign-up instead</a></p>
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

