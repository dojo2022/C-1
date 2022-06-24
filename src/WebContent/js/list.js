/**
 *
 */
 	function formSubmit(form){
 		//document.getElementById('tof').value = tf.checked;

		//console.log(tf.checked);

		form.submit();

	}


	const tableEl = document.getElementById("worktable")
	let count = tableEl.childElementCount;

	if(count === 0){
		const el =	document.getElementById("work");
		el.remove();
	}
