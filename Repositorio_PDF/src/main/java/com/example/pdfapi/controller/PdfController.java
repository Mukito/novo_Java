package com.example.pdfapi.controller;

import com.example.pdfapi.dto.RenameRequest;
import com.example.pdfapi.model.PdfFile;
import com.example.pdfapi.service.PdfService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pdfs")
public class PdfController {

    private final PdfService service;

    public PdfController(PdfService service) {
        this.service = service;
    }

    // Listar PDFs
    @GetMapping
    public List<PdfFile> listar() {
        return service.listar();
    }

    // Renomear PDF
    @PutMapping("/rename")
    public String renomear(@RequestBody RenameRequest req) {
        return service.renomear(req);
    }

    // Deletar PDF
    @DeleteMapping("/{nome}")
    public String deletar(@PathVariable String nome) {
        return service.deletar(nome);
    }
}
