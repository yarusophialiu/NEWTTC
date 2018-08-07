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
        if (year >= 11) {
            HashMap<Integer, Double> newYearlyStats = new HashMap<Integer, Double>();
            yearlyStats.put(11, fare);

            yearlyStats.forEach((key, value) -> {
                if (key >=2) {
                    newYearlyStats.put(key-1, value);
                }
            });
        }


        if (yearlyStats.size() < 10) {
            yearlyStats.replace(year, yearlyStats.get(year) + fare);
        }
    }
}
