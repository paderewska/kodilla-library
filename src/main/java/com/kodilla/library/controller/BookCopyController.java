package com.kodilla.library.controller;

import com.kodilla.library.domain.Dto.BookCopyDto;
import com.kodilla.library.domain.RentalStatus;
import com.kodilla.library.mapper.BookCopyMapper;
import com.kodilla.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/bookcopies")
public class BookCopyController {

    @Autowired
    private DbService service;

    @Autowired
    private BookCopyMapper bookCopyMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getBookCopies")
    public List<BookCopyDto> getBookCopies() {
        return bookCopyMapper.mapToBookCopiesDtoList(service.getAllBookCopies());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getBookCopy")
    public BookCopyDto getBookCopy(@RequestParam Long bookCopyId) throws NotFoundRecordException {
        return bookCopyMapper.mapToBookCopiesDto(service.getBookCopyById(bookCopyId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "addBookCopy", consumes = APPLICATION_JSON_VALUE)
    public void addBookCopy(@RequestBody BookCopyDto bookCopyDto) {
        bookCopyMapper.mapToBookCopiesDto(service.saveBookCopy(bookCopyMapper.mapToBookCopies(bookCopyDto)));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateBookCopy")
    public void updateBookCopy(@RequestBody BookCopyDto bookCopyDto) {
        bookCopyMapper.mapToBookCopiesDto(service.saveBookCopy(bookCopyMapper.mapToBookCopies(bookCopyDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteBookCopy")
    public void deleteUser(@RequestParam Long bookCopyId) {
        service.deleteBookCopy(bookCopyId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateStatus")
    public void updateStatus(@RequestParam Long bookCopyId, @RequestParam RentalStatus rentalStatus) {
        service.updateRentalStatus(bookCopyId, rentalStatus);
    }

    @RequestMapping(method = RequestMethod.GET, value = "countAvailableBookCopies")
    public Long countAvailableBookCopies(@RequestParam String title) throws NotFoundRecordException {
        return service.countAvailableBookCopies(title);
    }

}
