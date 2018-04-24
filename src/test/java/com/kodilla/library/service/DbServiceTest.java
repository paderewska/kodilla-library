package com.kodilla.library.service;

import com.kodilla.library.domain.*;
import com.kodilla.library.repository.BookCopyRepository;
import com.kodilla.library.repository.BookRepository;
import com.kodilla.library.repository.BorrowedBookRepository;
import com.kodilla.library.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DbServiceTest {

    @Autowired
    DbService dbService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookCopyRepository bookCopyRepository;

    @Autowired
    BorrowedBookRepository borrowedBookRepository;

    @Test
    public void getAllUsers() {

        //Given
        User user1 = new User("Iza", "Testowa", LocalDate.now());
        User user2 = new User("Jurand", "Testowy", LocalDate.now());
        userRepository.save(user1);
        userRepository.save(user2);

        //When
        List<User> test = dbService.getAllUsers();

        //Then
        assertEquals(2, test.size());

        //CleanUp
        userRepository.deleteById(user1.getId());
        userRepository.deleteById(user2.getId());
    }

    @Test
    public void getUserById() {

        //Given
        User user1 = new User("Iza", "Testowa", LocalDate.now());
        User user2 = new User("Jurand", "Testowy", LocalDate.now());
        userRepository.save(user1);
        userRepository.save(user2);
        Long id = user2.getId();

        //When
        User test = dbService.getUserById(id);

        //Then
        assertEquals("Jurand", test.getName());

        //CleanUp
        userRepository.deleteById(user1.getId());
        userRepository.deleteById(user2.getId());
    }

    @Test
    public void saveUser() {

        //Given
        User user1 = new User("Iza", "Testowa", LocalDate.now());
        User user2 = new User("Jurand", "Testowy", LocalDate.now());

        //When
        dbService.saveUser(user1);
        dbService.saveUser(user2);

        //Then
        assertEquals(2, dbService.getAllUsers().size());

        //CleanUp
        userRepository.deleteById(user1.getId());
        userRepository.deleteById(user2.getId());
    }

    @Test
    public void deleteUser() {

        //Given
        User user1 = new User("Iza", "Testowa", LocalDate.now());
        User user2 = new User("Jurand", "Testowy", LocalDate.now());
        userRepository.save(user1);
        userRepository.save(user2);

        //When
        dbService.deleteUser(user1.getId());
        dbService.deleteUser(user2.getId());

        //Then
        assertEquals(0, dbService.getAllUsers().size());
    }

    @Test
    public void getAllBooks() {

        //Given
        Book book1 = new Book("Ciekawa książka", "Interesujący autor", 2018);
        Book book2 = new Book("Różnorodny las", "Zrównaważona łąka", 2017);
        bookRepository.save(book1);
        bookRepository.save(book2);

        //When
        List<Book> test = dbService.getAllBooks();

        //Then
        assertEquals(2, test.size());

        //CleanUp
        bookRepository.deleteById(book1.getId());
        bookRepository.deleteById(book2.getId());
    }

    @Test
    public void getBookById() {

        //Given
        Book book1 = new Book("Ciekawa książka", "Interesujący autor", 2018);
        Book book2 = new Book("Różnorodny las", "Zrównaważona łąka", 2017);
        bookRepository.save(book1);
        bookRepository.save(book2);
        Long id = book2.getId();

        //When
        Book test = dbService.getBookById(id);

        //Then
        assertEquals("Różnorodny las", test.getTitle());

        //CleanUp
        bookRepository.deleteById(book1.getId());
        bookRepository.deleteById(book2.getId());
    }

    @Test
    public void saveBook() {

        //Given
        Book book1 = new Book("Ciekawa książka", "Interesujący autor", 2018);
        Book book2 = new Book("Różnorodny las", "Zrównaważona łąka", 2017);

        //When
        dbService.saveBook(book1);
        dbService.saveBook(book2);

        //Then
        assertEquals(2, dbService.getAllBooks().size());

        //CleanUp
        bookRepository.deleteById(book1.getId());
        bookRepository.deleteById(book2.getId());
    }

    @Test
    public void deleteBook() {

        //Given
        Book book1 = new Book("Ciekawa książka", "Interesujący autor", 2018);
        Book book2 = new Book("Różnorodny las", "Zrównaważona łąka", 2017);
        bookRepository.save(book1);
        bookRepository.save(book2);

        //When
        dbService.deleteBook(book1.getId());
        dbService.deleteBook(book2.getId());

        //Then
        assertEquals(0, dbService.getAllBooks().size());
    }

    @Test
    public void getAllBookCopies() {

        //Given
        Book book1 = new Book("Ciekawa książka", "Interesujący autor", 2018);
        bookRepository.save(book1);
        BookCopy bookCopy1 = new BookCopy(book1, RentalStatus.AVAILABLE);
        bookCopyRepository.save(bookCopy1);
        BookCopy bookCopy2 = new BookCopy(book1, RentalStatus.NEW);
        bookCopyRepository.save(bookCopy2);

        //When
        List<BookCopy> test = dbService.getAllBookCopies();

        //Then
        assertEquals(2, test.size());

        //CleanUp
        bookRepository.deleteById(book1.getId());
        bookCopyRepository.deleteById(bookCopy1.getId());
        bookCopyRepository.deleteById(bookCopy2.getId());
    }

    @Test
    public void getBookCopyById() {

        //Given
        Book book1 = new Book("Ciekawa książka", "Interesujący autor", 2018);
        bookRepository.save(book1);
        BookCopy bookCopy1 = new BookCopy(book1, RentalStatus.AVAILABLE);
        BookCopy bookCopy2 = new BookCopy(book1, RentalStatus.NEW);
        bookCopyRepository.save(bookCopy1);
        bookCopyRepository.save(bookCopy2);
        Long id = bookCopy2.getId();

        //When
        BookCopy test = dbService.getBookCopyById(id);

        //Then
        assertEquals(RentalStatus.NEW, test.getRentalStatus());

        //CleanUp
        bookRepository.deleteById(book1.getId());
        bookCopyRepository.deleteById(bookCopy1.getId());
        bookCopyRepository.deleteById(bookCopy2.getId());
    }

    @Test
    public void saveBookCopy() {

        //Given
        Book book1 = new Book("Ciekawa książka", "Interesujący autor", 2018);
        BookCopy bookCopy1 = new BookCopy(book1, RentalStatus.AVAILABLE);
        BookCopy bookCopy2 = new BookCopy(book1, RentalStatus.NEW);

        //When
        dbService.saveBook(book1);
        dbService.saveBookCopy(bookCopy1);
        dbService.saveBookCopy(bookCopy2);

        //Then
        assertEquals(2, dbService.getAllBookCopies().size());

        //CleanUp
        bookRepository.deleteById(book1.getId());
        bookCopyRepository.deleteById(bookCopy1.getId());
        bookCopyRepository.deleteById(bookCopy2.getId());
    }

    @Test
    public void deleteBookCopy() {

        //Given
        Book book1 = new Book("Ciekawa książka", "Interesujący autor", 2018);
        bookRepository.save(book1);
        BookCopy bookCopy1 = new BookCopy(book1, RentalStatus.NEW);
        BookCopy bookCopy2 = new BookCopy(book1, RentalStatus.NEW);
        bookCopyRepository.save(bookCopy1);
        bookCopyRepository.save(bookCopy2);

        //When
        dbService.deleteBookCopy(bookCopy1.getId());
        dbService.deleteBookCopy(bookCopy2.getId());

        //Then
        assertEquals(0, dbService.getAllBookCopies().size());

        //CleanUp
        dbService.deleteBook(book1.getId());

    }

    @Test
    public void updateRentalStatus() {
    }

    @Test
    public void countAvailableBookCopies() {
    }

    @Test
    public void getAllBorrowedBooks() {
    }

    @Test
    public void saveBorrowedBook() {

        //Given
        Book book1 = new Book("Ciekawa książka", "Interesujący autor", 2018);
        BookCopy bookCopy1 = new BookCopy(book1, RentalStatus.AVAILABLE);
        User user1 = new User("Iza", "Testowa", LocalDate.now());
        dbService.saveBook(book1);
        dbService.saveBookCopy(bookCopy1);
        dbService.saveUser(user1);
        BorrowedBook borrowedBook1 = new BorrowedBook(bookCopy1, user1, LocalDate.now
                (), LocalDate.now());

        //When
        dbService.saveBorrowedBook(borrowedBook1);

        //Then
        assertEquals(1, dbService.getAllBorrowedBooks().size());

        //CleanUp
        bookRepository.deleteById(book1.getId());
        bookCopyRepository.deleteById(bookCopy1.getId());
        userRepository.deleteById(user1.getId());
    }

    @Test
    public void returnBorrowedBook() {
    }
}