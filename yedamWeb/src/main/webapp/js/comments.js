/**
 * 
 */
window.onload = function() { //화면이 로딩될때 
	loadCommentList();
}

// 목록조회 ..
function loadCommentList() {
	// ajax ... 호출.. 서블릿 호출..
	let xhtp = new XMLHttpRequest(); //특정 위치에있는 파일을 읽어오는거 
	xhtp.open('get', '../CommentsServ?cmd=selectAll');
	xhtp.send();
	xhtp.onreadystatechange = loadCommentResult;
}

// 조회결과..
function loadCommentResult() {
	if (this.readyState == 4 && this.status == 200) {
		let xmp = new DOMParser();
		let xmlDoc = xmp.parseFromString(this.responseText, 'text/xml');
		let code = xmlDoc.getElementsByTagName('code')[0].innerHTML;
		console.log(code);
		let listDiv = document.getElementById('commentList');
		if (code == 'success') {
			let commentList = eval(xmlDoc.getElementsByTagName('data')[0].innerHTML);
			console.log(commentList);
			for (let i = 0; i < commentList.length; i++) {
				console.log(commentList[i]);
				listDiv.appendChild(makeCommentView(commentList[i]));
			}
		}
		//eval 사용함으로 xml 형식을 알아서 계산 가능
		console.log(commentList);

	}
	//console.log(xmlDoc);

}

// 한건을 화면보여줌
function makeCommentView(comment) {
	let div = document.createElement('div');
	div.setAttribute('id', comment.id);
	div.className = 'comment';
	div.comment = comment; // {id:1, name:'user', content:'test'}
	let str = '<strong>' + comment.name + '</strong>' +
		comment.content +
		'<input type="button" value="수정" onclick="viewUpdateForm('+ comment.id + ')">' +
		'<input type="button" value="삭제" onclick="confirmDeletion('+ comment.id + ')">';
	div.innerHTML = str;// <div><strong>ddd</strong></div>
	return div;
}
// 한건 등록..
function addComment() {
	let name = addForm.name.value;
	if (name == "") {
		alert("이름 입력..");
		addForm.name.focus();
		return;
	}
	let content = addForm.content.value;
	if (content == "") {
		alert("내용 입력..");
		addForm.content.focus();
		return;
	}
	let param = "&name=" + name + "&content=" + content;
	// ajax 호출..
	let xhtp = new XMLHttpRequest(); //특정 위치에있는 파일을 읽어오는거 
	xhtp.open('get', '../CommentsServ?cmd=insert' + param);
	xhtp.send();
	
	xhtp.onreadystatechange = addResult;
}
// 등록 콜백 함수.
function addResult() {
	if (this.readyState == 4 && this.status == 200) {
		let xmp = new DOMParser();
		let xmlDoc = xmp.parseFromString(this.responseText, 'text/xml');
		let code = xmlDoc.getElementsByTagName('code').item(0).innerHTML;
		console.log(xmlDoc);
		let listDiv = document.getElementById('commentList');
		if (code == "success") {
			let comment = JSON.parse(xmlDoc.getElementsByTagName('data').item(0).innerHTML);
			
			listDiv.appendChild(makeCommentView(comment));
			addForm.name.value = '';
			addForm.content.value ='';
			alert("등록했습니다! ["+comment.id + " ] ");
		} else if (code == "error") {
			alert("비상 오대기 비상! 에러!! 에러!! 에러!!! ");
		}
		console.log(xmlDoc);
	}
}

//수정 화면..
function viewUpdateForm(commentId) {
	let commentDiv = document.getElementById(commentId); //id 값으로 요소릉 찾아오겠음 <div></div>
	let updateFormDiv = document.getElementById('commentUpdate');
	
	commentDiv.appendChild(updateFormDiv); // 수정화면에 id 기준으로 정보를 보여줌.
	let comment = commentDiv.comment; // id, name, content 정보불러 오겠음. makeCommentView(comment)에 만들어놨던것
	updateForm.id.value = comment.id;
	updateForm.name.value = comment.name;
	updateForm.content.value = comment.content;
	updateFormDiv.style.display = 'block';
	
}