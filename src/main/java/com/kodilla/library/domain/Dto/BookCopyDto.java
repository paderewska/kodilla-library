package com.kodilla.library.domain.Dto;

import com.kodilla.library.domain.RentalStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookCopyDto {

    private Long Id;
    private Long bookId;
    private RentalStatus rentalStatus;

}
