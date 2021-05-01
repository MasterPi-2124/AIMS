package hust.soict.it2.aims.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DateUtils{

    public int compare(MyDate date1, MyDate date2) {
        if (date1.getYear() < date2.getYear()) return 1;
        else if (date1.getYear() > date2.getYear()) return -1;
        else {
            if (date1.getIntMonth() < date2.getIntMonth()) return 1;
            else if (date1.getIntMonth() > date2.getIntMonth()) return -1;
            else {
                if (date1.getDay() < date2.getDay()) return 1;
                else if (date1.getDay() > date2.getDay()) return -1;
                else return 0;
            }
        }
    }

    public void sort(List<MyDate> dateList){
        Collections.sort(dateList);
    }
}
