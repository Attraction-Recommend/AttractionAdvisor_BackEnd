package com.example.attractionadvisor_backend.init.csv.controller;

import com.example.attractionadvisor_backend.domain.entity.VisitDestination;
import com.example.attractionadvisor_backend.init.csv.service.CsvImportService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/import")
@RequiredArgsConstructor
public class CsvImportController {

    private final CsvImportService csvImportService;

    @GetMapping("/csv")
    public ResponseEntity<String> importCsvData() {
        List<VisitDestination> importedVisitDestinations = csvImportService.importCsvData();
        return ResponseEntity.ok(
                "Imported " + importedVisitDestinations.size() + " attractions from CSV file." + "⚡  " + importedVisitDestinations);
    }
}