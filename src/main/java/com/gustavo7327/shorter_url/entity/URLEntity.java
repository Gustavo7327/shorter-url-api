package com.gustavo7327.shorter_url.entity;

import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="urls")
public class URLEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String urlOriginal;

    private LocalDateTime expires;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getUrlOriginal() {
        return urlOriginal;
    }


    public void setUrlOriginal(String urlOriginal) {
        this.urlOriginal = urlOriginal;
    }


    public LocalDateTime getExpires() {
        return expires;
    }


    public void setExpires(LocalDateTime expires) {
        this.expires = expires;
    }

}
