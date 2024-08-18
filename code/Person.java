/**
 * Represents a person with a name and birthdate. Provides methods to access and manipulate the person's
 * information. This class can be extended to create specific types of people (e.g., Student, Teacher).
 *
 * @author Sabrina Nunes
 */
public class Person
        implements Comparable<Person>
{
    private final Date born;
    private       Date died;
    private final Name name;

    private final static String INVALID_DATE;
    private final static String INVALID_NAME;
    private final static String IS_NOT_DEAD;

    static
    {
        INVALID_DATE = "invalid date of birth";
        INVALID_NAME = "invalid name";
        IS_NOT_DEAD  = "is not dead";
    }

    // instances
    {
        died = null;
    }

    /**
     * Constructs a Person with the specified name and birthdate.
     *
     * @param name the name
     * @param born the birthdate
     */
    public Person(final Date born, final Name name)
    {
        validateInput(born, INVALID_DATE);
        validateInput(name, INVALID_NAME);

        this.born = born;
        this.name = name;
    }

    /**
     * Validates the specified input and throws a message if input is null.
     *
     * @param input   the input to validate
     * @param message String message which determines which type of error message to print
     *
     * @throws IllegalArgumentException if the name is null, empty, or blank
     */
    public void validateInput(final Object input, final String message)
    {
        if(input == null)
        {
            throw new IllegalPersonException(message);
        }
    }

    /**
     * Returns the birthdate of the person.
     *
     * @return birthdate
     */
    public Date getDateOfBirth()
    {
        return born;
    }

    /**
     * Returns the name of the person.
     *
     * @return the name
     */
    public Name getName()
    {
        return name;
    }

    /**
     * Returns the death date of the person.
     *
     * @return the death date
     * @throws IllegalArgumentException if the person is not dead
     */
    public Date getDateOfDeath()
    {
        if(isAlive())
        {
            throw new IllegalArgumentException(IS_NOT_DEAD);
        }
        return died;
    }

    /**
     * Sets the death date of the person.
     *
     * @param dateOfDeath the death date
     */
    public void die(final Date dateOfDeath)
    {
        validateInput(dateOfDeath, INVALID_DATE);

        this.died = dateOfDeath;
    }

    /**
     * Returns whether the person is alive.
     *
     * @return true if the person is alive, false otherwise
     */
    public boolean isAlive()
    {
        return died == null;
    }

    /**
     * Returns the comparison 2 people and their birthdates. Younger people are "larger".
     *
     * @return the string representation of this person
     */
    @Override
    public int compareTo(final Person p)
    {
        return born.compareTo(p.born);
    }

    /**
     * Returns the string representation of this person.
     *
     * @return the string representation of this person
     */
    @Override
    public String toString()
    {
        String string;
        string = String.format("%s was born %s and is still alive", name.getPrettyName(), born.getYyyyMmDd());
        ;
        if(!isAlive())
        {
            string = String.format("%s was born %s and died %s", name.getPrettyName(), born.getYyyyMmDd(),
                                   died.getYyyyMmDd());
        }

        return string;
    }
}
