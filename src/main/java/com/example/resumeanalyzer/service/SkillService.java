package com.example.resumeanalyzer.service;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SkillService {

   public String analyzeSkills(String text) {

    text = text.toLowerCase();

    StringBuilder detectedSkills = new StringBuilder();
    StringBuilder missingSkills = new StringBuilder();

    if (text.contains("java")) {
        detectedSkills.append("✓ Java\n");
    } else {
        missingSkills.append("• Java\n");
    }

    if (text.contains("python")) {
        detectedSkills.append("✓ Python\n");
    } else {
        missingSkills.append("• Python\n");
    }

    if (text.contains("sql")) {
        detectedSkills.append("✓ SQL\n");
    } else {
        missingSkills.append("• SQL\n");
    }

    if (text.contains("html")) {
        detectedSkills.append("✓ HTML\n");
    } else {
        missingSkills.append("• HTML\n");
    }

    if (text.contains("css")) {
        detectedSkills.append("✓ CSS\n");
    } else {
        missingSkills.append("• CSS\n");
    }

    if (text.contains("javascript")) {
        detectedSkills.append("✓ JavaScript\n");
    } else {
        missingSkills.append("• JavaScript\n");
    }

    if (text.contains("spring")) {
        detectedSkills.append("✓ Spring Boot\n");
    } else {
        missingSkills.append("• Spring Boot\n");
    }

    return "Detected Skills:\n"
            + detectedSkills.toString()
            + "\nMissing Skills:\n"
            + missingSkills.toString();
}




    public int calculateScore(String text) {

        text = text.toLowerCase();

        int score = 0;

        if (text.contains("java"))
            score += 15;

        if (text.contains("python"))
            score += 15;

        if (text.contains("sql"))
            score += 15;

        if (text.contains("html"))
            score += 10;

        if (text.contains("css"))
            score += 10;

        if (text.contains("javascript"))
            score += 15;

        if (text.contains("spring"))
            score += 20;

        return score;
    }

    public String getDetectedSkills(String text) {

        text = text.toLowerCase();

        StringBuilder skills = new StringBuilder();

        if (text.contains("java"))
            skills.append("Java, ");

        if (text.contains("python"))
            skills.append("Python, ");

        if (text.contains("sql"))
            skills.append("SQL, ");

        if (text.contains("html"))
            skills.append("HTML, ");

        if (text.contains("css"))
            skills.append("CSS, ");

        if (text.contains("javascript"))
            skills.append("JavaScript, ");

        if (text.contains("spring"))
            skills.append("Spring Boot, ");

        if (skills.length() > 2) {
            skills.setLength(skills.length() - 2);
        }

        return skills.toString();
    }

    public String getSuggestions(String text) {

        text = text.toLowerCase();

        StringBuilder suggestions = new StringBuilder();

        if (!text.contains("github")) {
            suggestions.append("• Add GitHub profile.\n");
        }

        if (!text.contains("linkedin")) {
            suggestions.append("• Add LinkedIn profile.\n");
        }

        if (!text.contains("project")) {
            suggestions.append("• Include project details.\n");
        }

        if (!text.contains("internship")) {
            suggestions.append("• Mention internship experience.\n");
        }

        if (!text.contains("certificate")) {
            suggestions.append("• Add certifications.\n");
        }

        if (suggestions.length() == 0) {
            suggestions.append("Excellent resume. No major improvements needed.");
        }

        return suggestions.toString();
    }

    public String extractText(MultipartFile file)
            throws IOException {

        try (PDDocument document =
                     PDDocument.load(file.getInputStream())) {

            PDFTextStripper stripper =
                    new PDFTextStripper();

            return stripper.getText(document);
        }
    }

}