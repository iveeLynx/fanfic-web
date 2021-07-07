package com.yaskovskyi.webfanfic.model;

import lombok.Data;

import javax.print.DocFlavor;
import javax.swing.*;
import java.util.Date;

@Data
public class Post {

    private int id;
    private String fanfic_name;
    private String fanfic_fandom;
    private String fanfic_tags;
    private String fanfic_description;
    private String fanfic_date;
    private String fanfic_image;
    private String fanfic_text;
    private String fanfic_creator;
//    private String text;
}
