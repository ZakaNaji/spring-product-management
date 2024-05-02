package com.znaji.productmanagement.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "picture")
public class Category {

    private Integer id;
    private String name;
    private String description;
    private byte[] picture;
}
