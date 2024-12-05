
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
	}else if(bunChk == 'CR'){
		//관엽요청
		if(dayIndex == 4){
			date.setDate(date.getDate()+1);
			
		}else if (dayIndex == 0 || dayIndex == 3){
			
			date.setDate(date.getDate()+2);
		}else if (dayIndex == 2 || dayIndex == 6){
			
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
		
	$("#searchStartDt").datepicker("setDate",strYear+"-"+strMonth+"-"+strDay);
	
	
	var endYear = endDate.getFullYear(); 
	var endMonth = new String(endDate.getMonth()+1); 
	var endDay = new String(endDate.getDate()); 
	
	if(endMonth.length == 1){ 
		endMonth = "0" + endMonth; 
	} 
	
	if(endDay.length == 1){ 
		endDay = "0" + endDay; 
	} 
		
	$("#searchEndDt").datepicker("setDate",endYear+"-"+endMonth+"-"+endDay);
	
	fn_search();
	
}

function setSearchDateInitBuy(dayCnt){
	
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
		
	$("#searchStartDt").datepicker("setDate",strYear+"-"+strMonth+"-"+strDay);
	
	$("#searchEndDt").datepicker("setDate",null);
	
	fn_search();
	
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


function layer_popup_adm(el){
	  
  var $el = $(el);        //레이어의 id를 $el 변수에 저장
  var isDim = $el.prev().hasClass('dimBg');   //dimmed 레이어를 감지하기 위한 boolean 변수

  isDim ? $('.dim-layer').fadeIn() : $el.fadeIn();

  var $elWidth = $( window ).width(),
	$elHeight = $( window ).height(),
	docWidth = $(window).width(),
	docHeight = $(window).height();

  var popboxW = $el.find(".popbox").width()/2; 
  var popboxH = $el.find(".popbox").height()/2;
  console.log(popboxW);
  console.log(popboxH);
  $el.find(".popbox").css({
	marginTop: $elHeight /2 - popboxH,
	marginLeft: $elWidth/2 - popboxW
  })
  // 화면의 중앙에 레이어를 띄운다.
  /*if ($elHeight < docHeight || $elWidth < docWidth) {
	$el.css({
	  marginTop: -$elHeight /2,
	  marginLeft: -$elWidth/2
	})
  } else {
	$el.css({top: 0, left: 0});
  }*/

  $el.find('a.btn-layerClose').click(function(){
	isDim ? $('.dim-layer').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
	$('body').css('overflow','auto');
	$('body').css('position','relative');
	return false;
  });

  $('.layer .dimBg').click(function(){
	$('.dim-layer').fadeOut();
	return false;
  });

}


$(document).ready(function(){
	/*
	$(document).on('focus', '.select2-selection.select2-selection--single', function (e) {
	  $(this).closest(".select2-container").siblings('select:enabled').select2('open');
	});*/
	
	
	$("form[name=searchFrm] select").change(function(){
		fn_search();
	});
	
	$("form[name=searchFrm] input[type=text]").keydown(function(key) {
        if (key.keyCode == 13) {
        	fn_search();
        }
	});
	
	$(".popup_search input[id=searchChulCode]").keydown(function(key) {
        if (key.keyCode == 13) {
        	getChulList();
        }
	});
	
	$(".popup_search input[id=searchJCode]").keydown(function(key) {
        if (key.keyCode == 13) {
        	getJList();
        }
	});
	
	$("input.datepicker").mask("9999-99-99");
	
	
	
	$("form[name=frm] #mokCode").change(function() {
       $("#pumCode").focus();
	});
	
	
	
	
	
	
	$(document).keydown(function(e){

		if(!($("select").is(":focus")) && !($("input").is(":focus")) && !($("textarea").is(":focus")) ) {
			if(e.which == 83){
				fn_save();
			}
			
			if(e.which == 76){
				$("#listBtn").get(0).click();
			}
			
			if(e.which == 88){
				$('.dim-layer').fadeOut()
				$('body').css('overflow','auto');
				$('body').css('position','relative');
			}
			
			if(e.which == 49){
				$("#btn_chul_pop").trigger("click");
				$("#searchChulCode").focus();
				
			}
			
			if(e.which == 50){
				$("#btn_j_pop").trigger("click");
				$("#searchJCode").focus();
			}
		}

	});
	
	
	$("input[type=number]").bind("keydown",function(e){
		
		if(e.keyCode == 107 || e.keyCode == 109 || e.keyCode == 187 || e.keyCode == 189 || e.keyCode == 229 || e.keyCode == 110 || e.keyCode == 190) {
	        return false;
	    }else{
	    	//alert(e.keyCode);
	    }
	});
	
	
	$("#updayRecent").change(function() {
		getRecentList();
	 })
	
	
		
});


/*
var isAlt = false;

document.onkeyup = function(e){
	if(e.which == 18) isAlt = false;
}

document.onkeydown = function(e){
	if(e.which == 18) {
		isAlt = true;
	}
	
	if(e.which == 49 && isAlt){
		firstDivFocus();
	}
	
	if(e.which == 50 && isAlt){
		secondDivFocus();
	}
	
	if(e.which == 83 && isAlt){
		fn_save();
	}
	
	if(e.which == 76 && isAlt){
		$("#listBtn").get(0).click();
	}
	
	if(e.which == 88 && isAlt){
		$('.dim-layer').fadeOut()
		$('body').css('overflow','auto');
		$('body').css('position','relative');
	}
}*/


function fn_rateCal(){
	var boxCnt = $("#boxCnt").val();
	var sokCnt = $("#sokCnt").val();
	
	if(sokCnt%boxCnt == 0){
		return true;
	}else{
		return false;	
	}
	
}

function fn_boxCntKeyUp(index, boxCnt, unitSok, wm){
	
	if(wm == "w"){
		//웹
		var bidSokCnt = $("#bidBoxCnt"+index).val();
		if(bidSokCnt > boxCnt){
			alert("최대 상자수를 초과 입찰할 수 없습니다.");
			$("#bidBoxCnt"+index).val("1");
			$("#bidSokCnt"+index).val(unitSok);
		}else{
			$("#bidSokCnt"+index).val(bidSokCnt*unitSok);
		}
		
		
	}else{
		//모바일
		var bidSokCnt = $("#mbidBoxCnt"+index).val();
		if(bidSokCnt > boxCnt){
			alert("최대 상자수를 초과 입찰할 수 없습니다.");
			$("#mbidBoxCnt"+index).val("1");
			$("#mbidSokCnt"+index).val(unitSok);
		}else{
			$("#mbidSokCnt"+index).val(bidSokCnt*unitSok);
		}
	}
	
	
	
}

function fn_sokCntFocusOut(index, unitSok){
	
}


function maxLengthCheck(object){
	
	if(object.value <= 0 || object.value == '' || object.value == null){
		object.value = '';
	}
	
    if (object.value.length > object.maxLength){
        object.value = object.value.slice(0, object.maxLength);
    }
    
    
}


function fn_fileAdd(){
	
	if($(".file_box").length >= 10){
		alert("이미지 파일은 10개 까지 등록 가능합니다.");
		return false;
	}
	
	var html = "";
	
	html +="<div class=\"file_box\">                                                                          ";
	html +="	<input type=\"text\" class=\"upload_text mt5\" readonly=\"readonly\">                             ";
	html +="	<div class=\"upload-btn_wrap mt5\">                                                           ";
	html +="	<button type=\"button\" title=\"파일찾기\">                                                         ";
	html +="	<span>파일찾기</span>                                                                           ";
	html +="	</button>                                                                                   ";
	html +="	<input type=\"file\" class=\"input_file\" name=\"attachFile\" title=\"파일찾기\" accept=\"image/*\">      ";
	html +="	</div>                                                                                      ";
	html +="</div>                                                                                          ";
	
	
	
	$("#fileTd").append(html);
	$('.input_file').each(function(index){
		$('.input_file').eq(index).change(function(){
		   var i = $(this).val();
		   $('.upload_text').eq(index).val(i);
		});
	});
	
}



			         