package hust.soict.it2.aims.media;

import java.util.ArrayList;

public class Book extends Media{
    String[] authors;
    String content;

    public Book(String id, String title, String categoty, String[] authors, String content, Float cost) {
        super(id, title, categoty, cost);
        this.authors = authors;
        this.content = content;
    }

    public Book() {

    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<String> authors) {
        authors = authors;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
