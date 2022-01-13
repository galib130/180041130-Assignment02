import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet{

    protected void doPost(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,IOException{
        response.setContentType("text/html");
        PrintWriter out= response.getWriter();
        request.getRequestDispatcher("navbar.html").include(request,response);
        String username=request.getParameter("username");
        String password= request.getParameter("password");
        //check password and username
        if(password.equals("admin")&&username.equals("galib130")){
            out.println("Welcome " +username);
            HttpSession session=request.getSession();
            session.setAttribute("username",username);


        }
        //if password or username do not match
        else{
            out.println("Wrong username or password");
                request.getRequestDispatcher("login.html").include(request,response);
            }
        out.close();

    }

}
