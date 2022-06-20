/**
 *
 */
	let tf = document.getElementById('tf');


 	function formSubmit(form){
 	document.getElementById('tof').value = tf.checked;

		console.log(tf.checked);

		form.submit();

	}