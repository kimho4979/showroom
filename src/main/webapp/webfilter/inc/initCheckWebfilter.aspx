<%@ Page Language="C#" Debug="false" %>
<%@ Import Namespace="System.Net" %>
<%@ Import Namespace="System.Net.Sockets" %>
<%@ Import Namespace="System.Text" %>
<%
	string wfcontextRoot = "";
	string wfServerAddress = "wf.kpc.or.kr";

	IPAddress host = IPAddress.Parse(wfServerAddress);
	IPEndPoint hostep = new IPEndPoint(host, 80);
	Socket socket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
	socket.SetSocketOption(SocketOptionLevel.Socket, SocketOptionName.SendTimeout, 3000);
	socket.SetSocketOption(SocketOptionLevel.Socket, SocketOptionName.ReceiveTimeout, 3000);

	try{
		socket.Connect(hostep);
		Response.Write("<script type='text/javascript' src='" + wfcontextRoot + "/webfilter/js/webfilter.js' defer='defer'></script>");
		Response.Write("<iframe id='webfilterTargetFrame' name='webfilterTargetFrame' width='0' height='0' frameborder='0' scrolling='no' noresize></iframe>");
	}catch (SocketException){
		Response.Write("<iframe id='webfilterSmsFrame' name='webfilterSmsFrame' width='0' src='" + wfcontextRoot + "/webfilter/html/webfilterBypass.html' height='0' frameborder='0' scrolling='no' noresize></iframe>");
	}finally{
		socket.Close();
  }
%>