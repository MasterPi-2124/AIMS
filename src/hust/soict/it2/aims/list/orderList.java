package hust.soict.it2.aims.list;

import hust.soict.it2.aims.media.DigitalVideoDisc;
import hust.soict.it2.aims.exception.aims.ExcessiveException;
import hust.soict.it2.aims.exception.aims.NotFoundException;
import hust.soict.it2.aims.media.Media;
import hust.soict.it2.aims.order.Order;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class orderList {
    private final List<Order> list = new ArrayList<>();
    private static final int MAX_ORDERS = 5;

    public boolean full() {
        return list.size() == MAX_ORDERS;
    }

    public int size() {
        return list.size();
    }

    public void add(Order order){
        list.add(order);
    }

    public Order get(int i) {
        return list.get(i);
    }

    public void remove(int x) throws NotFoundException {
        boolean check = false;
        for (int i = 0; i < list.size(); i++)
        {
            if (i == x - 1) {
                check = true;
                list.remove(i);
            }
        }
        if (!check) throw new NotFoundException();
    }

    public void search (String title) throws NotFoundException {
        StringBuilder message = new StringBuilder();
        if (title.equals("")) throw new NotFoundException();
        String[] store;
        String[] temp = title.split(" ");
        for(int i = 0; i < list.size(); i++) {
            for (Media item : list.get(i).getItemsOrdered()) {
                boolean check = false;
                store = item.getTitle().split(" ");
                for (String str : temp) {
                    check = false;
                    for (String str1 : store) {
                        if (str.equals(str1)) check = true;
                    }
                    if (!check) break;
                }
                if (check) {
                    message.append("  - Order ").append(i + 1).append(", ID: ").append(item.getID()).append(" - ").append(item.getTitle()).append(": ").append(item.getCost()).append("\n");
                }
            }
        }
        if (message.length() == 0) throw new NotFoundException();
        else {
            message.insert(0, "Here are your results:\n");
            JOptionPane.showMessageDialog(null, message);
        }
    }
}