/**
 * 
 *//*
$(function() 
{
	
	$("#registerForm").validate({
		rules : {
			username :{
				required:true
			},
		
			firstname : {
				required : true
			},
			email : {
				required : true,
				email : true
			},
			
			phonenumber : {
				required : true,
				minlength : 10,
				maxlength : 10
			},
			password : {
				required : true,
				minlength : 5,
				maxlength : 15
			},
			
			country : {
				required : true
			},
			
		},
		messages : {
			firstname : {
				required : "Please enter your name"
			},
			username : {
				required : "Please enter your username"
			},
			email : {
				required : "Please enter your email",
				email : "Please enter valid email"
			},
			
			
			phonenumber : {
				required : "Please enter phone",
				minlength : "Please enter valid 10 digit mobile number",
				maxlength : "Please enter valid 10 digit mobile number"
			},
			password : {
				required : "Please enter password",
				minlength : "Your password must between 5 and 15 characters",
				maxlength : "Your password must between 5 and 15 characters"
			},
			
			country : {
				required : "Please enter your country"
			}
		},
		submitHandler: function(form) {
			form.submit();
		}
	});
});*/