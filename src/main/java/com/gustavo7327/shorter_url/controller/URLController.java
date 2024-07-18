package com.gustavo7327.shorter_url.controller;

import java.net.URI;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gustavo7327.shorter_url.dto.URLRequest;
import com.gustavo7327.shorter_url.dto.URLResponse;
import com.gustavo7327.shorter_url.entity.URLEntity;
import com.gustavo7327.shorter_url.repository.URLRepository;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class URLController {

    private final URLRepository repository;
    
    public URLController(URLRepository repository) {
        this.repository = repository;
    }

    @PostMapping(value = "/shorten-url")
    public ResponseEntity<URLResponse> create(@RequestBody URLRequest request, HttpServletRequest httpServletRequest){

        var existingURL = repository.findByUrlOriginal(request.url());
        if(!existingURL.isEmpty()) {
            String redirect = httpServletRequest.getRequestURL().toString().replace("shorten-url", existingURL.get().getId());
            return ResponseEntity.ok(new URLResponse(redirect));
        }

        String id;
        do{
            id = RandomStringUtils.randomAlphanumeric(5,10);
        } while(repository.existsById(id));

        repository.save(new URLEntity(id, request.url()));

        String redirect = httpServletRequest.getRequestURL().toString().replace("shorten-url",id);
        return ResponseEntity.ok(new URLResponse(redirect));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Void> redirect(@PathVariable("id") String id){

        var urlOp = repository.findById(id);       

        if(urlOp.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(urlOp.get().getUrlOriginal()));
        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView("index.html");
        mav.addObject("url", new URLEntity());
        return mav;
    }
}
