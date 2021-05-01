package hust.soict.it2.test.disc;

import hust.soict.it2.aims.disc.DigitalVideoDisc;
import hust.soict.it2.aims.disc.wrapDigitalVideoDisc;

import javax.swing.*;

public class TestPassingParameter {
    public static void main (String[] args) {
        DigitalVideoDisc jungleDVD = new DigitalVideoDisc("Jungle");
        DigitalVideoDisc cinderellaDVD = new DigitalVideoDisc("Cinderella");
        wrapDigitalVideoDisc wrapJungle = new wrapDigitalVideoDisc(jungleDVD);
        wrapDigitalVideoDisc wrapCinderella = new wrapDigitalVideoDisc(cinderellaDVD);

        swap(wrapCinderella,wrapJungle);
        JOptionPane.showMessageDialog(null,"Jungle DVD Title: " + wrapJungle.disc.getTitle());
        JOptionPane.showMessageDialog(null,"Cinderella DVD Title: " + wrapCinderella.disc.getTitle());

        changeTitle(jungleDVD,cinderellaDVD.getTitle());
        JOptionPane.showMessageDialog(null,"Jungle DVD Title: " + jungleDVD.getTitle());
    }

    private static void swap(wrapDigitalVideoDisc o1, wrapDigitalVideoDisc o2) {
        DigitalVideoDisc temp = o1.disc;
        o1.disc = o2.disc;
        o2.disc = temp;
    }
    public static void changeTitle (DigitalVideoDisc disc, String Title) {
        String oldTitle = disc.getTitle();
        disc.setTitle(Title);
        disc = new DigitalVideoDisc(oldTitle);
    }
}
