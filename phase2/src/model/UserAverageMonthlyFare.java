package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

class UserAverageMonthlyFare implements Serializable {

    private int firstMonth = 0;
    private int firstYear = 0;
    private double totalFare = 0.0;
    private double averageMonthlyFare = 0.0;

  void updateAverageMonthlyFare(Date time, double fare) {
      LocalDate localDate = time.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      int currentYear = localDate.getYear();
      int currentMonth = localDate.getMonthValue();
      totalFare += fare;

      if (firstYear == 0) {
          firstMonth = currentMonth;
          firstYear = localDate.getYear();
          averageMonthlyFare = fare;
          // since first month are not updated, which means this is the month is the first time user
          // uses this card.
      } else if (firstYear == currentYear) {
          int monthDiff = currentMonth - firstMonth + 1; // incremented by 1 since if its the same month, it should be 1.
          averageMonthlyFare = totalFare / monthDiff;
      } else {
          int yearDiff = currentYear - firstYear;
          int monthDiff = currentMonth - firstMonth + 1;
          averageMonthlyFare = totalFare / (monthDiff + yearDiff * 12);
      }
  }

        double getAverageMonthlyFare(){
            return averageMonthlyFare;
        }

        int getFirstYear(){
            return firstYear;
        }
}
