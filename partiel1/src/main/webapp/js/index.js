const API_URL = "http://localhost:8081/partiel1/api"; // Root API URL

function ibanToPaperFormat(iban) {
	return formatIban(iban, " ");
}

function ibanToElectronicFormat(iban) {
	return formatIban(iban, "");
}

function formatIban(iban, separator) {
	let bban = iban.bban;
	let result = iban.country + iban.key;
	result += separator + bban.bank;
	result += separator + bban.counter;
	result += separator + bban.account;
	result += separator + bban.key;
	return result;
}

$(function() {

	$("#GETLAST, #GETALL").click(function() {
		
		$("#TRANSFERS").empty(); // Clear content
		
		let endpoint = (this.id === "GETLAST" ? "/last" : "/all");
		
		$.getJSON(API_URL + "/bankTransfer" + endpoint, function(json){
	    	let items = [];
	    	$.each(json, function(key, element) {
	    		let li = "<li>Request date: " + element.requestDate;
	    		li += ", Amount: " + element.amount;
	    		li += ", Execution date: " + element.executionDate;
	    		li += ", Origin IBAN: " + ibanToElectronicFormat(element.origin);
	    		li += ", Destination IBAN: " + ibanToElectronicFormat(element.destination);
	    		items.push(li + "</li>");
	    	});
	    	
	    	$("<ul/>", {
	    		html: items.join("")
	    	}).appendTo("#TRANSFERS");
	    });
	});
});
