package com.snow.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Problem {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    
    @Type(type = "text")
    private String content;
}
