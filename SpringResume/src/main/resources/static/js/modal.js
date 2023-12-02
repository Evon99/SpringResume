  
  document.addEventListener('DOMContentLoaded', function() {
	    // 모달 열기
	    document.getElementById('openModalBtn').addEventListener('click', function() {
	    	console.log('Open button clicked');
	        document.getElementById('loginModalContainer').style.display = 'block';
	    });

	    // 모달 닫기
	    document.getElementById('closeModalBtn').addEventListener('click', function() {
	        document.getElementById('loginModalContainer').style.display = 'none';
	    });

	    // 모달 외부 클릭 시 닫기
	    window.addEventListener('click', function(event) {
	        var modal = document.getElementById('loginModalContainer');
	        if (event.target == modal) {
	            modal.style.display = 'none';
	        }
	    });
	});

 	document.addEventListener('DOMContentLoaded', function() {
	    // 모달 열기
	    document.getElementById('openModalBtn2').addEventListener('click', function() {
	    	console.log('Open button clicked');
	        document.getElementById('loginModalContainer').style.display = 'block';
	    });

	    // 모달 닫기
	    document.getElementById('closeModalBtn').addEventListener('click', function() {
	        document.getElementById('loginModalContainer').style.display = 'none';
	    });

	    // 모달 외부 클릭 시 닫기
	    window.addEventListener('click', function(event) {
	        var modal = document.getElementById('loginModalContainer');
	        if (event.target == modal) {
	            modal.style.display = 'none';
	        }
	    });
	});
	
	// 구글 로그인 버튼 클릭
	document.getElementById('googleLogin').addEventListener('click', function() {
        document.getElementById('googleLoginForm').submit();
    });
	
	// 카카오 로그인 버튼 클릭
	document.getElementById('kakaoLogin').addEventListener('click', function() {
        document.getElementById('kakaoLoginForm').submit();
    });
    
    // 네이버 로그인 버튼 클릭
    document.getElementById('naverLogin').addEventListener('click', function() {
        document.getElementById('naverLoginForm').submit();
    });
	
