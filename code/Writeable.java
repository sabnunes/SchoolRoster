/**
 * An interface representing entities that can be written. Provides a method to print data within a specified range.
 * This is an abstract interface, meant to be implemented by concrete classes.
 *
 * @author Sabrina Nunes
 */
public abstract interface Writeable
{
    /**
     * Prints the specified data within the given range.
     *
     * @param s   the data to print
     * @param min the minimum value of the range
     * @param max the maximum value of the range
     */
    public abstract void printData(String s, int min, int max);
}
