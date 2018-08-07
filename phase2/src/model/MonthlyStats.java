package model;

import java.util.HashMap;

public class MonthlyStats implements SystemStats {

    private HashMap<Integer, Double> monthlyStats = new HashMap<>();

    MonthlyStats(){
        for (int i = 1; i < 13; i++){
            monthlyStats.put(i, 0.0);
        }
    }

    HashMap<Integer, Double> getMonthlyStats(){
        return monthlyStats;
    }

    void updateMonthlyStats(int month, double fare){
        monthlyStats.replace(month, monthlyStats.get(month) + fare);
    }
}
