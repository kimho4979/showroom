/* script.js */

//JQUERY 시작
$(document).ready(function(){

	// 본문바로가기
	$('.skip a').on('focus', function(){
        $(this).stop().animate({"top":0, "opacity":1});
    });
    $('.skip a').on('click', function(){
        $(this).stop().animate({"top":"-30px", "opacity":0});
    });
    $('.skip a').on('focusout', function(){
        $(this).stop().animate({"top":"-30px", "opacity":0});
    });
    




  // 메인 슬라이드
	$('.index_visual').cycle({
		fx: 'fade', 
		speed:  'slow',
   		timeout: 5000 ,
		pager:  '.visual_count',
		pagerAnchorBuilder: function(idx, el) {
	        return '<a href="#"><span class="hidden">'+idx+'</span></a>';
	    }
	});
	
	$(".visual_control .btn_visual_resume").hide();

	$(".visual_control .btn_visual_pause").click(function(){
		$('.index_visual').cycle('pause');
		$(this).hide();
		$(".visual_control .btn_visual_resume").show();
	});

	$(".visual_control .btn_visual_resume").click(function(){
		$('.index_visual').cycle('resume');
		$(this).hide();
		$(".visual_control .btn_visual_pause").show();
	});
  

	//탑버튼
	$(window).scroll(function() {
		if ($(this).scrollTop() > 500) {
			$('.btn-tab-new.right, .btn_scroll_top').fadeIn();
		} else {
			$('.btn-tab-new.right, .btn_scroll_top').fadeOut();
		}
	});
	
	$(".btn_scroll_top").click(function() {
		$('html, body').animate({
			scrollTop : 0
		}, 400);
		return false;
	});

			
	





  //GNB drop-down
//   $(".btn_m").hover(function() { //마우스를 topnav에 오버시
// 	$(this).parent().find(".gnb_box").slideDown('fast').show(); //subnav가 내려옴.
	
//     $(this).parent().hover(function() {  
//     }, function(){  
// 	  $(this).parent().find(".gnb_box").slideUp('fast'); //subnav에서 마우스 벗어났을 시 원위치시킴  
//     });  
//   });
	
	$(".btn_m").focus(function() { //마우스를 topnav에 오버시
		 $(this).parent().find(".gnb_box").css('display','block'); //subnav가 내려옴.

		    $(this).parent().hover(function() {
		    }, function(){
		   $(this).parent().find(".gnb_box").css('display','none'); //subnav에서 마우스 벗어났을 시 원위치시킴
		    });
		  });

		  $(".btn_m").hover(function() { //마우스를 topnav에 오버시
		 $(this).parent().find(".gnb_box").css('display','block'); //subnav가 내려옴.

		    $(this).parent().hover(function() {
		    }, function(){
		   $(this).parent().find(".gnb_box").css('display','none'); //subnav에서 마우스 벗어났을 시 원위치시킴
		   $(this)
		    });
		  });

		  $(".btn_p.login").focus(function(){
		 $(".gnb_box").css('display','none');
	});
		
	$(".btn_nav_depth_01:nth-child(1) > .nav_depth_box_02 .btn_nav_depth_02:last-child a").focusout(function(){
		$(".btn_nav_depth_01:nth-child(1) > .nav_depth_box_02").css('display','none');
	});
	
	$(".btn_nav_depth_01:nth-child(2) > .nav_depth_box_02 .btn_nav_depth_02:last-child a").focusout(function(){
		$(".btn_nav_depth_01:nth-child(2) > .nav_depth_box_02").css('display','none');
	});

	$(".tab_url ul li").focus(function(){
		/* $(".btn_nav_depth_01:nth-child(2) > .nav_depth_box_02 ").css('display','none');*/
		alert('!');
	});

  //NAV drop-down
  $(".btn_nav_depth_01 > a").click(function() { //마우스를 topnav에 클릭시
    $(this).parent().find(".nav_depth_box_02").slideDown('normal').show(); //subnav가 내려옴.
    $(this).parent().hover(function() {  
    }, function(){  
      $(this).parent().find(".nav_depth_box_02").slideUp('fast'); //subnav에서 마우스 벗어났을 시 원위치시킴  
    });  
  });

  //tab select 모바일
  $(".btn_choice").click(function() { //마우스를 topnav에 클릭시
    $(this).parent().find("ul").slideDown('normal').show(); //subnav가 내려옴.
    $(this).parent().hover(function() {  
    }, function(){  
      $(this).parent().find("ul").slideUp('fast'); //subnav에서 마우스 벗어났을 시 원위치시킴  
    });  
  });


  //메인 >> 이미지맵 반응형
  $('img[usemap]').rwdImageMaps();
	  
  // 파일선택
  $('.input_file').each(function(index){
	$('.input_file').eq(index).change(function(){
	   var i = $(this).val();
	   $('.upload_text').eq(index).val(i);
		});
 	});
  
	// $(function(){
	// 	$('.upload_text').val('...');
	// 	$('.input_file').change(function(){
	// 		var i = $(this).val();
	// 		$('.upload_text').val(i);
	// 	});
	// });

  //timepicker
  $("#timeFrom").timepicker({
	step: 1,            //시간간격 : 1분
	timeFormat: "H:i"    //시간:분 으로표시
});
	$("#timeTo").timepicker({
	step: 1,            //시간간격 : 1분
	timeFormat: "H:i"    //시간:분 으로표시
});

$("#timeFrom02").timepicker({
	step: 1,            //시간간격 : 1분
	timeFormat: "H:i"    //시간:분 으로표시
});
	$("#timeTo02").timepicker({
	step: 1,            //시간간격 : 1분
	timeFormat: "H:i"    //시간:분 으로표시
});

$("#timeFrom03").timepicker({
	step: 1,            //시간간격 : 1분
	timeFormat: "H:i"    //시간:분 으로표시
});
	$("#timeTo03").timepicker({
	step: 1,            //시간간격 : 1분
	timeFormat: "H:i"    //시간:분 으로표시
});

//현재시간
// $(document).ready(function(){

// 	$("#time1").timepicker('setTime', new Date());

// });



  //좌우 높이 동일 조정
	var heightArray = $(".m_col_left > form > .table_type_03").map(function(){
		return $(this).height();
	}).get();

	var maxHeight = Math.max.apply( Math, heightArray);

	$(".m_col_right > .info_box_02").innerHeight(maxHeight);

	$(window).resize(function() {

		var mobileHeight = $(".m_col_left > form > .table_type_03").height();

		if($(window).width() < 1086) {
			$(".m_col_right > .info_box_02").css('height','100%');
		}

		if($(window).width() > 1086) {
			$(".m_col_right > .info_box_02").innerHeight(mobileHeight);
		}
		
	});



	
  //datepicker
	$( "input.datepicker" ).datepicker({
	  showOn: "both",
	  buttonImage: contextPath+"/img/ico_datepicker.png",
	  buttonImageOnly: true,
   	  buttonText: "Select date",
    
	});

		// KR language callendar
	$.datepicker.regional['kr'] = {
		closeText: '닫기', // 닫기 버튼 텍스트 변경
		currentText: '오늘', // 오늘 텍스트 변경
		monthNames: ['1 월','2 월','3 월','4 월','5 월','6 월','7 월','8 월','9 월','10 월','11 월','12 월'], // 개월 텍스트 설정
		monthNamesShort: ['1 월','2 월','3 월','4 월','5 월','6 월','7 월','8 월','9 월','10 월','11 월','12 월'], // 개월 텍스트 설정
		dayNamesMin: ['일','월','화','수','목','금','토'], // 요일 텍스트 설정
		dayNamesShort: ['월','화','수','목','금','토','일'], // 요일 텍스트 축약 설정&nbsp;   dayNamesMin: ['월','화','수','목','금','토','일'], // 요일 최소 축약 텍스트 설정
		dateFormat: 'yy-mm-dd', // 날짜 포맷 설정
		showMonthAfterYear: true
  };
  
  $('.ui-datepicker ').css({ "margin-left" : "-100px", "margin-top": "0px"});  //달력(calendar) 위치
  //  $('img.ui-datepicker-trigger').attr('align', 'absmiddle');
		// Seeting up default language, Korean
	$.datepicker.setDefaults($.datepicker.regional['kr']);
  
  //검색조건창
    $('.btn_condition.close').click(function () {
		$('.condition_box').slideToggle();
		$('.btn_condition.close').css('display','none');
		$('.btn_condition.open').css('display','inline-block');  
	});
	
	$('.btn_condition.open').click(function () {
		$('.condition_box').slideToggle();
		$('.btn_condition.open').css('display','none');
		$('.btn_condition.close').css('display','inline-block');  
	});
	

	//서브 카테고리 이미지 변경
	$(".thumbs a").click(function(){
		$("#caption").remove();
		$("#largeImg img").attr("src",$(this).attr("href"));

	
		var msg=$(this).next("em").text();

		$("#largeImg").append("<div id='caption'></div>"); 
		$("#largeImg #caption").text(msg);
		
		var posy=$("largeImg #caption").height()+25;
	
		$("#largeImg #caption").animate({top:"-="+posy+"px"},500);
		return false;
	});    

	//서브 카테고리 이미지 3개 border처리
	$(".thumbs a").click(function(){
		$(".thumbs a").removeClass();
		$(this).addClass("b_selected");
	});

	// 테이블아코디언
	$(function(){  
		
		var article = (".recruit .show");  
		$(".recruit .item li").click(function() {  
			var myArticle2 =$(this).parents().parents().find(".item");         
         	for(var i=0; i<myArticle2.length;i++){
				$(myArticle2[i]).css(
					{'background':'#ffffff','border-bottom':'1px solid #dcdcdc'}
				);            
				$(myArticle2[i]).find('.upload_img').css('display','block');
				
         	}

			var myArticle =$(this).parents().next("ul");
			if($(myArticle).hasClass('hide')) {  
				$(this).parents("ul").css(
					{'background':'#f6f6f6','border-bottom':'0'}
					);
				$(this).next('li').find('.upload_img').css('display','none');
				$(myArticle).css('background','#f6f6f6');
				$(article).removeClass('show').addClass('hide');  
				$(myArticle).removeClass('hide').addClass('show');
			}
			
			
			else {  
				$(myArticle).addClass('hide').removeClass('show');  
				$(this).parents("ul").css(
					{'background':'#ffffff','border-bottom':'1px solid #dcdcdc'}
					);
				$(this).next('li').find('.upload_img').css('display','block');
			}

			
		});

		$(".item li").parents().next(".item").click(function() {  
			$(this).parents("ul").css(
				{'background':'#ffffff','border-bottom':'1px solid #dcdcdc'}
				);
			$(this).next('li').find('.upload_img').css('display','block');
		});

		
		
		});

		// 사진이 없을시		
		$(".item.no_photo > li").on('click', function(){
			$(this).preventDefault();
		});

		// 업로드 된 이미지 갯수 표시
		$('.recruit .item li').each(function () {
			var uploadImg = $(this).parents('.item').next('.details').find('.answer_box').length;
			var ImgTxt = $(this).parents('.item').find('.img_total');
			ImgTxt.html("+ "+ uploadImg);

			if($(uploadImg) < 0 ){
				ImgTxt.css('display','none');
			};
		});		
	
	
		// 별점주기
		$('.score_box .star').click(function(){
			$(this).parent().children('.star').removeClass('on');
			$(this).addClass('on').prevAll('.star').addClass('on');
			return false;
		});

	

		

		



		  


	
  
  
  // 높이동일
	function equalHeights( $objs )
	{
		var highest = 0;
	
		$objs.each(function() {
			thisHeight = $(this).outerHeight();
			if(thisHeight > highest ) {
				highest = thisHeight;
			}
		});
	
		$objs.outerHeight( highest );
	}
	
	$(function(){
		setTimeout(function(){
			equalHeights( $(".profile_box.devide_3_7 > li, .item > li") );
		}, 0);
	});

  // lnb
  
  //모바일 메뉴
      $('.btn_lnb').click(function () {
        $('#lnb').attr('style','display: block');  
	});
	
    $('.m_btn_close').click(function () {
        $('#lnb').attr('style','display: none');
    });

  //로그인 
    $(".log_ip" ).click(function(){
      $(".log_ip" ).removeClass("on");
      $(this).addClass("on");
    }); 

    var $width = $(".lnb_wrap");
    

    $(window).resize(function() {
       if($(window).width() > 1086) {
          $('#lnb').css('display','none');
        } 
    });

  // tab
  $(".tab_content > div").hide();
			$(".tab_content > div").eq(0).show();
			$(".tab > ul > li" ).click(function(){
				
				var tab_index = $(this).index();

				$(".tab > ul > li" ).removeClass("active");
				$(this).addClass("active");
				$(".tab_content > div").hide();
				$(".tab_content > div").eq(tab_index).show();
			});
  });

// lnb
(function($){
  
  var lnbUI = {
    click : function (target, speed) {
      var _self = this,
          $target = $(target);
      _self.speed = speed || 300;
      
      $target.each(function(){
        if(findChildren($(this))) {
          return;
        }
        $(this).addClass('noDepth');
      });
      
      function findChildren(obj) {
        return obj.find('> ul').length > 0;
      }
      
      $target.on('click','a', function(e){
          e.stopPropagation();
          var $this = $(this),
              $depthTarget = $this.next(),
              $siblings = $this.parent().siblings();
        
        $this.parent('li').find('ul li').removeClass('on');
        $siblings.removeClass('on');
        $siblings.find('ul').slideUp(250);
        
        if($depthTarget.css('display') == 'none') {
          _self.activeOn($this);
          $depthTarget.slideDown(_self.speed);
        } else {
          $depthTarget.slideUp(_self.speed);
          _self.activeOff($this);
        }
        
      })
      
    },
    activeOff : function($target) {
      $target.parent().removeClass('on');
    },
    activeOn : function($target) {
      $target.parent().addClass('on');
    }
  };
  
  
  // Call lnbUI
  $(function(){
    lnbUI.click('#lnb li', 300)
  });
  
  
}(jQuery));


//메인 >> 이미지맵 반응형
;(function($) {
	$.fn.rwdImageMaps = function() {
		var $img = this;

		var rwdImageMap = function() {
			$img.each(function() {
				if (typeof($(this).attr('usemap')) == 'undefined')
					return;

				var that = this,
					$that = $(that);

				// Since WebKit doesn't know the height until after the image has loaded, perform everything in an onload copy
				$('<img />').on('load', function() {
					var attrW = 'width',
						attrH = 'height',
						w = $that.attr(attrW),
						h = $that.attr(attrH);

					if (!w || !h) {
						var temp = new Image();
						temp.src = $that.attr('src');
						if (!w)
							w = temp.width;
						if (!h)
							h = temp.height;
					}

					var wPercent = $that.width()/100,
						hPercent = $that.height()/100,
						map = $that.attr('usemap').replace('#', ''),
						c = 'coords';

					$('map[name="' + map + '"]').find('area').each(function() {
						var $this = $(this);
						if (!$this.data(c))
							$this.data(c, $this.attr(c));

						var coords = $this.data(c).split(','),
							coordsPercent = new Array(coords.length);

						for (var i = 0; i < coordsPercent.length; ++i) {
							if (i % 2 === 0)
								coordsPercent[i] = parseInt(((coords[i]/w)*100)*wPercent);
							else
								coordsPercent[i] = parseInt(((coords[i]/h)*100)*hPercent);
						}
						$this.attr(c, coordsPercent.toString());
					});
				}).attr('src', $that.attr('src'));
			});
		};
		$(window).resize(rwdImageMap).trigger('resize');

		return this;
	};
})(jQuery);



//팝업
 $(function(){

	//dim처리 layerpopup
	$('.btn-popup').click(function(){
	  var $href = $(this).attr('href');
	  layer_popup($href);
	  $('body').css('overflow','hidden');
	  $('.dim-layer').css('position','fixed');
	});
	
	function layer_popup(el){
  
	  var $el = $(el);        //레이어의 id를 $el 변수에 저장
	  var isDim = $el.prev().hasClass('dimBg');   //dimmed 레이어를 감지하기 위한 boolean 변수
  
	  isDim ? $('.dim-layer').fadeIn() : $el.fadeIn();
  
	  var $elWidth = $( window ).width(),
		$elHeight = $( window ).height(),
		docWidth = $(window).width(),
		docHeight = $(window).height();
  
	  var popboxW = $el.find(".popbox").width()/2; 
	  var popboxH = $el.find(".popbox").height()/2; 
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
  
  });

var windowSize = window.matchMedia("screen and (max-width: 1086px)");

if (windowSize.matches) {
	$("body").css('overflow','hidden');
} else {
    // console.log("화면의 너비가 768px 보다 큽니다.");
}





