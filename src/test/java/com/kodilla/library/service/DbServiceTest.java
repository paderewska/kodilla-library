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
import java.util.ArrayList;
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
        List<BookCopy> theList = new ArrayList<>();
        theList.add(new BookCopy(RentalStatus.AVAILABLE));
        theList.add(new BookCopy(RentalStatus.NEW));
        Book book1 = new Book("Ciekawa książka", "Interesujący autor", 2018, theList);
        bookRepository.save(book1);

        //When
        List<BookCopy> test = dbService.getAllBookCopies();

        //Then
        assertEquals(2, test.size());

        //CleanUp
         bookCopyRepository.deleteById(book1.getBookCopies().get(0).getId());
         bookCopyRepository.deleteById(book1.getBookCopies().get(1).getId());
         bookRepository.deleteById(book1.getId());
    }

    @Test
    public void getBookCopyById() {

        //Given
        List<BookCopy> theList = new ArrayList<>();
        theList.add(new BookCopy(RentalStatus.AVAILABLE));
        theList.add(new BookCopy(RentalStatus.NEW));
        Book book1 = new Book("Ciekawa książka", "Interesujący autor", 2018, theList);
        bookRepository.save(book1);
        Long id = theList.get(1).getId();

        //When
        BookCopy test = dbService.getBookCopyById(id);

        //Then
        assertEquals(RentalStatus.NEW, test.getRentalStatus());

        //CleanUp
        bookCopyRepository.deleteById(book1.getBookCopies().get(0).getId());
        bookCopyRepository.deleteById(book1.getBookCopies().get(1).getId());
        bookRepository.deleteById(book1.getId());
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
        bookCopyRepository.deleteById(bookCopy1.getId());
        bookCopyRepository.deleteById(bookCopy2.getId());
        bookRepository.deleteById(book1.getId());
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
        bookRepository.deleteById(book1.getId());
    }

    @Test
    public void updateRentalStatus() {

        //Given
        List<BookCopy> theList = new ArrayList<>();
        theList.add(new BookCopy(RentalStatus.NEW));
        Book book1 = new Book("Ciekawa książka", "Interesujący autor", 2018, theList);
        bookRepository.save(book1);
        bookCopyRepository.save(theList.get(0));
        Long id = theList.get(0).getId();

        //When
        dbService.updateRentalStatus(id, RentalStatus.AVAILABLE);

        //Then
        assertEquals(RentalStatus.AVAILABLE, dbService.getBookCopyById(id).getRentalStatus());

        //CleanUp
        bookCopyRepository.deleteById(book1.getBookCopies().get(0).getId());
        bookRepository.deleteById(book1.getId());
    }

    @Test
    public void countAvailableBookCopies() {

        //Given
        Book book1 = new Book("Ciekawa książka", "Interesujący autor", 2018);
        bookRepository.save(book1);
        BookCopy bookCopy1 = new BookCopy(book1, RentalStatus.AVAILABLE);
        BookCopy bookCopy2 = new BookCopy(book1, RentalStatus.AVAILABLE);
        BookCopy bookCopy3 = new BookCopy(book1, RentalStatus.NEW);
        bookCopyRepository.save(bookCopy1);
        bookCopyRepository.save(bookCopy2);
        bookCopyRepository.save(bookCopy3);

        //When
        Long count = dbService.countAvailableBookCopies("Ciekawa książka");

        //Then
        assertEquals(2, count, 0);

        //CleanUp
        bookCopyRepository.deleteById(bookCopy1.getId());
        bookCopyRepository.deleteById(bookCopy2.getId());
        bookCopyRepository.deleteById(bookCopy3.getId());
        bookRepository.deleteById(book1.getId());
    }

    @Test
    public void getAllBorrowedBooks() {

        //Given
        Book book1 = new Book("Ciekawa książka", "Interesujący autor", 2018);
        bookRepository.save(book1);
        BookCopy bookCopy1 = new BookCopy(book1, RentalStatus.AVAILABLE);
        bookCopyRepository.save(bookCopy1);
        User user1 = new User("Iza", "Testowa", LocalDate.now());
        userRepository.save(user1);
        BorrowedBook borrowedBook1 = new BorrowedBook(bookCopy1, user1, LocalDate.now
                (), LocalDate.now());
        dbService.saveBorrowedBook(borrowedBook1);

        //When
        List<BorrowedBook> test = dbService.getAllBorrowedBooks();

        //Then
        assertEquals(1, test.size());

        //CleanUp
        borrowedBookRepository.delete(borrowedBook1);
        bookCopyRepository.deleteById(bookCopy1.getId());
        userRepository.deleteById(user1.getId());
        bookRepository.deleteById(book1.getId());
    }

    @Test
    public void saveBorrowedBook() {

        //Given
        Book book1 = new Book("Ciekawa książka", "Interesujący autor", 2018);
        bookRepository.save(book1);
        BookCopy bookCopy1 = new BookCopy(book1, RentalStatus.NEW);
        bookCopyRepository.save(bookCopy1);
        User user1 = new User("Iza", "Testowa", LocalDate.now());
        userRepository.save(user1);
        BorrowedBook borrowedBook1 = new BorrowedBook(bookCopy1, user1, LocalDate.now(), LocalDate.of(2999, 12,31));

        //When
        dbService.saveBorrowedBook(borrowedBook1);

        //Then
        assertEquals(1, dbService.getAllBorrowedBooks().size());
        assertEquals(RentalStatus.BORROWED, bookCopy1.getRentalStatus());

        //CleanUp
        borrowedBookRepository.delete(borrowedBook1);
        bookCopyRepository.deleteById(bookCopy1.getId());
        userRepository.deleteById(user1.getId());
        bookRepository.deleteById(book1.getId());
    }

    @Test
    public void returnBorrowedBook() {

        //Given
        Book book1 = new Book("Ciekawa książka", "Interesujący autor", 2018);
        bookRepository.save(book1);
        BookCopy bookCopy1 = new BookCopy(book1, RentalStatus.NEW);
        bookCopyRepository.save(bookCopy1);
        User user1 = new User("Iza", "Testowa", LocalDate.now());
        userRepository.save(user1);
        BorrowedBook borrowedBook1 = new BorrowedBook(bookCopy1, user1, LocalDate.now(), LocalDate.of(1990, 12,31));
        dbService.saveBorrowedBook(borrowedBook1);

        //When
        dbService.returnBorrowedBook(borrowedBook1.getId());

        //Then
        assertEquals(RentalStatus.AVAILABLE, dbService.getAllBorrowedBooks().get(0).getBookCopy().getRentalStatus());
        assertEquals(LocalDate.now(), dbService.getAllBorrowedBooks().get(0).getReturnBookDate());

        //CleanUp
        borrowedBookRepository.delete(borrowedBook1);
        bookCopyRepository.deleteById(bookCopy1.getId());
        userRepository.deleteById(user1.getId());
        bookRepository.deleteById(book1.getId());
    }
}