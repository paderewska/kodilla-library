package com.kodilla.library.domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowedBookDto {

    private Long id;
    private Long bookCopyId;
    private Long userId;
    private LocalDate borrowedBookDate;
    private LocalDate returnBookDate;

}
