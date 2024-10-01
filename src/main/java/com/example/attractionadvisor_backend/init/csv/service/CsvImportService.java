package com.example.attractionadvisor_backend.init.csv.service;


import com.example.attractionadvisor_backend.domain.entity.VisitDestination;
import com.example.attractionadvisor_backend.init.csv.domain.VisitDestinationMapper;
import com.example.attractionadvisor_backend.init.csv.domain.CsvReader;
import com.example.attractionadvisor_backend.init.csv.jpa.VisitDestinationCsvRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CsvImportService {
    private final CsvReader csvReader;
    private final VisitDestinationMapper visitDestinationMapper;
    private final VisitDestinationCsvRepository visitDestinationCsvRepository;

    @Transactional
    public List<VisitDestination> importCsvData() {
        List<String[]> rawData = csvReader.readCsvFile();
        List<VisitDestination> visitDestinations = visitDestinationMapper.mapToVisitDestination(rawData);
        return saveAttractions(visitDestinations);
    }

    private List<VisitDestination> saveAttractions(List<VisitDestination> visitDestinations) {
        return visitDestinationCsvRepository.saveAll(visitDestinations);
    }
}
