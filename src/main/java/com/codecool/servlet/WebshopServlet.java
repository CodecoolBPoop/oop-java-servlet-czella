package main.java.com.codecool.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "webshopServlet", urlPatterns = {"/webshop"}, loadOnStartup = 1)
public class WebshopServlet extends HttpServlet{

    private List<Item> availableItems = new ArrayList<>();

    public void init() {
        Item dog = new Item("dog", 300);
        availableItems.add(dog);
        Item cat = new Item("cat", 280);
        availableItems.add(cat);
        Item mouse = new Item("mouse", 10);
        availableItems.add(mouse);
        Item bird = new Item("bird", 20);
        availableItems.add(bird);

    }

    public Item getItemByName(String name) {
        for (Item item: availableItems) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        throw new RuntimeException("Should never, ever, reach this point!");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        StringBuffer buffer = new StringBuffer();
        for (Item item: availableItems
             ) {
            buffer.append("<tr>");
            buffer.append("<td>");
            buffer.append(item.getName());
            buffer.append("</td>");
            buffer.append("<td>");
            buffer.append(item.getPrice() + " USD");
            buffer.append("</td>");
            buffer.append("<td>");
            buffer.append("<a href=\"/webshop?item_name_to_add=" + item.getName() + "\">");
            buffer.append("Add");
            buffer.append("</a>");
            buffer.append("</td>");
            buffer.append("<td>");
            buffer.append("<a href=\"/webshop?item_name_to_remove=" + item.getName() + "\">");
            buffer.append("Remove");
            buffer.append("</a>");
            buffer.append("</td>");
            buffer.append("</tr>");

        }
        String itemNameToAdd = request.getParameter("item_name_to_add");
        if (itemNameToAdd != null) {
            Item itemToBuy = getItemByName(itemNameToAdd);
            ItemStore.add(itemToBuy);
            System.out.println(Arrays.toString(ItemStore.getItemList().toArray()));

        }
        String itemNameToRemove = request.getParameter("item_name_to_remove");
        if (itemNameToRemove != null) {
            Item itemToRemove = getItemByName(itemNameToRemove);
            ItemStore.remove(itemToRemove);
            System.out.println(Arrays.toString(ItemStore.getItemList().toArray()));
        }


        out.println(
                "<html>\n" +
                        "<head><title>Webshop page</title></head>\n" +
                        "<body>\n" +
                        "<h1>Hello CodeCooler, welcome to my Webshop!</h1>" +
                        "<table>"+
                        buffer.toString() +
                        "</table>"+
                        "<a href=\"/shoppingCart\">Check shopping cart</a>"+
                        "</body></html>"
        );
    }

}
