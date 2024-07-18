package com.gustavo7327.shorter_url.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavo7327.shorter_url.entity.URLEntity;

public interface URLRepository extends JpaRepository<URLEntity,String>{
    
    Optional<URLEntity> findByUrlOriginal(String urlOriginal);
}
