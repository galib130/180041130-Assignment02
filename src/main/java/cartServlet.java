import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class cartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException{

        ArrayList<String> cartList=new ArrayList<String>();
        ArrayList<Integer> countList= new ArrayList<Integer>();
        response.setContentType ("text/html");
        HttpSession session=request.getSession(false);
        //request.getRequestDispatcher("navbar.html").include(request,response);
        //request.getRequestDispatcher("navbar.html").include(request,response);

        if(session!=null) {
        cartList= (ArrayList<String>) session.getAttribute("cart");
        if((ArrayList<String>) session.getAttribute("countList")!=null)
            countList= (ArrayList<Integer>) session.getAttribute("countList");

        PrintWriter writer=response.getWriter();
        request.getRequestDispatcher("navbar.html").include(request,response);

        writer.println ("<HTML>") ;
        writer.println ("<HEAD>") ;
        writer.println ("<TITLE> Servlet Response </TITLE>") ;
        writer.println ("</HEAD>") ;
        writer.println ("<BODY>") ;


            if (cartList == null||session.getAttribute("cart")==null||cartList.size()==0||request.getParameter("checkout")!=null) {
                writer.println("Cart is empty");
            } else {
                System.out.println(cartList.size());
                System.out.println(countList.size());
                for (int i = 0; i < cartList.size(); i++) {

                    writer.println("<form action='addServlet'  method='post'> ");
                    writer.println(cartList.get(i));
                    writer.println("&nbsp &nbsp &nbsp");
                    if(cartList.get(i)=="car")
                    writer.println("$200");
                    else
                        writer.println("$100");
                    writer.println("<button type='submit' name=");
                    writer.println(i);
                    writer.println(">remove</button>");
                    writer.println("</form>");
                    writer.println("<form action='addServlet'  method='post'> ");
                    writer.println("<button type='submit' name=");
                    if(cartList.get(i)=="car")
                    {writer.println("addcar"+i);}
                    else
                    {writer.println("addbike"+i);}

                    writer.println(">add</button>");
                    //writer.println(session.getAttribute("count"+i));
                    writer.println(countList.get(i));
                    writer.println("<button type='submit' name=");
                    if(cartList.get(i)=="car")
                    {writer.println("subcar"+i);}
                    else
                    {writer.println("subbike"+i);}

                    writer.println(">subtract</button>");

                    writer.println("</form>");
                    if(session.getAttribute("total")!=null)

                    writer.println("<br>");
                }
                writer.println("total $"+session.getAttribute("total"));
                writer.println("<form action='addServlet' method='post'> ");

                writer.println("<button type='submit' name='checkout'>checkout</button>");
                writer.println("</form>");
            }


        }
        else
            request.getRequestDispatcher("login.html").include(request,response);
    }





}
