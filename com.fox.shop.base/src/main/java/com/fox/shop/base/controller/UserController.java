package com.fox.shop.base.controller;

import com.fox.protocol.user.UserModel;
import com.fox.shop.base.service.i.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserModel> create(@RequestBody final UserModel userModel) {
        return ResponseEntity.ok(userService.create(userModel));
    }

    @GetMapping("/{id}")
    public UserModel get(@PathVariable final long id) {
        return userService.get(id);
    }
}
