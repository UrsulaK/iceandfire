$(document).ready(function(){
	
	function notempty(value){
	    
	    var len = value.length;
	   
	    if (len < 1){
	        return false;
	    }else{
	        return true;
	    }
	}
	var searchHouses = function( event ){
		
		$("#content").empty();
		$("#pager").empty();
		$("#character").empty();

		var baseUrlSearch = "/service/houses/search/";
		var baseUrl = "";
		var name = $("#house").val();
		var region = $( "#region option:selected" ).text();
		var option = $( "#region option:selected" ).val();
		if(option != "default" && notempty(name)){
			var searchTerms = name + "/" + region;
			baseUrl = baseUrlSearch.concat(searchTerms);
		}
		else if(option != "default"){
			baseUrl = baseUrlSearch.concat("region/", region);
		}
		else if(notempty(name)){
			baseUrl = baseUrlSearch.concat("name/", name);
		}
		if(event.data.page != undefined){
			var pageCount = event.data.page;
		}else{
			var pageCount = 0;
		}
		var paging = "/"+ pageCount + "/10";
		var url = baseUrl.concat(paging);
		
		if(option != "default" || notempty(name)){
			$.get(url, function(data) {
				console.log(data);
				
				for(var i=0; i<data.houseList.length; i++){
					
					var tr = $("<tr/>");
					var td = $("<td/>");
					td.append($("<div class='lead' />").text(data.houseList[i].name));
					td.append($("<div />").text(data.houseList[i].coatOfArms));
					td.append($("<div class='text-muted' />").text(data.houseList[i].words));
					
					var td2 = $("<td/>")
					var swornMembers = data.houseList[i].swornMembers;
					Object.keys(swornMembers).forEach(function (key) {
						
						td2.append($("<div class='text-muted' id='"+ key + "' />").text(swornMembers[key]));
					});
					
					tr.append(td);
					tr.append(td2);
					$("#content").append(tr);

				}
				var page = data.page.number;
				var size = data.page.size;
				var totalPages = data.page.totalPages;
				for(var j=0; j < totalPages; j++){
					if(page == j){
						var li = $("<li class='active'/>");
					}else{
						var li = $("<li/>");
					}
					var id = "page_" + j;
					
					var anker = $("<a href='#' id='" + id + "' />").text(j+1);
					$("#pager").off("click", "#" + id , searchHouses);
					$("#pager").on("click", "#" + id ,{page: j}, searchHouses);
					li.append(anker);
					$("#pager").append(li);
				}
			});
		}
		
	};
	
	$("#searchHouses").click({page: 0}, searchHouses);
	
	var searchCharacter = function( event ){
		
		$("#characterContent").empty();
		$("#characterPager").empty();
		

		var baseUrlSearch = "/service/swornMembers/";
		
		var name = $("#character").val();
	
		if(notempty(name)){
			baseUrl = baseUrlSearch.concat( name);
		}
		if(event.data.page != undefined){
			var pageCount = event.data.page;
		}else{
			var pageCount = 0;
		}
		var paging = "/"+ pageCount + "/10";
		var url = baseUrl.concat(paging);
		
		if(notempty(name)){
			$.get(url, function(data) {
				console.log(data);
				
				for(var i=0; i<data.swornMemberList.length; i++){
					
					var tr = $("<tr/>");
					var td = $("<td/>");
					td.append($("<div class='lead' />").text(data.swornMemberList[i].name));
					td.append($("<div />").text(data.swornMemberList[i].gender));
					td.append($("<div class='text-muted' />").text(data.swornMemberList[i].culture));
					
					var td2 = $("<td/>")
					var aliases = data.swornMemberList[i].aliases;
					for(var j = 0; j < aliases.length; j++){
						td2.append($("<div class='text-muted' />").text(aliases[j]));
					}
					
					tr.append(td);
					tr.append(td2);
					$("#characterContent").append(tr);

				}
				var page = data.page.number;
				var size = data.page.size;
				var totalPages = data.page.totalPages;
				for(var j=0; j < totalPages; j++){
					if(page == j){
						var li = $("<li class='active'/>");
					}else{
						var li = $("<li/>");
					}
					var id = "charPage_" + j;
					
					var anker = $("<a href='#' id='" + id + "' />").text(j+1);
					$("#characterPager").off("click", "#" + id , searchCharacter);
					$("#characterPager").on("click", "#" + id ,{page: j}, searchCharacter);
					li.append(anker);
					$("#characterPager").append(li);
				}
			});
		}
		
	};
	
	$("#searchCharacter").click({page: 0}, searchCharacter);
	
	var load = function(){
		
		$("#response").empty();
		var importUrl = "/iceandfire/import";

		$.get(importUrl, function(data) {
			
			$("#response").append($("<div class='lead' />").text(data.message));
		});
	};
	
	$("#import").on("click", load);
	
	
	
})