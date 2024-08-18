/**
 * Exception thrown when an attempt is made to create an illegal person. This exception is a subclass of
 * {@link RuntimeException}. It is used to indicate that a person cannot be created with the given parameters. This is
 * an unchecked exception.
 *
 * @author Sabrina Nunes
 */
public class IllegalPersonException
        extends RuntimeException
{
    /**
     * Constructs an IllegalPersonException with the specified detail message.
     *
     * @param message the detail message
     */
    public IllegalPersonException(String message)
    {
        super(message);
    }
}
