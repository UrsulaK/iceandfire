$(document).ready(function(){
	
	var search = function(){
		
		$("#content").empty();
		var baseUrl = "http://localhost:8080/iceandfire/houses/"
		var region = $( "#region option:selected" ).text();
		var paging = "/1/10";
		var url = baseUrl.concat(region, paging);
		
		$.get(url, function(data) {
			
			for(var i=0; i<data.length; i++){
				
				var tr = $("<tr/>");
				var td = $("<td/>");
				td.append($("<div class='lead' />").text(data[i].name));
				td.append($("<div />").text(data[i].coatOfArms));
				td.append($("<div class='text-muted' />").text(data[i].words));
				
				var td2 = $("<td/>")
				var swornMembers = data[i].swornMembers;
				for(var j=0; j<swornMembers.length; j++){
					td2.append($("<div class='text-muted' />").text( swornMembers[j]));
				}
				
				tr.append(td);
				tr.append(td2);
				$("#content").append(tr);
			}
		});
	};
	$("#search").on("click", search);
})