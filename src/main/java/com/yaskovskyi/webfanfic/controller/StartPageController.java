package com.yaskovskyi.webfanfic.controller;

import com.yaskovskyi.webfanfic.model.Post;
import com.yaskovskyi.webfanfic.util.HttpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class StartPageController {


    @RequestMapping("/")
    public String openStartPage(HttpServletRequest request,
                              HttpServletResponse response1, Model model) throws Exception {
        List<Post> posts = HttpService.getInstance().getPosts();
        model.addAttribute("postsList", posts);
        return "index";
    }

//    private String[] getTags(List<Post> list) {
//        String[] tags = new String[list.size()];
//        for (int i = 0; i < list.size(); i++) {
//            tags[i] = list.get(i).getFanfic_tags().split(", ");
//        }
//        return ;
//    }

}
