package model;

import java.io.Serializable;
import java.util.HashMap;

public class YearlyStats implements SystemStats, Serializable {
    private HashMap<Integer, Double> yearlyStats = new HashMap<Integer, Double>();

    YearlyStats() {
        for (int i = 1; i < 11; i++) {
            yearlyStats.put(i, 0.0);

        }
    }

    public HashMap<Integer, Double> getYearlyStats() {
        return yearlyStats;
    }

    void updateYearlyStats(int year, double fare) {
        yearlyStats.replace(year, yearlyStats.get(year) + fare);
    }
}
