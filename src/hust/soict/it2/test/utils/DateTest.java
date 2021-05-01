package hust.soict.it2.test.utils;

import hust.soict.it2.aims.exception.date.DayFormatException;
import hust.soict.it2.aims.exception.date.LunarYearException;
import hust.soict.it2.aims.exception.date.MonthFormatException;
import hust.soict.it2.aims.exception.date.YearFormatExceprion;
import hust.soict.it2.aims.utils.DateUtils;
import hust.soict.it2.aims.utils.MyDate;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DateTest {
    public static List<MyDate> dateList = new ArrayList<>();

    public static void accept() {
        boolean check;
        do {
            check = true;
            String string_date = JOptionPane.showInputDialog(null, "Input the date:");
            try {
                MyDate date = new MyDate().accept(string_date);
                JOptionPane.showMessageDialog(null,"The day is: " + date.getMonth() + " " + date.getDay() + ", " + date.getYear());
            } catch (MonthFormatException e) {
                JOptionPane.showMessageDialog(null,"Month!");
                check = false;
            } catch (LunarYearException e) {
                JOptionPane.showMessageDialog(null,"Lunar!");
                check = false;
            } catch (DayFormatException e) {
                JOptionPane.showMessageDialog(null,"Day!");
                check = false;
            } catch (YearFormatExceprion e) {
                JOptionPane.showMessageDialog(null,"Year!");
                check = false;
            } catch (Exception e) {
                return;
            }
        } while (!check);
    }

    public static void print() {
        MyDate current = new MyDate();
        StringBuilder str;
        if (current.getDay() % 10 == 1) {
            str = new StringBuilder().append(current.getDay()).append("st");
        } else if (current.getDay() % 10 == 2) {
            str = new StringBuilder().append(current.getDay()).append("nd");
        } else {
            str = new StringBuilder().append(current.getDay()).append("th");
        }
        JOptionPane.showMessageDialog(null,"Today is: " + current.getMonth() + " " + str + " " + current.getYear());
    }

    public static void format() {
        try {
            String format = JOptionPane.showInputDialog("Input format:");
            JOptionPane.showMessageDialog(null,"Today is: " + new MyDate().format(format));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Format Error!");
        }
    }

    public static void compare() {
        boolean check;
        JTextField getDate1 = new JTextField(),
                   getDate2 = new JTextField();
        Object[] message = new Object[] {
            "Input date 1:", getDate1,
            "Input date 2:", getDate2
        };
        do {
            check = true;
            int option = JOptionPane.showConfirmDialog(null,message,"Compare two Dates",JOptionPane.OK_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    MyDate date1 = new MyDate().accept(getDate1.getText());
                    MyDate date2 = new MyDate().accept(getDate2.getText());
                    if (new DateUtils().compare(date1,date2) == 1) JOptionPane.showMessageDialog(null,"Date 1 is less than Date 2");
                    else if (new DateUtils().compare(date1,date2) == -1) JOptionPane.showMessageDialog(null,"Date 1 is greater than Date 2");
                    else JOptionPane.showMessageDialog(null,"Two dates are the same!");
                } catch (MonthFormatException e) {
                    JOptionPane.showMessageDialog(null,"Month!");
                    check = false;
                } catch (LunarYearException e) {
                    JOptionPane.showMessageDialog(null,"Lunar!");
                    check = false;
                } catch (DayFormatException e) {
                    JOptionPane.showMessageDialog(null,"Day!");
                    check = false;
                } catch (YearFormatExceprion e) {
                    JOptionPane.showMessageDialog(null,"Year!");
                    check = false;
                } catch (Exception e) {
                    return;
                }
            }
        } while (!check);
    }

    public static void sort() {
        do {
            boolean check;
            JTextField string_date = new JTextField();
            do {
                Object[] message = new Object[]{
                        "Input the date:", string_date
                };
                check = true;
                int option = JOptionPane.showConfirmDialog(null, message, "sdfdfd", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    try {
                        MyDate date = new MyDate().accept(string_date.getText());
                        dateList.add(date);
                    } catch (MonthFormatException e) {
                        JOptionPane.showMessageDialog(null, "Month!");
                        check = false;
                    } catch (LunarYearException e) {
                        JOptionPane.showMessageDialog(null, "Lunar!");
                        check = false;
                    } catch (DayFormatException e) {
                        JOptionPane.showMessageDialog(null, "Day!");
                        check = false;
                    } catch (YearFormatExceprion e) {
                        JOptionPane.showMessageDialog(null, "Year!");
                        check = false;
                    } catch (Exception e) {
                        return;
                    }
                } else {
                    Collections.sort(dateList);
                    for (MyDate date : dateList) {
                        System.out.println(date.getMonth() + " " + date.getDay() + ", " + date.getYear());
                    }
                    return;
                }
            } while (!check);
        } while (true);

    }

    public static void main(String[] args) throws Exception {
        /*accept();
        print();
        format();*/
        //compare();
        sort();
    }
}
