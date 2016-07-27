$(document).ready(function(e) {
	addstaff.addEvent();
});
var addstaff = {
	sendCoded: false,
	inputList: new Array(),
	btnList: new Array(),
	addEvent: function() {
		addstaff.inputList = [];
		addstaff.btnList = [];
		addstaff.inputList.push($(".phoneNumber"),$('.yzm'),$('.InviteCode'),$('.password'));
		addstaff.btnList.push($('.sendCode'), $("#regBtn1"), $("#regBtn2"));
		addstaff.btnList[0].click(
			function() {
				if (addstaff.sendCoded) {
					return;
				}
				var phoneNumber = addstaff.inputList[0].val();
				if (phoneNumber == "") {
					alert("请填写手机号码");
					return;
				}
				var myreg = /^0?1[3|4|5|8][0-9]\d{8}$/;
				if (!myreg.test(phoneNumber)) {
					alert("手机号码有误！ 请输入11位数字");
					return;
				}
//				var postData = {
//					JSON: '{"mobile":' + phoneNumber + '}',
//					"CODE": "103"
//				};
//				fnBase.commonAjax(Constant.URL, postData, function(data) {
//					console.log(data);
//					if (data.msgcode == "1") {
//						fnBase.myalert("发送成功");
//					}
					//					   else{
					//					       fnBase.myalert(data.msginfo);
					//					   }
//				})
				addstaff.sendCoded = true;
				addstaff.timecount();
			}
		);
		addstaff.btnList[1].click(
			function() {
				var phoneNumber = addstaff.inputList[0].val();
				if (phoneNumber == "") {
					alert("请填写手机号码")
					return;

				}
				var myreg = /^0?1[3|4|5|8][0-9]\d{8}$/;
				if (!myreg.test(phoneNumber)) {
					alert("手机号码有误！ 请输入11位数字");
					return;
				}
				var _yzm = addstaff.inputList[1].val();
				if (_yzm == "") {
					alert("请填写验证码");
					return;
				}
				var _invite = addstaff.inputList[2].val();
				if (_invite == "") {
					alert("请输入邀请码");
					return;
				}
				var _psw = addstaff.inputList[3].val();
				if (_psw == "") {
					alert("请输入密码");
					return;
				}
				
//				var postData = {
//					JSON: '{"mobile":"' + phoneNumber + '","password":"' + _psw + '","code":"' + _yzm + '"}',
//					"CODE": "102"
//				}
//				var postData = '{"mobile":"' + phoneNumber + '","password":"' + _psw + '"}'
//				localStorage.setItem('userData',postData);
//				window.history.back(-1); 
//				console.log(postData)
//				fnBase.commonAjax(Constant.URL, postData, function(data) {
//					console.log(data)
//					if (data.msgcode == "1") {
//						console.log(data)
//						fnBase.myalert("注册成功");
//						Storage.setUid(data.data.uid);
//
//						setTimeout(function() {
//							window.location.href = "usercenter.html"
//						}, 300)
//
//					}
//					//					else {
//					//						fnBase.myalert(data.msginfo);
//					//					}
//				})
				window.location.href = 'agetnstaff.html'
				
			}
		);
		addstaff.btnList[2].click(function(){
			window.location.href = 'agetnstaff.html'
		})
	},
	timecount: function() {
		var total = 60;
		var mytimecont;
		addstaff.btnList[0].text("60秒后重发");
		clearInterval(mytimecont);
		mytimecont = setInterval(function() {
			total = total - 1;
			var str = total + "秒后重发";
			addstaff.btnList[0].text(str);
			if (total <= 0) {
				clearInterval(mytimecont);
				addstaff.sendCoded = false;
				addstaff.btnList[0].text("发送验证码");
			}
		}, 1000);

	}

}