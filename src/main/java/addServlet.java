import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class addServlet extends  HttpServlet{
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException{
        String carButton= request.getParameter("car");
        String bikeButton= request.getParameter("bike");
        String checkoutButton=request.getParameter("checkout");

        ArrayList<String> cartList=new ArrayList<String>();
        ArrayList<Integer>countList=new ArrayList<Integer>();
        response.setContentType ("text/html");
        int total=0;
        HttpSession session=request.getSession(false);
        //request.getRequestDispatcher("navbar.html").include(request,response);
    if(session!=null) {
        //if car is added to cart
        if (carButton != null) {
            if (session.getAttribute("cart") != null) {
                cartList = (ArrayList<String>) session.getAttribute("cart");
                cartList.add("car");
                session.setAttribute("cart", cartList);
                session.setAttribute("count" + (cartList.size() - 1), 1);
                if (session.getAttribute("countList") != null)
                    countList = (ArrayList<Integer>) session.getAttribute("countList");
                countList.add(1);

                if (session.getAttribute("total") != null) {
                    total = (int) session.getAttribute("total");
                }
                total += 200;
                session.setAttribute("total", total);


                session.setAttribute("countList", countList);
            } else {
                cartList.add("car");
                session.setAttribute("cart", cartList);
                countList.add(1);
                session.setAttribute("countList", countList);

                // session.setAttribute("count"+(cartList.size()-1),1);
                session.setAttribute("count" + (cartList.size() - 1), 1);

                session.setAttribute("countList", countList);
                total += 200;
                session.setAttribute("total", total);

            }
            System.out.println(cartList.size() - 1);
        }
        //add bike to cart
        else if (bikeButton != null) {

            if (session.getAttribute("cart") != null) {
                cartList = (ArrayList<String>) session.getAttribute("cart");
                cartList.add("bike");
                session.setAttribute("cart", cartList);

                session.setAttribute("count" + (cartList.size() - 1), 1);

                if (session.getAttribute("countList") != null)
                    countList = (ArrayList<Integer>) session.getAttribute("countList");
                countList.add(1);

                session.setAttribute("countList", countList);

                if (session.getAttribute("total") != null) {
                    total = (int) session.getAttribute("total");
                }
                total += 100;
                session.setAttribute("total", total);

            } else {
                cartList.add("bike");
                session.setAttribute("cart", cartList);
                session.setAttribute("count" + (cartList.size() - 1), 1);
                countList.add(1);
                session.setAttribute("countList", countList);
                total += 100;
                session.setAttribute("total", total);

            }

        }

        //remove button, updating the list, updating the total, removing the item
        if (cartList != null) {
            if (session.getAttribute("cart") != null)
                cartList = (ArrayList<String>) session.getAttribute("cart");
            if (session.getAttribute("countList") != null)
                countList = (ArrayList<Integer>) session.getAttribute("countList");
            total = (int) session.getAttribute("total");
            for (int i = 0; i < cartList.size(); i++) {
                String index = request.getParameter(String.valueOf(i));
                if (index != null) {
                    if (cartList.get(i) == "car") {
                        total -= 200 * countList.get(i);
                        session.setAttribute("total", total);
                    } else if (cartList.get(i) == "bike") {
                        total -= 100 * countList.get(i);
                        session.setAttribute("total", total);
                    }
                    cartList.remove(i);
                    countList.remove(i);
                    //session.removeAttribute("count");
                    System.out.println("success");
                    session.setAttribute("cart", cartList);
                    session.setAttribute("countList", countList);
                    response.sendRedirect("cartServlet");
                    break;
                }
            }

        }
        //if add or remove button is pressed
        if (cartList != null) {
            //cartList= (ArrayList<String>) session.getAttribute("cart");
            int count = 0;
            int cartcount = 0;
            System.out.println("else if ");
            for (int i = 0; i < cartList.size(); i++) {

                String index_car_add = request.getParameter(String.valueOf("addcar" + i));

                String index_bike_add = request.getParameter(String.valueOf("addbike" + i));
                String index_car_sub = request.getParameter(("subcar") + i);
                String index_bike_sub = request.getParameter(("subbike") + i);
                if (index_car_add != null || index_bike_add != null || index_bike_sub != null || index_car_sub != null) {
                    System.out.println("add ");

                    if (session.getAttribute("count" + i) != null) {
                        count = (int) session.getAttribute("count" + i);

                        countList = (ArrayList<Integer>) session.getAttribute("countList");

                        cartcount = countList.get(i);


                        System.out.println("old " + count);
                    } else {
                        session.setAttribute("count" + i, 1);
                        count = (int) session.getAttribute("count" + i);

                        countList = (ArrayList<Integer>) session.getAttribute("countList");

                        cartcount = countList.get(i);


                        System.out.println("new " + count);
                    }
                    if (index_bike_add != null || index_car_add != null) {
                        count++;
                        cartcount++;

                        if (index_bike_add != null) {
                            total = (int) session.getAttribute("total");
                            total += 100;
                            session.setAttribute("total", total);
                        } else {
                            total = (int) session.getAttribute("total");
                            total += 200;
                            session.setAttribute("total", total);
                        }

                    } else {
                        count--;

                        if (cartcount != 1) {
                            cartcount--;

                            if (index_bike_sub != null) {
                                total = (int) session.getAttribute("total");
                                total -= 100;
                                session.setAttribute("total", total);
                            } else {
                                total = (int) session.getAttribute("total");
                                total -= 200;
                                session.setAttribute("total", total);
                            }
                        }

                    }

                    countList.set(i, cartcount);
                    session.setAttribute("count" + i, count);
                    session.setAttribute("countList", countList);
                    response.sendRedirect("cartServlet");
                    break;
                }
            }
          //if checkout button is pressed on cart page
            if (checkoutButton != null) {

                if (session.getAttribute("cart") != null) {
                    cartList = (ArrayList<String>) session.getAttribute("cart");
                    countList = (ArrayList<Integer>) session.getAttribute("countList");

                    session.setAttribute("buyList", cartList);
                    session.setAttribute("buyCount", countList);

                }
                System.out.println(session.getAttribute("buylist") + " testsize");


                //cartList.clear();
                if (session.getAttribute("total") != null)
                    session.setAttribute("salesTotal", session.getAttribute("total"));
                //session.setAttribute("cart",cartList);
                response.sendRedirect("buyServlet");


            }
        }

        //if only home button is pressed
        request.getRequestDispatcher("home.html").include(request, response);
    }
    //if session not valid
    else
        request.getRequestDispatcher("login.html").include(request,response);

    }

}
