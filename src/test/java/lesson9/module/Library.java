package lesson9.module;

import java.util.List;


public class Library {

    private String name;
    private String address;
    private String opening_hours;
    private List<LibraryInner> books;

    public List<LibraryInner> getBooks() {
        return books;
    }

    public void setBooks(List<LibraryInner> books) {
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpening_hours() {
        return opening_hours;
    }

    public void setOpening_hours(String opening_hours) {
        this.opening_hours = opening_hours;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
