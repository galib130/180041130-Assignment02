import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class buyServlet extends HttpServlet{
protected void doGet(HttpServletRequest request,HttpServletResponse response)
    throws ServletException,IOException{

      ArrayList<String> buyList=new ArrayList<String>();

      ArrayList<Integer>countList=new ArrayList<>();

      PrintWriter writer=response.getWriter();
      response.setContentType ("text/html");
      request.getRequestDispatcher("navbar.html").include(request,response);

      HttpSession session=request.getSession();

      if(session.getAttribute("buyList")!=null){


            if(session.getAttribute("countList")!=null)
            countList= (ArrayList<Integer>) session.getAttribute("buyCount");
            System.out.println(session.getAttribute("buyList")+"  size");
            System.out.println(buyList.size());

                  buyList= (ArrayList<String>) session.getAttribute("buyList");


            writer.println ("<HTML>") ;
            writer.println ("<HEAD>") ;
            writer.println ("<TITLE> Servlet Response </TITLE>") ;
            writer.println ("</HEAD>") ;
            writer.println ("<BODY>") ;

            //Show checked out item
            writer.println("<h1>");
                  for (int i = 0; i < buyList.size(); i++) {
                        if (buyList.get(i).equals("car")) {
                              writer.println(buyList.get(i) + "   $" + 200 * countList.get(i));
                        } else {
                              writer.println(buyList.get(i) + "   $" + 100 * countList.get(i));
                        }
                  writer.println("<br>");

                  }

      //empty cart list
            writer.println("Total $: "+session.getAttribute("salesTotal"));
          writer.println("</h1>");
            session.removeAttribute("cart");
            session.removeAttribute("countList");
            session.setAttribute("total",0);

      }

}



}
