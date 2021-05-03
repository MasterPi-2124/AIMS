package hust.soict.it2;

import hust.soict.it2.aims.exception.aims.ExcessiveException;
import hust.soict.it2.aims.media.Book;
import hust.soict.it2.aims.media.DigitalVideoDisc;
import hust.soict.it2.aims.exception.aims.NotFoundException;
import hust.soict.it2.aims.list.orderList;
import hust.soict.it2.aims.media.Media;
import hust.soict.it2.aims.order.Order;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Aims {
    private static final orderList list = new orderList();
    private static String title, category, director, id, content;
    private static String[] authors;
    private static Integer length = null;
    private static Float cost = null;
    private static boolean check;
    private static Order order;

    public static void init() {
        int option = JOptionPane.showConfirmDialog(null, "Hi, wanna buy some disc? You can order up to 5 orders, and maximum of 3 discs per order. Press OK to start or Cancel to exit.",
                "Buy Digital Video Disc", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            while (true) {
                do {
                    check = true;
                    String str = JOptionPane.showInputDialog(null, "What do you want to do with your order?\n 1. Create new Order.\n 2. Modify an Order.\n 3. Remove an Order.\n 4. Print an Order.\n 5. Search for items.\n 6. I'm feeling lucky.\n ", "Choose Option", JOptionPane.PLAIN_MESSAGE);
                    switch (str) {
                        case "1":
                            addOrder();
                            break;
                        case "2":
                            modifyOrder();
                            break;
                        case "3":
                            removeOrder();
                            break;
                        case "4":
                            printOrder();
                            break;
                        case "5":
                            search();
                            break;
                        case "6":
                            lucky();
                            break;
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
            JOptionPane.showMessageDialog(null, "Your order list is full! You can not add any more orders.");
            return;
        } else {
            order = new Order();
            JOptionPane.showMessageDialog(null, "Order " + (list.size() + 1) + " is created successfully! Now you can add items to it.");
            addItem(order);
            if (order.getItemsOrdered().size() == 0) {
                JOptionPane.showMessageDialog(null, "No orders added!");
            } else {
                list.add(order);
                JOptionPane.showMessageDialog(null, "Order " + list.size() + " is added to list successfully!\nOrder cost: " + order.total());
                if (list.full())
                    JOptionPane.showMessageDialog(null, "Your list is full! You can not add any more orders.");
            }
            return;
        }
    }

    public static void modifyOrder() {
        if (list.size() == 0) {
            JOptionPane.showMessageDialog(null,"Your order list is empty!");
            return;
        }

        do {
            check = true;
            String str = JOptionPane.showInputDialog(null, "Choose Order to modify. (1 ~ " + list.size() + ")", "Modify Order", JOptionPane.PLAIN_MESSAGE);
            try {
                int option = Integer.parseInt(str);
                if (option < 1 || option > list.size()) {
                    check = false;
                    JOptionPane.showMessageDialog(null, "Try again.");
                } else {
                    while (true) {
                        do {
                            String string = JOptionPane.showInputDialog(null, "What do you want to do with your order?\n 1. Add an item.\n 2. Modify an item.\n 3. Remove an item.\n 4. Print the Order.\n", "Choose Option", JOptionPane.PLAIN_MESSAGE);
                            if (string == null) return;
                            switch (string) {
                                case "1":
                                    addItem(list.get(option - 1));
                                    break;
                                case "2":
                                    modifyItem(list.get(option - 1));
                                    break;
                                case "3":
                                    removeItem(list.get(option - 1));
                                    break;
                                case "4":
                                    printOrder(option);
                                    break;
                                default:
                                    check = false;
                                    JOptionPane.showMessageDialog(null,"Try again.");
                            }
                        } while (!check);
                    }
                }
            } catch (NumberFormatException e) {
                check = false;
                if (str == null) return;
                JOptionPane.showMessageDialog(null, "You must type an Integer!");
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
            str = JOptionPane.showInputDialog("Choose order (1 ~ " + list.size() + "):");
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
                x = Integer.parseInt(JOptionPane.showInputDialog("Choose order (1 ~ " + list.size() + "):"));
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
        message.append("**********************ORDER #").append(x).append("************************\n")
               .append("Date: ").append(list.get(x - 1).getDateOrder()).append("\n")
               .append("Ordered items:\n")
               .append("+---------------+---------------------------+---------------+\n")
               .append("|\t\tID\t\t|\t\t\tTitle\t\t\t|\t\tCost\t|\n")
               .append("+---------------+---------------------------+---------------+\n");
        for (Media item : list.get(x - 1).getItemsOrdered()){
            message.append(" ").append(item.getID()).append("\t\t\t\t").append(item.getTitle()).append("\t\t\t\t\t\t").append(item.getCost()).append("\n");
        }
        message.append("----------\nTotal cost: $").append(list.get(x - 1).total()).append("\n")
               .append("******************************************************");
        System.out.println(message);
        JOptionPane.showMessageDialog(null,message);
    }

    public static void printOrder(int x) {
        StringBuilder message = new StringBuilder();
        message.append("**********************ORDER #").append(x).append("************************\n")
                .append("Date: ").append(list.get(x - 1).getDateOrder()).append("\n")
                .append("Ordered items:\n")
                .append("+---------------+---------------------------+---------------+\n")
                .append("|\t\tID\t\t|\t\t\tTitle\t\t\t|\t\tCost\t|\n")
                .append("+---------------+---------------------------+---------------+\n");
        for (Media item : list.get(x - 1).getItemsOrdered()){
            message.append(" ").append(item.getID()).append("\t\t\t\t").append(item.getTitle()).append("\t\t\t\t\t\t").append(item.getCost()).append("\n");
        }
        message.append("----------\nTotal cost: $").append(list.get(x - 1).total()).append("\n")
                .append("******************************************************");
        System.out.println(message);
        JOptionPane.showMessageDialog(null,message);
    }

    public static void addItem(Order order) {
        if (order.full()) {
            JOptionPane.showMessageDialog(null, "The order is full. You can't add any more items!");
            return;
        }
        while (true) {
            JTextField Title = new JTextField(),
                    Category = new JTextField(),
                    Director = new JTextField(),
                    Length = new JTextField(),
                    Cost = new JTextField(),
                    ID = new JTextField(),
                    Authors = new JTextField(),
                    Content = new JTextField();
            Object[] message;
            int option;
            do {
                check = true;
                try {
                    String str = JOptionPane.showInputDialog("What do you choose? Press Cancel to add your order to list.\n 1. DVD\n 2. Book\n 3. CD\n");
                    if (str.equals(null)) return;
                    try {
                        option = Integer.parseInt(str);
                        switch (option) {
                            case 1:
                                message = new Object[]{
                                        "Enter information of the Disc. Press Cancel to exit.\n",
                                        "ID", ID,
                                        "Title:", Title,
                                        "Category:", Category,
                                        "Director:", Director,
                                        "Length:", Length,
                                        "Cost:", Cost,
                                };
                                option = JOptionPane.showConfirmDialog(null, message, "Add a disc", JOptionPane.OK_CANCEL_OPTION);

                                if (option == JOptionPane.OK_OPTION) {
                                    title = Title.getText();
                                    category = Category.getText();
                                    director = Director.getText();

                                    try {
                                        int test = Integer.parseInt(ID.getText());
                                        id = ID.getText();
                                        length = Integer.parseInt(Length.getText());
                                        cost = Float.parseFloat(Cost.getText());
                                        DigitalVideoDisc disc = new DigitalVideoDisc(id, title, category, director, length, cost);

                                        try {
                                            order.addMedia(disc);
                                            message = new Object[] {
                                                    "Your disc has been added successfully!",
                                                    "ID: " + id,
                                                    "Title: " + title,
                                                    "Category: " + category,
                                                    "Director: " + director,
                                                    "Length: " + length,
                                                    "Cost: " + cost,
                                            };
                                            JOptionPane.showMessageDialog(null, message);
                                            JOptionPane.showMessageDialog(null, "Total cost: " + order.total());
                                        } catch (ExcessiveException e) {
                                            message = new Object[] {
                                                    "Your disc has been added successfully!",
                                                    "ID: " + id,
                                                    "Title: " + title,
                                                    "Category: " + category,
                                                    "Director: " + director,
                                                    "Length: " + length,
                                                    "Cost: " + cost,
                                            };
                                            JOptionPane.showMessageDialog(null, message);
                                            JOptionPane.showMessageDialog(null, "Total cost: " + order.total());
                                            JOptionPane.showMessageDialog(null, "The order is now full. You can't add any more items!");
                                            return;
                                        }
                                    } catch (NumberFormatException e) {
                                        check = false;
                                        JOptionPane.showMessageDialog(null, "Try again.");
                                    }
                                } else break;
                                break;

                            case 2:
                                message = new Object[]{
                                        "Enter information of the Book. Press Cancel to exit.\n",
                                        "ID:", ID,
                                        "Title:", Title,
                                        "Category:", Category,
                                        "Authors:", Authors,
                                        "Content:", Content,
                                        "Cost:", Cost,
                                };
                                option = JOptionPane.showConfirmDialog(null, message, "Add a book", JOptionPane.OK_CANCEL_OPTION);

                                if (option == JOptionPane.OK_OPTION) {
                                    title = Title.getText();
                                    category = Category.getText();
                                    authors = Authors.getText().split(";");
                                    content = Content.getText();

                                    try {
                                        int test = Integer.parseInt(ID.getText());
                                        id = ID.getText();
                                        cost = Float.parseFloat(Cost.getText());
                                        Book book = new Book(id, title, category, authors, content, cost);

                                        try {
                                            order.addMedia(book);
                                            message = new Object[] {
                                                    "Your book has been added successfully!",
                                                    "ID: " + id,
                                                    "Title: " + title,
                                                    "Category: " + category,
                                                    "Authors: " + Authors.getText(),
                                                    "Content: " + content,
                                                    "Cost: " + cost,
                                            };
                                            JOptionPane.showMessageDialog(null, message);
                                            JOptionPane.showMessageDialog(null, "Total cost: " + order.total());
                                        } catch (ExcessiveException e) {
                                            message = new Object[] {
                                                    "Your book has been added successfully!",
                                                    "ID: " + id,
                                                    "Title: " + title,
                                                    "Category: " + category,
                                                    "Authors: " + Authors.getText(),
                                                    "Content: " + content,
                                                    "Cost: " + cost,
                                            };
                                            JOptionPane.showMessageDialog(null, message);
                                            JOptionPane.showMessageDialog(null, "Total cost: " + order.total());
                                            JOptionPane.showMessageDialog(null, "The order is now full. You can't add any more items!");
                                            return;
                                        }
                                    } catch (NumberFormatException e) {
                                        check = false;
                                        JOptionPane.showMessageDialog(null, "Try again.");
                                    }
                                } else break;
                                break;
                            case 3:

                        }
                    } catch (NumberFormatException e) {
                        check = false;
                        JOptionPane.showMessageDialog(null,"You must type an Integer!");
                    }

                } catch (NullPointerException e) {
                    return;
                }
            } while (!check);

        }
    }

    public static void modifyItem(Order order) {
        do {
            check = true;
            String str = JOptionPane.showInputDialog(null, "Choose item to modify. (1 ~ " + order.getItemsOrdered().size() + ")", "Modify Item", JOptionPane.PLAIN_MESSAGE);
            try {
                int option = Integer.parseInt(str);
                if (option < 1 || option > order.getItemsOrdered().size()) {
                    check = false;
                    JOptionPane.showMessageDialog(null, "Try again.");
                } else {
                    JTextField Title = new JTextField(),
                            Category = new JTextField(),
                            Director = new JTextField(),
                            Length = new JTextField(),
                            Cost = new JTextField(),
                            ID = new JTextField(),
                            Authors = new JTextField(),
                            Content = new JTextField();
                    Title.setText(order.getItemsOrdered().get(option - 1).getTitle());
                    Category.setText(order.getItemsOrdered().get(option - 1).getCategory());
                    Cost.setText(Float.toString(order.getItemsOrdered().get(option - 1).getCost()));
                    ID.setText(order.getItemsOrdered().get(option - 1).getID());

                    if (order.getItemsOrdered().get(option - 1).getAuthors() == null) {
                        Director.setText(order.getItemsOrdered().get(option - 1).getDirector());
                        Length.setText(Integer.toString(order.getItemsOrdered().get(option - 1).getLength()));
                        Object[] message = new Object[]{
                                "Enter information of the Disc. Press Cancel to exit.\n",
                                "ID", ID,
                                "Title:", Title,
                                "Category:", Category,
                                "Director:", Director,
                                "Length:", Length,
                                "Cost:", Cost,
                        };
                        int option1 = JOptionPane.showConfirmDialog(null, message, "Modify item", JOptionPane.OK_CANCEL_OPTION);

                        if (option1 == JOptionPane.OK_OPTION) {
                            title = Title.getText();
                            category = Category.getText();
                            director = Director.getText();
                            int test = Integer.parseInt(ID.getText());
                            id = ID.getText();
                            length = Integer.parseInt(Length.getText());
                            cost = Float.parseFloat(Cost.getText());
                            order.getItemsOrdered().set(option - 1, new DigitalVideoDisc(id, title, category, director, length, cost));
                            message = new Object[]{
                                    "Your disc has been modified successfully!",
                                    "ID: " + id,
                                    "Title: " + title,
                                    "Category: " + category,
                                    "Director: " + director,
                                    "Length: " + length,
                                    "Cost: " + cost,
                            };
                            JOptionPane.showMessageDialog(null, message);
                            JOptionPane.showMessageDialog(null, "Total cost: " + order.total());
                            return;
                        } else break;
                    } else if (order.getItemsOrdered().get(option - 1).getDirector() == null) {
                        Authors.setText(order.getItemsOrdered().get(option - 1).getAuthors().toString());
                        Content.setText(order.getItemsOrdered().get(option - 1).getContent());
                        Object[] message = new Object[]{
                                "Enter information of the Book. Press Cancel to exit.\n",
                                "ID", ID,
                                "Title:", Title,
                                "Category:", Category,
                                "Authors:", Authors,
                                "Content:", Content,
                                "Cost:", Cost,
                        };
                        int option1 = JOptionPane.showConfirmDialog(null, message, "Modify item", JOptionPane.OK_CANCEL_OPTION);

                        if (option1 == JOptionPane.OK_OPTION) {
                            title = Title.getText();
                            category = Category.getText();
                            authors = Authors.getText().split(";");
                            content = Content.getText();
                            int test = Integer.parseInt(ID.getText());
                            id = ID.getText();
                            cost = Float.parseFloat(Cost.getText());
                            order.getItemsOrdered().set(option - 1, new Book(id, title, category, authors, content, cost));
                            message = new Object[] {
                                    "Your book has been modified successfully!",
                                    "ID: " + id,
                                    "Title: " + title,
                                    "Category: " + category,
                                    "Authors: " + Authors.getText(),
                                    "Content: " + content,
                                    "Cost: " + cost,
                            };
                            JOptionPane.showMessageDialog(null, message);
                            JOptionPane.showMessageDialog(null, "Total cost: " + order.total());
                            return;
                        } else break;
                    }
                }
            } catch (NumberFormatException e) {
                check = false;
                if (str == null) return;
                JOptionPane.showMessageDialog(null,"You must type an Integer!");
            }
        } while (!check);
    }

    public static void removeItem(Order order){
        do {
            String ID = JOptionPane.showInputDialog(null,"Input the item's ID you want to delete: ","Delete items", JOptionPane.INFORMATION_MESSAGE);
            try {
                order.removeMedia(ID);
                JOptionPane.showMessageDialog(null, "Item #" + ID + " is removed.\nTotal cost: " + order.total());
                check = true;
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null,"No itwms are removed.\nTotal cost: " + order.total());
                return;
            } catch (NotFoundException e) {
                JOptionPane.showMessageDialog(null, "Item not found!");
                check = false;
            }
        } while (!check);
    }

    public static void lucky() {
        //JOptionPane.showMessageDialog(null, "This feature is under maintainance. It will be available in the near future.");
        if (list.size() == 0) {
            JOptionPane.showMessageDialog(null,"Your order list is empty!");
            return;
        }
        int option = JOptionPane.showConfirmDialog(null,"So you are a lucky man, right? Press OK and see the magic.","Lucky",JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION){
            int i = (int) (Math.random() * (list.size() - 1));
            int j = (int) (Math.random() * (list.get(i).getItemsOrdered().size() - 1));
            JOptionPane.showMessageDialog(null,
                    "You got an ordered item for free!\nYour item is:\n  - Order " + (i + 1) + "\n  - ID: " + list.get(i).getItemsOrdered().get(j).getID() + "\n - Title: " + list.get(i).getItemsOrdered().get(j).getTitle());
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
        int option = JOptionPane.showConfirmDialog(null,message,"Search for item",JOptionPane.OK_CANCEL_OPTION);
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