 $(document).ready(function() {
      //마지막수정날짜값이 없을시 계정생성날짜로 대체
      if (!("${member.modified}")) {
         $("#modified").val("${member.created }");
      }
      //프로필파일 미리보기 및 선택.
      $("#fileload").on("change", function(e){
         imgchk(e);
      });
   });
   function imgchk(e){
      var thumbext1 = document.getElementById('fileload').value; //파일을 추가한 input 박스의 값
      var thumbext = thumbext1.slice(thumbext1.indexOf(".") + 1).toLowerCase(); //파일 확장자를 잘라내고, 비교를 위해 소문자로 만듭니다.
      if(thumbext != "jpg" && thumbext != "png" &&  thumbext != "gif" &&  thumbext != "bmp"){ //확장자를 확인합니다.
    	  alert('프로필은 이미지 파일(jpg, png, gif, bmp)만 등록 가능합니다.');
    	  $("#fileload").val(null);
    	  $("#imageplace").html(null);
    	  $("#copyimagenameplace").val(null);
      } else{//이미지 미리보기 기능.
    	   $("#copyimagenameplace").val($("#fileload").val());
           var file = e.target.files[0],
           reader = new FileReader();
           var img = new Image();
           reader.onload = function(e){
              img.src = e.target.result;               
              img.width = 280; // 미리보기 크기 조절.
              img.height = 280;
              $("#imageplace").empty();
              $("#imageplace").append(img);
           }
           reader.readAsDataURL(file);
      }
   }
   //퇴장
   function exit(){
      location.href = "timeline";
   }
   //폼체크
   function chk() {
      if (!(changeProfile.password.value.trim())) {
         alert("비밀번호를 입력하여주십시오!!");
         $("#password").focus();
         return false;
      }
      if (changeProfile.password.value.match(/\s/g)) {
         alert("비밀번호에는 공백이 들어갈 수 없습니다!!");
         $("#password").focus();
         return false;
      }
      if (changeProfile.password.value != changeProfile.password2.value) {
         alert("비밀번호랑 비밀번호확인이 일치하지 않습니다!!")
         $("#password").focus();
         return false;
      }
      return true;
   }