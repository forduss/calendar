var url = new URL(window.location.href);
var error = url.searchParams.get('error');

if(error != null) {
	document.getElementById('msg').innerText = "※ 아이디 또는 패스워드가 잘못되었습니다.";
}

function search(button){
	var keyword = document.getElementById("keyword").value;
	var boardno = button.getAttribute('data-boardno');
	location.href="/board/" + boardno + "?keyword=" + keyword;
}

function delFile(button){
	button.parentElement.remove();
}

function addFile(button){
	const parent = button.parentElement;
	var addEl = document.createElement('p');
	addEl.innerHTML = '<input type="file" name="file">'
					+ '<input type="checkbox">대표이미지'
					+ '<input type="button" value="X" onclick="delFile(this)">';
	parent.appendChild(addEl);
}

function findPw(){
	window.open('/findpw','비밀번호 찾기',
	'width=420,height=400,scrollbars=no,resizable=no,history=no,status=no,menubar=no');
}

function findId(){
	window.open('/findId','아이디 찾기',
	'width=420,height=400,scrollbars=no,resizable=no,history=no,status=no,menubar=no');
}

// 메일 보내기
function sendMail() {
    // 폼 요소 참조
    const form = document.getElementById('inquireForm');
    const title = form.title.value;
    const email = form.email.value;
    const name = form.name.value;
    const telno = form.telno.value;
    const content = form.content.value;
    
    // 필수 입력 필드 검사
    if (!title) {
        alert("제목을 입력해 주세요.");
        return;
    }
    if (!email) {
        alert("이메일을 입력해 주세요.");
        return;
    }
    if (!name) {
        alert("이름을 입력해 주세요.");
        return;
    }
    if (!telno) {
        alert("전화번호를 입력해 주세요.");
        return;
    }
    if (!content) {
        alert("내용을 입력해 주세요.");
        return;
    }
    
    // 메일 내용 설정
    const subject = `${name}님이 문의하신 내용입니다.`;
    const to = "xpdlfys5032@kakao.com";
    const text = `
    <table>
    <tr><td>제목</td><td>${title}</td></tr>
    <tr><td>이메일</td><td>${email}</td></tr>
    <tr><td>이름</td><td>${name}</td></tr>
    <tr><td>전화번호</td><td>${telno}</td></tr>
    <tr><td>내용</td><td>${content}</td></tr>
    </table>
    `;
    
    // 확인용 경고창
    alert(`제목: ${title}\n이메일: ${email}\n이름: ${name}\n전화번호: ${telno}\n내용: ${content}`);
    
    // 숨겨진 입력 필드 생성
    const subjectInput = document.createElement('input');
    subjectInput.type = 'hidden';
    subjectInput.name = 'subject';
    subjectInput.value = subject;
    
    const toInput = document.createElement('input');
    toInput.type = 'hidden';
    toInput.name = 'to';
    toInput.value = to;
    
    const textInput = document.createElement('input');
    textInput.type = 'hidden';
    textInput.name = 'text';
    textInput.value = text;
    
    // 폼에 숨겨진 입력 필드 추가
    form.appendChild(subjectInput);
    form.appendChild(toInput);
    form.appendChild(textInput);
    
    // 폼 제출
    form.submit();
}

function editBoard(button) {
	var boardno = button.getAttribute('data-boardno');
	var descriptEl = button.parentElement.previousElementSibling.firstElementChild;
	var boardnameEl = descriptEl.parentElement.previousElementSibling.firstElementChild;
	var descript = descriptEl.value;
	var boardname = boardnameEl.value;
	
	var form = document.createElement('form');
	form.action = '/editBoard';
	form.method = 'post';
	
	var boardnoInput = document.createElement('input');
	boardnoInput.type = 'hidden';
	boardnoInput.name = 'board_no';
	boardnoInput.value = boardno;

	var boardnameInput = document.createElement('input');
	boardnameInput.type = 'hidden';
	boardnameInput.name = 'board_name';
	boardnameInput.value = boardname;
	
	var descriptInput = document.createElement('input');
	descriptInput.type = 'hidden';
	descriptInput.name = 'descript';
	descriptInput.value = descript;

	form.appendChild(boardnoInput);
	form.appendChild(boardnameInput);
	form.appendChild(descriptInput);
	
	document.body.appendChild(form);
	
	form.submit();
}

function deleteBoard(button) {
	var boardno = button.getAttribute('data-boardno');
	location.href = "/deleteBoard/" + boardno;	
}

function userSubmit(button) {
	var userid = button.getAttribute('data-userid');
	var permitEl = button.parentElement.previousElementSibling.firstElementChild;
	var userpwEl = permitEl.parentElement.previousElementSibling.firstElementChild;
	var userpw = userpwEl.value;
	var permit = permitEl.value;
	console.log(userpw);
	console.log(permit);
	
	var form = document.createElement('form');
	form.action = 'editUser';
	form.method = 'post';
	
	var useridInput = document.createElement('input');
	useridInput.type = 'hidden';
	useridInput.name = 'userid';
	useridInput.value = userid;

	var userpwInput = document.createElement('input');
	userpwInput.type = 'hidden';
	userpwInput.name = 'userpw';
	userpwInput.value = userpw;
	
	var permitInput = document.createElement('input');
	permitInput.type = 'hidden';
	permitInput.name = 'permit';
	permitInput.value = permit;

	form.appendChild(useridInput);
	form.appendChild(userpwInput);
	form.appendChild(permitInput);
	
	document.body.appendChild(form);
	
	form.submit();
}

function deleteUser(button) {
	var userid = button.getAttribute('data-userid');
	location.href = "/deleteUser/" + userid;	
}

function checkLoginForm() {
    const form = document.loginForm;
    const userid = document.getElementById('userid').value;
    const userpw = document.getElementById('userpw').value;

    if(userid.length < 3 || userid.length > 8) {
        window.alert('아이디는 3~8자로 입력해주세요.');
        form.userid.focus();
        return;
    }
    if(userpw.length < 4 || userpw.length > 20) {
        window.alert('패스워드는 4~20자로 입력해주세요.');
        form.userpw.focus();
        return;
    }
    form.submit();
}

function checkId() {
	var snd_data = $("#userid").val();
	$.ajax({
		type:"get",
		dataType:"text",
		async:true,
		url:"http://localhost:8090/checkid",
		data:{data:snd_data},
		success:function (data,textStatus) {
			if(data == "true") {
    			$("#id-area").html("<p>※ 사용 가능한 아이디입니다.</p>");
			}
			else {
				$("#id-area").html("<p>※ 사용할 수 없는 아이디입니다.</p>");
			}
		},
		error:function (data,textStatus) {
			window.alert("에러가 발생했습니다.");
			window.alert(textStatus);
		},
		complete:function (data,textStatus) {
		}
	})
}

function checkJoinForm() {
    const form = document.joinForm;
    const userid = document.getElementById('userid').value;
    const userpw = document.getElementById('userpw').value;
    const username = document.getElementById('username').value;
    const birthdate = document.getElementById('birthdate').value;
    const genderM = document.getElementById('genderM').checked;
    const genderF = document.getElementById('genderF').checked;
    const telnumber = document.getElementById('telnumber').value;
    const addr = document.getElementById('addr').value;

    if(userid.length < 3 || userid.length > 8) {
        window.alert('아이디는 3~8자로 입력해주세요.');
        form.userid.focus();
        return;
    }
	if(userpw.length < 4 || userpw.length > 20) {
	    window.alert('패스워드는 4~20자로 입력해주세요.');
	    form.userpw.focus();
	    return;
	}
    if(username == '') {
        window.alert('이름을 입력해주세요.');
        form.username.focus();
        return;
    }
    if(birthdate == '') {
        window.alert('생년월일을 입력해주세요.');
        form.birthdate.focus();
        return;
    }
    if(!genderM && !genderF) {
        window.alert('성별을 입력해주세요.');
        return;
    }
    if(telnumber == '') {
        window.alert('전화번호를 입력해주세요.');
        form.telnumber.focus();
        return;
    }
    if(addr == '') {
        window.alert('주소를 입력해주세요.');
        form.addr.focus();
        return;
    }
    window.alert("회원가입이 완료되었습니다.");
    form.submit();
}

function checkMemberForm() {
    const form = document.memberForm;
    const userpw = document.getElementById('userpw').value;
    const username = document.getElementById('username').value;
    const telnumber = document.getElementById('telnumber').value;
    const addr = document.getElementById('addr').value;

    if(username == '') {
        window.alert('이름을 입력해주세요.');
        form.username.focus();
        return;
    }
    if(telnumber == '') {
        window.alert('전화번호를 입력해주세요.');
        form.telnumber.focus();
        return;
    }
    if(addr == '') {
        window.alert('주소를 입력해주세요.');
        form.addr.focus();
        return;
    }
    window.alert("회원정보가 수정되었습니다.");
    form.submit();
}

function checkFormJquery() {
	const form = $('#joinForm');
	const userid = $('#userid').val();
	const userpw = $('#userpw').val();
	const username = $('#username').val();
	const birthdate = $('#birthdate').val();
	const genderM = $('#genderM').is(':checked');
	const genderF = $('#genderF').is(':checked');
	const telnumber = $('#telnumber').val();
	const addr = $('#addr').val();
	
	if(userid.length < 3 || userid.length > 8) {
		window.alert('아이디는 3~8자로 입력해주세요.');
		$('#userid').focus();
		return;
	}
	if(userpw.length < 4 || userpw.length > 20) {
		window.alert('패스워드는 4~20자로 입력해주세요.');
		$('#userpw').focus();
		return;
	}
    if(username == '') {
        window.alert('이름을 입력해주세요.');
        $('#username').focus();
        return;
    }
    if(birthdate == '') {
        window.alert('생년월일을 입력해주세요.');
        $('#birthdate').focus();
        return;
    }
    if(!genderM && !genderF) {
        window.alert('성별을 입력해주세요.');
        return;
    }
    if(telnumber == '') {
        window.alert('전화번호를 입력해주세요.');
        $('#telnumber').focus();
        return;
    }
    if(addr == '') {
        window.alert('주소를 입력해주세요.');
        $('#addr').focus();
        return;
    }
    window.alert("회원가입이 완료되었습니다.");
    form.submit();
}