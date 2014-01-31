function isEmpty(tag) {
	if (!tag.value) {
		alert("This field is mandatory.");
		return false;
	} else {
		return true;
	}
}

function isNumeric(tag) {
	if (isNaN(tag.value)) {
		alert("Please enter numeric data only.");
		return false;
	} else {
		return true;
	}
}

function isNumericValue(tag) {
	if (isEmpty(tag) || isNaN(tag.value)) {
		alert("Please enter numeric data only.");
		return false;
	} else {
		return true;
	}
}

function isValidEmail(tag) {
	var x = tag.value;
	if(x == null || x.length == 0) {
		return true;
	}
	var atpos = x.indexOf("@");
	var dotpos = x.lastIndexOf(".");
	if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= x.length) {
		alert("Please enter a valid email address.");
		return false;
	} else {
		return true;
	}
}
