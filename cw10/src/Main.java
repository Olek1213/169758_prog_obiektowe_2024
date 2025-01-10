import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Box<String> b1 = new Box<>();
        b1.setObiekt("1234");
        System.out.println(b1.getObiekt());

        Box<Integer> b2 = new Box<>();
        b2.setObiekt(1234);
        System.out.println(b2.getObiekt());

    }
    public static <T> void swap(T[] tablica, int index1, int index2) {
        try {
            T x = tablica[index1];
            tablica[index1]=tablica[index2];
            tablica[index2]=x;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException();
        }
        catch (Exception e) {
            throw new RuntimeException();
        }
    }
    public static <T extends Animal> T findMax(T element1, T element2) {
        if(element1.wiek >= element2.wiek) {
            return element1;
        }
        else {
            return element2;
        }
    }
    public static <T> void findMinMaxAge(Dog[] tablica, Pair<?super Dog> result) {
        int max=tablica[0].getAge(),min=tablica[0].getAge(),minindex=0,maxindex=0;
        for(int i=0;i<tablica.length;i++) {
            if(tablica[i].getAge() > max) {
                max = tablica[i].getAge();
                maxindex = i;
            }
            if(tablica[i].getAge() < min) {
                min = tablica[i].getAge();
                minindex = i;
            }
        }
        result.obiekt1 = tablica[minindex];
        result.obiekt2 = tablica[maxindex];
    }
}

class Box<T>{
    T obiekt;
    public static <T> boolean isEqual(T a , T b) {
        return a.equals(b);
    }
    public void setObiekt(T obiekt) {
        this.obiekt = obiekt;
    }
    public T getObiekt() {
        return obiekt;
    }
}

class Counter<T>{
    List<T> lista = new ArrayList<T>();
    public void add(T obiekt) {
        lista.add(obiekt);
    }
    public int getCount() {
        return lista.size();
    }
}

class Pair<T>{
    T obiekt1;
    T obiekt2;
}