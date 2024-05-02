package com.znaji.productmanagement.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString(exclude = "picture")
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @Column(name = "category_id")
    private Integer id;
    @Column(name = "category_name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "picture")
    private byte[] picture;
}
