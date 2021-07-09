package com.yaskovskyi.webfanfic.model;

import lombok.Data;

@Data
public class User {
        private Long id;
        private String email;
        private String userName;
        private String password;
        private String firstname;
        private String lastname;
}
