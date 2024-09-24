package com.example.attractionadvisor_backend.init.csv.domain;

import com.example.attractionadvisor_backend.domain.entity.VisitDestination;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AttractionMapper {

    public List<VisitDestination> mapToAttractions(List<String[]> rawData) {
        return rawData.stream()
                .map(this::createAttractionFromCsvData)
                .collect(Collectors.toList());
    }

    private VisitDestination createAttractionFromCsvData(String[] data) {
        if (data.length < 3) {
            log.warn("Insufficient data in CSV row. Expected at least 3 columns, got {}", data.length);
            return null;
        }

        VisitDestination visitDestination = VisitDestination.builder()
                .userId(data[0])
                .destinationId(data[1])
                .build();

        return visitDestination;
    }
}