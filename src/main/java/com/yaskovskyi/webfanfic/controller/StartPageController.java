package com.yaskovskyi.webfanfic.controller;

import com.yaskovskyi.webfanfic.model.Post;
import com.yaskovskyi.webfanfic.util.HttpService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StartPageController {


    @RequestMapping("/")
    public String openStartPage(HttpServletRequest request,
                              HttpServletResponse response1, Model model) throws Exception {
        List<Post> posts = HttpService.getInstance().getPosts();
        System.out.println("CHECKS  " + posts.get(0));
        model.addAttribute("postsList", posts);
        model.addAttribute("tagsList", getTags(posts));
        return "index";
    }

    private List<String[]> getTags(List<Post> list) {
        List<String[]> tags = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String[] array = list.get(0).getFanfic_tags().split(", ");
            tags.add(array);
        }
        return tags;
    }

}
