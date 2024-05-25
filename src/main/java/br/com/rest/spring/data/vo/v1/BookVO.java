package br.com.rest.spring.data.vo.v1;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.Objects;

public class BookVO extends RepresentationModel<PersonVO> {
    private Long bookId;
    private String author;
    private LocalDate launchDate;
    private Double price;
    private String title;

    public BookVO() {
    }

    public BookVO(Long bookId, String author, LocalDate launchDate, Double price, String title) {
        this.bookId = bookId;
        this.author = author;
        this.launchDate = launchDate;
        this.price = price;
        this.title = title;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDate launchDate) {
        this.launchDate = launchDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookVO bookVO)) return false;
        return Objects.equals(getBookId(), bookVO.getBookId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBookId());
    }
}
