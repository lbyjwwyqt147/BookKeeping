/**
 * 只能由字母数字下划线组成
 */
jQuery.validator.addMethod("loginAccount", function(value, element, params) {
		return this.optional(element) || /^[a-zA-Z0-9_]+$/.test(value);
	}, "该字段值由字母、数字、下划线组成.");
	
/**
 * 只能由汉字字母数字下划线组成
 */
jQuery.validator.addMethod("userName", function(value, element, params) {
		return this.optional(element) || /^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$/.test(value);
	}, "该字段值由汉字、字母、数字、下划线组成.");

	
/**
 * 手机号码验证     
 */
jQuery.validator.addMethod("isMobile", function(value, element) {       
         var length = value.length;   
         var mobile = /^((1[3,5,8][0-9])|(14[5,7])|(17[0,1,6,7,8]))\d{8}$/;   
         return this.optional(element) || (length == 11 && mobile.test(value));     
    }, "手机格式不正确.");   	

/**
 * 只能输入字母或者数字
 */
jQuery.validator.addMethod("numberLetter", function(value, element, params) {
		return this.optional(element) || /^[A-Za-z0-9]+$/.test(value);
	}, "该字段值只能输入字母或者数字.");

/**
 * 只能输入中文、字母或者数字
 */
jQuery.validator.addMethod("chineseNumber", function(value, element, params) {
		return this.optional(element) || /^[a-zA-Z\u4e00-\u9fa5][a-zA-Z0-9\u4e00-\u9fa5]*$/.test(value);
	}, "该字段值只能输入中文、字母或者数字.");

/**
 * 只能输入中文、字母
 */
jQuery.validator.addMethod("chineseLetter", function(value, element, params) {
		return this.optional(element) || /^[A-Za-z\u4e00-\u9fa5]*$/.test(value);
	}, "该字段值只能输入中文或者字母.");
	
	
/**
 * 只能输入字母
 */
jQuery.validator.addMethod("letter", function(value, element, params) {
		return this.optional(element) || /^[A-Za-z]+$/.test(value);
	}, "该字段值只能输入字母.");
	