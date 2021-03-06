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

<title>ActiveMQ Demo程序</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<script type="text/javascript" src="resources/jquery-1.11.0.min.js"></script>
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
	
	function send(controller){
		if($("#message").val()==""){
			$("#message").css("border","1px solid red");
			return;
		}else{
			$("#message").css("border","1px solid gray");
		}
		$.ajax({
			type: 'post',
			url:'<%=basePath%>activemq/'+controller,
			dataType:'text', 
			data:{"message":$("#message").val()},
			success:function(data){
				if(data=="suc"){
					$("#status").html("<font color=green>发送成功</font>");
					setTimeout(clear,1000);
				}else{
					$("#status").html("<font color=red>"+data+"</font>");
					setTimeout(clear,5000);
				}
			},
			error:function(data){
				$("#message").val("Error!");
				$("#status").html("<font color=red>ERROR:"+data["status"]+","+data["statusText"]+"</font>");
				setTimeout(clear,5000);
			}
			
		});
	}
	
	function clear(){
		$("#status").html("");
	}

</script>
</head>

<body>
	<h1>ActiveMQ Demo</h1>
	<div id="producer">
		<h2>发送</h2>
		<textarea id="message"></textarea>
		<br>
		<br>
		<button onclick="send('queueSender')">发送Queue消息</button>
		<button onclick="send('topicSender')">发送Topic消息</button>
		<br>
		<span id="status"></span>
		<br>
		<h3><a href="activemq/queuelistener" target="_blank">新建queue监听器</a></h3>
		<h3><a href="activemq/topiclistener" target="_blank">新建topic监听器</a></h3>
	</div>
</body>
</html>
