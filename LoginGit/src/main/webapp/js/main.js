(function($) {

	"use strict";

	var fullHeight = function() {

		$('.js-fullheight').css('height', $(window).height());
		$(window).resize(function(){
			$('.js-fullheight').css('height', $(window).height());
		});

	};
	fullHeight();

	$(".toggle-password").click(function() {
		$(this).toggleClass("fa-eye fa-eye-slash");
		var input = $($(this).attr("toggle"));
		if (input.attr("type") == "password") {
			input.attr("type", "text");
		} else {
			input.attr("type", "password");
		}
	});

	$(".logform").submit(function(event) {
		event.preventDefault(); // Prevent form submission
		var passwordInput = $("#password-field").val();
		var passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^\da-zA-Z]).{8,}$/;

		if (!passwordRegex.test(passwordInput)) {
			// Password does not meet the requirements
			alert("Password must be at least 8 characters long and contain uppercase, lowercase letters, a number and special characters.");
			return;
		}

		$(".logform")[0].submit();
	});
	
	$(".regform").submit(function(event) {
		event.preventDefault(); // Prevent form submission
		var password = $("#password-field1").val();
		var confirmPassword = $("#confirm-password-field").val();
		var passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^\da-zA-Z]).{8,}$/;

		if (!passwordRegex.test(password)) {
			// Password does not meet the requirements
			alert("Password must be at least 8 characters long and contain uppercase, lowercase letters, a number and special characters.");
			return;
		}
		
		if (password !== confirmPassword) {
            alert("Password do not match");
			return;
        } 

		$(".regform")[0].submit();
	});
	
	$(document).ready(function() {
	    var alertMessage = $("#alert").val();
	    
	    switch (alertMessage) {
	        case "duplicate":
	            showAlert('Duplicate Entry!', 'Username, Email or Phone number already registered.', 'warning');
	            break;
	        case "error":
	            showAlert('Error!', 'An internal error occurred. Try again.', 'danger');
	            break;
	        case "notOtp":
	            showAlert('Wrong Otp!', 'Try again.', 'warning');
	            break;
	        case "notLogin":
	            showAlert('Login Denied!', 'Wrong credentials. Try again.', 'warning');
	            break;
	        case "mail":
	            showAlert('Email Sent!', 'Please check your mails.', 'success');
	            break;
	        case "login":
	            showAlert('Login Success!', 'You\'ve successfully logged in.', 'success');
	            break;
	        case "expired":
	            showExpiredOTPAlert();
	            break;
	        case "expiredLink":
	            showExpiredLinkAlert();
	            break;
	        case "registered":
	            showAlert('Registration Success!', 'Account registered successfully.', 'success');
	            break;
	        case "notExist":
	            showAlert('User Not Found!', 'This username or email is not registered.', 'warning');
	            break;
	        case "notMail":
	            showAlert('Mail Not Sent!', 'Unable to send Mail. Check ypur connection and try again.', 'warning');
	            break;
	        case "invalid":
	            showAlert('Invalid Link!', 'The link you\'ve used is invalid. Check again', 'warning');
	            break;
	        case "changed":
	            showAlert('Changed!', 'Your password has been changed successfully.', 'success');
	            break;
	    }
	
		function showAlert(title, message, alertType) {
		    var alertDiv = '<div class="alert alert-' + alertType + ' alert-dismissible fade show" role="alert">' +
		                   '<strong>' + title + '</strong> ' + message +
		                   '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
		                   '<span aria-hidden="true">&times;</span>' +
		                   '</button>' +
		                   '</div>';
		    $(".alert-container").append(alertDiv);
		}
		
		function showExpiredOTPAlert() {
		    var email = $("#email").val();
		    var user = $("#user").val();
		    var pass = $("#pass").val();
		    var phone = $("#phone").val();
		    var userid = $("#userid").val();
		    var alertDiv = '<div class="alert alert-warning alert-dismissible fade show" role="alert">' +
		                   '<strong>Otp expired!</strong> ' +
		                   '<a href="verifyOTP?email=' + email + '&user=' + user + '&pass=' + pass + '&phone=' + phone + '&userid=' + userid + '" class="alert-link">Request another?</a>' +
		                   '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
		                   '<span aria-hidden="true">&times;</span>' +
		                   '</button>' +
		                   '</div>';
		    $(".alert-container").append(alertDiv);
		}
		
		function showExpiredLinkAlert() {
		    var email = $("#email").val();
		    var userid = $("#userid").val();
		    var alertDiv = '<div class="alert alert-warning alert-dismissible fade show" role="alert">' +
		                   '<strong>Link expired!</strong> ' +
		                   '<a href="passReset?email=' + email + '&userid=' + userid + '" class="alert-link">Request another?</a>' +
		                   '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
		                   '<span aria-hidden="true">&times;</span>' +
		                   '</button>' +
		                   '</div>';
		    $(".alert-container").append(alertDiv);
		}
	});

	
	//T&C popup
	$(".terms-arrow").click(function() {
		$('#myModal').modal('show');
	});
	
	 // OTP input handling code
	$(function(){
		$('#otp input').joinInputs();
	});
	
})(jQuery);
