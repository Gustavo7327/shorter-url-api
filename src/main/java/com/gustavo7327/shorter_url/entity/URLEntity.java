package com.gustavo7327.shorter_url.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="urls")
public class URLEntity {
    
    @Id
    private String id;

    private String urlOriginal;

    
    public URLEntity(){

    }

    public URLEntity(String id, String urlOriginal) {
        this.id = id;
        this.urlOriginal = urlOriginal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlOriginal() {
        return urlOriginal;
    }

    public void setUrlOriginal(String urlOriginal) {
        this.urlOriginal = urlOriginal;
    }

}