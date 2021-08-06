$(document).ready(function(){
    var selectedAccount = null;
	var selectedAcBalance = null;
    $('.deposit-modal-btn, .withdraw-modal-btn').click(function(){
		selectedAccount  = $(this).closest('.account-bar').find('#selectedAccountId').val();
		selectedAcBalance  = $(this).closest('.account-bar').find('#selectedBalance').val();
    });

	$('#withdraw-btn').click(function(){
            var amt = $(".withdraw-amount").val();
			if(!isNaN(amt)&&(parseFloat(selectedAcBalance)-parseFloat(amt))>=100){
				alert("good");
			}
			else{
				
			}

    });

	$('#deposit-btn').click(function(){
            var amt = $(".deposit-amount").val();

    });
});