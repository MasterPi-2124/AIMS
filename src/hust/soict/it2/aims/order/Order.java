package hust.soict.it2.aims.order;

import hust.soict.it2.aims.media.DigitalVideoDisc;
import hust.soict.it2.aims.exception.aims.ExcessiveException;
import hust.soict.it2.aims.exception.aims.NotFoundException;
import hust.soict.it2.aims.media.Media;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class Order {
    public static final int MAX_PER_ORDER = 3;
    private String dateOrder;
    private final List<Media> itemsOrdered = new ArrayList<>();

    public boolean full() {
        if (itemsOrdered.size() == MAX_PER_ORDER) {
            return true;
        }
        return false;
    }
    public void addMedia(Media item) throws ExcessiveException {
        itemsOrdered.add(item);
        dateOrder = new SimpleDateFormat("MMMM-dd-yyyy HH:mm:ss").format(new Date());
        if (full()) throw new ExcessiveException();
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void removeMedia(String ID) throws NotFoundException {
        boolean check = false;
        if (ID.equals(null)) throw new NullPointerException();
        for (int i = 0; i < itemsOrdered.size(); i++)
        {
            if (ID.equals(itemsOrdered.get(i).getID())) {
                check = true;
                itemsOrdered.remove(i);
                return;
            }
        }
        if (!check) throw new NotFoundException();
    }

    public List<Media> getItemsOrdered() {
        return itemsOrdered;
    }

    public float total() {
        float cost = 0;
        for (Media item : itemsOrdered) cost += item.getCost();
        return cost;
    }
}
