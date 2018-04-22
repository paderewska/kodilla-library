package com.kodilla.library.repository;

import com.kodilla.library.domain.BorrowedBook;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BorrowedBookRepository extends CrudRepository<BorrowedBook, Long> {

    @Override
    List<BorrowedBook> findAll();

    @Override
    BorrowedBook save(BorrowedBook borrowedBook);



}