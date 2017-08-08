<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>ActiveMQ Demo 接收</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<script type="text/javascript" src="<%=basePath%>resources/jquery-1.11.0.min.js"></script>
<style type="text/css">
.h1 {
	margin: 0 auto;
}

#producer{
	width: 48%;
	height: 80%;
	align:center;
	margin:0 auto;
}

body{
	text-align :center;
} 
div {
	text-align :center;
}
textarea{
	width:80%;
	height:100px;
	border:1px solid gray;
}
button{
	background-color: rgb(62, 156, 66);
	border: none;
	font-weight: bold;
	color: white;
	height:30px;
}
</style>
<script type="text/javascript">

	function refresh(listener){
		$.ajax({
			type: 'post',
			url:'<%=basePath%>activemq/'+listener,
			dataType:'text',
			success:function(data){
				if(data == ""){
					$("#message"+listener).css("border","1px solid red");
					$("#status"+listener).html("<font color=red>没有收到新消息</font>");
					setTimeout(function() {
							$("#status"+listener).html("");
							$("#message"+listener).css("border","1px solid gray");
						}
						,2000);
				}else{
					$("#message"+listener).css("border","1px solid gray");
					$("#message"+listener).val(data+$("#message"+listener).val());
					$("#status"+listener).html("<font color=green>接收成功</font>");
					setTimeout(function() {
						$("#status"+listener).html("");
					}
					,1000);
				}
			},
			error:function(data){
				$("#message"+listener).val("Error!");
			}
		});
	}

</script>
</head>

<body>
	<h1>ActiveMQ Demo</h1>
	<div id="producer">
		<h2>Receiver</h2>
		<br>
		<textarea id="messagerefreshQueueListener1"></textarea><br><br>
		<button onclick="refresh('refreshQueueListener1')">刷新Queue监听器1</button>
		<br>
		<span id="statusrefreshQueueListener1"></span>
		<br>
		<textarea id="messagerefreshQueueListener2"></textarea><br><br>
		<button onclick="refresh('refreshQueueListener2')">刷新Queue监听器2</button>
		<br>
		<span id="statusrefreshQueueListener2"></span>
		<br>
		<textarea id="messagerefreshTopicListener1"></textarea><br><br>
		<button onclick="refresh('refreshTopicListener1')">刷新Topic监听器1</button>
		<br>
		<span id="statusrefreshTopicListener1"></span>
		<br>
		<textarea id="messagerefreshTopicListener2"></textarea><br><br>
		<button onclick="refresh('refreshTopicListener2')">刷新Topic监听器2</button>
		<br>
		<span id="statusrefreshTopicListener2"></span>
		<br>
		<h3><a href="send.jsp">返回发送页面</a></h3>
	</div>
	
</body>
</html>
