/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

/**
 *
 * @author Pc World
 */
public class Book {
   private int id;
    private String title;
    private String author;
    private String isban;
    private String publishDate;
    private String bookImagePath;
    private String language;
    private String catgeory;
    private int pageCount;
    private int copyCount;
    private String publisher;

    public Book(int id, String title, String author, String isban, String publishDate, String bookImagePath, String language, String catgeory, int pageCount, int copyCount, String publisher) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isban = isban;
        this.publishDate = publishDate;
        this.bookImagePath = bookImagePath;
        this.language = language;
        this.catgeory = catgeory;
        this.pageCount = pageCount;
        this.copyCount = copyCount;
        this.publisher = publisher;
    }

    public Book(String title, String author, String isban, String publishDate, String bookImagePath, String language, String catgeory, int pageCount, int copyCount, String publisher) {
        this.title = title;
        this.author = author;
        this.isban = isban;
        this.publishDate = publishDate;
        this.bookImagePath = bookImagePath;
        this.language = language;
        this.catgeory = catgeory;
        this.pageCount = pageCount;
        this.copyCount = copyCount;
        this.publisher = publisher;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the isban
     */
    public String getIsban() {
        return isban;
    }

    /**
     * @param isban the isban to set
     */
    public void setIsban(String isban) {
        this.isban = isban;
    }

    /**
     * @return the publishDate
     */
    public String getPublishDate() {
        return publishDate;
    }

    /**
     * @param publishDate the publishDate to set
     */
    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * @return the bookImagePath
     */
    public String getBookImagePath() {
        return bookImagePath;
    }

    /**
     * @param bookImagePath the bookImagePath to set
     */
    public void setBookImagePath(String bookImagePath) {
        this.bookImagePath = bookImagePath;
    }

    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return the catgeory
     */
    public String getCatgeory() {
        return catgeory;
    }

    /**
     * @param catgeory the catgeory to set
     */
    public void setCatgeory(String catgeory) {
        this.catgeory = catgeory;
    }

    /**
     * @return the pageCount
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * @param pageCount the pageCount to set
     */
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * @return the copyCount
     */
    public int getCopyCount() {
        return copyCount;
    }

    /**
     * @param copyCount the copyCount to set
     */
    public void setCopyCount(int copyCount) {
        this.copyCount = copyCount;
    }

    /**
     * @return the publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * @param publisher the publisher to set
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

}
