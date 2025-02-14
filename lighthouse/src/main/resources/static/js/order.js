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
			if(e.altKey && e.key == 1){ //결제하기
				$('#payment-button').focus();
			}
			if(e.altKey && e.key == 2){ // 가입한 배송지 주소로 배송지 등록
				$('#deliveryInfo').focus();
			}
			if(e.altKey && e.key == 3) {//첫번째 주문상품 이동
			console.log($('.img-fluid'));
				$('a.img-fluid')[0].focus();
			}
            if(e.altKey && e.key == 4){ // 배송지 정보 조회
				$('#deliveryContent').focus();
			}
            if(e.altKey && e.key == 5){ // 배송 시 요청 선택
				$('#selbox').focus();
			}
            if(e.altKey && e.key == 9){ // 메인 페이지 이동
                location.href = "/";
			}
			if(e.altKey && e.key == 0){ //다시 듣기
				VoiceMenual($('#orderInfo').val());
			}
	})