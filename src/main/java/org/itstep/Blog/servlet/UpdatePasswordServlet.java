package org.itstep.Blog.servlet;

import org.itstep.Blog.db.DbManager;
import org.itstep.Blog.db.Operation;
import org.itstep.Blog.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/updatePassword")
public class UpdatePasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPassword = request.getParameter("old_password");
        String newPassword = request.getParameter("new_password");
        String confirmPassword = request.getParameter("confirm_password");

        User user = (User) request.getSession().getAttribute("USER_SESSION");

        String redirect = "/profile?error";

        if(user != null && user.getPassword().equals(oldPassword)){
            if(newPassword.equals(confirmPassword)){
                user.setPassword(newPassword);
                if(DbManager.addOrUpdateUser(Operation.Update, user)){
                    redirect = "/profile?success";
                }
            }
        }

        response.sendRedirect(redirect);
    }
}
