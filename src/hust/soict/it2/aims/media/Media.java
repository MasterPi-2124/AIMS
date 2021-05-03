package hust.soict.it2.aims.media;

public class Media {
    private String title;
    private String category;
    private float cost;
    private String ID;

    public Media (String ID, String title, String category, Float cost) {
        this.ID = ID;
        this.title = title;
        this.cost = cost;
        this.category = category;
    }

    public Media() {

    }

    public String[] getAuthors() {
        Book book = new Book();
        return book.getAuthors();
    }

    public String getContent() {
        Book book = new Book();
        return book.getContent();
    }

    public String getDirector() {
        DigitalVideoDisc disc = new DigitalVideoDisc();
        return disc.getDirector();
    }

    public int getLength() {
        DigitalVideoDisc disc = new DigitalVideoDisc();
        return disc.getLength();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String categoty) {
        this.category = categoty;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

}
