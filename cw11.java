import java.util.*;

public class cw11 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(2);
        printUnique(list);
        System.out.println(findUniqueElements(list));
        TreeSet<Integer> drzewo = new TreeSet<>();
        drzewo.add(1);
        drzewo.add(2);
        drzewo.add(3);
        drzewo.add(4);
        drzewo.add(5);
        System.out.println(findElementsInRange(drzewo,2,4));
    }
    public static <T> void printUnique(Collection<T> items) {
        Set<T> uniqueElements = new HashSet<>(items);
        for(T item: uniqueElements) {
            System.out.println(item);
        }
    }
    public static <T> ArrayList<T> mergeLists(ArrayList<T> list1, ArrayList<T> list2) {
        ArrayList<T> list3 = new ArrayList<>();
        list3.addAll(list1);
        list3.addAll(list2);
        return list3;
    }
    public static <T> void reversePrint(Iterable<T> items) {
        ArrayList<T> lista = new ArrayList<>();
        for(T item: items) {
            lista.add(item);
        }
        for(int i=lista.size()-1;i>=0;i++) {
            System.out.println(lista.get(i));
        }
    }
    public static <T> boolean isPalindrome(LinkedList<T> list) {
        for(int i=0;i<list.size();i++) {
            if(list.get(i)!=list.get(list.size()-1-i)) {
                return false;
            }
        }
        return true;
    }
    public static <T> HashSet<T> findUniqueElements(List<T> list) {
        HashSet<T> uniqueElements = new HashSet<>(list);
        return uniqueElements;
    }
    public static <T> TreeSet<T> findElementsInRange(TreeSet<T> drzewo, T lowerBound, T upperBound) {
        return new TreeSet<T>(drzewo.subSet(lowerBound,true, upperBound, true));
    }
    public static <K,V> HashMap<V,Integer> countValueOccerrences(HashMap<K,V> map){
        HashMap<V,Integer> occurrences = new HashMap<>();
        for(V value: map.values()) {
            if(occurrences.containsKey(value)){
                occurrences.replace(value,occurrences.get(value)+1);
            }
            else {
                occurrences.put(value,1);
            }
        }
        return occurrences;
    }
    public static <K,V> TreeMap<K,V> subMapInRange(TreeMap<K,V> map, K startKey, K endKey) {
        return new TreeMap<K,V>(map.subMap(startKey, true, endKey, true));
    }
}