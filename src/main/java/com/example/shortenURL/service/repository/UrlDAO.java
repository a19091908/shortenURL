package com.example.shortenURL.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shortenURL.obj.URL;

public interface UrlDAO extends JpaRepository<URL , String> {

}
