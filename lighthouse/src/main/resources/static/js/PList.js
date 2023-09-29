console.log('hello js');

var AudioContext;
var audioContext;

window.onload = function() {
    navigator.mediaDevices.getUserMedia({ audio: true }).then(() => {
        AudioContext = window.AudioContext || window.webkitAudioContext;
        audioContext = new AudioContext();
    }).catch(e => {
        console.error(`Audio permissions denied: ${e}`);
    });
}
// 먼저 읽히는것 
$(document).ready(function() {
	let url =  new URL(location.href);
	let paramChk = url.search;
	
	if(paramChk.length==0){
		let firstMsg = "상품 목록 페이지입니다. 메뉴얼을 들으신 후 alt 키와 함께 해당 메뉴얼의 번호를 눌러주세요.";
		let message =  firstMsg + $('#PListMenual').val();
    	VoiceMenual(message);
	}
	

});

function VoiceMenual(message) {
    var data = {
        "voice" : {
            "languageCode" : "ko-KR",
            "name" : "ko-KR-Neural2-B"
        },
        "input" : {
            "text" : message
        },
        "audioConfig" : {
            "audioEncoding" : "mp3"
        }
    }
    console.log(data);
    $.ajax({
        type : 'POST',
        url : 'https://texttospeech.googleapis.com/v1/text:synthesize?key=AIzaSyArcSKerGDYE7ngIVMaLhHCbNtmWN1-uac',
        data : JSON.stringify(data),
        dataType : 'JSON',
        contentType : "application/json; charset=UTF-8",
        success : function(res) {
            $('#menuText').val(res.audioContent)
            var audioFile = new Audio();
            let audioBlob = base64ToBlob(res.audioContent,
                    "mp3");
            audioFile.src = window.URL
                    .createObjectURL(audioBlob);
            audioFile.playbackRate = 2; //재생속도
            if(audioFileNow.paused == false){
            	audioFileNow.pause();
            }
    		audioFileNow = audioFile;
            audioFileNow.play();
        
        },
        error : function(request, status, error) {
            alert("오류", "오류가 발생하였습니다. 관리자에게 문의해주세요.");
        }
    });
};
function base64ToBlob(base64, fileType) {
    let typeHeader = "data:application/" + fileType + ";base64,"; // base64 헤더 파일 유형 정의
    let audioSrc = typeHeader + base64;
    let arr = audioSrc.split(",");
    let array = arr[0].match(/:(.*?);/);
    let mime = (array && array.length > 1 ? array[1] : type) || type;
    // url헤더 제거하고 btye로 변환
    let bytes = window.atob(arr[1]);
    // 예외를 처리하고 0보다 작은 ASCII 코드를 0보다 큰 값으로 변환
    let ab = new ArrayBuffer(bytes.length);
    // 뷰 생성(메모리에 직접): 8비트 부호 없는 정수, 길이 1바이트
    let ia = new Uint8Array(ab);
    for (let i = 0; i < bytes.length; i++) {
        ia[i] = bytes.charCodeAt(i);
    }
    return new Blob([ ab ], {
        type : mime
    });
}



	window.addEventListener("keydown", function(e){ 
			console.log(e);
			if(e.altKey && e.key == 1){ //이전 페이지로 가기
				let nowURL = new URL(location.href);
				let nowNum = nowURL.searchParams.get('pageNum');
				let num;
				if(nowNum == 1 || nowNum == undefined){
					num = 1;
					VoiceMenual('첫번째 페이지입니다. 이전 페이지가 없습니다.');
					
				}else{
					num = Number(nowNum)-1;
					movePage(num);
				}
			}
			if(e.altKey && e.key == 2){ // 상품 포커스
				console.log(2);
				let product = document.querySelector('a.card-img');
				product.focus();
			}
			
			if(e.altKey && e.key == 3){ // 다음 페이지로 가기
				let nowURL = new URL(location.href);
				let nowNum = nowURL.searchParams.get('pageNum');
				let num;
				if(nowNum == undefined){
					num = 2;
				}else{
					num = Number(nowNum)+1;
				}
					movePage(num);
				
			}
			if(e.altKey && e.key == 4){ // 뒤로가기
				location.href = "javascript:window.history.back();"
			}
			if(e.altKey && e.key == 5){ // 검색창 가기
				let searchBars = document.querySelectorAll('input[type="search"]');
				searchBars[1].focus();;
			}
			if(e.altKey && e.key == 6){ // 메인 페이지로 가기
				location.href = "/";
			}
			if(e.altKey && e.key == 7){ // 카테고리 페이지로 이동
				location.href = "/";
				var form = document.createElement('form');
				form.setAttribute('method', 'post'); 
				form.setAttribute('action', "/category");
				document.body.appendChild(form);
				form.submit();
			}
			if(e.altKey && e.key == 8){
				location.href = "/"; // 장바구니로 이동
			}
			if(e.altKey && e.key == 9){ // 마이크로 입력하기
				console.log(9);
				let activeInput = document.activeElement
				if(activeInput.tagName=='INPUT'){
					recognition.addEventListener("start", sttToInput(activeInput))
					recognition.start();
				}else{
					let noActiveMsg = "검색하시려면 alt키와 5번을 누르신 후 입력해주세요.";
					VoiceMenual(noActiveMsg);
				}
			}
			if(e.altKey && e.key == 0){
				VoiceMenual($('#PListMenual').val());
			}
	})