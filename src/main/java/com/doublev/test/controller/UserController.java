package com.doublev.test.controller;

import com.doublev.test.dto.UserDto;
import com.doublev.test.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/users")
@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserDto> find(@RequestParam("q") String username) {
        return ResponseEntity.ok(userService.findUsers(username));
    }

}
