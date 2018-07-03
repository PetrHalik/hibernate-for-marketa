<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<form  method="post" action="login.do">
    <label for="userName">User name</label>
    <input type="text" name="userName" id="userName" >
    <label for="password" >Password</label>
    <input type="password" name="inputPassword" id="password" >
    <br/><br/>
    <div class="checkbox">
        <label>
            <input type="checkbox" name="rememberMe"> Remember me
        </label>
    </div>
    <br/>
    <button type="submit" name="btn_login">Login</button>
</form>
</body>
</html>

