package com.connectgroup;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DataFiltererTest {
    @Test
    public void shouldReturnEmptyCollection_WhenLogFileIsEmpty() throws FileNotFoundException {
        assertTrue(DataFilterer.filterByCountry(openFile("src/test/resources/empty"), "GB").isEmpty());
    }

    @Test
    public void canParseEntriesAndFilterByCountry() throws Exception {
        List<DataEntry> expectedDataEntries = new ArrayList<>();
        expectedDataEntries.add(new DataEntry(1433190845, "US", 539));
        expectedDataEntries.add(new DataEntry(1433666287, "US", 789));
        expectedDataEntries.add(new DataEntry(1432484176, "US", 850));

        Collection<DataEntry> dataEntries = (Collection<DataEntry>) DataFilterer
                .filterByCountry(openFile("src/test/resources/multi-lines"), "US");

        assertThat(dataEntries.size(), is(3));
        assertTrue(dataEntries.containsAll(expectedDataEntries));
    }

    private FileReader openFile(String filename) throws FileNotFoundException {
        return new FileReader(new File(filename));
    }
}
