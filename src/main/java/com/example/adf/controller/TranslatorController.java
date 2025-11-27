package com.example.adf.controller;

import com.example.adf.model.TabularTranslator;
import com.example.adf.model.TranslatorRequest;
import com.example.adf.service.TranslatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class TranslatorController {

    private final TranslatorService translatorService;
    private final com.example.adf.service.CsvParserService csvParserService;

    @Autowired
    public TranslatorController(TranslatorService translatorService,
            com.example.adf.service.CsvParserService csvParserService) {
        this.translatorService = translatorService;
        this.csvParserService = csvParserService;
    }

    @Operation(summary = "Generate TabularTranslator from JSON", description = "Accepts a JSON payload with field specifications and returns an ADF TabularTranslator configuration")
    @PostMapping("/generate-translator")
    public TabularTranslator generate(@RequestBody TranslatorRequest request) {
        return translatorService.generateTranslator(request);
    }

    @Operation(summary = "Generate TabularTranslator from CSV file", description = "Upload a CSV file with field specifications (columns: Name, Type, Precision, Scale, Optional, Tokenization)")
    @PostMapping(value = "/generate-translator/csv", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public TabularTranslator generateFromCsv(
            @Parameter(description = "CSV file with field specifications", required = true, content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)) @RequestParam("file") MultipartFile file)
            throws Exception {
        java.util.List<com.example.adf.model.FieldSpec> fields = csvParserService.parseCsv(file);
        TranslatorRequest request = new TranslatorRequest();
        request.setFields(fields);
        return translatorService.generateTranslator(request);
    }
}
