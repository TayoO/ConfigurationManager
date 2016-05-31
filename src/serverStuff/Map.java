package serverStuff;




import java.util.NoSuchElementException;

/**
 * Defines the <code>Map</code> abstract data type. An object that maps keys to
 * values. For this project, a map can contain duplicate keys, in which cases
 * the methods <code>get</code>, <code>put</code>, and <code>remove</code> refer
 * to the leftmost (last) association for the given key.
 *
 * @author Marcel Turcotte (turcotte@eecs.uottawa.ca)
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public interface Map< K, V> {



    /**
     * Returns the lefmost value associated with this key.
     *
     * @param key key whose associated value is to be returned
     * @return the value associated with this key
     * @throws java.util.NoSuchElementException if the element being requested
     * does not exist
     * @throws NullPointerException if the specified key is null
     */
    public abstract V get(K key) throws NoSuchElementException;

    /**
     * Returns true if an association exists for this key.
     *
     * @param key looking up for an association containing this key
     * @return true if this map contains the specified key
     * @throws NullPointerException if the specified key is null
     */
    public abstract boolean contains(K key);

    /**
     * Creates a new association: <code>key,value</code>.
     *
     * @param key key with which the specified value is to be associated
     * @param value the value to be associated with this key
     * @throws NullPointerException if the specified key is null
     */
    public abstract void put(K key, V value);
    public abstract void putAll(Map <K, V> map);
    /**
     * Replaces the value of the leftmost occurrence of the key.
     *
     * @param key key with which the specified value is to be associated
     * @param value the value to be associated with this key
     * @throws NullPointerException if the specified key or value is null
     */
    public abstract void replace(K key, V value) throws NoSuchElementException;

    /**
     * Removes the association the leftmost association: <code>key,value</code>.
     *
     * @param key the key of the association to remove
     * @return value associated this key
     * @throws java.util.NoSuchElementException if the element being requested
     * does not exist
     * @throws NullPointerException if the specified key or value is null
     */
    public abstract V remove(K key) throws NoSuchElementException;

}
