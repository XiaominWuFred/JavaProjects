<%@page import = "tree.*" %>
<html>
<head><title>Welcome</title></head>
<body>

<br/>
<br/>
<br/>

<br/>
<% 

if("Load from file".equals(request.getParameter("option"))){
	FXGuiMaker.l(request.getParameter("fileName"));
}

if("Cursor to child (index number)".equals(request.getParameter("option"))){
	out.println(FXGuiMaker.c(Integer.parseInt(request.getParameter("index"))));
}

if("Add child (index, type, prompt for text)".equals(request.getParameter("option"))){
	
	out.println(FXGuiMaker.a(FXGuiMaker.type(request.getParameter("type")),request.getParameter("text"),Integer.parseInt(request.getParameter("index"))));
}


if("Cursor up (to parent)".equals(request.getParameter("option"))){
	
		out.println(FXGuiMaker.u());
	
}

if("Edit Text".equals(request.getParameter("option"))){
	
			out.println(FXGuiMaker.e(request.getParameter("text")));
		
}

if("Delete child (index number)".equals(request.getParameter("option"))){
	out.println(FXGuiMaker.d(Integer.parseInt(request.getParameter("index"))));
}

if("Cursor to root".equals(request.getParameter("option"))){
	
			out.println(FXGuiMaker.r());
	
}

if("Save to Text File".equals(request.getParameter("option"))){

	out.println(FXGuiMaker.s(request.getParameter("fileName")));
}

if("Export to FXML file (Extra Credit)".equals(request.getParameter("option"))){
	
			out.println(FXGuiMaker.x(request.getParameter("fileName")));
		
}


out.println(FXGuiMaker.p());

%>


<a href = "TreeForm.html">choose options</a>
</body>

</html>