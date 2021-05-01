package hust.soict.it2.aims.order;

import hust.soict.it2.aims.disc.DigitalVideoDisc;
import hust.soict.it2.aims.exception.aims.ExcessiveException;
import hust.soict.it2.aims.exception.aims.NotFoundException;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class Order {
    public static final int MAX_PER_ORDER = 3;
    private String dateOrder;
    private final List<DigitalVideoDisc> itemsOrdered = new ArrayList<>();

    public void addDVD(DigitalVideoDisc disc) throws Exception {
        itemsOrdered.add(disc);
        dateOrder = new SimpleDateFormat("MMMM-dd-yyyy HH:mm:ss").format(new Date());
        if (itemsOrdered.size() == MAX_PER_ORDER) {
            throw new ExcessiveException();
        }
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void addDVD(List<DigitalVideoDisc> discList) throws Exception {
        if (discList.size() + itemsOrdered.size() > MAX_PER_ORDER) {
            int d = MAX_PER_ORDER - itemsOrdered.size();
            for (int i = 0; i < d; i++) {
                itemsOrdered.add(discList.get(i));
            }
            dateOrder = new SimpleDateFormat("MMMM-dd-yyyy HH:mm:ss").format(new Date());
            throw new ExcessiveException(String.valueOf(d));
        }
        else {
            itemsOrdered.addAll(discList);
            dateOrder = new SimpleDateFormat("MMMM-dd-yyyy HH:mm:ss").format(new Date());
        }
    }

    public void addDVD(DigitalVideoDisc disc1, DigitalVideoDisc disc2) throws Exception {
        int d = MAX_PER_ORDER - itemsOrdered.size();
        if (d == 0) throw new ExcessiveException(String.valueOf(d));
        else if (d == 1) {
            itemsOrdered.add(disc1);
            dateOrder = new SimpleDateFormat("MMMM-dd-yyyy HH:mm:ss").format(new Date());
            throw new ExcessiveException(String.valueOf(d));
        } else {
            itemsOrdered.add(disc1);
            itemsOrdered.add(disc2);
            dateOrder = new SimpleDateFormat("MMMM-dd-yyyy HH:mm:ss").format(new Date());
        }
    }

    public void removeDVD(String title) throws Exception {
        boolean check = false;
        if (title.equals("")) throw new NullPointerException();
        for (int i = 0; i < itemsOrdered.size(); i++)
        {
            if (title.equals(itemsOrdered.get(i).getTitle())) {
                check = true;
                itemsOrdered.remove(i);
            }
        }
        if (!check) throw new NotFoundException();
    }

    public List<DigitalVideoDisc> getItemsOrdered() {
        return itemsOrdered;
    }

    public float total() {
        float cost = 0;
        for (DigitalVideoDisc digitalVideoDisc : itemsOrdered) cost += digitalVideoDisc.getCost();
        return cost;
    }
}
