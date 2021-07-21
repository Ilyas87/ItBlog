package org.itstep.Blog.servlet;

import org.itstep.Blog.db.DbManager;
import org.itstep.Blog.db.Operation;
import org.itstep.Blog.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/updateProfile")
public class UpdateProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String fullname = request.getParameter("full_name");

        String redirect = "/profile?error=1";

        User user = DbManager.getUserByEmail(email);

        if (user != null) {
            user.setFullname(fullname);
            DbManager.addOrUpdateUser(Operation.Update, user);
            redirect = "/profile?success";
        }

        response.sendRedirect(redirect);
    }
}
