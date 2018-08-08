package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/** this class is to handle the average monthly fares for each user.*/
class UserAverageMonthlyFare implements Serializable {

  /**  The int representation of the month that the user just started to use the card.*/
  private int firstMonth = 0;

  /**  the first year that the user used this card.*/
  private int firstYear = 0;

  /**  total amount of money user have used.*/
  private double totalFare = 0.0;

  /**  averageMonthlyFare is used to store the average fare for the user per month.*/
  private double averageMonthlyFare = 0.0;

  /** This method is called whenever balance changed for the user.
   * @param time this is the time that the user tapped the card.
   * @param fare the amount of money that's deducted from the card.
   */
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

  /** this method is a getter to get the average monthly fare for each user.
   *  @return return the average monthly fare for the user.*/
  double getAverageMonthlyFare() {
            return averageMonthlyFare;
        }

  /** this method is used to get the first year that the user used this card.
   *  @return the first year that the user used this card.*/
  int getFirstYear() {
            return firstYear;
        }
}
