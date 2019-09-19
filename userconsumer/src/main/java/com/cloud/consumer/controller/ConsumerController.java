package com.cloud.consumer.controller;

import com.cloud.consumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("consumer")
public class ConsumerController {

    @Autowired
    private UserService userService;


    @GetMapping
    public List<String> consume(@RequestParam("ids") List<Long> ids) {
        return this.userService.querUserByIds(ids);
    }

}