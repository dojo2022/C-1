/**
 *
 */

/*a-function confirmDel(e) {
		if (!window.confirm("本当にログアウトしますか？")) {
			e.preventDefault();
		}
	}*/

const mediasVol_def = function() {

	const audios = document.getElementByTagName('audio');
	const len1 = audios.length;
	for(let n=0; n<len1; n++) audios[n].volume = 0;
}

window/addEventListener('DOMContentLoaded' , function(){
	mediasVol_def();
},false);