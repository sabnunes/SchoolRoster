/**
 * An interface representing orderable entities.
 * Provides methods to get the next and previous entities in the order.
 * This can be used to navigate through a sequence of ordered entities.
 *
 * @author Sabrina Nunes
 */
public interface Orderable
{
    /**
     * Returns the next entity in the order.
     *
     * @return the next entity
     */
    public Orderable next();

    /**
     * Returns the previous entity in the order.
     *
     * @return the previous entity
     */
    public Orderable previous();
}
