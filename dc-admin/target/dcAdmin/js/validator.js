/**
 * 校验老师分成比例
 * @param rate
 */
function validatorTeacherRate(rate){
	if(rate>100 || rate<0){
		alert("请确认分成比例为0-100%");
		return false;
	};
	return true;
}

/**
 * 校验老师分成课次
 * @param rateCourseNum
 */
function validatorTeacherRateCourseNum(rateCourseNum){
	if(rateCourseNum>=1000 || rateCourseNum<0){
		alert("请确认课次为0-999");
		return false;
	}
	return true;
}

/**
 * 校验图片大小
 * @param target
 * @param maxSize
 * @returns {Boolean}
 */
var isIE = /msie/i.test(navigator.userAgent) && !window.opera; 
function checkFileSize(target,maxSize){
	var fileSize;
	if(isIE && !target.files){
		var fileSystem = new ActiveXObject("Scripting.FileSystemObject"); 
		if(!fileSystem.FileExists(target.value)){ 
			alert("请上传图片。"); 
			return false; 
		}
		var file = fileSystem.GetFile (target.value); 
		fileSize = file.Size; 
		
	}else{
		fileSize = target.files[0].size; 
	}
	var size = fileSize / 1024; 
	if(size>maxSize){ 
		alert("附件大小不能大于"+maxSize/1024+"M！"); 
		target.value =""; 
		return false; 
	}
	return true;
}