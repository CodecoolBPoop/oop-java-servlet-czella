package main.java.com.codecool.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.System.out;

@WebServlet(name = "shoppingCartServlet", urlPatterns = {"/shoppingCart"}, loadOnStartup = 1)
public class ShoppingCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        PrintWriter out = response.getWriter();
        StringBuffer buffer = new StringBuffer();
        for (Item item: ItemStore.getItemList()
        ) {
            buffer.append("<tr>");
            buffer.append("<td>");
            buffer.append(item.getName());
            buffer.append("</td>");
            buffer.append("<td>");
            buffer.append(item.getPrice() + " USD");
            buffer.append("</td>");
            buffer.append("<td>");
            buffer.append("</tr>");

        }

        double sumOfItems = 0;
        for (Item item:ItemStore.getItemList()) {
            sumOfItems += item.getPrice();
        }

        out.println(
                "<html>\n" +
                        "<head><title>Webshop page</title></head>\n" +
                        "<body>\n" +
                        "<h1>Hello CodeCooler, welcome to my Webshop!</h1>" +
                        "<table>"+
                        buffer.toString() +
                        "</table>"+
                        "<div>"+
                        "Sum of items is: " + sumOfItems +
                        "</div>"+
                        "</body></html>"
        );

    }
}
