package com.mydecoration.decorationmall.web.controllers;

import com.mydecoration.decorationmall.common.http.Result;
import com.mydecoration.decorationmall.common.model.response.TestResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/whippet/download")
public class TestController {

    @PostMapping("/create")
    public Result<TestResponse> create(HttpServletRequest httpRequest) {
        return Result.success(null);
    }
}
