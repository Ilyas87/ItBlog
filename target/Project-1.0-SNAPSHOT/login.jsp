<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <%@include file="template/bootstrap.jsp"%>
</head>
<body>
<div class="container-fluid">
    <%@include file="template/navbar.jsp"%>
</div>
<div class="container mt-3">
    <div class="d-flex justify-content-center">
        <div class="col-sm-4">
            <%
                if(request.getParameter("error") != null && request.getParameter("error").equals("1")){
            %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                <label>User with email <%=request.getParameter("email")%> already exist!</label>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <%
            } else if(request.getParameter("error") != null && request.getParameter("error").equals("2")) {
            %>
            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                <label>Passwords do not match!</label>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <%
                }
            %>
            <form method="post" action="/login">
                <div class="mb-3">
                    <label class="form-label">EMAIL</label>
                    <input type="email" class="form-control" name="email" value="<%=request.getParameter("email") != null ? request.getParameter("email") : ""%>" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">PASSWORD</label>
                    <input type="password" class="form-control" name="password" required>
                </div>
                <button type="submit" class="btn btn-info">SIGN IN</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
