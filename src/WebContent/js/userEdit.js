	function previewImage(obj){

		var fileReader = new FileReader();

		// 読み込み後に実行する処理
		fileReader.onload = (function() {

			// canvas にプレビュー画像を表示
			var canvas = document.getElementById('preview');
			var ctx = canvas.getContext('2d');
			var image = new Image();
			image.src = fileReader.result;
			console.log(fileReader.result) // ← (確認用)

			image.onload = (function () {
				canvas.width = image.width;
				canvas.height = image.height;
				ctx.drawImage(image, 0, 0);
			});
		});
		// 画像読み込み
		fileReader.readAsDataURL(obj.files[0]);
		console.log(fileReader.result) // ← (確認用)null
	}

	//②読み込まれた時に行う処理(画面表示時に画像を表示するために使う）---------------------------------------------
	var fileReader = new FileReader();

	// 読み込み後に実行する処理
	fileReader.onload = (function() {
		// canvas にプレビュー画像を表示
		var canvas = document.getElementById('preview');
		var ctx = canvas.getContext('2d');
		var image = new Image();

		/*③ここ大事！34行目のvalue値のデータを取得してimage.srcに入れる！ ------------------------------*/
		image.src = document.getElementById("image_file").value;

		/* console.log(fileReader.result) */ // ← (確認用)

		image.onload = (function () {
			canvas.width = image.width;
			canvas.height = image.height;
			ctx.drawImage(image, 0, 0);
		});
	});
	// 画像読み込み
	fileReader.readAsDataURL(obj.files[0]);

	/* console.log(fileReader.result)  */// ← (確認用)null


	document.getElementById('form').onsubmit = function (event) {
			const old = document.getElementById('form').Password.value;
			const new_pass = document.getElementById('form').Change_Password.value;
			const again = document.getElementById('form').Check_Password.value;
			if (old === '' || new_pass === '' || again === '') {
				window.alert('全て入力してください！');
				return false;
			}
		}