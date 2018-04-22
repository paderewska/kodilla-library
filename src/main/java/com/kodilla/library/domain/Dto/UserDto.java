package com.kodilla.library.domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {

    private Long id;
    private String name;
    private String surname;
    private LocalDate accountStartDate;
}