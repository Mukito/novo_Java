# Projeto Repositorio PDF

### 🚀 Passo 1 – Criar o projeto no Spring Initializr

Acesse: https://start.spring.io/

Configurações:

* **Project**: Maven
* **Language**: Java
* **Spring Boot**: 3.x
* **Dependencies**:
    * Spring Web
    * Spring Boot DevTools
    * Lombok (opcional, mas ajuda muito)
Clique em **Generate**, extraia o `.zip` e abra a pasta no **VS Code**.

### 📂 Passo 2 – Estrutura de pastas (no VS Code)

Vamos organizar assim:
```
src/main/java/com/example/pdfapi/
│
├── controller/      → Endpoints REST
├── dto/             → Objetos de transporte (entrada/saída)
├── model/           → Representação de entidade (PDF)
├── repository/      → Interface de persistência (simulação ou BD futuramente)
├── service/         → Regras de negócio
└── PdfApiApplication.java
```

### 📝 Passo 3 – Criar as classes
**3.1 Model** (`PdfFile.java`)
```
package com.example.pdfapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PdfFile {
    private String name;
    private long size;
}
```

**3.2 DTO** (`RenameRequest.java`)
```
package com.example.pdfapi.dto;

import lombok.Data;

@Data
public class RenameRequest {
    private String oldName;
    private String newName;
}
```

**3.3 Repository** (`PdfRepository.java`)

>Por enquanto, vamos simular a pasta como “repositório”.
```
package com.example.pdfapi.repository;

import com.example.pdfapi.model.PdfFile;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PdfRepository {

    private static final String DIRECTORY = "C:\\meus_pdfs";

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
```

**3.4 Service** (`PdfService.java`)
```
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
```

**3.5 Controller** (`PdfController.java`)
```
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
```

## ⚡ Testando a API

Com o projeto rodando (`mvn spring-boot:run` ou botão Run no VS Code):

* **Listar PDFs**
```bash
GET http://localhost:8080/pdfs
```


Retorno (JSON):
```json
[
  {"name": "teste.pdf", "size": 2048},
  {"name": "contrato.pdf", "size": 8192}
]
```


* **Renomear PDF**
```
PUT http://localhost:8080/pdfs/rename
Content-Type: application/json

{
  "oldName": "teste.pdf",
  "newName": "teste_renomeado.pdf"
}
```

* **Deletar PDF**
```
DELETE http://localhost:8080/pdfs/contrato.pdf
```

