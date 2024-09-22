package com.example.attractionadvisor_backend.init.csv.domain;

import java.util.List;

public interface CsvReader {
    List<String[]> readCsvFile();
}
