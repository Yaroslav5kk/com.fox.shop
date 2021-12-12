package com.fox.shop.notify.bot.controller;

import com.fox.shop.notify.bot.entity.UserInfoEntity;
import com.fox.shop.notify.bot.repository.UserInfoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/user-info")
public class UserInfoController {

    private final UserInfoRepository userInfoRepository;

    public UserInfoController(
            final UserInfoRepository userInfoRepository
    ) {
        this.userInfoRepository = userInfoRepository;
    }

    @PostMapping
    public Mono<ResponseEntity<UserInfoEntity>> save(
            @RequestBody final UserInfoEntity user
    ) {
        return userInfoRepository.save(user).map(ResponseEntity::ok);
    }
}



















