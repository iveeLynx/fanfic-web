package com.yaskovskyi.webfanfic.controller;

import com.yaskovskyi.webfanfic.model.Post;
import com.yaskovskyi.webfanfic.util.HttpService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostReading {

    @RequestMapping("/post_reading")
    public String showPostText(@RequestParam(value = "post-id")String id, Model model) {
        Post post = null;
        post = HttpService.getInstance().getPost(id);
        model.addAttribute("post", post);
        System.out.println(post);
        return "reading";
    }
}
