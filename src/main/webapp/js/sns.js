					var domainOri = "https://flower.at.or.kr/";
				 	//var domain = "http://localhost/yfmc/";
				 	//var domain = "http://flower.kdev.co.kr:56989/yfmc/";
					var domain = "https://flower.at.or.kr/yfmc/";
	        	 	
				 	//var redirect_uri = "http://flower.kdev.co.kr:56989/naverLogin.do";
				 	
				 	var redirect_uri_naver = domain+"naverLogin.do";
				 	//var redirect_uri_naver = domain+"/sns/naverLogin.html";
				 	var client_id = "8_piJLvcWuBQtaB4D9ms";
			 		var naver_id_login = new naver_id_login(client_id, redirect_uri_naver);
			 		
				 	function naverLogin(){
				 		
				 	  	var state = naver_id_login.getUniqState();
					  	var url = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id="+encodeURIComponent(client_id)+"&redirect_uri="+encodeURIComponent(redirect_uri_naver)+"&state="+state;
					  	naver_id_login.setDomain(domainOri);
					  	naver_id_login.setState(state);
					  	naver_id_login.setPopup()
					  	
				 		window.open(url, 'naverloginpop', 'titlebar=1, resizable=1, scrollbars=yes, width=600, height=550');
				 		return false;
				 	}
				 	
				 	function naverLoginInit(){
				 		
				 		var state = naver_id_login.getUniqState();
					  	naver_id_login.setDomain(domain);
					  	naver_id_login.setState(state);
					  	naver_id_login.setPopup();
					  	naver_id_login.init_naver_id_login();
					  	$("#naver_id_login img").attr("src",contextPath+"/img/icon_logo_naver.png");
						$("#naver_id_login img").attr("title","네이버 로그인 (새 창으로 이동)");
					  	$("#naver_id_login img").attr("alt","네이버 로그인");
					  	$("#naver_id_login img").css("width","100%");
					  	$("#naver_id_login img").css("height","100%");
					  	
				 	}

				 	function naverLoginInitMypage(){
				 		if($("#naver_id_login").val() != null){
				 			var state = naver_id_login.getUniqState();
						  	naver_id_login.setDomain(domain);
						  	naver_id_login.setState(state);
						  	naver_id_login.setPopup();
						  	naver_id_login.init_naver_id_login();
						  	$("#naver_id_login").attr("href",$("#naver_id_login a").attr("href"));
						  	$("#naver_id_login").attr("onclick",$("#naver_id_login a").attr("onclick"));
						  	$("#naver_id_login").html("연계");	
				 		}
				 		
				 	}
				 	
				  	
				  	
				  	Kakao.init('4ba5ba209cdc1e5e8bed585bd89319da');
				  	window.name='login';
				  	  function loginWithKakao() {
				  		  /*
						Kakao.Auth.authorize({
					  	  redirectUri: domain+'kakaoLogin.do'
					  	}); */
						  
						
					    Kakao.Auth.login({
					      success: function(authObj) {
					    	  console.log("authObj",authObj);
					    	  window.location.href = domain+'kakaoLogin.do?access_token='+authObj.access_token;
					    	  
					    	  /*
					    	  Kakao.API.authorize({
				    	        url: '/v2/user/me',
				    	        //redirectUri: domain+'kakaoLogin.do'
				    	        
				    	        success: function(res) {
				    	        	console.log(res);
				    	            console.log(JSON.stringify(res));
				    	        },
				    	        fail: function(error) {
				    	          alert(
				    	            'login success, but failed to request user information: ' +
				    	              JSON.stringify(error)
				    	          )
				    	        },
				    	      })*/
					      },
					      fail: function(err) {
					        alert(JSON.stringify(err))
					      },
					    });
					  }
				  	
				  	
				  	function refreshAuthStatus() {
			            FB.getLoginStatus(function (response) {
			                console.log('FB.getLoginStatus', response);
			                if (response.status === 'connected') {
			                    FB.api('/me', function (response) { // me, 혹은 사용자 id 입력
			                        document.querySelector('#authBtn').value = 'Logout';
			                        document.querySelector('#welcome').innerHTML = 'Welcome, ' + response.name;
			                    })
			                } else {
			                    document.querySelector('#authBtn').value = 'Login';
			                    document.querySelector('#welcome').innerHTML = '';
			                }
			            });
			        }
			        

			        /*
			          window.fbAsyncInit = function() {
			            FB.init({
			              appId      : '784135178790977',
			              cookie     : true,
			              xfbml      : true,
			              version    : 'v7.0'
			            });
			              
			            FB.AppEvents.logPageView();   
			              
			          };

			          (function(d, s, id){
			             var js, fjs = d.getElementsByTagName(s)[0];
			             if (d.getElementById(id)) {return;}
			             js = d.createElement(s); js.id = id;
			             js.src = "http://connect.facebook.net/en_US/sdk.js";
			             fjs.parentNode.insertBefore(js, fjs);
			           }(document, 'script', 'facebook-jssdk'));
				  	*/
			          
			         function faceLogin(){
			        	 var redirect_uri = domain+"facebookLogin.do";
			        	 //var redirect_uri = "http://flower.kdev.co.kr:56989/facebookLogin.do";
			        	 var url = "https://www.facebook.com/v2.11/dialog/oauth?client_id=784135178790977&redirect_uri="+encodeURIComponent(redirect_uri);
			        	 window.open(url, 'facebookloginpop', 'titlebar=1, resizable=1, scrollbars=yes, width=600, height=550');
			                 
			         }
			         
			         
			         function atLogin(){
					  		var id = $("#logId").val();
					  		var pw = $("#logPw").val();

							//25.02 2.4
							pw = CryptoJS.SHA256(pw).toString();					  		

					  		$.ajax({
					   			url: domain+"/front/atlogin.json",
					  	        type: 'POST',
					  	        data:{
					  	        	"id": id,
					  	        	"pw": pw
					  	        },
					  	        success: function (res) {
					  	           if(res.result == 1){
					  	        	   //console.log(res);
					  	        	   location.href = domain+"/front/mypage.do";
					  	           }else{
					  	        	   alert("회원정보가 일치하지않습니다.");
					  	           }
					  	        }
					 	   });
					  	}
			         
			         
			         function fn_snsDel(type){
			        	 $.ajax({
					   			url: domain+"/front/snsDel.json",
					  	        type: 'POST',
					  	        data:{
					  	        	"snsType": type
					  	        },
					  	        success: function (res) {
					  	           if(res.result == 1){
					  	        	   alert("성공적으로 해제되었습니다.");
					  	        	   location.href = domain+"/front/mypage.do";
					  	           }else{
					  	        	   alert("오류가발생했습니다. 관리자에게 문의하세요");
					  	           }
					  	        }
					 	   });
			         }
			         
			         
			         
			        
			         