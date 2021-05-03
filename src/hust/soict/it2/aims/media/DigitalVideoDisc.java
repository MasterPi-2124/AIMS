package hust.soict.it2.aims.disc;

import java.security.PublicKey;

public class DigitalVideoDisc{

    private String title;
    private String category;
    private String director;
    private int length;
    private float cost;

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        this.title = title;
        this.category = category;
        this.director = director;
        this.length = length;
        this.cost = cost;
    }

    public DigitalVideoDisc(String title) {
        this.title = title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }


     public String getTitle() {
        return title;
     }

    public String getDirector() {
        return director;
    }
    public int getLength() {
        return length;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }
}
