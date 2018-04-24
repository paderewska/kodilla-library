package com.kodilla.library.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "BORROWED_BOOKS")
public class BorrowedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_copy_id")
    private BookCopy bookCopy;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @NotNull
    @Column(name = "borrowed_book_date")
    private LocalDate borrowedBookDate;

    @NotNull
    @Column(name = "return_book_date")
    private LocalDate returnBookDate;

    public BorrowedBook(BookCopy bookCopy, User userId, LocalDate borrowedBookDate, LocalDate returnBookDate) {
        this.bookCopy = bookCopy;
        this.userId = userId;
        this.borrowedBookDate = borrowedBookDate;
        this.returnBookDate = returnBookDate;
    }

    public BorrowedBook(Long id, BookCopy bookCopy, User userId, LocalDate borrowedBookDate, LocalDate returnBookDate) {
        this.id = id;
        this.bookCopy = bookCopy;
        this.userId = userId;
        this.borrowedBookDate = borrowedBookDate;
        this.returnBookDate = returnBookDate;
    }
}
