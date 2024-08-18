/**
 * Represents a person's name with a first and last name. Provides methods to validate and format the name.
 *
 * @author Sabrina Nunes
 */
public class Name
{
    private final String first;
    private final String last;

    private final static String INVALID_FIRST_NAME;
    private final static String INVALID_LAST_NAME;

    static
    {
        INVALID_FIRST_NAME = "invalid first name";
        INVALID_LAST_NAME  = "invalid last name";
    }

    /**
     * Constructs a Name with the specified first and last names.
     *
     * @param first the first name
     * @param last  the last name
     *
     * @throws IllegalArgumentException if the first or last name is invalid
     */
    public Name(final String first, final String last)
    {
        validateName(first, "first");
        validateName(last, "last");

        this.first = first;
        this.last  = last;
    }

    /**
     * Validates the specified name.
     *
     * @param name   the name to validate
     * @param firstOrLast indicates whether the name is a first or last name
     *
     * @throws IllegalArgumentException if the name is null, empty, or blank
     */
    public void validateName(final String name, final String firstOrLast)
    {
        if(name == null || name.trim().isEmpty() || name.isBlank())
        {
            if(firstOrLast == "first")
            {
                throw new IllegalArgumentException(INVALID_FIRST_NAME);
            }
            else if(firstOrLast == "last")
            {
                throw new IllegalArgumentException(INVALID_LAST_NAME);
            }
        }
    }

    /**
     * Returns the first name.
     *
     * @return the first name
     */
    public String getFirst()
    {
        return first;
    }

    /**
     * Returns the last name.
     *
     * @return the last name
     */
    public String getLast()
    {
        return last;
    }

    /**
     * Returns the full name in a pretty format (capitalized).
     *
     * @return the full name
     */
    public String getPrettyName()
    {
        return capitalize(first.strip()) + " " + capitalize(last.strip());
    }

    /**
     * Returns the initials of the name.
     *
     * @return the formatted initials
     */
    public String getInitials()
    {
        return capitalize(first).charAt(0) + "." + capitalize(last).charAt(0) + ".";
    }

    /**
     * Capitalizes the specified string.
     *
     * @param str the string to capitalize
     *
     * @return the capitalized string
     */
    private String capitalize(String str)
    {
        return Character.toUpperCase(str.strip().charAt(0)) + str.strip().substring(1).toLowerCase();
    }
}
