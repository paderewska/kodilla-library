package com.kodilla.library.controller;

import com.kodilla.library.domain.Dto.BorrowedBookDto;
import com.kodilla.library.mapper.BorrowedBookMapper;
import com.kodilla.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/borrowedbook")
public class BorrowedBookController {

    @Autowired
    private DbService service;

    @Autowired
    private BorrowedBookMapper borrowedBookMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getBorrowedBooks")
    public List<BorrowedBookDto> getBorrowedBooks() {
        return borrowedBookMapper.mapToBorrowedBooksDtoList(service.getAllBorrowedBooks());
    }

    @RequestMapping(method = RequestMethod.POST, value = "borrowBook", consumes = APPLICATION_JSON_VALUE)
    public void borrowBook(@RequestBody BorrowedBookDto borrowedBookDto) {
        borrowedBookMapper.maptoBorrowedBookDto(service.saveBorrowedBook(borrowedBookMapper.mapToBorrowedBook(borrowedBookDto)));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "returnBook")
    public void returnBook(@RequestParam Long borrowedBookId) {
        service.returnBorrowedBook(borrowedBookId);
    }
}
