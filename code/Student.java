/**
 * Represents a student, which is a type of person. Each student has a unique student number.
 *
 * @author Sabrina Nunes
 */
public class Student
        extends Person
{
    private final String studentNumber;

    private final static int    STUDENT_NUM_EXACT_CHARS;
    private final static String INVALID_STUDENT_NUM;

    static
    {
        STUDENT_NUM_EXACT_CHARS = 9;
        INVALID_STUDENT_NUM     = "bad student number";
    }

    /**
     * Constructs a Student with the specified birthdate, name, and student number.
     *
     * @param born          the birthdate, from super class
     * @param name          the name, from super class
     * @param studentNumber the student number
     *
     * @throws IllegalArgumentException if the student number is invalid
     */
    public Student(final Date born, final Name name, final String studentNumber)
    {
        super(born, name);

        if(studentNumber == null || studentNumber.isBlank() || studentNumber.length() != STUDENT_NUM_EXACT_CHARS)
        {
            throw new IllegalPersonException(INVALID_STUDENT_NUM);
        }
        this.studentNumber = studentNumber;
    }

    /**
     * Returns the student number.
     *
     * @return the student number
     */
    public String getStudentNumber()
    {
        return studentNumber;
    }

    /**
     * Returns the string representation of this student.
     *
     * @return the string representation of this student
     */
    @Override
    public String toString()
    {
        String string;
        string = String.format("%s (student number: %s) was born %s and is still alive", getName().getPrettyName(),
                               studentNumber, getDateOfBirth().getYyyyMmDd());

        if(!isAlive())
        {
            string = String.format("%s (student number: %s) was born %s and died %s", getName().getPrettyName(),
                                   studentNumber, getDateOfBirth().getYyyyMmDd(), getDateOfDeath().getYyyyMmDd());
        }
        return string;
    }
}
