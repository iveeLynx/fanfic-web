package com.yaskovskyi.webfanfic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PublishController {
    @RequestMapping("/publish")
    public String openPublishPostPage() {
        return "publish";
    }

}
