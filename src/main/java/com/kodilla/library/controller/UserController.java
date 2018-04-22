package com.kodilla.library.controller;

import com.kodilla.library.domain.Dto.UserDto;
import com.kodilla.library.mapper.UserMapper;
import com.kodilla.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private DbService service;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getUsers")
    public List<UserDto> getUsers() {
        return userMapper.mapToUserDtoList(service.getAllUsers());
    }

    @RequestMapping(method =  RequestMethod.GET, value = "getUser")
    public UserDto getUser(@RequestParam Long userId) throws NotFoundRecordException {
        return userMapper.maptoUserDto(service.getUserById(userId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "addUser", consumes = APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody UserDto userDto) {
        userMapper.maptoUserDto(service.saveUser(userMapper.maptoUser(userDto)));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateUser")
    public void updateUser(@RequestBody UserDto userDto) {
        userMapper.maptoUserDto(service.saveUser(userMapper.maptoUser(userDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteUser")
    public void deleteUser(@RequestParam Long userId) {
        service.deleteUser(userId);
    }

}
