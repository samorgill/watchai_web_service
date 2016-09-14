/**
 * 
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
	//var dat = myData[0].propertyMap.zone;
	
	//$('#resultScreen').append(dat);
	
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
	
		/*for(var i = 0; i < myData.length; i++){
			tr = $('<tr/>');
			
			tr.append(
			'<ul id="menu">' + '<li class="ui-state-disabled"><div>' + myData[i].propertyMap.zone + '</div></li>'
			+  '</ul>')
			
			tr.append('<td> </td>');
		
			$('#resultScreen').append(tr);
						
		}
*/
	}

/*$( function() {
    $( "#speed" ).selectmenu();
 
    $( "#files" ).selectmenu();
 
    $( "#number" )
      .selectmenu()
      .selectmenu( "menuWidget" )
        .addClass( "overflow" );
 
    $( "#salutation" ).selectmenu();
  } );*/
  


	



$(document).ready(function(){
	$("#zoneBtn").click(function(){
		//user = $("#user2").val();
		user = localStorage.getItem("user");
		zone = $("#zone").val();
		//$('#userArea').append(localStorage.getItem("user"));	
		
		$.ajax({
			url: 'AddZone?user4=' + user + "&zone=" + zone,
			type: 'GET',
			dataType: 'json',
			error: function(){
				
				alert('error');
			}
		
		})
		
		alert('zone added');
		//$('#logArea').slideUp();
		
	});
	
	
});

