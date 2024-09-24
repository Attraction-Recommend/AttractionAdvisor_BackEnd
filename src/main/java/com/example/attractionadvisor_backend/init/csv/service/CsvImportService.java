package com.example.attractionadvisor_backend.init.csv.service;


import com.example.attractionadvisor_backend.domain.entity.VisitDestination;
import com.example.attractionadvisor_backend.init.csv.domain.AttractionMapper;
import com.example.attractionadvisor_backend.init.csv.domain.CsvReader;
import com.example.attractionadvisor_backend.init.csv.jpa.AttractionCsvRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CsvImportService {
    private final CsvReader csvReader;
    private final AttractionMapper attractionMapper;
    private final AttractionCsvRepository attractionCsvRepository;

    @Transactional
    public List<VisitDestination> importCsvData() {
        List<String[]> rawData = csvReader.readCsvFile();
        List<VisitDestination> visitDestinations = attractionMapper.mapToAttractions(rawData);
        return saveAttractions(visitDestinations);
    }

    private List<VisitDestination> saveAttractions(List<VisitDestination> visitDestinations) {
        return attractionCsvRepository.saveAll(visitDestinations);
    }
}
