public class zadanie6 {
    public static void main(String[] args) {

    }
    public static <T> boolean isEqualOrNull(T object1, T object2) {
        if(object1.equals(object2) || (object1 == null && object2 == null)) {
            return true;
        }
        else {
            return false;
        }
    }
}