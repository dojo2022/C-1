let tableHtml = '' // HTMLを組み立てる変数

const modal = document.getElementById('easyModal');//ID名easyModalのドキュメント要素を取得する。
const buttonClose = document.getElementsByClassName('modalClose')[0];//modalCloseのドキュメント要素を取得する

const weeks = ['日', '月', '火', '水', '木', '金', '土']
const date = new Date()
let year = date.getFullYear()
let month = date.getMonth() + 1
const config = {
    show: 1,
}

function showCalendar(year, month) {
    for ( i = 0; i < config.show; i++) {
        const calendarHtml = createCalendar(year, month)
        const sec = document.createElement('section')
        sec.innerHTML = calendarHtml
        document.querySelector('#calendar').appendChild(sec)

        month++
        if (month > 12) {
            year++
            month = 1
        }
    }
}


function createCalendar(year, month) {
    const startDate = new Date(year, month - 1, 1) // 月の最初の日を取得
    const endDate = new Date(year, month,  0) // 月の最後の日を取得
    const endDayCount = endDate.getDate() // 月の末日
    const lastMonthEndDate = new Date(year, month - 2, 0) // 前月の最後の日の情報
    const lastMonthendDayCount = lastMonthEndDate.getDate() // 前月の末日
    const startDay = startDate.getDay() // 月の最初の日の曜日を取得
    let dayCount = 1 // 日にちのカウント
    let calendarHtml = '' // HTMLを組み立てる変数

    //headerに年月を表示
    document.getElementById('header').innerHTML = year +'/'+ month;
    //calendarHtml += '<h1>' + year  + '/' + month + '</h1>'
    calendarHtml += '<table class="o~iocha">'

    // 曜日の行を作成
    for (let i = 0; i < weeks.length; i++) {
        calendarHtml += '<td class="wow">' + weeks[i] + '</td>'
    }

    for (let w = 0; w < 6; w++) {
        calendarHtml += '<tr>'

        for (let d = 0; d < 7; d++) {
            if (w == 0 && d < startDay) {
                // 1行目で1日の曜日の前
                let num = lastMonthendDayCount - startDay + d + 1
                calendarHtml += '<td class="is-disabled_lastmonth">' + num + '</td>'
            } else if (dayCount > endDayCount) {
                // 末尾の日数を超えた
                let num = dayCount - endDayCount
                calendarHtml += '<td class="is-disabled_nextmonth">' + num + '</td>'
                dayCount++
            } else {
                calendarHtml += `<td class="calendar_td" data-date="${year}/${month}/${dayCount}">${dayCount}</td>`
                dayCount++
            }
        }
        calendarHtml += '</tr>'
    }
    calendarHtml += '</table>'

    return calendarHtml
}

function moveCalendar(e) {
    document.querySelector('#calendar').innerHTML = ''//ID名'calendar'を書き換える

    if (e.target.id === 'prev') {   //引数のイベントeの idが'prev'だったら
        month--

        if (month < 1) {
            year--
            month = 12
        }
    }

    if (e.target.id === 'next') {
        month++

        if (month > 12) {
            year++
            month = 1
        }
    }

    showCalendar(year, month)//カレンダーを表示する
}

document.querySelector('#prev').addEventListener('click', moveCalendar)//document.querySelector←Elementオブジェクト
//第一引数はイベント。2つめは関数
document.querySelector('#next').addEventListener('click', moveCalendar)



document.addEventListener("click", function(e) {
    if(e.target.classList.contains("calendar_td")) {
        var clickDate = e.target.dataset.date;
        document.getElementById('test_data2').value = clickDate;

        //日付のデータをPOSTで送り、LISTのデータを受け取る
        goAjax()


        //モーダルを開けよう
        document.getElementById('MH-content').textContent = clickDate+'のリスト';
        modal.style.display = 'block';//cssを編集。
    }
})


function showModal(){}



// バツ印がクリックされた時
buttonClose.addEventListener('click', modalClose);
function modalClose() {
  	modal.style.display = 'none';
	let el = document.querySelector("#pastList section");
	el.remove();
	tableHtml="";
}

// モーダルコンテンツ以外がクリックされた時
addEventListener('click', outsideClose);
function outsideClose(e) {
  if (e.target == modal) {
    modal.style.display = 'none';
    let el = document.querySelector("#pastList section");
	el.remove();
	tableHtml="";
  }
}



showCalendar(year, month)

//モーダルの中のHTML
function showPastList(data) {
	let date = new Date();
	let year = date.getFullYear();
	let month= date.getMonth()+1;
	let day = date.getDate();
	format = 'YYYY/MM/DD';
	format = format.replace(/YYYY/g,year);
	format = format.replace(/MM/g,month);
	format = format.replace(/DD/g,day);


	let clickDay = document.getElementById('test_data2').value;

	console.log(format);
	console.log(clickDay);

	let clickDate = new Date(clickDay);
	let today = new Date(format);

	console.log(clickDate);
	console.log(today);

	tableHtml += "<form method='post' action='/osilis/PastListServlet'>"
	tableHtml += "<table>";


	//作成日をクリックしたとき

	if(clickDay == format){

		tableHtml = "<button onclick='location.href=\"/osilis/TopServlet\"'> 今日のリストへ！ </button>"

	}else if(clickDate > today){

		tableHtml = "<p>また会いに来てね</p>"

	}else if(data[0].listCheck_tf === null){
		tableHtml = "<p>その日はリストを作ってません</p>"
	//その日に達成画面まで行ってなかった時
	}else if(data[0].listCheck_tf === false){

	    for(let i = 0 ; i < 6 ; i++){
	        tableHtml += "<tr>"
	        tableHtml += "<td >"+ data[i].event+"</td>";


	        if(data[i].check_tf === true){
	            tableHtml += "<td><input type='hidden' name='listNum' value='" +data[i].listNum+"'>";
	            tableHtml += "<input type='checkbox' name='check_tf' value='"+ data[i].list_dataNum +"' checked></td>";
	        }else if(data[i].check_tf === false){
	        	tableHtml += "<td><input type='hidden' name='listNum' value='" +data[i].listNum+"'>";
	            tableHtml += "<input type='checkbox' name='check_tf' value='"+ data[i].list_dataNum +"'></td>"
	        }
	    	tableHtml += "</tr>"

		}
		tableHtml += "</table>"
		tableHtml += "<input type='submit' name='達成報告' value='達成報告'>"
		tableHtml += "</form>"

	//その日に達成画面まで行ってた時
	}else if(data[0].listCheck_tf === true){
	    for(let i = 0 ; i < 6 ; i++){
	        tableHtml += "<tr>"
	        tableHtml += "<td class='good'>"+ data[i].event+"</td>";
	        tableHtml += "</tr>"

	    }
	    tableHtml += "</table>"
	}else{
		tableHtml = "<p>その日はリストを作ってませんs</p>"
	}





	const section = document.createElement('section')
	section.innerHTML = tableHtml
	document.querySelector('#pastList').appendChild(section)

}


//非同期通信について
	function goAjax(){

			//入力値を取得してくる

			let testData2 = document.getElementById('test_data2').value;


			//{変数名：中に入れるものみたいに書いて、複数の値をpostData変数に格納
			let postData = {

					data2:testData2,

					}


			//非同期通信始めるよ
			$.ajaxSetup({scriptCharset:'utf-8'});
			$.ajax({
				//どのサーブレットに送るか
				//ajaxSampleのところは自分のプロジェクト名に変更する必要あり。
				url: '/osilis/PastListServlet',
				//どのメソッドを使用するか
				type:"POST",
				//受け取るデータのタイプ
				dataType:"json",
				//何をサーブレットに飛ばすか（変数を記述）
				data: postData,
				//この下の２行はとりあえず書いてる（書かなくても大丈夫？）
				processDate:false,
				timeStamp: new Date().getTime()
			   //非同期通信が成功したときの処理
			})
				//dataがdata.idのdata
			  .done(function(data) {

					showPastList(data);
				// 今回は上の<div id="test"></div>の中に返ってきた文字列を入れる
				//idはJavaBeansのフィールド名
				//document.getElementById("test").innerText=data[0].listCheck_tf;

				/*
                let date = new Date(data.date);
				let clickDate = date.getFullYear() + '-'+ (date.getMonth()+1) + '-' + date.getDate();
				document.getElementById("test").innerText= clickDate;
                */

			  })
			  .fail(function(xr) {
				//非同期通信が失敗したときの処理
				//失敗とアラートを出す
                document.getElementById("test").innerText="リスト作成されていません。";
			  });
		}



