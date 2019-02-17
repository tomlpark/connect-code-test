package com.connectgroup;

import java.util.Objects;

public class DataEntry implements Comparable<DataEntry> {
    private Integer timestamp;

    private String countryCode;

    private Integer responseTime;

    public DataEntry(Integer timestamp, String countryCode, Integer responseTime) {
        this.timestamp = timestamp;
        this.countryCode = countryCode;
        this.responseTime = responseTime;
    }

    @Override
    public int compareTo(DataEntry o) {
        return getTimestamp().compareTo(o.getTimestamp());
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Integer getResponseTime() {
        return responseTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataEntry dataEntry = (DataEntry) o;
        return Objects.equals(timestamp, dataEntry.timestamp) &&
                Objects.equals(countryCode, dataEntry.countryCode) &&
                Objects.equals(responseTime, dataEntry.responseTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, countryCode, responseTime);
    }
}
