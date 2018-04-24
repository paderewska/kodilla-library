package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOOK_COPIES")
public class BookCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "status")
    private RentalStatus rentalStatus;

    @OneToMany(
            targetEntity = BorrowedBook.class,
            mappedBy = "bookCopy",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<BorrowedBook> borrowedBooks = new ArrayList<>();

    public BookCopy(Book book, RentalStatus rentalStatus) {
        this.book = book;
        this.rentalStatus = rentalStatus;
    }

    public BookCopy(Long id, Book book, RentalStatus rentalStatus) {
        this.id = id;
        this.book = book;
        this.rentalStatus = rentalStatus;
    }
}
