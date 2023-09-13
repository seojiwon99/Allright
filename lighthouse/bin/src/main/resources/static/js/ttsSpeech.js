/**
 * 
 */
 		var voices = [];
	  	function setVoiceList() {
	  	voices = window.speechSynthesis.getVoices();
	  	}
  
	 	 setVoiceList();
	  
	 	 if (window.speechSynthesis.onvoiceschanged !== undefined) {
	 	 	window.speechSynthesis.onvoiceschanged = setVoiceList;
	 	 }
	  
	  	function speech(txt) {
	      if(!window.speechSynthesis) {
	        alert("음성 재생을 지원하지 않는 브라우저입니다. 크롬, 파이어폭스 등의 최신 브라우저를 이용하세요");
	        return;
	      }

	      var lang = 'ko-KR';
	      var utterThis = new SpeechSynthesisUtterance(txt);

	      utterThis.onend = function (event) {
	        console.log('end');
	      };

	      utterThis.onerror = function(event) {
	        console.log('error', event);
	      };

	      var voiceFound = false;

	      for(var i = 0; i < voices.length ; i++) {
	        if(voices[i].lang.indexOf(lang) >= 0 || voices[i].lang.indexOf(lang.replace('-', '_')) >= 0) {
	          utterThis.voice = voices[i];
	          voiceFound = true;
	        }
	      }
	      if(!voiceFound) {
	        alert('voice not found');
	        return;
	      }

	      utterThis.lang = lang;
	      utterThis.pitch = 1;
	      utterThis.rate = 1; //속도

	      window.speechSynthesis.speak(utterThis);
	  }
	  
	  	  function g_gout(){
	      var t = document.getElementById("code_reddit");
	      speech(t.value);
	  	}
	  	let msg = "반갑습니다. 생활용품 종합 쇼핑몰 All Right입니다. 메뉴얼을 들으시려면  하나 라고 말씀해주세요."
	  	
	  	$(document).ready(function(){
	  		g_gout();
	  	})
	  	function t_gout(text){
	  		speech(text);
	  	}
	  	
	  
	  