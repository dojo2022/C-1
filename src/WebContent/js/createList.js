/**
 *
 */

let date = new Date () ;
let dow = date.getDay();

let formHtml = "";

//土日、平日で分岐
if(dow === 0 || dow === 6){
	formHtml =	'<form method="post" action="/osilis/CreateListServlet">';
	formHtml +=	'<input type="radio" id="weekday" value="平日" name="week" class="weekday" checked><label for="weekday" >平日</label>';
	formHtml +=	'<input type="radio" id="weekend" value="休日" name="week" class="weekend"><label for="weekend">休日</label><br>';
	formHtml +=	'<input type="submit" id="weeklist" value="予定の作成">';
	formHtml += '</form>';
}else if(0<dow && dow<6){
	formHtml =	'<form method="post" action="/osilis/CreateListServlet">';
	formHtml +=	'<input type="radio" id="weekday" value="平日" name="week" class="weekday"><label for="weekday">平日</label>';
	formHtml +=	'<input type="radio" id="weekend" value="休日" name="week" class="weekend" checked><label for="weekend" >休日</label><br>';
	formHtml +=	'<input type="submit" id="weeklist" value="予定の作成">';
	formHtml += '</form>';
}
	const section = document.createElement('section');
	section.innerHTML = formHtml;
	document.querySelector('#select-week').appendChild(section);
