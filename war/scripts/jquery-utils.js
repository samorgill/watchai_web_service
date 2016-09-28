
/**
 * @author Samuel Orgill 15118035
 * NW5 Smartwatch Control of Environment
 * September 2016
 * 
 * JQuery utilities for making ajax calls to the datastore and dynamically load data
 */

function openZone(){
	window.open("Zones.html");
}


/**
 * Function to Add thing to the database
 */
function addit(){
	
	var thing = $("#thing").val();
	var state = $("#state").val();
	var serial = $("#serial").val();
	var type = $("#type").val();
	var zone = $("#zone").val();
	var room = $("#room").val();

	$.ajax({
		url: 'AddThing?thing=' + thing + "&state=" + state + "&user=" +user + "&serial=" + serial 
		+ "&type=" + type + "&zone=" + zone + "&room=" + room,
		type: 'POST',
		dataType: 'text',
		error: function(data){
			onReg2();
				
			$('#doorName').val('');
			$('#serial').val('');
		}
	})
	
};


/*JQuery for inserting a user into the database*/
$(document).ready(function(){
	$("#register").click(function(){
		user = $("#user2").val();
		localStorage.setItem("user", user);
		var pass = $("#pass2").val();
		var email = $("#email").val();
		var phone = $("#phone").val();
		var hub = $("#hub").val
		
		init();
		
		$('#userArea').append(localStorage.getItem("user"));	
		
		$.ajax({
			url: 'Register?user2=' + user + "&pass2=" + pass + "&email=" + email + "&phone=" + phone + "&hub=" + hub,
			type: 'POST',
			dataType: 'json',
			error: function(){
				
				onReg();
			}
		
		})
		
		$('#logArea').slideUp();
		
	});
	
	
});

//OnReg gets all things
function onReg(){
	
	$("#add").show();
	
	user = localStorage.getItem("user");
	$.ajax({
		url: 'GetAllThings?user3=' + user,
		type: 'GET',
		dataType: 'text',		
		
		success: function(data) {
			$('#resultScreen').empty();
			getAllThings(data)
		}});
}

//Variation of above
function onReg2(){
	
	$.ajax({
		url: 'GetAllThings?user3=' + user,
		type: 'GET',
		dataType: 'text',		
		
		success: function(data) {
			$('#resultScreen').empty();
			getAllThings(data)
		}});
}

/**
 * Function to get users things from the database and load them dynamically with switches
 * @param data
 */

function getAllThings(data){
	var tr;	
	myData = $.parseJSON(data);
			
		for(var i = 0; i < myData.length; i++){
			tr = $('<tr/>');
			//tr.append('<input/>');
			
			if(myData[i].propertyMap.state=="Locked"){
			tr.append('<div id="togs' + i + '">' + '<label class="switch">' +
					  '<input type="checkbox" class="' + myData[i].propertyMap.thing +'" checked>' +
					  '<div class="slider round"></div>'
					+'</label>' + '</div>');
					
				tr.append("<td>" + myData[i].propertyMap.thing + 
						 " " + "</td>");
					
				$('#resultScreen').append(tr);
				
				var up = '.' + myData[i].propertyMap.thing;
				var dr = myData[i].propertyMap.thing;
				
				var st = "Unlocked";
				var sl = myData[i].propertyMap.serial;
				var ty = myData[i].propertyMap.type;
				var zo = myData[i].propertyMap.zone;
				var ro = myData[i].propertyMap.room;
					//myData[i].propertyMap.serial;
				
					clickAllThings(up, dr, st, sl, ty, zo, ro);
					
				
				} else if (myData[i].propertyMap.state=="Unlocked"){
					tr.append('<label class="switch">' +
							  '<input type="checkbox" class="' + myData[i].propertyMap.thing +'" >' +
							  '<div class="slider round"></div>'
							+'</label>');
							
				tr.append("<td>" + myData[i].propertyMap.thing + 
						 " " + "</td>");
				
		
				$('#resultScreen').append(tr);
				
				var up = '.' + myData[i].propertyMap.thing;
				var dr = myData[i].propertyMap.thing;
				var st = "Locked";
				var sl = myData[i].propertyMap.serial;
				var ty = myData[i].propertyMap.type;
				var zo = myData[i].propertyMap.zone;
				var ro = myData[i].propertyMap.room;
				
				clickAllThings(up, dr, st, sl, ty, zo, ro);
				
		}
	}
}



/**
 * Sets switches as clickable and then updates the database when a switch is clicked
 * 
 * @param up
 * @param dr
 * @param st
 * @param sl
 * @param ty
 * @param zo
 * @param ro
 */
function clickAllThings(up, dr, st, sl, ty, zo, ro){
	
	$(up).unbind("click");
	
	$(up).on('click', function() { 
		
		sendnew(dr, st);
		
		user = localStorage.getItem("user");
		$.ajax({
			url: 'UpdateThing?thing3=' + dr + "&state=" + st + "&user4=" + user + "&serial2=" + sl 
			+ "&type2=" + ty + "&zone2=" + zo + "&room2=" + ro,
			type: 'POST',
			dataType: 'text',
			success:function(data){
								
				$.ajax({
					url: 'GetAllThings?user3=' + user,
					type: 'GET',
					dataType: 'text',		
					
					success: function(theData) {
						$('#resultScreen').empty();
						getAllThings(theData);
					}});
			}	
		})
	});
}


/**
 * Websockets
 */

//Create a client instance, local or remote
client = new Paho.MQTT.Client("m21.cloudmqtt.com", 37781,"lapwebht" + parseInt(Math.random() * 100, 10)); 
clientLocal = new Paho.MQTT.Client("192.168.0.30", 9001,"Web App " + parseInt(Math.random() * 100, 10));


//set callback handlers
client.onConnectionLost = onConnectionLost;
client.onMessageArrived = onMessageArrived;
var options = {
useSSL: true,
userName: "xcihlzki",
password: "7wph1kJER7Xh",
onSuccess:onConnect,
onFailure:doFail
}

//Initialises client
function init() {
client.connect(options);
}

//Subscribes to users topics
function onConnect() {
console.log("onConnect");
client.subscribe("#");
}

function doFail(e){
console.log("fail");
}


function onConnectionLost(responseObject) {
if (responseObject.errorCode !== 0) {
console.log("onConnectionLost:"+responseObject.errorMessage);
}
}

//Toasts message received.
function onMessageArrived(message) {
console.log("onMessageArrived:"+message.payloadString);
var str = message.payloadString;
window.alert(str);

}

/**
 * Sends new message to subscribers
 * 
 * @param dr
 * @param st
 */
function sendnew(dr, st){
	
	message = new Paho.MQTT.Message(st);
	message.destinationName = user + "/" + dr;
	client.send(message); 
}
