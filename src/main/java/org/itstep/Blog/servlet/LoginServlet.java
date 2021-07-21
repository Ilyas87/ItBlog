package org.itstep.Blog.servlet;

import lombok.extern.java.Log;
import org.itstep.Blog.db.DbManager;
import org.itstep.Blog.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@Log
@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("Login servlet");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String redirect = "/login?email=" + email + "&error1";

        if (email != null && password != null){
            User dbUser = DbManager.getUserByEmail(email);
            if(dbUser != null){
                redirect = "/login?email=" + email + "&error2";
                if (dbUser.getPassword().equals(password)){
                    redirect = "/";
                    HttpSession session = request.getSession();
                    session.setAttribute("USER_SESSION", dbUser);
                }
            }
        }

        response.sendRedirect(redirect);
    }
}
