
public class Main {
    public static void main(String[] args) {
        BookDTG ksiazka1 = new BookDTG("Dziady","Adam Mickiewicz",20,1980);
        BookDTG ksiazka2 = new BookDTG("Harry Potter","J.K. Rowling",30,1990);
        BookDTG ksiazka3 = new BookDTG("Krzyzacy","Henryk Sienkiewicz",40,1970);
        Address adres1 = new Address("Sloneczna",2,"10-123","Olsztyn");
        Person osoba1 = new Person("Jan","Nowak",31,adres1);
        Car samochod = new Car("Ferrari","model1",20);
        System.out.println(samochod.fuelCost(6,150));
        Person osoba2 = new Person("Andrzej","Kowalski",-3,adres1);
        System.out.println(osoba2.toString());
        BankAccount konto1 = new BankAccount(321312);
        System.out.println(konto1.toString());

    }
}
