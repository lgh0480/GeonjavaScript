/**
 * ajax.js : Asynchronous JavaScript And Xml
 */
//비동기 방식으로 처리해주는 것입니다.
let tableTag = document.createElement('table');
function ajaxFnc() {
	console.log("1");

	let xhtp = new XMLHttpRequest(); // Define a Callback Function은  다른 함수에 매개변수를 전달함
	xhtp.open("get", "EmpListServlet");  //get->요청방식 EmpListServlet -> 페이지 //JSON 데이터
	xhtp.send();
	xhtp.onreadystatechange = function() { //체인지가 시작될때마다 
		if (xhtp.readyState == 4 && xhtp.status == 200) {
			//console.log(xhtp.readyState, xhtp.status);
			//console.log(xhtp.responseText);
			let data = JSON.parse(xhtp.responseText);
			console.log(data);
			showEmpList(data, tableTag)
		}
	}
	//테이블 생성 구문 
	function showEmpList(data, tableTag) {

		tableTag.setAttribute('border', '1');

		let titleTr = document.createElement('tr');
		let titleTh = document.createElement('th');
		titleTh.setAttribute('colspan', '5');
		let title = document.createTextNode('사원정보');
		titleTh.appendChild(title);
		titleTr.appendChild(titleTh);
		tableTag.appendChild(titleTr);
		createHeader();

		for (let row of data) {
			let trTag = document.createElement('tr');
			trTag.setAttribute('id', row.employeeId);
			//이벤트 발생 하는 애들
			trTag.onmouseover = changeColor;
			trTag.onmouseout = orginalColor;
			trTag.onclick = trClick; //테이블에 tr부분을 클릭하면 
			//trTag.onclick = showDetail;
			for (let field in row) { //필드..(td)
				let tdTag = document.createElement('td');
				let text = document.createTextNode(row[field]);
				tdTag.appendChild(text);

				trTag.appendChild(tdTag);

			}
			tableTag.appendChild(trTag);
		}
		document.getElementById('show').appendChild(tableTag);
	}
}

ajaxFnc();


function createHeader() {
	let titles = ['id', 'first_name', 'last_name', 'email', 'hire_data'];
	let tr = document.createElement('tr');
	for (let field of titles) {
		let td = document.createElement('th');
		let text = document.createTextNode(field);
		td.appendChild(text);
		tr.appendChild(td);
	}
	tableTag.appendChild(tr);
}
function orginalColor() {
	this.style.backgroundColor = '';
}

function changeColor() {
	this.style.backgroundColor = 'yellow';
}
// 클릭하면 정보 보여줌.
/*function showDetail() {
	alert('detail');
	let inputs = document.getElementsByTagName('input');
	console.log(inputs);
	for (let i = 0; i < inputs.length; i++) {
		inputs[i].value = this.childNodes[i].childNodes[0].nodeValue;
	}
}*/
function trClick() {
	console.log(this.getAttribute('id')); //아이디 값을 불러오겠습니다.

	const eid = this.getAttribute('id');
	const xhtp = new XMLHttpRequest();
	xhtp.open('get', 'EmployeeServlet?eid=' + eid);
	xhtp.send();
	xhtp.onreadystatechange = function() {
		if (xhtp.readyState == 4 && xhtp.status == 200) {
			const data = JSON.parse(xhtp.responseText);
			document.querySelector('input[name="eid"]').value = data.employeeId;
			document.querySelector('input[name="last_name"]').value = data.lastName;
			document.querySelector('input[name="email"]').value = data.email;
			document.querySelector('input[name="hire_date"]').value = data.hireDate;
			document.querySelector('input[name="first_name"]').value = data.firstName;

		}
	}

}// end of - trClick

// 입력 되어있는 데이터 수정
function updateRow() {
	let id = document.getElementById('eid').value;
	let first_name = document.getElementById('first_name').value;
	let last_name = document.getElementById('eid').value;
	let email = document.getElementById('email').value;
	let hire_date = document.getElementById('hire_date').value;

	let array = [id, first_name, last_name, email, hire_date];


}
//저장 버튼 누르면 전송
// last_name, email, hire_date 체크


function frm_submit() {
	if (frm.eid.value == "") {
		alert("아이디 입력확인");
		frm.eid.focus();
		return;
	}
	//frm.submit(); 제대로 들어갔어
	let xhtp = new XMLHttpRequest();
	const id = document.querySelectorAll('input[name="eid"]')[0].value;
	const fn = document.querySelectorAll('input[name="first_name"]')[0].value;
	const ln = document.querySelectorAll('input[name="last_name"]')[0].value;
	const em = document.querySelectorAll('input[name="email"]')[0].value;
	const hd = document.querySelectorAll('input[name="hire_date"]')[0].value;
	const param = 'eid=' + id + '&last_name=' + ln + '&email=' + em + '&hire_date=' + hd + '&first_name=' + fn;
	//RegisterServlet?eid=10000&last_name=Hong&email=lgh0480%40gmail.com&hire_date=2021-07-14&first_name=이건희
	xhtp.open('get', 'RegisterServlet?' + param);
	xhtp.send();
	if (xhtp.readyState == 4 && xhtp.status == 200) {
		alert(id+"저장할끄야 할끄야!");
		// {id:? , first_name:? , last_name:? , email: ? , hire_date:? }
		console.log(xhtp.responseText);
		const data = JSON.parse(xhtp.responseText);

		//data.id;
		//data.first_name;
		//data.last_name;
		//data.email;
		//data.hire_date;

		let tr = document.createElement('tr');
		for (let field in data) {
			let td = document.createElement('td');
			let text = document.createTextNode(data[field]);
			td.appendChild(text);
			tr.append(td);
		}
		document.getElementsByTagName()[0].appendChild(tr);
	}
}
//화면 수정	
function frm_update() {
	if (frm.eid.value == "") {
		alert("입력한 값을 다시 확인하세요.");
		frm.eid.focus();
		return;
	}
	let xhtp = new XMLHttpRequest();
	const id = document.querySelectorAll('input[name="eid"]')[0].value;
	const fn = document.querySelectorAll('input[name="first_name"]')[0].value;
	const ln = document.querySelectorAll('input[name="last_name"]')[0].value;
	const em = document.querySelectorAll('input[name="email"]')[0].value;
	const hd = document.querySelectorAll('input[name="hire_date"]')[0].value;
	const param = 'eid=' + id + '&last_name=' + ln + '&email=' + em + '&hire_date=' + hd + '&first_name=' + fn;
	//RegisterServlet?eid=10000&last_name=Hong&email=lgh0480%40gmail.com&hire_date=2021-07-14&first_name=이건희
	xhtp.open('get', 'ModifyServlet?' + param);
	xhtp.send();
	if (xhtp.readyState == 4 && xhtp.status == 200) {
		alert("수정완 ");
		// {id:? , first_name:? , last_name:? , email: ? , hire_date:? }
		console.log(xhtp.responseText);
		const data = JSON.parse(xhtp.responseText);
		// data.employeeId 필드를 사용해서 테이블에서 tr의 id값이 같은 요소.
		const findTr = document.getElementById(data.employeeId);
		findTr.childNodes[1].childNodes[0].nodeValue = data.firstName;
		findTr.childNodes[2].childNodes[0].nodeValue = data.lastName;
		findTr.childNodes[3].childNodes[0].nodeValue = data.email;
		findTr.childNodes[4].childNodes[0].nodeValue = data.hireDate;




	}
} // end - frm_update()	
function frm_delete() {

	if (frm.eid.value == "") {
		alert("입력한 값을 다시 확인하세요.");
		frm.eid.focus();
		return;
	}
	let xhtp = new XMLHttpRequest();
	const id = document.querySelectorAll('input[name="eid"]')[0].value;
	const fn = document.querySelectorAll('input[name="first_name"]')[0].value;
	const ln = document.querySelectorAll('input[name="last_name"]')[0].value;
	const em = document.querySelectorAll('input[name="email"]')[0].value;
	const hd = document.querySelectorAll('input[name="hire_date"]')[0].value;
	const param = 'eid=' + id + '&last_name=' + ln + '&email=' + em + '&hire_date=' + hd + '&first_name=' + fn;
	//RegisterServlet?eid=10000&last_name=Hong&email=lgh0480%40gmail.com&hire_date=2021-07-14&first_name=이건희
	xhtp.open('get', 'DeleteServlet?' + param);
	xhtp.send();
	xhtp.onreadystatechange = function(){
	if (xhtp.readyState == 4 && xhtp.status == 200) {
		alert(id+"삭제할끄야! 할끄야! ");
		// {id:? , first_name:? , last_name:? , email: ? , hire_date:? }
		console.log(xhtp.responseText);
		document.getElementById(id).remove();
		
		}
	}
}	