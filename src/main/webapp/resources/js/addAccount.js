$(document).on('click',"#addAccount", function() {
	var user = $('#user').find(":selected").val();
	var accountType = $('#accountType').find(":selected").val();
	if (user == "") {
		$('#error').show();
		$("#error").text("Please select a user to add an Account.");
		setTimeout(function() {
			if ($('#error').length > 0) {
				$('#error').hide();
			}
		}, 3000);
	} else if (accountType == "") {
		$('#error').show();
		$("#error").text("Please select an Account Type to add an Account.");
		setTimeout(function() {
			if ($('#error').length > 0) {
				$('#error').hide();
			}
		}, 3000);
	} else {
		var accountData = {
				user_id: user,
				account_type_id: accountType
		};
		$.ajax({
			url: "/CityBank/openAccount",
			type: "POST",
            contentType: 'application/json',
			data: JSON.stringify(accountData),
			success : function(result) {
				if (result.statusText == "success") {
					$('#success').show();
					$('#success').text("User account added successfully.");
					setTimeout(function() {
						$('#success').hide();
					}, 5000);
				} else {
					$('#error').show();
					$("#error").text("User with same account Type already exists.");
					setTimeout(function() {
						if ($('#error').length > 0) {
							$('#error').hide();
						}
					}, 3000);
				}
			},
			error : function(result) {
				if (result.statusText == "success") {
					$('#success').show();
					$('#success').text("User account added successfully.");
					setTimeout(function() {
						$('#success').hide();
					}, 5000);
				} else {
					$('#error').show();
					$("#error").text("User with same account Type already exists.");
					setTimeout(function() {
						if ($('#error').length > 0) {
							$('#error').hide();
						}
					}, 3000);
				}
			}
		})
	}
});