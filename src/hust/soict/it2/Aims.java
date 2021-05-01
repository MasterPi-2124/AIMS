package hust.soict.it2;

import hust.soict.it2.aims.disc.DigitalVideoDisc;
import hust.soict.it2.aims.exception.aims.NotFoundException;
import hust.soict.it2.aims.list.orderList;
import hust.soict.it2.aims.order.Order;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Aims {
    private static final orderList list = new orderList();
    private static String title, category, director;
    private static Integer length = null;
    private static Float cost = null;
    private static boolean check;
    private static final Order order = new Order();

    public static void init() {
        int option = JOptionPane.showConfirmDialog(null, "Hi, wanna buy some disc? You can order up to 5 orders, and maximum of 3 discs per order. Press OK to start or Cancel to exit.",
                "Buy Digital Video Disc", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            while (true) {
                do {
                    check = true;
                    String str = JOptionPane.showInputDialog(null, "What do you want to do with your order?\n 1. Add an Order.\n 2. Remove an Order.\n 3. Print an Order.\n 4. Search for items.\n 5. I'm feeling lucky.\n ", "Choose Option", JOptionPane.PLAIN_MESSAGE);
                    switch (str) {
                        case "1":
                            addOrder();
                            break;
                        case "2":
                            removeOrder();
                            break;
                        case "3":
                            printOrder();
                            break;
                        case "4":
                            search();
                            break;
                        case "5":
                            lucky();
                            break;
                        case "":
                            //if (str.equals(null))
                                return;
                        default:
                            check = false;
                            JOptionPane.showMessageDialog(null,"Try again.");
                    }
                } while (!check);
            }
        }
    }

    public static void addOrder() {
        if (list.full()) {
            JOptionPane.showMessageDialog(null,"Your order list is full! You can not add any more orders.");
            return;
        }
        do {
            check = true;
            String str = JOptionPane.showInputDialog(null, "Type add option for Order:\n 1. Add an item.\n 2. Add two items\n 3. Add a list of items.", "Add items", JOptionPane.PLAIN_MESSAGE);
            switch (str) {
                case "1" -> add();
                case "2" -> add2Items();
                case "3" -> addList();
                default -> {
                    check = false;
                    JOptionPane.showMessageDialog(null, "Try again.");
                }
            }
        } while (!check);
    }

    public static void removeOrder() {
        if (list.size() == 0) {
            JOptionPane.showMessageDialog(null,"Your order list is empty!");
            return;
        }
        String str;
        do {
            check = true;
            str = JOptionPane.showInputDialog("Choose order:");
            if (str == null) {
                JOptionPane.showMessageDialog(null, "No orders are removed.");
                return;
            }
            try {
                int x = Integer.parseInt(str);
                try {
                    list.remove(x);
                    JOptionPane.showMessageDialog(null,"Your order is removed successfully!");
                } catch (NotFoundException e) {
                    check = false;
                    JOptionPane.showMessageDialog(null,"Not found!");
                }
            } catch (Exception e) {
                check = false;
                JOptionPane.showMessageDialog(null, "Try again");
            }
        } while (!check);
    }

    public static void printOrder() {
        if (list.size() == 0) {
            JOptionPane.showMessageDialog(null,"Your order list is empty!");
            return;
        }
        int x = 0;
        do {
            check = true;
            try {
                x = Integer.parseInt(JOptionPane.showInputDialog("Choose order:"));
                if (x > list.size() || x == 0) {
                    check = false;
                    JOptionPane.showMessageDialog(null,"Order not found!");
                }
            } catch (Exception e) {
                check = false;
                JOptionPane.showMessageDialog(null,"Try again.");
            }
        } while (!check);
        StringBuilder message = new StringBuilder();
        message.append("**********************ORDER #").append(x).append("************************\nDate: ").append(list.get(x - 1).getDateOrder()).append("\nOrdered items:\n");
        int d = 0;
        for (DigitalVideoDisc disc : list.get(x - 1).getItemsOrdered()) message.append(" ").append(++d).append(". DVD - ").append(disc.getTitle()).append(" - ").append(disc.getCategory()).append(" - ").append(disc.getDirector()).append(" - ").append(disc.getLength()).append(": $").append(disc.getCost()).append("\n");
        message.append("----------\nTotal cost: $").append(list.get(x - 1).total()).append("\n******************************************************");
        System.out.println(message);
        JOptionPane.showMessageDialog(null,message);
    }

    public static void add() {
        Order order = new Order();
        while (true) {
            JTextField Title = new JTextField(),
                       Category = new JTextField(),
                       Director = new JTextField(),
                       Length = new JTextField(),
                       Cost = new JTextField();
            Object[] message = new Object[] {
                    "Enter information of the Disc. Press Cancel to exit.\n",
                    "Title:", Title,
                    "Category:", Category,
                    "Director:", Director,
                    "Length:", Length,
                    "Cost:", Cost,
            };

            do {
                check = true;
                int option = JOptionPane.showConfirmDialog(null, message, "Add a disc", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    title = Title.getText();
                    category = Category.getText();
                    director = Director.getText();
                    try {
                        length = Integer.parseInt(Length.getText());
                    } catch (NumberFormatException e) {
                        check = false;
                    }
                    try {
                        cost = Float.parseFloat(Cost.getText());
                    } catch (NumberFormatException e) {
                        check = false;
                    }
                    if (!check) JOptionPane.showMessageDialog(null, "Try again.");
                }
                else {
                    try {
                        if (order.getItemsOrdered().size() != 0) list.add(order);
                        else {
                            JOptionPane.showMessageDialog(null,"No items added!");
                            return;
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,"Your order list is full! You can not add any more orders.");
                    }
                    JOptionPane.showMessageDialog(null,"Total cost: " + order.total());
                    return;
                }
            } while (!check);
            DigitalVideoDisc disc = new DigitalVideoDisc(title, category, director, length, cost);
            try {
                order.addDVD(disc);
                message = new Object[] {
                        "Your disc has been added successfully!",
                        "Title: " + title,
                        "Category: " + category,
                        "Director: " + director,
                        "Length: " + length,
                        "Cost: " + cost,
                };
                JOptionPane.showMessageDialog(null, message);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"The queue is now full. You can't add any more items!");
                JOptionPane.showMessageDialog(null,"Total cost: " + order.total());
                try {
                    if (order.getItemsOrdered().size() != 0) list.add(order);
                    else {
                        JOptionPane.showMessageDialog(null,"No items added!");
                        return;
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null,"Your order list is full! You can not add any more orders.");
                }
                return;
            }
        }
    }

    public static void addList() {
        Order order = new Order();
        List<DigitalVideoDisc> discList = new ArrayList<>();
        while (true) {
            JTextField Title = new JTextField(),
                    Category = new JTextField(),
                    Director = new JTextField(),
                    Length = new JTextField(),
                    Cost = new JTextField();
            Object[] message = new Object[] {
                    "Enter information of the Disc " + (discList.size() + 1) + ". Press Cancel to exit.\n",
                    "Title:", Title,
                    "Category:", Category,
                    "Director:", Director,
                    "Length:", Length,
                    "Cost:", Cost,
            };

            do {
                check = true;
                int option = JOptionPane.showConfirmDialog(null, message, "Add a list of discs", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    title = Title.getText();
                    category = Category.getText();
                    director = Director.getText();
                    try {
                        length = Integer.parseInt(Length.getText());
                    } catch (NumberFormatException e) {
                        check = false;
                    }
                    try {
                        cost = Float.parseFloat(Cost.getText());
                    } catch (NumberFormatException e) {
                        check = false;
                    }
                    if (check) {
                        discList.add(new DigitalVideoDisc(title,category,director,length,cost));
                    } else JOptionPane.showMessageDialog(null, "Try again.");
                }
                else {
                    try {
                        if (discList.size() != 0) {
                            order.addDVD(discList);
                            JOptionPane.showMessageDialog(null, "Your order is added successfully!\nTotal cost: " + order.total());
                        } else {
                            JOptionPane.showMessageDialog(null, "No items added!");
                            return;
                        }
                    } catch (Exception e) {
                        int d = Integer.parseInt(e.getMessage());
                        StringBuilder info = new StringBuilder("Only " + d + "/" + discList.size() + " disc(s) are successfully added!\nItems not added: ");
                        for (int i = d; i < discList.size(); i++) {
                            info.append(discList.get(i).getTitle());
                            if (i != discList.size() - 1) info.append(", ");
                            else info.append(".");
                        }
                        JOptionPane.showMessageDialog(null, info.toString(),"Error",JOptionPane.PLAIN_MESSAGE);
                    }
                    try {
                        if (order.getItemsOrdered().size() != 0) list.add(order);
                        else {
                            JOptionPane.showMessageDialog(null,"No items added!");
                            return;
                        }
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null,"Your order list is full! You can not add any more orders.");
                    }
                    return;
                }
            } while (!check);
        }
    }

    public static void add2Items(){
        Order order = new Order();
        List<DigitalVideoDisc> discList = new ArrayList<>();
        while (discList.size() < 2) {
            JTextField Title = new JTextField(),
                    Category = new JTextField(),
                    Director = new JTextField(),
                    Length = new JTextField(),
                    Cost = new JTextField();
            Object[] message = new Object[] {
                    "Enter information of the Disc " + (discList.size() + 1) + ". Press Cancel to exit.\n",
                    "Title:", Title,
                    "Category:", Category,
                    "Director:", Director,
                    "Length:", Length,
                    "Cost:", Cost,
            };

            do {
                check = true;
                int option = JOptionPane.showConfirmDialog(null, message, "Add 2 discs", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    title = Title.getText();
                    category = Category.getText();
                    director = Director.getText();
                    try {
                        length = Integer.parseInt(Length.getText());
                    } catch (NumberFormatException e) {
                        check = false;
                    }
                    try {
                        cost = Float.parseFloat(Cost.getText());
                    } catch (NumberFormatException e) {
                        check = false;
                    }
                    if (check) {
                        discList.add(new DigitalVideoDisc(title,category,director,length,cost));
                    } else JOptionPane.showMessageDialog(null, "Try again.");
                }
                else return;
            } while (!check);
        }
        try {
                order.addDVD(discList.get(0), discList.get(1));
                JOptionPane.showMessageDialog(null, "Your order is added successfully!\nTotal cost: " + order.total());
        } catch (Exception e) {
                int d = Integer.parseInt(e.getMessage());
                StringBuilder info = new StringBuilder("Only " + d + "/" + discList.size() + " disc(s) are successfully added!\nItems not added: ");
                for (int i = d; i < discList.size(); i++) {
                    info.append(discList.get(i).getTitle());
                    if (i != discList.size() - 1) info.append(", ");
                    else info.append(".");
                }
                JOptionPane.showMessageDialog(null, info.toString(), "Error", JOptionPane.PLAIN_MESSAGE);
        }
        try {
            list.add(order);
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null,"Your order list is full! You can not add any more orders.");
        }
    }

    public static void remove() {
        do {
            String title = JOptionPane.showInputDialog(null,"Input the disc's title you want to delete: ","Delete items", JOptionPane.INFORMATION_MESSAGE);
            try {
                order.removeDVD(title);
                JOptionPane.showMessageDialog(null, "Disc '" + title + "' is removed.\nTotal cost: " + order.total());
                check = true;
            } catch (NullPointerException e1) {
                JOptionPane.showMessageDialog(null,"No discs are removed.\nTotal cost: " + order.total());
                return;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Disc not found!");
                check = false;
            }
        } while (!check);
    }

    public static void lucky() {
        if (list.size() == 0) {
            JOptionPane.showMessageDialog(null,"Your order list is empty!");
            return;
        }
        int option = JOptionPane.showConfirmDialog(null,"So you are a lucky man, right? Press OK and see the magic.","Lucky",JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION){
            int i = (int) (Math.random() * (list.size() - 1));
            int j = (int) (Math.random() * (list.get(i).getItemsOrdered().size() - 1));
            JOptionPane.showMessageDialog(null,
                    "You got an ordered item for free!\nYour item is:\n  - Order " + (i + 1) + "\n  - Title: " + list.get(i).getItemsOrdered().get(j).getTitle() + "\n  - Category: " + list.get(i).getItemsOrdered().get(j).getCategory() + "\n  - Director: " + list.get(i).getItemsOrdered().get(j).getDirector() + "\n  - Length: " + list.get(i).getItemsOrdered().get(j).getLength());
            list.get(i).getItemsOrdered().get(j).setCost(0.0F);
        } else {
            JOptionPane.showMessageDialog(null,"Yep, you cancelled that.");
            return;
        }
    }

    public static void search() {
        if (list.size() == 0) {
            JOptionPane.showMessageDialog(null,"Your order list is empty!");
            return;
        }
        JTextField item = new JTextField();
        Object[] message = new Object[] {
          "Type item title:", item
        };
        int option = JOptionPane.showConfirmDialog(null,message,"Lucky",JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String title = item.getText();
            try {
                list.search(title);
            } catch (NotFoundException e) {
                JOptionPane.showMessageDialog(null,"Not found!");
            }
        } else {
            return;
        }
    }

    public static void main(String[] args){
        init();
    }
}