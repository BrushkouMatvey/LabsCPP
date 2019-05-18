package com.Controller;

import com.Entity.Statistics;
import com.Entity.Triangle;

import java.util.List;

public class ResponseData {
    private List<Triangle> results;
    private Statistics stats;

    public ResponseData(List<Triangle> results, Statistics stats)
    {
        this.results = results;
        this.stats = stats;
    }

    public List<Triangle> getResults() {
        return results;
    }

    public Statistics getStats() {
        return stats;
    }

}
