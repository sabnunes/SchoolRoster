import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;

/**
 * Represents a school that maintains a list of people. Provides methods to add people to the school and save their
 * details to a file.
 *
 * @author Sabrina Nunes
 */
public class School
{
    private final List<Person> people;

    private final static int    CURRENT_YEAR;
    private final static String NULL_PERSON;
    private final static String FILENAME;

    static
    {
        CURRENT_YEAR = 2022;
        NULL_PERSON  = "cannot register a non-person";
        FILENAME     = "people.txt";
    }

    /**
     * Adds a person to the school's list of people.
     *
     * @param p the person to add
     *
     * @throws IllegalPersonException if the person is null
     */
    public void register(final Person p)
    {
        if(p == null)
        {
            throw new IllegalPersonException(NULL_PERSON);
        }
        people.add(p);
    }

    /** Constructs a School */
    public School()
    {
        // Initializer block to create an empty ArrayList and assign it to the List variable
        people = new ArrayList<>();
    }

    /** Prints the roster of people in the school. */
    public void printRoster()
    {
        people.forEach(System.out::println);
    }


    /**
     * Prints the ages and years of all people in the school. Calculates and prints the age of each person for each year
     * of their life.
     */
    public void printAgesAndYears()
    {
        int currentYear;
        currentYear = CURRENT_YEAR;

        for(Person person : people)
        {
            String fullName;
            int    yearBorn;
            int    maxYear;

            fullName = person.getName().getPrettyName();
            yearBorn = person.getDateOfBirth().getYear();
            maxYear  = person.isAlive() ? currentYear : person.getDateOfDeath().getYear();

            // Lambda expression to print the person's name and age for each year of life
            Writeable w;
            w = (name, birthYear, endYear)->{
                for(int year = birthYear; year <= endYear; year++)
                {
                    int age;
                    age = year - birthYear;
                    System.out.printf("%s: %d (age %d)%n", name, year, age);
                }
            };

            // Use the Writeable instance to print data
            w.printData(fullName, yearBorn, maxYear);
        }
    }

    /**
     * Saves the details of all people in the school to a file.
     *
     * @throws RuntimeException if an IOException occurs during writing
     */
    public void saveDetails()
    {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME)))
        {
            for(Person p : people)
            {
                String string;
                string = String.format("%s (%s) was born on %s %s", p.getName().getPrettyName(),
                                       p.getName().getInitials(), p.getDateOfBirth().getDayOfTheWeek(),
                                       p.getDateOfBirth());

                if(!p.isAlive())
                {
                    string += String.format(" and died on %s %s", p.getDateOfDeath().getDayOfTheWeek(),
                                            p.getDateOfDeath());
                }
                string += ".";

                writer.write(string);
                writer.newLine();
            }
        } catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
