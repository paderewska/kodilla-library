package com.kodilla.library.domain.Dto;

import com.kodilla.library.domain.BorrowedBook;
import com.kodilla.library.domain.User;
import com.kodilla.library.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDtoTestSuite {

    @Autowired
    UserDto userDto;

    @Autowired
    UserRepository userRepository;

//    @Test
//    public void testSaveUsers() {
//
//        //Given
//        List<BorrowedBook> borrowedBooks = new ArrayList<>();
//        User user = new User("Iza", "Kowalska", LocalDate.of(2018, 3, 21), borrowedBooks);
//
//        //When
//        userRepository.save(user);
//
//        //Then
//        Optional<User> users = userRepository.findById(999L);
//        Assert.assertEquals(999L, users.get().getId(), 0);
//
//        //CleanUp
//        userRepository.delete(user);
//    }



}