package com.connectgroup;

import com.connectgroup.exception.DataFiltererParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DataFilterer {
    private static final String DATA_ENTRY_SEPARATOR = ",";

    public static Collection<?> filterByCountry(Reader source, String country) {
        try {
            return parseDataSource(source).stream()
                    .filter(e -> e.getCountryCode().equals(country))
                    .collect(Collectors.toList());
        } catch (DataFiltererParseException e) {
            // todo: log this properly to a log file
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    public static Collection<?> filterByCountryWithResponseTimeAboveLimit(Reader source, String country, long limit) {
        try {
            return parseDataSource(source).stream()
                    .filter(e -> e.getCountryCode().equals(country))
                    .filter(e -> e.getResponseTime() > limit)
                    .collect(Collectors.toList());
        } catch (DataFiltererParseException e) {
            // todo: log this properly to a log file
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    public static Collection<?> filterByResponseTimeAboveAverage(Reader source) {
        try {
            Collection<DataEntry> dataEntries = parseDataSource(source);
            Double averageResponseTime = dataEntries.stream()
                    .collect(Collectors.averagingInt(DataEntry::getResponseTime));

            return dataEntries.stream()
                    .filter(e -> e.getResponseTime() > averageResponseTime)
                    .collect(Collectors.toList());
        } catch (DataFiltererParseException e) {
            // todo: log this properly to a log file
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    private static Collection<DataEntry> parseDataSource(Reader source) throws DataFiltererParseException {
        String line;
        List<DataEntry> parsedDataEntries = new ArrayList<>();

        try (BufferedReader bufferedReader  = new BufferedReader(source)) {
            bufferedReader.readLine(); // grab the header

            while ((line = bufferedReader.readLine()) != null) {
                String[] lineData = line.split(DATA_ENTRY_SEPARATOR);

                DataEntry dataEntry = new DataEntry(Integer.parseInt(lineData[0]),
                        lineData[1], Integer.parseInt(lineData[2]));

                parsedDataEntries.add(dataEntry);
            }

            Collections.sort(parsedDataEntries);

            return parsedDataEntries;
        } catch (IOException e) {
            throw new DataFiltererParseException("Unable to read the source file", e);
        }
    }
}