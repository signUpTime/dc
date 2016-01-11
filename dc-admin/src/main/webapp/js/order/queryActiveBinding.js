function queryActiveBinding() {
	$("#activeBinding").empty();
	$
			.ajax({
				type : "post",
				url : "../addressBook/queryActiveBinding.do",
				async : false,// 设置为
				dataType : "json",
				success : function(data) {
					if (data.result == true) {
						var dataTable = "";
						var response = data.model;
						for ( var i = 0; i < response.length; i++) {
							dataTable += "<div class='data' id='"
									+ response[i].studentId
									+ "ActiveBindingDiv'>"
									+ "<span class='order_name'>家长昵称："
									+ response[i].studentName
									+ "("
									+ response[i].studentPhoneNum
									+ ")"
									+ "</span><br/>"
									+ "科目："
									+ response[i].courseName
									+ "<br/>"
									+ "授课老师："
									+ response[i].teacherName
									+ "("
									+ response[i].teacherPhoneNum
									+ ")"
									+ "<br/>"
									+ "家长请求时间："
									+ new Date(response[i].createTime)
											.format("yyyy-MM-dd hh:mm")
									+ "<br/>"
									+ "<span class='order_button' id='a4'>"
									+ "<input type='button' id='button' value='知道了' class='order_accept' onclick='aleadyKnowActiveBinding("
									+ response[i].studentId + ")'/>"
									+ "</span>" + "</div>";
						}
						$("#activeBindingDiv").append(dataTable);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
}

function aleadyKnowActiveBinding(studentId) {
	$.ajax({
		type : "post",
		url : "../addressBook/aleadyKnowActiveBinding.do",
		dataType : "json",
		async : false,// 设置为
		data : {
			"studentId" : studentId
		},
		success : function(data) {
			if (data.result == true) {
				$("#" + studentId + "ActiveBindingDiv").remove();
			} else {
				alert(data.resultMsg);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}

function refreshActiveBinding() {
	queryActiveBinding();
	setTimeout(function() {
		refreshActiveBinding();
	}, 903000);
}