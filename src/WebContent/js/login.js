/**
 *
 */
var formObj = document.getElementById('form');
var errorMessageObj = document.getElementById('error_message');
formObj.onsubmit = function() {
  if (formObj.ID.value === "" && formObj.PW.value === "") {
    errorMessageObj.textContent = '※IDとパスワードを入力してください！';
    return false;
  }
  else if (formObj.ID.value === "") {
    errorMessageObj.textContent = '※IDを入力してください！';
    return false;
  }
  else if (formObj.PW.value === ""){
    errorMessageObj.textContent = '※パスワードを入力してください！';
    return false;
  }
  errorMessageObj.textContent = null;
};

formObj.onreset = function() {
  errorMessageObj.textContent = null;
};



 document.addEventListener('DOMContentLoaded', function() {
	  const targetElement = document.getElementById('pass');
	  const triggerElement = document.getElementById('password-check');
	  triggerElement.addEventListener('change', function() {
	    if ( this.checked ) {
	      targetElement.setAttribute('type', 'text');
	    } else {
	      targetElement.setAttribute('type', 'password');
	    }
	  }, false);
	}, false);