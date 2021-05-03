package hust.soict.it2.aims.media;

public class DigitalVideoDisc extends Disc {
    private String director;
    private int length;

    public DigitalVideoDisc(String ID, String title, String category, String director, int length, float cost) {
        super(ID, title,category,cost);
        this.director = director;
        this.length = length;
    }

    public DigitalVideoDisc() {
        super();
    }

    public String getDirector() {
        return director;
    }

    public int getLength() {
        return length;
    }
}
