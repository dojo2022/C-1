/**
 *
 */

 document.getElementById('newForm').onsubmit = function () {
 			const userName = document.getElementById('newForm').User.value;
			const id = document.getElementById('newForm').New_ID.value;
			const new_pass = document.getElementById('newForm').New_Password.value;
			if (userName === '' || id === '' || new_pass === '') {
				window.alert('全て入力してください！');
				return false;
			}
		}