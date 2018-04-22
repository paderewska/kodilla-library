package com.kodilla.library.mapper;

import com.kodilla.library.domain.Dto.UserDto;
import com.kodilla.library.domain.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User maptoUser(final UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getSurname(),
                userDto.getAccountStartDate()==null? LocalDate.now(): userDto.getAccountStartDate());
    }

    public UserDto maptoUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getAccountStartDate());
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList) {
        return userList.stream()
                .map(t -> new UserDto(t.getId(), t.getName(), t.getSurname(), t.getAccountStartDate()))
                .collect(Collectors.toList());
    }
}
