package com.example.pdfapi.service;

import com.example.pdfapi.dto.RenameRequest;
import com.example.pdfapi.model.PdfFile;
import com.example.pdfapi.repository.PdfRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PdfService {

    private final PdfRepository repository;

    public PdfService(PdfRepository repository) {
        this.repository = repository;
    }

    public List<PdfFile> listar() {
        return repository.findAll();
    }

    public String renomear(RenameRequest req) {
        return repository.rename(req.getOldName(), req.getNewName())
                ? "Arquivo renomeado com sucesso!"
                : "Erro ao renomear arquivo.";
    }

    public String deletar(String nome) {
        return repository.delete(nome)
                ? "Arquivo deletado com sucesso!"
                : "Arquivo não encontrado ou erro ao deletar.";
    }

}
