/**
 * @author Samuel Orgill 15118305
 * @version 5
 * JQuery utils
 */

function openZone(){
	window.open("Zones.html");
}

/*JQuery for inserting a door into the database
$(document).ready(function(){
	$("#add").click(function(){
		var doorName = $("#doorName").val();
		var state = $("#state").val();
		var serial = $("#serial").val();
		
		//var user = document.getElementById('userArea').textContent;
		
		
		//1-dot-projectbabywatch.appspot.com/InsertState?doorName=Back&state=Unlocked&user=CarloHome
		$.ajax({
			url: 'InsertState?doorName=' + doorName + "&state=" + state + "&user=" +user + "&serial=" + serial,
			type: 'POST',
			dataType: 'text',
			error: function(data){
				
				

			}
		})
			
	});
	onReg2();
});


function updateFunction(data){
	var tr;	
	myData = $.parseJSON(data);


	$('#resultScreen').append(myData);
	
	
	alert(myData.propertyMap.doorName + " has been added");
}


*/

function addit(){
	
	var thing = $("#thing").val();
	var state = $("#state").val();
	var serial = $("#serial").val();
	var type = $("#type").val();
	var zone = $("#zone").val();
	var room = $("#room").val();
	//var user = document.getElementById('userArea').textContent;
	
	
	//1-dot-projectbabywatch.appspot.com/InsertState?doorName=Back&state=Unlocked&user=CarloHome
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







/* JS for registering */

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


function onReg(){
	
	$("#add").show();
	
	//user = $("#user2").val();
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

//function to build buttons per user
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



//Function to update door state in the DB when toggle clicked

function clickAllThings(up, dr, st, sl, ty, zo, ro){
	
	$(up).unbind("click");
	
	$(up).on('click', function() { 
		
	
		sendnew(dr, st);
		
		//user = $("#user2").val();
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




//Create a client instance
client = new Paho.MQTT.Client("m21.cloudmqtt.com", 37781,"lapwebht" + parseInt(Math.random() * 100, 10)); 
//client = new Paho.MQTT.Client("192.168.0.30", 9001,"Web App " + parseInt(Math.random() * 100, 10));

//Example client = new Paho.MQTT.Client("ws://m11.cloudmqtt.com", 37781, "web_" + parseInt(Math.random() * 100, 10));

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

function init() {
client.connect(options);
}

//called when the client connects
function onConnect() {
//Once a connection has been made, make a subscription and send a message.
console.log("onConnect");
client.subscribe("#");
/*message = new Paho.MQTT.Message("Hello from the Watch");
message.destinationName = "/test";
client.send(message); */
}

function doFail(e){
console.log("fail");
}

//called when the client loses its connection
function onConnectionLost(responseObject) {
if (responseObject.errorCode !== 0) {
console.log("onConnectionLost:"+responseObject.errorMessage);
}
}

//called when a message arrives
function onMessageArrived(message) {
console.log("onMessageArrived:"+message.payloadString);
var str = message.payloadString;
window.alert(str);

}

function sendnew(dr, st){
	
	message = new Paho.MQTT.Message(st);
	message.destinationName = user + "/" + dr;
	client.send(message); 
}



/** This is old code that returned all the doors when there were no uses
 * scheduled for deletion**/

/*
function onLoad(){
	
	
	$.ajax({
		url: 'GetAllDoors',
		type: 'GET',
		dataType: 'text',		
		
		success: function(data) {
			$('#resultScreen').empty();
			getAllDyn(data)
		}});
}


//function to build buttons
function getAllDyn(data){
	var tr;	
	myData = $.parseJSON(data);
	
		
		for(var i = 0; i < myData.length; i++){
			tr = $('<tr/>');
			//tr.append('<input/>');
			
			if(myData[i].propertyMap.state=="Locked"){
			tr.append('<div id="togs' + i + '">' + '<label class="switch">' +
					  '<input type="checkbox" class="' + myData[i].propertyMap.doorName +'" checked>' +
					  '<div class="slider round"></div>'
					+'</label>' + '</div>');
					
		tr.append("<td>" + myData[i].propertyMap.doorName + 
				 " " + "</td>");
			
		$('#resultScreen').append(tr);
		
		var up = '.' + myData[i].propertyMap.doorName;
		var dr = myData[i].propertyMap.doorName;
		
		var st = "Unlocked";
		
			upTog(up, dr, st);
			
		
		} else if (myData[i].propertyMap.state=="Unlocked"){
			tr.append('<label class="switch">' +
					  '<input type="checkbox" class="' + myData[i].propertyMap.doorName +'" >' +
					  '<div class="slider round"></div>'
					+'</label>');
					
		tr.append("<td>" + myData[i].propertyMap.doorName + 
				 " " + "</td>");
		

		$('#resultScreen').append(tr);
		
		var up = '.' + myData[i].propertyMap.doorName;
		var dr = myData[i].propertyMap.doorName;
		var st = "Locked";
		
		upTog(up, dr, st);
		
		}
	}
}

// Function to update door state in the DB when toggle clicked

function upTog(up, dr, st){
	
	$(up).unbind("click");
	
	$(up).on('click', function() { 
		
		$.ajax({
			url: 'UpdateDoor?doorName3=' + dr + "&state=" + st,
			type: 'POST',
			dataType: 'text',
			success:function(data){
								
				$.ajax({
					url: 'GetAllDoors',
					type: 'GET',
					dataType: 'text',		
					
					success: function(theData) {
						$('#resultScreen').empty();
						getAllDyn(theData);
					}});
			}	
		})
	});
}

*/