package com.example.resumeanalyzer.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.resumeanalyzer.model.Resume;
import com.example.resumeanalyzer.repository.ResumeRepository;
import com.example.resumeanalyzer.service.PdfService;
import com.example.resumeanalyzer.service.SkillService;

@Controller
public class ResumeController {

    private final PdfService pdfService;
    private final SkillService skillService;
    private final ResumeRepository resumeRepository;

    public ResumeController(
            PdfService pdfService,
            SkillService skillService,
            ResumeRepository resumeRepository) {

        this.pdfService = pdfService;
        this.skillService = skillService;
        this.resumeRepository = resumeRepository;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String uploadResume(
            @RequestParam("file") MultipartFile file)
            throws IOException {

        String text = pdfService.extractText(file);

        int score = skillService.calculateScore(text);

        String skills = skillService.getDetectedSkills(text);

        String suggestions =
                skillService.getSuggestions(text);

        Resume resume = new Resume();

        resume.setFileName(file.getOriginalFilename());
        resume.setAtsScore(score);
        resume.setSkills(skills);
        resume.setSuggestions(suggestions);

        resumeRepository.save(resume);

        return "ATS Score: " + score + "%\n\n"
                + skillService.analyzeSkills(text)
                + "\n\nSuggestions:\n"
                + suggestions;
    }

    @GetMapping("/history")
    public String history(Model model) {

        model.addAttribute(
                "resumes",
                resumeRepository.findAll());

        return "history";
    }
}