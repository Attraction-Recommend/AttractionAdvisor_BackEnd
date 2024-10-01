package com.example.attractionadvisor_backend.init.csv.domain;

import com.example.attractionadvisor_backend.domain.entity.Coordinates;
import com.example.attractionadvisor_backend.domain.entity.VisitDestination;
import com.example.attractionadvisor_backend.domain.entity.VisitPeriod;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class VisitDestinationMapper {

        public List<VisitDestination> mapToVisitDestination(List<String[]> rawData) {
            return rawData.stream()
                    .map(this::createVisitDestinationFromCsvData)
                    .filter(java.util.Objects::nonNull)
                    .collect(Collectors.toList());
        }

        private VisitDestination createVisitDestinationFromCsvData(String[] data) {
            if (data.length < 7) {
                log.warn("Insufficient data in CSV row. Expected at least 7 columns, got {}. Data: {}", data.length, String.join(",", data));
                return null;
            }

            try {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                return VisitDestination.builder()
                        .destinationId(data[0].trim())
                        .userId(data[1].trim())
                        .destinationName(data[2].trim())
                        .visitPeriod(new VisitPeriod(
                                LocalDate.parse(data[3].trim(), dateFormatter),
                                LocalDate.parse(data[4].trim(), dateFormatter)
                        ))
                        .coordinates(new Coordinates(
                                Double.parseDouble(data[5].trim()),
                                Double.parseDouble(data[6].trim())
                        ))
                        .build();
            } catch (DateTimeParseException e) {
                log.error("Error parsing date for row: {}. Error: {}", String.join(",", data), e.getMessage());
            } catch (NumberFormatException e) {
                log.error("Error parsing coordinate for row: {}. Error: {}", String.join(",", data), e.getMessage());
            } catch (Exception e) {
                log.error("Unexpected error while creating VisitDestination for row: {}. Error: {}", String.join(",", data), e.getMessage());
            }
            return null;
        }
    }