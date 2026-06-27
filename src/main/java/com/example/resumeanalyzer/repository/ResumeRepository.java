package com.example.resumeanalyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.resumeanalyzer.model.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

}