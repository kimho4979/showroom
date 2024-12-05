function comma(num){
    var len, point, str; 
       
    num = num + ""; 
    point = num.length % 3 ;
    len = num.length; 
   
    str = num.substring(0, point); 
    while (point < len) { 
        if (str != "") str += ","; 
        str += num.substring(point, point + 3); 
        point += 3; 
    } 
     
    return str;
}

			         

function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}



function getNextDate(bunChk){
	
	var date = new Date(); 
	var dayIndex = date.getDay();
	if(bunChk == 'N'){
		//절화
		if(dayIndex == 0 || dayIndex == 2 || dayIndex == 4){
			
			date.setDate(date.getDate()+1);
		}else if (dayIndex == 1 || dayIndex == 3 || dayIndex == 6){
			
			date.setDate(date.getDate()+2);
		}else{
			
			date.setDate(date.getDate()+3);
		}
	}else if(bunChk == 'Y'){
		//난
		if(dayIndex == 0 || dayIndex == 3){
			
			date.setDate(date.getDate()+1);
		}else if (dayIndex == 6 || dayIndex == 2){
			
			date.setDate(date.getDate()+2);
		}else if (dayIndex == 5 || dayIndex == 1){
			
			date.setDate(date.getDate()+3);
		}else{
			date.setDate(date.getDate()+4);
		}
	}else{
		//관엽 
		if(dayIndex == 6){
			date.setDate(date.getDate()+6);
		}else if (dayIndex == 4 || dayIndex == 0){
			date.setDate(date.getDate()+5);
		}else if (dayIndex == 5 || dayIndex == 1){
			date.setDate(date.getDate()+4);
		}else if(dayIndex == 2){
			date.setDate(date.getDate()+3);
		}else{
			date.setDate(date.getDate()+2);
		}
		
	}
	
	
	var year = date.getFullYear(); 
	var month = new String(date.getMonth()+1); 
	var day = new String(date.getDate()); 
	
	if(month.length == 1){ 
	  month = "0" + month; 
	} 
	
	if(day.length == 1){ 
	   day = "0" + day; 
	} 
		
	$("#chulDate").datepicker("setDate",year+"-"+month+"-"+day);
}


function setSearchDateInit(dayCnt){
	
	var endDate = new Date(); 
	var strDate = new Date();
	
	strDate.setDate(endDate.getDate()-dayCnt);
	
	var strYear = strDate.getFullYear(); 
	var strMonth = new String(strDate.getMonth()+1); 
	var strDay = new String(strDate.getDate()); 
	
	if(strMonth.length == 1){ 
		strMonth = "0" + strMonth; 
	} 
	
	if(strDay.length == 1){ 
		strDay = "0" + strDay; 
	} 
		
	$("#startDate").datepicker("setDate",strYear+"-"+strMonth+"-"+strDay);
	
	
	var endYear = endDate.getFullYear(); 
	var endMonth = new String(endDate.getMonth()+1); 
	var endDay = new String(endDate.getDate()); 
	
	if(endMonth.length == 1){ 
		endMonth = "0" + endMonth; 
	} 
	
	if(endDay.length == 1){ 
		endDay = "0" + endDay; 
	} 
		
	$("#endDate").datepicker("setDate",endYear+"-"+endMonth+"-"+endDay);
	
	fn_search();
	
}

function setSearchMonthInit(monthCnt){
	
	var endDate = new Date(); 
	var strDate = new Date();
	
	strDate.setMonth(endDate.getMonth()-monthCnt);
	
	var strYear = strDate.getFullYear(); 
	var strMonth = new String(strDate.getMonth()); 
	
	console.log(new Date(strYear, strMonth, 1));
		
	$("#startMonth").datepicker("setDate",new Date(strYear, strMonth, 1));
	
	var endYear = endDate.getFullYear(); 
	var endMonth = new String(endDate.getMonth()); 
	
		
	$("#endMonth").datepicker("setDate",new Date(endYear, endMonth, 1));
	
	var currentMonth = new String(endDate.getMonth()+1); 
	if(currentMonth.length == 1){ 
		currentMonth = "0" + currentMonth; 
	} 
	
	$("#stYear").val(endYear);
	$("#selMonth").prop("value",currentMonth);
	
	fn_search();
	
}


function customInputCheck(){
	
	$("#customInput").click();
	
}






//출처 : http://tonks.tistory.com/79 
//에러가 나온다면, 여기에 댓글을 남겨주세요. 


/* sortingNumber() : 숫자인 실수만으로 되어있을 때, 적용될 함수 */ 

function sortingNumber( a , b ){  

     if ( typeof a == "number" && typeof b == "number" ) return a - b; 

     // 천단위 쉼표와 공백문자만 삭제하기.  
     var a = ( a + "" ).replace( /[,\s\xA0]+/g , "" ); 
     var b = ( b + "" ).replace( /[,\s\xA0]+/g , "" ); 

     var numA = parseFloat( a ) + ""; 
     var numB = parseFloat( b ) + ""; 

     if ( numA == "NaN" || numB == "NaN" || a != numA || b != numB ) return false; 

     return parseFloat( a ) - parseFloat( b ); 
} 


/* changeForSorting() : 문자열 바꾸기. */ 

function changeForSorting( first , second ){  

     // 문자열의 복사본 만들기. 
     var a = first.toString().replace( /[\s\xA0]+/g , " " ); 
     var b = second.toString().replace( /[\s\xA0]+/g , " " ); 

     var change = { first : a, second : b }; 

     if ( a.search( /\d/ ) < 0 || b.search( /\d/ ) < 0 || a.length == 0 || b.length == 0 ) return change; 

     var regExp = /(\d),(\d)/g; // 천단위 쉼표를 찾기 위한 정규식. 

     a = a.replace( regExp , "$1" + "$2" ); 
     b = b.replace( regExp , "$1" + "$2" ); 

     var unit = 0; 
     var aNb = a + " " + b; 
     var numbers = aNb.match( /\d+/g ); // 문자열에 들어있는 숫자 찾기 

     for ( var x = 0; x < numbers.length; x++ ){ 

             var length = numbers[ x ].length; 
             if ( unit < length ) unit = length; 
     } 

     var addZero = function( string ){ // 숫자들의 단위 맞추기 

             var match = string.match( /^0+/ ); 

             if ( string.length == unit ) return ( match == null ) ? string : match + string; 

             var zero = "0"; 

             for ( var x = string.length; x < unit; x++ ) string = zero + string; 

             return ( match == null ) ? string : match + string; 
     }; 

     change.first = a.replace( /\d+/g, addZero ); 
     change.second = b.replace( /\d+/g, addZero ); 

     return change; 
} 


/* byLocale() */ 

function byLocale(){ 

     var compare = function( a , b ){ 

             var sorting = sortingNumber( a , b ); 

             if ( typeof sorting == "number" ) return sorting; 

             var change = changeForSorting( a , b ); 

             var a = change.first; 
             var b = change.second; 

             return a.localeCompare( b ); 
     }; 

     var ascendingOrder = function( a , b ){  return compare( a , b );  }; 
     var descendingOrder = function( a , b ){  return compare( b , a );  }; 

     return { ascending : ascendingOrder, descending : descendingOrder }; 
} 


/* replacement() */ 

function replacement( parent ){  
     var tagName = parent.tagName.toLowerCase(); 
     if ( tagName == "table" ) parent = parent.tBodies[ 0 ]; 
     tagName = parent.tagName.toLowerCase(); 
     if ( tagName == "tbody" ) var children = parent.rows; 
     else var children = parent.getElementsByTagName( "li" ); 

     var replace = { 
             order : byLocale(), 
             index : false, 
             array : function(){ 
                     var array = [ ]; 
                     for ( var x = 0; x < children.length; x++ ) array[ x ] = children[ x ]; 
                     return array; 
             }(), 
             checkIndex : function( index ){ 
                     if ( index ) this.index = parseInt( index, 10 ); 
                     var tagName = parent.tagName.toLowerCase(); 
                     if ( tagName == "tbody" && ! index ) this.index = 0; 
             }, 
             getText : function( child ){ 
                     if ( this.index ) child = child.cells[ this.index ]; 
                     return getTextByClone( child ); 
             }, 
             setChildren : function(){ 
                     var array = this.array; 
                     while ( parent.hasChildNodes() ) parent.removeChild( parent.firstChild ); 
                     for ( var x = 0; x < array.length; x++ ) parent.appendChild( array[ x ] ); 
             }, 
             ascending : function( index ){ // 오름차순 
                     this.checkIndex( index ); 
                     var _self = this; 
                     var order = this.order; 
                     var ascending = function( a, b ){ 
                             var a = _self.getText( a ); 
                             var b = _self.getText( b ); 
                             return order.ascending( a, b ); 
                     }; 
                     this.array.sort( ascending ); 
                     this.setChildren(); 
             }, 
             descending : function( index ){ // 내림차순
                     this.checkIndex( index ); 
                     var _self = this; 
                     var order = this.order; 
                     var descending = function( a, b ){ 
                             var a = _self.getText( a ); 
                             var b = _self.getText( b ); 
                             return order.descending( a, b ); 
                     }; 
                     this.array.sort( descending ); 
                     this.setChildren(); 
             } 
     }; 
     return replace; 
} 

function getTextByClone( tag ){  
     var clone = tag.cloneNode( true ); // 태그의 복사본 만들기. 
     var br = clone.getElementsByTagName( "br" ); 
     while ( br[0] ){ 
             var blank = document.createTextNode( " " ); 
             clone.insertBefore( blank , br[0] ); 
             clone.removeChild( br[0] ); 
     } 
     var isBlock = function( tag ){ 
             var display = ""; 
             if ( window.getComputedStyle ) display = window.getComputedStyle ( tag, "" )[ "display" ]; 
             else display = tag.currentStyle[ "display" ]; 
             return ( display == "block" ) ? true : false; 
     }; 
     var children = clone.getElementsByTagName( "*" ); 
     for ( var x = 0; x < children.length; x++){ 
             var child = children[ x ]; 
             if ( ! ("value" in child) && isBlock(child) ) child.innerHTML = child.innerHTML + " "; 
     } 
     var textContent = ( "textContent" in clone ) ? clone.textContent : clone.innerText; 
     return textContent; 
} 


function fn_focus(elId){
	$("#"+elId).focus();
}

function fn_enterFocus(elId){
	if (window.event.keyCode == 13) {
		$("#"+elId).focus();
	}
}

function fn_tabFocus(elId){
	if (window.event.keyCode == 9) {
		$("#"+elId).focus();
	}
}


$(document).ready(function(){
	
	$("#startDate").change(function() {
	    var startDate = $("#startDate").val();
	    var endDate = $("#endDate").val();
	    
	    var sDate = new Date(startDate);
	    var eDate = new Date(endDate);
	    
	    if(sDate > eDate ){
	    	alert("시작일자가 종료일자 이후입니다.");
	    	$("#startDate").datepicker("setDate",endDate);
	    	return false;
	    }
	    
	    eDate.setMonth(eDate.getMonth()-3);
	    
	    var strYear = eDate.getFullYear(); 
		var strMonth = new String(eDate.getMonth()+1); 
		var strDay = new String(eDate.getDate()); 
		
		if(strMonth.length == 1){ 
			strMonth = "0" + strMonth; 
		} 
		
		if(strDay.length == 1){ 
			strDay = "0" + strDay; 
		} 
	    
	    if(sDate < eDate){
	    	alert("조회기간은 시작일자와 종료일자가 3개월 이내에서만 가능합니다. \n 종료일자는 선택한 시작일자로 변경됩니다.");
	    	//$("#startDate").datepicker("setDate",strYear+"-"+strMonth+"-"+strDay);
	    	$("#endDate").datepicker("setDate",startDate);
	    	$("#customInput").focus();
	    }
	 })
	 
	 
	 $("#endDate").change(function() {
	    var startDate = $("#startDate").val();
	    var endDate = $("#endDate").val();
	    
	    var sDate = new Date(startDate);
	    var eDate = new Date(endDate);
	    
	    if(sDate > eDate ){
	    	alert("종료일자가 시작일자 이전입니다.");
	    	$("#endDate").datepicker("setDate",startDate);
	    	return false;
	    }
	    
	    
	    sDate.setMonth(sDate.getMonth()+3);
	    
	    var endYear = sDate.getFullYear(); 
		var endMonth = new String(sDate.getMonth()+1); 
		var endDay = new String(sDate.getDate()); 
		
		if(endMonth.length == 1){ 
			endMonth = "0" + endMonth; 
		} 
		
		if(endDay.length == 1){ 
			endDay = "0" + endDay; 
		} 
	    
	    if(sDate < eDate){
	    	alert("조회기간은 시작일자와 종료일자가 3개월 이내에서만 가능합니다.");
	    	$("#endDate").datepicker("setDate",endYear+"-"+endMonth+"-"+endDay);
	    	$("#customInput").focus();
	    }
	 })
	 
})


			         