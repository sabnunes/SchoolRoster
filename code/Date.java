/**
 * Represents a date with day, month, and year. Implements Orderable and Comparable interfaces. Provides methods to
 * manipulate and compare dates. This class ensures that the date is valid upon creation.
 *
 * @author Sabrina Nunes
 */
public class Date
        implements Orderable, Comparable<Date>
{
    private int day;
    private int month;
    private int year;

    private final static int    INVALID_YEAR_VALUE;
    private final static int    MIN_MONTH;
    private final static int    MAX_MONTH;
    private final static int    MIN_DAY;
    private final static int    DAY_INCREMENT;
    private final static int    DAYS_PER_WEEK;
    private final static int    MONTHS_PER_YEAR;
    private final static int    YEARS_PER_CENTURY;
    private final static int    LEAP_YEAR_DIVISOR_4; // A year is a leap year if it is divisible by 4, except when it is divisible by 100 (unless it is also divisible by 400).
    private final static int    LEAP_YEAR_DIVISOR_400; // A year is a leap year if it is divisible by 400.
    private final static String INVALID_DAY;
    private final static String INVALID_MONTH;
    private final static String INVALID_YEAR;
    private final static int    LEAP_YEAR_SPECIAL_OFFSET;
    private final static int    CENTURY_1600_OFFSET;
    private final static int    CENTURY_1700_OFFSET;
    private final static int    CENTURY_1800_OFFSET;
    private final static int    CENTURY_2000_OFFSET;
    private final static int    CENTURY_2100_OFFSET;
    private final static int    DAYS_IN_MONTH_THIRTY_ONE;
    private final static int    DAYS_IN_MONTH_THIRTY;
    private final static int    DAYS_IN_FEBRUARY;
    private final static int    DAYS_IN_FEBRUARY_LEAP_YEAR;
    private final static int    JANUARY;
    private final static int    FEBRUARY;
    private final static String SATURDAY;
    private final static String SUNDAY;
    private final static String MONDAY;
    private final static String TUESDAY;
    private final static String WEDNESDAY;
    private final static String THURSDAY;
    private final static String FRIDAY;
    private final static int    SPECIALCODE_1;
    private final static int    SPECIALCODE_4;
    private final static int    SPECIALCODE_0;
    private final static int    SPECIALCODE_2;
    private final static int    SPECIALCODE_5;
    private final static int    SPECIALCODE_3;
    private final static int    SPECIALCODE_6;
    private final static int    CENTURY_1600;
    private final static int    CENTURY_1700;
    private final static int    CENTURY_1800;
    private final static int    CENTURY_1900;
    private final static int    CENTURY_2000;
    private final static int    CENTURY_2100;

    static
    {
        INVALID_YEAR_VALUE         = 0;
        MIN_MONTH                  = 1;
        MAX_MONTH                  = 12;
        MIN_DAY                    = 1;
        DAY_INCREMENT              = 1;
        YEARS_PER_CENTURY          = 100;
        DAYS_PER_WEEK              = 7;
        MONTHS_PER_YEAR            = 12;
        LEAP_YEAR_DIVISOR_4        = 4;
        LEAP_YEAR_DIVISOR_400      = 400;
        INVALID_DAY                = "invalid day of the month";
        INVALID_MONTH              = "invalid month";
        INVALID_YEAR               = "invalid year";
        LEAP_YEAR_SPECIAL_OFFSET   = 6;
        CENTURY_1600_OFFSET        = 6;
        CENTURY_1700_OFFSET        = 4;
        CENTURY_1800_OFFSET        = 2;
        CENTURY_2000_OFFSET        = 6;
        CENTURY_2100_OFFSET        = 4;
        DAYS_IN_MONTH_THIRTY_ONE   = 31;
        DAYS_IN_MONTH_THIRTY       = 30;
        DAYS_IN_FEBRUARY           = 28;
        DAYS_IN_FEBRUARY_LEAP_YEAR = 29;
        JANUARY                    = 1;
        FEBRUARY                   = 2;
        SATURDAY                   = "Saturday";
        SUNDAY                     = "Sunday";
        MONDAY                     = "Monday";
        TUESDAY                    = "Tuesday";
        WEDNESDAY                  = "Wednesday";
        THURSDAY                   = "Thursday";
        FRIDAY                     = "Friday";
        SPECIALCODE_1              = 1;
        SPECIALCODE_4              = 4;
        SPECIALCODE_0              = 0;
        SPECIALCODE_2              = 2;
        SPECIALCODE_5              = 5;
        SPECIALCODE_3              = 3;
        SPECIALCODE_6              = 6;
        CENTURY_1600               = 1600;
        CENTURY_1700               = 1700;
        CENTURY_1800               = 1800;
        CENTURY_1900               = 1900;
        CENTURY_2000               = 2000;
        CENTURY_2100               = 2100;

    }

    //instances
    {
        day   = MIN_DAY;
        month = MIN_MONTH;
        year  = INVALID_YEAR_VALUE;
    }

    /**
     * Constructs a Date with the specified day, month, and year.
     *
     * @param day   the day of the month
     * @param month the month of the year
     * @param year  the year
     *
     * @throws IllegalArgumentException if the date is invalid
     */
    public Date(final int day, final int month, final int year)
    {
        if(year == INVALID_YEAR_VALUE)
        {
            throw new IllegalArgumentException(INVALID_YEAR);
        }
        if(month < MIN_MONTH || month > MAX_MONTH)
        {
            throw new IllegalArgumentException(INVALID_MONTH);
        }
        if(day < MIN_DAY || day > getNumberOfDaysPerMonth(month, year))
        {
            throw new IllegalArgumentException(INVALID_DAY);
        }
        this.day   = day;
        this.month = month;
        this.year  = year;
    }

    /**
     * Returns the year of this date.
     *
     * @return the year
     */
    public int getYear()
    {
        return year;
    }

    /**
     * Returns the date in the format yyyy-MM-dd.
     *
     * @return the formatted date string
     */
    public String getYyyyMmDd()
    {
        return String.format("%04d-%02d-%02d", year, month, day);
    }

    /**
     * Returns the string representation of this date.
     *
     * @return the string representation of this date
     */
    @Override
    public String toString()
    {
        return getYyyyMmDd();
    }

    /**
     * Returns the previous date.
     *
     * @return the previous date
     */
    @Override
    public Date previous()
    {
        int previousDay;
        int previousYear;
        int previousMonth;

        previousDay   = day - DAY_INCREMENT;
        previousMonth = month;
        previousYear  = year;

        // If the day goes below 1, adjust the month and year accordingly
        if(previousDay < MIN_DAY)
        {
            previousMonth--;
            if(previousMonth < MIN_MONTH)
            {
                previousMonth = MAX_MONTH;
                previousYear--;
            }
            // Set the day to the last day of the new month
            previousDay = getNumberOfDaysPerMonth(previousMonth, previousYear);
        }

        return new Date(previousDay, previousMonth, previousYear);
    }

    /**
     * Returns the next date.
     *
     * @return the next date
     */
    @Override
    public Date next()
    {
        int nextDay;
        int nextMonth;
        int nextYear;

        nextDay   = day + DAY_INCREMENT;
        nextMonth = month;
        nextYear  = year;

        // Get the number of days in the current month
        int daysInMonth = getNumberOfDaysPerMonth(nextMonth, nextYear);

        // If the day exceeds the number of days in the month, adjust the month and year accordingly
        if(nextDay > daysInMonth)
        {
            nextDay = MIN_DAY;
            nextMonth++;
            if(nextMonth > MAX_MONTH)
            {
                nextMonth = MIN_MONTH; // Move to the next year and set month to January
                nextYear++;
            }
        }

        return new Date(nextDay, nextMonth, nextYear);

    }

    /**
     * Compares this date to another date.
     *
     * @param d the date to compare to
     *
     * @return a negative integer, zero, or a positive integer as this date is less than, equal to, or greater than the
     * specified date
     */
    @Override
    public int compareTo(final Date d)
    {
        if(year != d.year)
        {
            return Integer.compare(year, d.year);
        }
        if(month != d.month)
        {
            return Integer.compare(month, d.month);
        }
        return Integer.compare(day, d.day);
    }

    /**
     * Returns the day of the week for this date.
     *
     * @return the day of the week
     */
    public String getDayOfTheWeek()
    {
        int step1; // Step1: extract last two digits of the year, divide by 12, ie. August 16, 1989 -> 7 twelves in 89
        int step2; // Step2: determine remainder of step1, ie. 89 – (7 * 12) = 5
        int step3; // Step3: determine how many fours fit into step2, ie. 1 four in 5
        int step4; // Step4: add the day of the month, ie. 16 for August 16th
        int step5; // Step5: add the month code from the table provided (use getCodeForMonth(int month))
        int step6; // Step6: (sum of steps 1-5 + special offset) mod by 7
        int specialOffset; // special offset to add
        int dayOfTheWeek; // step6

        step1 = (year % YEARS_PER_CENTURY) / MONTHS_PER_YEAR;
        step2 = (year % YEARS_PER_CENTURY) - (step1 * MONTHS_PER_YEAR);
        step3 = step2 / LEAP_YEAR_DIVISOR_4;
        step4 = day;
        step5 = getCodeForMonth(month);

        specialOffset = 0;
        // January and February dates in leap years: add 6 to step 5
        if(isLeapYear(year) && (month == JANUARY || month == FEBRUARY))
        {
            specialOffset += LEAP_YEAR_SPECIAL_OFFSET;
        }

        /**
         All dates in the 1600s: add 6 to step 5
         All dates in the 1700s: add 4 to step 5
         All dates in the 1800s: add 2 to step 5
         All dates in the 2000s: add 6 to step 5
         All dates in the 2100s: add 4 to step 5 */
        if(year >= CENTURY_1600 && year < CENTURY_1700)
        {
            specialOffset += CENTURY_1600_OFFSET;
        }
        else if(year >= CENTURY_1700 && year < CENTURY_1800)
        {
            specialOffset += CENTURY_1700_OFFSET;
        }
        else if(year >= CENTURY_1800 && year < CENTURY_1900)
        {
            specialOffset += CENTURY_1800_OFFSET;
        }
        else if(year >= CENTURY_2000 && year < CENTURY_2100)
        {
            specialOffset += CENTURY_2000_OFFSET;
        }
        else if(year >= CENTURY_2100)
        {
            specialOffset += CENTURY_2100_OFFSET;
        }

        step6        = (step1 + step2 + step3 + step4 + step5 + specialOffset) % DAYS_PER_WEEK;
        dayOfTheWeek = step6;

        switch(dayOfTheWeek)
        {
            case 0:
                return SATURDAY;
            case 1:
                return SUNDAY;
            case 2:
                return MONDAY;
            case 3:
                return TUESDAY;
            case 4:
                return WEDNESDAY;
            case 5:
                return THURSDAY;
            case 6:
                return FRIDAY;
            default:
                return INVALID_DAY;
        }
    }

    /**
     * Checks if the specified year is a leap year.
     *
     * @param year the year to check
     *
     * @return true if the year is a leap year, false otherwise
     */
    private boolean isLeapYear(final int year)
    {
        return (year % LEAP_YEAR_DIVISOR_400 == 0) || ((year % LEAP_YEAR_DIVISOR_4 == 0) && (year % YEARS_PER_CENTURY != 0));
    }

    /**
     * Returns the number of days in the specified month and year.
     *
     * @param month the month
     * @param year  the year
     *
     * @return the number of days in the month
     * @throws IllegalArgumentException if the month is invalid
     */
    private int getNumberOfDaysPerMonth(final int month, final int year)
    {
        switch(month)
        {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return DAYS_IN_MONTH_THIRTY_ONE; // 31 days per month
            case 4:
            case 6:
            case 9:
            case 11:
                return DAYS_IN_MONTH_THIRTY; // 30 days per month
            case 2:
                return isLeapYear(year) ?
                       DAYS_IN_FEBRUARY_LEAP_YEAR :
                       DAYS_IN_FEBRUARY; // February 28 days per month except on leap years (29)
            default:
                throw new IllegalArgumentException(INVALID_MONTH);
        }
    }

    /**
     * Returns the code for the specified month.
     *
     * @param month the month
     *
     * @return the code for the month
     * @throws IllegalArgumentException if the month is invalid
     */
    private int getCodeForMonth(final int month)
    {
        switch(month)
        {
            case 1:
            case 10:
                return SPECIALCODE_1;
            case 2:
            case 3:
            case 11:
                return SPECIALCODE_4;
            case 4:
            case 7:
                return SPECIALCODE_0;
            case 5:
                return SPECIALCODE_2;
            case 6:
                return SPECIALCODE_5;
            case 8:
                return SPECIALCODE_3;
            case 9:
            case 12:
                return SPECIALCODE_6;
            default:
                throw new IllegalArgumentException(INVALID_MONTH);
        }
    }
}
