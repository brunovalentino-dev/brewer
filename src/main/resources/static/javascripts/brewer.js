$(function() {
	var decimal = $('.js-decimal');
	decimal.maskMoney({ decimal: ',', thousands: '.' });
	
	var integer = $('.js-integer');
	integer.maskMoney({ precision: 0, thousands: '.' });
});