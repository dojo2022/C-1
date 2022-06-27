/**
 *
 */

 //②読み込まれた時に行う処理(画面表示時に画像を表示するために使う）---------------------------------------------
	var fileReader = new FileReader();

	// 読み込み後に実行する処理
	/* fileReader.onload = (function() { */

		// canvas にプレビュー画像を表示
		var canvas = document.getElementById('preview');

		var ctx = canvas.getContext('2d');
		var image = new Image();

		/*③ここ大事！34行目のvalue値のデータを取得してimage.srcに入れる！ ------------------------------*/
		image.src = document.getElementById("good_file").value;

		/* console.log(fileReader.result) */ // ← (確認用)

		image.onload = (function () {
			canvas.width = image.width;
			canvas.height = image.height;
			ctx.drawImage(image, 0, 0);
		});
	/* }); */
	// 画像読み込み
	//fileReader.readAsDataURL(obj.files[0]);

	/* console.log(fileReader.result)  */// ← (確認用)null



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
	var fileReader_2 = new FileReader();

	// 読み込み後に実行する処理
	/* fileReader_2.onload = (function() { */

		// canvas にプレビュー画像を表示
		var canvas_2 = document.getElementById('preview_2');

		var ctx_2 = canvas_2.getContext('2d');
		var image_2 = new Image();

		/*③ここ大事！34行目のvalue値のデータを取得してimage.srcに入れる！ ------------------------------*/
		image_2.src = document.getElementById("bad_file").value;

		/* console.log(fileReader_2.result) */ // ← (確認用)

		image_2.onload = (function () {
			canvas_2.width = image_2.width;
			canvas_2.height = image_2.height;
			ctx_2.drawImage(image_2, 0, 0);
		});
	/* }); */
	// 画像読み込み
	//fileReader_2.readAsDataURL(obj.files[0]);

	/* console.log(fileReader_2.result)  */// ← (確認用)null



	function previewImage_2(obj){

		var fileReader_2 = new FileReader();

		// 読み込み後に実行する処理
		fileReader_2.onload = (function() {

			// canvas にプレビュー画像を表示
			var canvas_2 = document.getElementById('preview_2');
			var ctx_2 = canvas_2.getContext('2d');
			var image_2 = new Image();
			image_2.src = fileReader_2.result;
			console.log(fileReader_2.result) // ← (確認用)

			image_2.onload = (function () {
				canvas_2.width = image_2.width;
				canvas_2.height = image_2.height;
				ctx_2.drawImage(image_2, 0, 0);
			});
		});
		// 画像読み込み
		fileReader_2.readAsDataURL(obj.files[0]);
		console.log(fileReader_2.result) // ← (確認用)null
	}



	 //②読み込まれた時に行う処理(画面表示時に画像を表示するために使う）---------------------------------------------
	var fileReader_3 = new FileReader();

	// 読み込み後に実行する処理
	/* fileReader_3.onload = (function() { */

		// canvas にプレビュー画像を表示
		var canvas_3 = document.getElementById('preview_3');

		var ctx_3 = canvas_3.getContext('2d');
		var image_3 = new Image();

		/*③ここ大事！34行目のvalue値のデータを取得してimage.srcに入れる！ ------------------------------*/
		image_3.src = document.getElementById("other_file").value;

		/* console.log(fileReader_3.result) */ // ← (確認用)

		image_3.onload = (function () {
			canvas_3.width = image_3.width;
			canvas_3.height = image_3.height;
			ctx_3.drawImage(image_3, 0, 0);
		});
	/* }); */
	// 画像読み込み
	//fileReader_3.readAsDataURL(obj.files[0]);

	/* console.log(fileReader_3.result)  */// ← (確認用)null



	function previewImage_3(obj){

		var fileReader_3 = new FileReader();

		// 読み込み後に実行する処理
		fileReader_3.onload = (function() {

			// canvas にプレビュー画像を表示
			var canvas_3 = document.getElementById('preview_3');
			var ctx_3 = canvas_3.getContext('2d');
			var image_3 = new Image();
			image_3.src = fileReader_3.result;
			console.log(fileReader_3.result) // ← (確認用)

			image_3.onload = (function () {
				canvas_3.width = image_3.width;
				canvas_3.height = image_3.height;
				ctx_3.drawImage(image_3, 0, 0);
			});
		});
		// 画像読み込み
		fileReader_3.readAsDataURL(obj.files[0]);
		console.log(fileReader_3.result) // ← (確認用)null
	}
