package com.kodilla.library.service;

import com.kodilla.library.domain.*;
import com.kodilla.library.repository.BookCopyRepository;
import com.kodilla.library.repository.BookRepository;
import com.kodilla.library.repository.BorrowedBookRepository;
import com.kodilla.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DbService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private BorrowedBookRepository borrowedBookRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(final Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public void deleteUser(final Long userId) {
        userRepository.deleteById(userId);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(final Long bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    public Book saveBook(final Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(final Long bookId) {
        bookRepository.deleteById(bookId);
    }

    public List<BookCopy> getAllBookCopies() {
        return bookCopyRepository.findAll();
    }

    public BookCopy getBookCopyById(final Long bookCopyId) {
        return bookCopyRepository.findById(bookCopyId).orElse(null);
    }

    public BookCopy saveBookCopy(final BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }

    public void deleteBookCopy(final Long bookCopyId) {
        bookCopyRepository.deleteById(bookCopyId);
    }

    public void updateRentalStatus(Long bookCopyId, RentalStatus rentalStatus) {

        Optional<BookCopy> bookCopy = bookCopyRepository.findById(bookCopyId);
        BookCopy bookCopy1 = bookCopy.get();
        bookCopy1.setRentalStatus(rentalStatus);
        bookCopyRepository.save(bookCopy1);
    }

    public Long countAvailableBookCopies(String title) {

        return bookCopyRepository.findAll().stream()
                .filter(t -> t.getRentalStatus().equals(RentalStatus.AVAILABLE))
                .filter(t -> t.getBook().getTitle().equals(title))
                .count();
    }

    public List<BorrowedBook> getAllBorrowedBooks() {
        return borrowedBookRepository.findAll();
    }

    public BorrowedBook saveBorrowedBook(final BorrowedBook borrowedBook) {
        borrowedBook.getBookCopy().setRentalStatus(RentalStatus.BORROWED);
        bookCopyRepository.save(borrowedBook.getBookCopy());
        return borrowedBookRepository.save(borrowedBook);
    }

    public void returnBorrowedBook(Long borrowedBookId) {
        Optional<BorrowedBook> borrowedBook = borrowedBookRepository.findById(borrowedBookId);
        BorrowedBook borrowedBook1 = borrowedBook.get();
        borrowedBook1.getBookCopy().setRentalStatus(RentalStatus.AVAILABLE);
        bookCopyRepository.save(borrowedBook1.getBookCopy());
        borrowedBook1.setReturnBookDate(LocalDate.now());
        borrowedBookRepository.save(borrowedBook1);
    }
}