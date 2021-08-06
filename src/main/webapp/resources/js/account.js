$(document).ready(function(){
    var selectedAccount = null;
	var selectedAcBalance = null;
	
	$("#logOut").removeClass("dropdown-menu");
	
    $('.deposit-modal-btn, .withdraw-modal-btn').click(function(){
		selectedAccount  = $(this).closest('.account-bar').find('#selectedAccountId').val();
		selectedAcBalance  = $(this).closest('.account-bar').find('#selectedBalance').val();
    });

	$('#withdraw-btn').click(function(){
            var amt = $(".withdraw-amount").val();
			if(!isNaN(amt)&&(parseFloat(selectedAcBalance)-parseFloat(amt))>=100 && parseFloat(amt)>0){
				$(".withdraw-amount").val(amt);
				$(".withdraw-acc").val(selectedAccount);
				$(".form-withdraw").submit();
			}
			else{
				$(".withdraw-error").show();
				setTimeout(function(){ 
					$(".withdraw-error").hide();
				}, 3000);
			}

    });

	$('#myDepositModal, #myWithdrawModal').on('hidden.bs.modal', function () {
		 $(".deposit-amount").val("");
	 	 $(".withdraw-amount").val("");
	});

	$('#deposit-btn').click(function(){
            var amt = $(".deposit-amount").val();
			if(!isNaN(amt)&& parseFloat(amt)>0){
				$(".deposit-amount").val(amt);
				$(".deposit-acc").val(selectedAccount);
				$(".form-deposit").submit();
			}
			else{
				$(".deposit-error").show();
				setTimeout(function(){ 
					$(".deposit-error").hide();
				}, 3000);
			}

    });
});