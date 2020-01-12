package com.jiangj.concurrency.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jiang Jian
 * @since 2020/1/12
 */
@RestController
@Slf4j
public class TestController {

    @RequestMapping("/test")
    public String test(){
        return "test";
    }

}
