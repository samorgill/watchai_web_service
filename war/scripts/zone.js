/**
 * @author Samuel Orgill 15118035
 * NW5 Smartwatch Control of Environment
 * September 2016
 * 
 * Unimplemented zones feature, for future wrk
 */
function openZone(){
	window.open("Zones.html");
}



function showZones(){
	console.log('Onload is triggered');
	user = localStorage.getItem("user");
	$.ajax({
		url: 'GetAllZones?user5=' + user,
		type: 'GET',
		dataType: 'text',		
		
		success: function(data) {
			$('#zoneArea').empty();
			getAllZones(data)
		}});
}

/**
 * Function to get All Zones
 */

function getAllZones(data){
	var tr;	
	myData = $.parseJSON(data);
	
	console.log(myData[0].propertyMap.zone);
	
	 $( "#speed" ).selectmenu();
	 
	    $( "#files" ).selectmenu();
	 
	    $( "#number" )
	      .selectmenu()
	      .selectmenu( "menuWidget" )
	        .addClass( "overflow" );
	 
	    $( "#salutation" ).selectmenu();
	    
	    var prn = $('<fieldset/>');
	    
	    prn.append('<fieldset> <label for="speed">Select a speed</label> <select name="speed" id="speed">')
	    
	    for(var i = 0; i < myData.length; i++){
	    prn.append('<option>' + myData[i].propertyMap.zone + '</option>');
	    }
	    prn.append('</select> </fieldset>');
	    $('#resultScreen').append(prn);
	
	}

$(document).ready(function(){
	$("#zoneBtn").click(function(){
	
		user = localStorage.getItem("user");
		zone = $("#zone").val();	
		
		$.ajax({
			url: 'AddZone?user4=' + user + "&zone=" + zone,
			type: 'GET',
			dataType: 'json',
			error: function(){
				
				alert('error');
			}
		
		})
		
		alert('zone added');
		
	});
	
	
});

