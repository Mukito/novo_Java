package com.example.pdfapi.dto;

import lombok.Data;

@Data
public class RenameRequest {
    private String oldName;
    private String newName;
}
