/**
 * 
 */

function openZone(){
	window.open("Zones.html");
}

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
