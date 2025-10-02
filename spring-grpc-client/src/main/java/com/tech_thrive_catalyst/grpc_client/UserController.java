package com.tech_thrive_catalyst.grpc_client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    public UserController(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable String id) {
        return userService.getUser(id);
    }

    @PostMapping
    public UserDto addUser(@RequestParam String name, @RequestParam String email) {
        return userService.addUser(name, email);
    }
}
