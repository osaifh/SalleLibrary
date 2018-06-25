package com.example.alumne.sallelibrary;

public class Book {

    private String title, description, author, image_url;

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {

        return author;
    }

    public Book() {

    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Book(String title, String description, String author) {
        this.title = title;
        this.description = description;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Titulo:'" + title;
    }
}
