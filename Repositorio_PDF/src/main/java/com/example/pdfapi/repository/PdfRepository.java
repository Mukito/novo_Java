package com.example.pdfapi.repository;

import java.io.File;
import java.util.Arrays;
import java.util.List;
//import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.pdfapi.model.PdfFile;

@Repository
public class PdfRepository {

    private static final String DIRECTORY = "C://meus_PDFs";

    public List<PdfFile> findAll() {
        File folder = new File(DIRECTORY);
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));

        if (files != null) {
            return Arrays.stream(files)
                    .map(f -> new PdfFile(f.getName(), f.length()))
                    .collect(Collectors.toList());
        }
        return List.of();
    }

    public boolean delete(String name) {
        File file = new File(DIRECTORY, name);
        return file.exists() && file.delete();
    }

    public boolean rename(String oldName, String newName) {
        File oldFile = new File(DIRECTORY, oldName);
        File newFile = new File(DIRECTORY, newName);
        return oldFile.exists() && oldFile.renameTo(newFile);
    }
}
