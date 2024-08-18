/**
 * Represents a teacher, which is a type of person. Each teacher has a specialty subject.
 *
 * @author Sabrina Nunes
 */
public class Teacher
        extends Person
{
    private final String specialty;

    private final static String SPECIALTY_BLANK;

    static
    {
        SPECIALTY_BLANK = "bad specialty";
    }

    /**
     * Constructs a Teacher with the specified birthdate, name, and specialty.
     *
     * @param born      the birthdate, from super class
     * @param name      the name, from super class
     * @param specialty the specialty subject
     */
    public Teacher(final Date born, final Name name, final String specialty)
    {
        super(born, name);

        if(specialty.isBlank())
            throw new IllegalPersonException(SPECIALTY_BLANK);

        this.specialty = specialty;
    }

    /**
     * Returns the specialty subject.
     *
     * @return the specialty subject
     */
    public String getSpecialty()
    {
        return specialty;
    }

    /**
     * Returns the string representation of this teacher.
     *
     * @return the string representation of this teacher
     */
    @Override
    public String toString()
    {
        String string;
        string = String.format("%s (specialty: %s) was born %s and is still alive", getName().getPrettyName(),
                               specialty, getDateOfBirth().getYyyyMmDd());

        if(!isAlive())
        {
            string = String.format("%s (specialty: %s) was born %s and died %s", getName().getPrettyName(), specialty,
                                   getDateOfBirth().getYyyyMmDd(), getDateOfDeath().getYyyyMmDd());
        }

        return string;
    }
}
