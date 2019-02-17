package com.connectgroup;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class DataEntryTest {

    @Test
    public void canPopulateDataEntryOnConstruction() {
        DataEntry dataEntry = new DataEntry(1433190845, "US", 539);

        assertThat(dataEntry.getTimestamp(), is(1433190845));
        assertThat(dataEntry.getCountryCode(), is("US"));
        assertThat(dataEntry.getResponseTime(), is(539));
    }

    @Test
    public void canAssertEqualityOfDataEntriesCorrectly() {
        DataEntry dataEntry1 = new DataEntry(1433190845, "US", 539);
        DataEntry dataEntry2 = new DataEntry(1433190845, "US", 539);

        assertTrue(dataEntry1.equals(dataEntry2));
        assertTrue(dataEntry1.hashCode() == dataEntry2.hashCode());

        assertEquals(0, dataEntry1.compareTo(dataEntry2));
    }
}
