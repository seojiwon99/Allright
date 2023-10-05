
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
		let firstMsg = "반갑습니다. 생활용품 종합쇼핑몰 All Right 입니다. 저희는 TTS를 통해 음성자료를 제공하고 있습니다.  메뉴얼을 들으신 후 alt 키와 함께 해당 메뉴얼의 번호를 눌러주세요.";
		let message =  firstMsg + $('#mainMenual').val();
    	VoiceMenual(message);
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

			if(e.altKey && e.key == 1){ // 회원가입 페이지
				location.href="/page/member/joinBuyerForm";
			}
			if(e.altKey && e.key == 2){ // 로그인 페이지
				location.href="/page/member/loginForm";
			}
			
			if(e.altKey && e.key == 3){ // 로그아웃
				let memberId = [[${session.loginMember}]]
				console.log(memberId);
				window.localStorage.clear();
				$.ajax({
					url : '/logout',
					type : 'POST',
					success : function(){
						location.href="/";
					},
					error : function(err){
						console.log(err)
					}
				})
			}
			if(e.altKey && e.key == 4){ // 마이페이지로 이동
				location.href = "/page/buyer/orderList"
			}
			if(e.altKey && e.key == 5){ // 상품 전체 페이지로 이동
				location.href = "/PList"
			}
			if(e.altKey && e.key == 6){ // 현재 페이지 둘러보기
				$('.row align-items-center py-12').focus();
			}
			if(e.altKey && e.key == 7){ // 장바구니 페이지로 이동
				location.href="/cart/cartView";
			}
			if(e.altKey && e.key == 8){ //공지사항 페이지로 이동
				location.href = "/custom/faqList"; 
			}
			if(e.altKey && e.key == 0){
				VoiceMenual($('#mainMenual').val());
			}
	})