<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="de.irs.fopengine.web.model.User" %>
<%@ page import="de.irs.fopengine.web.model.Project" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">

<%
    DateFormat dfFormat = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
    Date dNow = new Date();
    String sDate =  dfFormat.format(dNow);
    User user = (User) session.getAttribute("user");

%>

<head>
    <meta charset="UTF-8">
    <title>project list</title>
    <link rel="stylesheet" type="text/css" href="css/fopengine.css"/>
</head>
<body>
<form  method="post" action="projectlist.do">
    <h2 >Prehled projektu</h2>
    <div>Date: <%= sDate%></div>
    <div>User: <%= user.getUsername()%></div>
    <br/>
    <table>
        <thead>
        <th>id</th>
        <th>name</th>
        <th>url</th>
        <th></th>

        </thead>
        <tbody>
<%
    List<Project> projects = (List<Project>) request.getAttribute("projects");
    for(Project project : projects) {
%>
        <tr>
        <td><%=project.getId()%></td>
        <td><%=project.getName()%></td>
        <td><%=project.getGitUrl()%></td>
        <td><button type="submit" name="btn_detail" value="<%=project.getId()%>">project detail</button></td>
        </tr>
<%
    }
%>
</tbody>
    </table>
    <br/>
    <button type="submit" name="btn_logout">logout</button>

</form>
</body>
</html>
