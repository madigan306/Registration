<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>


<SCRIPT LANGUAGE="JavaScript">
<!-- Original:  Russ Swift (rswift220@yahoo.com) -->

<!-- Begin;
function validateForm() {
	var invalid = " "; // Invalid character is a space
	var minLength = 6; // Minimum length
	var pw1 = document.myForm.password.value;
	var pw2 = document.myForm.password2.value;
	// check for a value in both fields.
	if (pw1 == '' || pw2 == '') {
		alert('Please enter your password twice.');
		return false;
	}
	// check for minimum length
	if (document.myForm.password.value.length < minLength) {
		alert('Your password must be at least ' + minLength + ' characters long. Try again.');
		return false;
	}
	// check for spaces
	if (document.myForm.password.value.indexOf(invalid) > -1) {
		alert("Sorry, spaces are not allowed.");
		return false;
	}
	
	if (pw1 != pw2) {
		alert ("You did not enter the same new password twice. Please re-enter your password.");
		return false;
	}

	var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	var address = document.myForm.email.value;
   if(reg.test(address) == false) {
      	alert('Invalid Email Address');
      	return false;
   }

   return true;
}
//  End -->
</script>

</head>
<body>

	<b>Registration</b>
      <form name=myForm action="http://localhost:8080/Registration/Registration" method="POST" onSubmit="return validateForm()">
	     <table>
			<tr>
			<td>UserName:</td>
			<td>
			<input type="text" name="userName" value="" maxlength="44" />
			</td>
			</tr>
			<tr>
			<td>Password:</td>
			<td>
			<input type="password" name="password" value="sony1234" maxlength="15" />
			</td>
			</tr>
			<tr>
			<td>Confirm Password:</td>
			<td>
			<input type="password" name="password2" value="sony1234" maxlength="15" />
			</td>
			</tr>
			<tr>
			<td>First Name:</td>
			<td>
			<input type="text" name="firstName" value="Mike" maxlength="44" />
			</td>
			</tr>
			<tr>
			<td>Last Name:</td>
			<td>
			<input type="text" name="lastName" value="Madigan" maxlength="44" />
			</td>
			</tr>
			<tr>
			<td>Email:</td>
			<td>
			<input type="text" name="email" value="a@b.com" maxlength="44" />
			</td>
			</tr>
			<tr>
			<td>
			<input type="submit" value="Submit" />
			</td>
			</tr>
		</table>
      </form>

</body>
</html>
