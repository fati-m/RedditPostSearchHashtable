import java.util.NoSuchElementException;

/*
 * public class that defines all components of a hashtablemap
 */
@SuppressWarnings("unchecked")
public class HashtableMap<KeyType,ValueType> implements MapADT<KeyType,ValueType>{
    public int capacity; // the number of elements that can be stored in the map
    public int size; // the number of elements in the map
    private Pairs<KeyType, ValueType>[] map; // the array that holds each value and key pair

    /*
     * private helper class to make pairs
     */
    private static class Pairs<KeyType, ValueType> {
        private KeyType key;
        private ValueType value;

        // pair constructor
        public Pairs(KeyType key, ValueType value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * explicit constructor to choose capacity
     * @param capacity
     */
    public HashtableMap(int capacity){
        this.capacity = capacity;
        this.size = 0;
        this.map = (Pairs<KeyType, ValueType>[]) new Pairs[capacity];    
    }

    /**
     * default constructor
     */
    public HashtableMap(){
        this.capacity = 8; // default capacity = 8
        this.size = 0;
        this.map = (Pairs<KeyType, ValueType>[]) new Pairs[8]; 
    }

    /**
     * add a new key-value pair/mapping to this collection
     * throws exception when key is null or duplicate of one already stored
     * 
     * @param key
     * @param value
     * @throws IllegalArgumentException when key is null or duplicate of one already stored
     */
    public void put(KeyType key, ValueType value) throws IllegalArgumentException {
        // throw exception if key is null
    if (key == null) {
        throw new IllegalArgumentException("key cannot be null");
    }

    // check for duplicate key and throw exception if found
    for (Pairs<KeyType, ValueType> pair : map) {
        if (pair != null && pair.key.equals(key)) {
            throw new IllegalArgumentException("duplicate key found");
        }
    }

    // double capacity and rehash if load factor >= 0.7
    double currLoadFactor = ((double) size + 1) / capacity;
    if (currLoadFactor >= 0.70) {
        size = 0;
        int doubleCapacity = capacity * 2;
        Pairs<KeyType, ValueType>[] doublePairs = new Pairs[doubleCapacity];

        // rehash existing key-value pairs to new indices
        for (Pairs<KeyType, ValueType> pair : map) {
            if (pair != null) {
                int i = Math.abs(pair.key.hashCode()) % doubleCapacity;
                while (doublePairs[i] != null) {
                    i = (i + 1) % doubleCapacity;
                }
                doublePairs[i] = pair;
                size++;
            }
        }

        // update capacity and map
        capacity = doubleCapacity;
        map = doublePairs;
    }

    // store the new pair
    int i = Math.abs(key.hashCode()) % capacity;
    while (map[i] != null) {
        i = (i + 1) % capacity;
    }

    // update pairs and increment size
    map[i] = new Pairs<>(key, value);
    size++;
    }
    
    /**
     * Check whether a key maps to a value within this collection.
     *
     * @param key the key to be checked
     * @return true if a matching key is found, false otherwise
     */
    public boolean containsKey(KeyType key) {
        // goes through non-null elements and checks for key equality 
        for (Pairs<KeyType, ValueType> pair : map) {
            if (pair != null) {
            if (pair.key.equals(key)) {
                return true;
            }
            }
        }
        return false;
    }

    /**
     * retrieve the specific value that a key maps to
     * throws exception when key is not stored in this collection
     * 
     * @param key
     * @throws NoSuchElementException if key is not in hashmap 
     * @return ValueType of the matching key
     */
    public ValueType get(KeyType key) throws NoSuchElementException {
        // iterate through every position in the hash table
    for (int i = 0; i < capacity; i++) {
        int index = (Math.abs(key.hashCode()) + i) % capacity;
        if (map[index] != null && map[index].key.equals(key)) {
            // if the position contains the target key-value pair, return the corresponding value
            return map[index].value;
        }
        else if (map[index] == null) {
            // if the position is null, the key is not in the hash table, so we throw a NoSuchElementException
            throw new NoSuchElementException("could not find key");
        }
    }
    // if we iterate through the entire hash table and still haven't found the key, it's not in the hash table, so we throw a NoSuchElementException
    throw new NoSuchElementException("could not find key");
    }

    /**
     * remove the mapping for a given key from this collection
     * throws exception when key is not stored in this collection
     * 
     * @param key
     * @throws NoSuchElementException if key is not in hashmap 
     * @return value that is removed
     */
    public ValueType remove(KeyType key) throws NoSuchElementException {
        // find the index
        int i = Math.abs(key.hashCode()) % capacity;
        while (map[i] != null && !map[i].key.equals(key)) {
            i = (i + 1) % capacity;
        }
        
        // throw exception if key is null
        if (map[i] == null) {
            throw new NoSuchElementException("could not find key");
        }
        
        // temp to store key value
        ValueType value = map[i].value;

        // set removed element to null and decrement size
        map[i] = null;
        size--;
    
        // rehash the remaining elements after key is removed
        i = (i + 1) % capacity;
        while (map[i] != null) {
            KeyType currKey = map[i].key;
            ValueType currValue = map[i].value;
            map[i] = null;
            size--;
            put(currKey, currValue);
            i = (i + 1) % capacity;
        }
        return value;
    }

    /**
    remove all key-value pairs from this collection
    */
    public void clear() {
        // go through maps array and set it all to null
        for (int i = 0; i < capacity; i++) {
            map[i] = null;
        }

        // size set back to zero
        size = 0;
    }
    
    /**
     * retrieve the number of keys stored within this collection
     */
    public int getSize() {
        return this.size;
    }

    /**
     * retrieve this collection's capacity (size of its underlying array)
     */
    public int getCapacity() {
        return this.capacity;
    }
}
