package org.itstep.Blog.servlet;

import org.itstep.Blog.db.DbManager;
import org.itstep.Blog.db.Operation;
import org.itstep.Blog.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        String fullname = request.getParameter("full_name");

        String redirect = "/register?error=1&email=" + email;

        User checkUser = DbManager.getUserByEmail(email);

        if (checkUser == null) {
            redirect = "/register?error=2";
            if (password.equals(confirmPassword)) {
                DbManager.addOrUpdateUser(Operation.Create, new User(null, email, password, fullname));
                redirect = "/register?success";
            }
        }

        response.sendRedirect(redirect);
    }
}
