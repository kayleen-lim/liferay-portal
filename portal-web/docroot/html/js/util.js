/**
 * Copyright (c) 2000-2006 Liferay, LLC. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

var submitCountdown = 0;

function $(id) {
	var item = null;
	
    if(typeof(id) == "string") {
    	item = document.getElementById(id);
	}
    else if(typeof(id) == "object") {
    	item = id;
	}
	
	return item;
}

if (!Array.prototype.push) {
	function array_push() {
		for(var i = 0; i < arguments.length; i++) {
			this[this.length] = arguments[i];
		}

		return this.length;
	}

	Array.prototype.push = array_push;
}

if (!Array.prototype.pop) {
	function array_pop(){
		lastElement = this[this.length - 1];
		this.length = Math.max(this.length - 1, 0);

		return lastElement;
	}

	Array.prototype.pop = array_pop;
}

function Array_iterate (func, options) {
	if (typeof(func) == "function") {
		for (var i = 0; i < this.length; i++) {
			if (func(this[i], i, options)) {
				break;
			}
		}
	}
}

Array.prototype.foreach = Array_iterate;

function changeOpacity (object, opacity) {
	opacity = (opacity >= 100) ? 99.999 : opacity;
	opacity = (opacity < 0) ? 0 : opacity;
    
	object.style.opacity = (opacity / 100);
	object.style.MozOpacity = (opacity / 100);
	object.style.KhtmlOpacity = (opacity / 100);
	object.style.filter = "alpha(opacity=" + opacity + ")";
}

function checkMaxLength(box, maxLength) {
	if ((box.value.length) >= maxLength) {
		box.value = box.value.substring(0, maxLength - 1);
	}
}

function checkTab(box) {
	if ((document.all) && (event.keyCode == 9)) {
		box.selection = document.selection.createRange();
		setTimeout("processTab(\"" + box.id + "\")", 0);
	}
}

function cloneObject(obj, recurse) {
    for (i in obj) {
        if (typeof obj[i] == 'object' && recurse) {
            this[i] = new cloneObject(obj[i], true);
        }
        else
            this[i] = obj[i];
    }
}

var Cookie = {
	create : function(name, value, days) {
		if (days) {
			var date = new Date();

			date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));

			var expires = "; expires=" + date.toGMTString();
		}
		else {
			var expires = "";
		}

		document.cookie = name + "=" + value + expires + "; path=/";
	},

	read : function(name) {
		var nameEQ = name + "=";

		var ca = document.cookie.split(';');

		for (var i = 0; i < ca.length; i++) {
			var c = ca[i];

			while (c.charAt(0) == ' ') {
				c = c.substring(1, c.length);
			}

			if (c.indexOf(nameEQ) == 0) {
				return c.substring(nameEQ.length, c.length);
			}
		}

		return null;
	},

	erase : function(name) {
		createCookie(name, "", -1);
	}
}

document.createInputElement = function(name) {
	if (is_ie) {
		var entry = document.createElement("<input name='" + name + "'></input>");
	}
	else {
		var entry = document.createElement("input");
		entry.name = name;
	}
	
	return entry;
}

function disableEsc() {
	if ((document.all) && (event.keyCode == 27)) {
		event.returnValue = false;
	}
}

var Element = {
	addClassName: function(element, className) {
		if (className && !this.hasClassName(element, className)) {
			var classArray = element.className.split(" ");
			classArray.push(className);
			element.className = classArray.join(" ");
		}
	},
	
	removeClassName: function(element, className) {
		if (className && this.hasClassName(element, className)) {
			var classArray = element.className.split(new RegExp("(^|\\s)" + className + "(\\s|$)"));
			element.className = classArray.join(" ");
		}
	},
	
	hasClassName: function(element, className) {
		var rt = false;
	
		if (element.className && className) {
			rt = element.className.match(new RegExp("(^|\\s)" + className + "(\\s|$)"));
		}
	
		return rt;
	}
}

var Event = {
	addHandler: function(obj, type, func) {
		if (type.indexOf("on") != 0) {
			type = "on" + type;
		}
		
	    var temp = obj[type];
	
		if (typeof obj[type] != "function") {
	        obj[type] = func;
	    }
		else {
	        obj[type] = function() {
	        	if (temp) {
		            temp();
	        	}
	
				func();
	        }
	    }
	},
	
	enterPressed: function(event) {
		if (!event) {
			event = window.event;
		}
		var keycode = event.keyCode;
		
		if (keycode == 13) {
			return true;
		}
		else {
			return false;
		}
	}
}

document.getElementsByClassName = function(className, parentElement) {
	var children = ($(parentElement) || document.body).getElementsByTagName('*');
	var elements = new Array();
	
	for (var i = 0; i < children.length; i++) {
		var child = children[i];
		if (Element.hasClassName(child, className)){
			elements.push(child);
		}
	}
	
	return elements;
}

function getSelectedIndex(col) {
	for (var i = 0; i < col.length; i++) {
		if (col[i].checked == true) {
			return i;
		}
	}

	return -1;
}

function getSelectedRadioName(col) {
	var i = getSelectedIndex(col);

	if (i == -1) {
		var radioName = col.name;

		if (radioName == null) {
			radioName = "";
		}

		return radioName;
	}
	else {
		return col[i].name;
	}
}

function getSelectedRadioValue(col) {
	var i = getSelectedIndex(col);

	if (i == -1) {
		var radioValue = col.value;

		if (radioValue == null) {
			radioValue = "";
		}

		return radioValue;
	}
	else {
		return col[i].value;
	}
}

function listChecked(form) {
	var s = "";

	for (var i = 0; i < form.elements.length; i++) {
		var e = form.elements[i];

		if ((e.type == "checkbox") && (e.checked == true) && (e.value > "")) {
			s += e.value + ",";
		}
	}

	return s;
}

function listCheckedExcept(form, except) {
	var s = "";

	for (var i = 0; i < form.elements.length; i++) {
		var e = form.elements[i];

		if ((e.type == "checkbox") && (e.checked == true) && (e.value > "") && (e.name.indexOf(except) != 0)) {
			s += e.value + ",";
		}
	}

	return s;
}

function listSelect(box, delimeter) {
	var s = "";

	if (delimeter == null) {
		delimeter = ",";
	}

	if (box == null) {
		return "";
	}

	for (var i = 0; i < box.length; i++) {
    	if (box.options[i].value > "") {
			s += box.options[i].value + delimeter;
		}
	}

	if (s == ".none,") {
		return "";
	}
	else {
		return s;
	}
}

function listUnchecked(form) {
	var s = "";

	for (var i = 0; i < form.elements.length; i++) {
		var e = form.elements[i];

		if ((e.type == "checkbox") && (e.checked == false) && (e.value > "")) {
			s += e.value + ",";
		}
	}

	return s;
}

function listUncheckedExcept(form, except) {
	var s = "";

	for (var i = 0; i < form.elements.length; i++) {
		var e = form.elements[i];

		if ((e.type == "checkbox") && (e.checked == false) && (e.value > "") && (e.name.indexOf(except) != 0)) {
			s += e.value + ",";
		}
	}

	return s;
}

function moveItem(fromBox, toBox, sort) {
	var newText = null;
	var newValue = null;
	var newOption = null;

	if (fromBox.selectedIndex >= 0) {
		for (var i = 0; i < fromBox.length; i++) {
			if (fromBox.options[i].selected) {
				newText = fromBox.options[i].text;
				newValue = fromBox.options[i].value;

				newOption = new Option(newText, newValue);

				toBox[toBox.length] = newOption;
			}
		}

		for (var i = 0; i < toBox.length; i++) {
			for (var j = 0; j < fromBox.length; j++) {
				if (fromBox[j].value == toBox[i].value) {
					fromBox[j] = null;

					break;
				}
			}
		}
	}

	if (newText != null) {
		if (sort == true) {
			sortBox(toBox);
		}
	}
}

function processTab(id) {
	document.all[id].selection.text = String.fromCharCode(9);
	document.all[id].focus();
}

function reelHome(id, startPosX, startPosY, duration, count, c) {
    if (isNaN(startPosX) || isNaN(startPosY)) {
        return;
	}
        
	var obj = document.getElementById(id);

	if (obj == null) {
		return;
	}
	
	var top = parseInt(obj.style.top);
	var left = parseInt(obj.style.left);
	
	if (count == null) {
	    count = 1;
	}
	
	if (duration == null) {
	    duration == 20;
	}
	
	if (c == null) {

		// Calculate this constant once to speed up next iteration

		c = Math.PI / (2 * duration);
		obj.style.zIndex = 10;
    }
	
	if (count < duration) {
	    var ratio = 1 - Math.sin(count * c);
	    
	    // Shift cos by -PI/2 and up 1

		obj.style.left = (startPosX * ratio) + "px";
		obj.style.top = (startPosY * ratio) + "px";
		
		setTimeout("reelHome(\"" + id + "\"," + startPosX + "," + startPosY + "," + duration + "," + (++count) + "," + c + ")", 16);
	}
	else {
		obj.style.top = "0px";
		obj.style.left = "0px";
		obj.style.zIndex = 0;
	}
}

function removeItem(box, value) {
	if (value == null) {
		for (var i = box.length - 1; i >= 0; i--) {
			if (box.options[i].selected) {
				box[i] = null;
			}
		}
	}
	else {
		for (var i = box.length - 1; i >= 0; i--) {
			if (box.options[i].value == value) {
				box[i] = null;
			}
		}
	}
}

function reorder(box, down) {
	var si = box.selectedIndex;

	if (si == -1) {
		box.selectedIndex = 0;
	}
	else {
		sText = box.options[si].text;
		sValue = box.options[si].value;

		if ((box.options[si].value > "") && (si > 0) && (down == 0)) {
			box.options[si].text = box.options[si - 1].text;
			box.options[si].value = box.options[si - 1].value;
			box.options[si - 1].text = sText;
			box.options[si - 1].value = sValue;
			box.selectedIndex--;
		}
		else if ((si < box.length - 1) && (box.options[si + 1].value > "") && (down == 1)) {
			box.options[si].text = box.options[si + 1].text;
			box.options[si].value = box.options[si + 1].value;
			box.options[si + 1].text = sText;
			box.options[si + 1].value = sValue;
			box.selectedIndex++;
		}
		else if (si == 0) {
			for (var i = 0; i < (box.length - 1); i++) {
				box.options[i].text = box.options[i + 1].text;
				box.options[i].value = box.options[i + 1].value;
			}

			box.options[box.length - 1].text = sText;
			box.options[box.length - 1].value = sValue;

			box.selectedIndex = box.length - 1;
		}
		else if (si == (box.length - 1)) {
			for (var j = (box.length - 1); j > 0; j--) {
				box.options[j].text = box.options[j - 1].text;
				box.options[j].value = box.options[j - 1].value;
			}

			box.options[0].text = sText;
			box.options[0].value = sValue;

			box.selectedIndex = 0;
		}
	}
}

function resubmitCountdown(formName) {
	if (submitCountdown > 0) {
		submitCountdown--;

		setTimeout("resubmitCountdown('" + formName + "')", 1000);
	}
	else {
		submitCountdown = 0;

		if (!is_ns_4) {
			document.body.style.cursor = "auto";
		}
	
		var form = document.forms[formName];

		for (var i = 0; i < form.length; i++){
			var e = form.elements[i];

			if (e.type && (e.type.toLowerCase() == "button" || e.type.toLowerCase() == "reset" || e.type.toLowerCase() == "submit")) {
				e.disabled = false;
			}
		}
	}
}

function selectAndCopy(el) {
	el.focus();
	el.select();

	if (document.all) {
		var textRange = el.createTextRange();

		textRange.execCommand("copy");
	}
}

function setBox(oldBox, newBox) {
	for (var i = oldBox.length - 1; i > -1; i--) {
		oldBox.options[i] = null;
	}

	for (var i = 0; i < newBox.length; i++) {
		oldBox.options[i] = new Option(newBox[i].value, i);
	}

	oldBox.options[0].selected = true;
}

function setCursorPosition(oInput,oStart,oEnd) {
   if( oInput.setSelectionRange ) {
         oInput.setSelectionRange(oStart,oEnd);
     } 
     else if( oInput.createTextRange ) {
        var range = oInput.createTextRange();
        range.collapse(true);
        range.moveEnd('character',oEnd);
        range.moveStart('character',oStart);
        range.select();
     }
}

function setSelectedValue(col, value) {
	for (var i = 0; i < col.length; i++) {
		if ((col[i].value != "") && (col[i].value == value)) {
			col.selectedIndex = i;

			break;
		}
	}
}

function setSelectVisibility(mode, obj) {
	if (is_ie) {
		if (obj) {
			obj = $(obj);
		}
		else {
			obj = document.getElementsByTagName("body")[0];
		}
		
		selectList = obj.getElementsByTagName("select");
		for (var i = 0; i < selectList.length; i++) {
			selectList[i].style.visibility = mode;
		}
	}
}

function slideMaximize(id, height, speed) {
	var obj = document.getElementById(id);
	var reference = obj.getElementsByTagName("DIV")[0];

	height += speed;

	if (height < (reference.offsetHeight)) {
		obj.style.height = height + "px";

		setTimeout("slideMaximize(\"" + id + "\"," + height + "," + speed + ")", 10);
	}
	else {
		obj.style.overflow = "";
		obj.style.height = "";
	}
}

function slideMinimize(id, height, speed) {
	var obj = document.getElementById(id);

	height -= speed;

	if (height > 0) {
		obj.style.height = height + "px";
		setTimeout("slideMinimize(\"" + id + "\"," + height + "," + speed + ")", 10);
	}
	else {
		obj.style.display = "none";
	}
}

function sortBox(box) {
	var newBox = new Array();

	for (var i = 0; i < box.length; i++) {
		newBox[i] = new Array(box[i].value, box[i].text);
	}

	newBox.sort(sortByAscending);

	for (var i = box.length - 1; i > -1; i--) {
		box.options[i] = null;
	}

	for (var i = 0; i < newBox.length; i++) {
		box.options[box.length] = new Option(newBox[i][1], newBox[i][0]);
	}
}

function sortByAscending(a, b) {
	if (a[1].toLowerCase() > b[1].toLowerCase()) {
		return 1;
	}
	else if(a[1].toLowerCase() < b[1].toLowerCase()) {
		return -1;
	}
	else {
		return 0;
	}
}

function sortByDescending(a, b) {
	if (a[1].toLowerCase() > b[1].toLowerCase()) {
		return -1;
	}
	else if(a[1].toLowerCase() < b[1].toLowerCase()) {
		return 1;
	}
	else {
		return 0;
	}
}

function submitForm(form, action, singleSubmit) {
	if (submitCountdown == 0) {
		submitCountdown = 10;

		setTimeout("resubmitCountdown('" + form.name + "')", 1000);
		
		if (singleSubmit == null || singleSubmit) {
			submitCountdown++;

			for (var i = 0; i < form.length; i++){
				var e = form.elements[i];

				if (e.type && (e.type.toLowerCase() == "button" || e.type.toLowerCase() == "reset" || e.type.toLowerCase() == "submit")) {
					e.disabled = true;
				}
			}
		}

		if (action != null) {
			form.action = action;
		}

		if (!is_ns_4) {
			document.body.style.cursor = "wait";
		}

		form.submit();
	}
	else {
		if (this.submitFormAlert != null) {
  			submitFormAlert(submitCountdown);
		}
	}
}

// Netscape 4 functions

if (is_ns_4) {
	function encodeURIComponent(uri) {
		return escape(uri);
	}

	function decodeURIComponent(uri) {
		return unescape(uri);
	}
}

// String functions

function startsWith(str, x) {
	if (str.indexOf(x) == 0) {
		return true;
	}
	else {
		return false;
	}
}

function endsWith(str, x) {
	if (str.lastIndexOf(x) == str.length - x.length) {
		return true;
	}
	else {
		return false;
	}
}

function toggleById(id, returnState, displayType) {
	var obj = document.getElementById(id);

	if (returnState) {
		return toggleByObject(obj, returnState, displayType);
	}
	else {
		toggleByObject(obj, null, displayType);
	}
}

function toggleByIdSpan(obj, id) {
	var hidden = toggleById(id, true);
	var spanText = obj.getElementsByTagName("span");

	if (hidden) {
		spanText[0].style.display = "none";
		spanText[1].style.display = "";
	}
	else {
		spanText[0].style.display = "";
		spanText[1].style.display = "none";
	}
}

function toggleByObject(obj, returnState, displayType) {
	var hidden = false;
	var display = "block";

	if (displayType != null) {
		display = displayType;
	}

	if (obj != null) {
		if (!obj.style.display || !obj.style.display.toLowerCase().match("none")) {
			obj.style.display = "none";
		}
		else {
			obj.style.display = display;
			hidden = true;
		}
	}
	
	if (returnState) {
		return hidden;
	}
}

function trimString(str) {
	str = str.replace(/^\s+/g, "").replace(/\s+$/g, "");

	var charCode = str.charCodeAt(0);

	while (charCode == 160) {
		str = str.substring(1, str.length);
		charCode = str.charCodeAt(0);
	}

	charCode = str.charCodeAt(str.length - 1);

	while (charCode == 160) {
		str = str.substring(0, str.length - 1);
		charCode = str.charCodeAt(str.length - 1);
	}

	return str;
}

String.prototype.trim = trimString;

var ZINDEX = {
	ALERT: 100,
	DRAGITEM: 10
}