<%@ page import="java.text.DateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="de.irs.fopengine.web.model.User" %>
<%@ page import="de.irs.fopengine.web.model.Project" %>
<%@ page import="de.irs.fopengine.web.model.Font" %>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
<%
    DateFormat dfFormat = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
    Date dNow = new Date();
    String sDate =  dfFormat.format(dNow);
    User user = (User) session.getAttribute("user");
    Project project = (Project) request.getAttribute("project");
%>
<head>
    <meta charset="UTF-8">
    <title>project detail</title>
</head>
<body>
<form  method="post" action="projectdetail.do">
    <h2 >Project detail</h2>
    project id: <%=project.getId()%>
    name: <%=project.getName()%>
    url: <%=project.getGitUrl()%>
    <br/>
    <br/>
    <h3>Fonts</h3>
    <table>
        <thead>
        <th>id</th>
        <th>name</th>
        <th></th>

        </thead>
        <tbody>
        <%
            for(Font font : project.getFonts()) {
        %>
        <tr>
            <td><%=font.getId()%></td>
            <td><%=font.getFontName()%></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <br/>
    <button type="submit" name="btn_logout">logout</button>
    <button type="submit" name="btn_back">back</button>
</form>
</body>
</html>
