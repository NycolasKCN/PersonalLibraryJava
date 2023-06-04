package nycdev.dto;

public class BookDto {
    private Long id;
    private String name;
    private String author;
    private String pages;
    private String owner;

    public BookDto() {
    }

    public BookDto(Long id, String name, String author, String pages, String owner) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.pages = pages;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}

