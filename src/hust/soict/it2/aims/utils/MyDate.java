package hust.soict.it2.aims.utils;

import hust.soict.it2.aims.exception.date.DayFormatException;
import hust.soict.it2.aims.exception.date.LunarYearException;
import hust.soict.it2.aims.exception.date.MonthFormatException;
import hust.soict.it2.aims.exception.date.YearFormatExceprion;

import java.text.SimpleDateFormat;
import java.util.*;

public class MyDate implements Comparable{
    private int year, day, intMonth;
    private String month;
    public MyDate() {
        this.month = new SimpleDateFormat("MMMM").format(new Date());
        this.day = Integer.parseInt(new SimpleDateFormat("d").format(new Date()));
        this.year = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
    }

    public MyDate(String month, int day, int year) throws Exception {
        this.setMonth(month);
        this.setYear(year);
        this.setDay(day);
        setIntMonth(month);
    }

    public void setIntMonth(String month) {
        switch (month) {
                case "January", "Jan", "jan":
                    intMonth = 1;
                    break;
                case "February", "Feb", "feb":
                    intMonth = 2;
                    break;
                case "March", "Mar", "mar":
                    intMonth = 3;
                    break;
                case "April", "Apr", "apr":
                    intMonth = 4;
                    break;
                case "May", "may":
                    intMonth = 5;
                    break;
                case "June", "Jun", "jun":
                    intMonth = 6;
                    break;
                case "July", "Jul", "jul":
                    intMonth = 7;
                    break;
                case "August", "Aug", "aug":
                    intMonth = 8;
                    break;
                case "September", "Sep", "sep":
                    intMonth = 9;
                    break;
                case "October", "Oct", "oct":
                    intMonth = 10;
                    break;
                case "November", "Nov", "nov":
                    intMonth = 11;
                    break;
                case "December", "Dec", "dec":
                    intMonth = 12;
                    break;
                default:
                    break;
            }
    }

    public String format(String format) throws Exception{
        return new SimpleDateFormat(format).format(new Date());
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) throws Exception {
        if (year < 0)
            throw new YearFormatExceprion();
        this.year = year;
    }

    public int getIntMonth() {
        return intMonth;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) throws Exception {
        if (month == "February" && ((year % 4 != 0) || ((year % 100 == 0) && (year % 400 != 0))) && (day < 0 || day > 28))
            throw new LunarYearException();
        else if (month == "February" && ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) && (day < 0 || day > 29))
            throw new LunarYearException();
        this.day = day;
    }

    public MyDate accept (String date) throws Exception {
        int d = 0;
        while (date.length() > 0) {
            d++;
            String str = "";
            while (date.charAt(0) != ' ') {
                str = str + date.charAt(0);
                if (date.length() == 1) break;
                date = date.substring(1,date.length());
            }
            while (date.charAt(0) == ' ') {
                if (date.length() == 1) break;
                date = date.substring(1, date.length());
            }
            if (d == 1) {
                switch (str) {
                    case "January", "jan", "Jan":
                        month = "January";
                        intMonth = 1;
                        break;
                    case "February", "Feb", "feb":
                        month = "February";
                        intMonth = 2;
                        break;
                    case "March", "Mar", "mar":
                        month = "March";
                        intMonth = 3;
                        break;
                    case "April", "Apr", "apr":
                        month = "April";
                        intMonth = 4;
                        break;
                    case "May", "may":
                        month = "May";
                        intMonth = 5;
                        break;
                    case "June", "Jun", "jun":
                        month = "June";
                        intMonth = 6;
                        break;
                    case "July", "Jul", "jul":
                        month = "July";
                        intMonth = 7;
                        break;
                    case "August", "Aug", "aug":
                        month = "August";
                        intMonth = 8;
                        break;
                    case "September", "Sep", "sep":
                        month = "September";
                        intMonth = 9;
                        break;
                    case "October", "Oct", "oct":
                        month = "October";
                        intMonth = 10;
                        break;
                    case "November", "Nov", "nov":
                        month = "November";
                        intMonth = 11;
                        break;
                    case "December", "Dec", "dec":
                        month = "December";
                        intMonth = 12;
                        break;
                    default:
                        throw new MonthFormatException();
                }
            } else if (d == 2) {
                switch (str) {
                    case "1st", "1":
                        day = 1;
                        break;
                    case "2nd", "2":
                        day = 2;
                        break;
                    case "3rd", "3":
                        day = 3;
                        break;
                    case "4th", "4":
                        day = 4;
                        break;
                    case "5th", "5":
                        day = 5;
                        break;
                    case "6th", "6":
                        day = 6;
                        break;
                    case "7th", "7":
                        day = 7;
                        break;
                    case "8th", "8":
                        day = 8;
                        break;
                    case "9th", "9":
                        day = 9;
                        break;
                    case "10th", "10":
                        day = 10;
                        break;
                    case "11st", "11":
                        day = 11;
                        break;
                    case "12nd", "12":
                        day = 12;
                        break;
                    case "13rd", "13":
                        day = 13;
                        break;
                    case "14th", "14":
                        day = 14;
                        break;
                    case "15th", "15":
                        day = 15;
                        break;
                    case "16th", "16":
                        day = 16;
                        break;
                    case "17th", "17":
                        day = 17;
                        break;
                    case "18th", "18":
                        day = 18;
                        break;
                    case "19th", "19":
                        day = 19;
                        break;
                    case "20th", "20":
                        day = 20;
                        break;
                    case "21st", "21":
                        day = 21;
                        break;
                    case "22nd", "22":
                        day = 22;
                        break;
                    case "23rd", "23":
                        day = 23;
                        break;
                    case "24th", "24":
                        day = 24;
                        break;
                    case "25th", "25":
                        day = 25;
                        break;
                    case "26th", "26":
                        day = 26;
                        break;
                    case "27th", "27":
                        day = 27;
                        break;
                    case "28th", "28":
                        day = 28;
                        break;
                    case "29th", "29":
                        day = 29;
                        break;
                    case "30th", "30":
                        day = 30;
                        break;
                    case "31st", "31":
                        day = 31;
                        break;
                    default:
                        throw new DayFormatException();
                }
            } else if (d == 3) {
                try {
                    year = Integer.parseInt(str);
                } catch (Exception e) {
                    throw new YearFormatExceprion();
                }
                if (date.length() == 1) break;
            }
        }
        MyDate myDate = new MyDate(month,day,year);
        return myDate;
    }

    @Override
    public int compareTo(Object o) {
        int check = 0;
        if (this.getYear() > ((MyDate) o).getYear()) check = 1;
        else if (this.getYear() < ((MyDate) o).getYear()) check = -1;
        else {
            if (this.getIntMonth() > ((MyDate) o).getIntMonth()) check = 1;
            else if (this.getIntMonth() < ((MyDate) o).getIntMonth()) check = -1;
            else {
                if (this.getDay() > ((MyDate) o).getDay()) check = 1;
                else if (this.getDay() < ((MyDate) o).getDay()) check = -1;
                else check = 1;
            }
        }
        return check;
    }
}