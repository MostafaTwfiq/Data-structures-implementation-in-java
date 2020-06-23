import java.util.LinkedList;

public class HashMap implements IHashMap{

    private class Entry{
        private int key;
        private String value;

        private Entry(int key, String value){
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString(){
            return Integer.toString(key) + " --> " + value;
        }
    }

    private LinkedList<Entry>[] linkedList = new LinkedList[10];

    /** Adding a new element of Entry object in the LinkedList with the parameter key,
    and if there is no LinkedList with this key it will create a new object of this LinkedList.
     */

    public void put(int key, String value){
        int index = hashedIndex(key);

        if (linkedList[index] == null)
            linkedList[index] = new LinkedList<>();

        for (Entry temp : linkedList[index]) {
            if (temp.key == key) {
                temp.value = value;
                return;
            }

        }

        linkedList[index].addLast(new Entry(key, value));
    }

    /** This method will create a string array with the size of the number of element that
    has that key, and it will copy all the values with this key into the string array,
    then return it.
     */

    public String get(int key){
        int index = hashedIndex(key);

        if (linkedList[index] != null) {
            for (Entry temp : linkedList[index]) {
                if (temp.key == key)
                    return temp.value;

            }

        }
        return null;
    }

    /** This method will remove all the value with this key. */

    public void remove(int key){
        int index = hashedIndex(key);

        if (linkedList[index] != null)
            linkedList[index].removeIf(temp -> temp.key == key);

    }

    /** This method generates a hash key that referencing to the index that all
    the elements with this key will store in it.
     */

    private int hashedIndex(int key){
        return key % linkedList.length;
    }

    /** This method will print all the values in the hash table.*/

    public void print(){
        for (LinkedList<Entry> entries : linkedList) {
            if (entries != null)
                System.out.println(entries);

        }
    }
}