import java.util.ArrayList;
import java.util.Calendar;

public class zestaw8 {
    public static void main(String[] args) {
        Produkt pomidor = new Produkt("Pomidor",4,100);
        System.out.println(pomidor.toString());
        System.out.println();

        pomidor.dodajDoMagazynu(50);
        System.out.println(pomidor.toString());
        pomidor.usunZMagazynu(200);
        System.out.println();

        Produkt ziemniak = new Produkt("Ziemniak",3,200);
        KoszykZakupowy lista_zakupowa = new KoszykZakupowy(3, pomidor, ziemniak, pomidor);
        System.out.println(lista_zakupowa.toString());
        System.out.println();

        System.out.println("Wartosc koszyka: " + lista_zakupowa.obliczCalkowitaWartosc());
        System.out.println();

        Zamowienie zamowienie = new Zamowienie(lista_zakupowa,"W trakcie");
        zamowienie.ustawStatusZamowienia("Wykonane");
        System.out.println(zamowienie.toString());
        System.out.println();

        Adres adres = new Adres("Sloneczna",2,1,"Olsztyn","10-123");
        Klient klient1 = new Klient("Janusz","Nowak",adres);
        klient1.dodajZamowienie(zamowienie);
        System.out.println(klient1.toString());
        System.out.println();

        System.out.println("Laczny koszt zamowien: " + klient1.obliczLacznyKosztZamowien());
        System.out.println();

        Magazyn magazyn = new Magazyn(pomidor, ziemniak);
        System.out.println(magazyn.toString());
        System.out.println();

        Jablko jablko1 = new Jablko();
        jablko1.setKolor("Czerwony");
        System.out.println(jablko1.getKolor());
    }
}







interface Przedmiot {
    String toString();
    void dodajDoMagazynu(int liczba);
    void usunZMagazynu(int liczba);
    int hashCode();
    boolean equals(Produkt produkt);
}
class Produkt implements Przedmiot {
    private String nazwa;
    private double cena;
    private int iloscNaMagazynie;
    public Produkt(String nazwa, double cena, int iloscNaMagazynie) {
        this.nazwa = nazwa;
        this.cena = cena;
        this.iloscNaMagazynie = iloscNaMagazynie;
    }
    public Produkt() {
    }
    @Override
    public String toString() {
        String napis = "";
        napis += "Nazwa: " + this.nazwa;
        napis += "Cena: " + this.cena;
        napis += "Ilosc na magazynie: " + this.iloscNaMagazynie;
        return napis;
    }
    @Override
    public void dodajDoMagazynu(int liczba) {
        this.iloscNaMagazynie+=liczba;
    }
    @Override
    public void usunZMagazynu(int liczba) {
        if(this.iloscNaMagazynie>=liczba) {
            this.iloscNaMagazynie-=liczba;
        }
        else {
            System.out.println("Nie ma wystarczajaco duzo towaru!");
        }
    }
    @Override
    public int hashCode() {
        return Integer.parseInt(Integer.toString(this.nazwa.hashCode())+Double.hashCode(this.cena));
    }
    public boolean equals(Produkt produkt) {
        if(this.nazwa != produkt.nazwa) {
            return false;
        }
        if(this.cena != produkt.cena) {
            return false;
        }
        return true;
    }
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        try {
            this.nazwa = nazwa;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        try {
            this.cena = cena;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getIloscNaMagazynie() {
        return iloscNaMagazynie;
    }

    public void setIloscNaMagazynie(int iloscNaMagazynie) {
        try {
            this.iloscNaMagazynie = iloscNaMagazynie;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
class ProduktSpozywczy extends Produkt {

}
class ProduktPrzemyslowy extends Produkt {

}




interface Kosz {
    void dodajProdukt(Produkt produkt, int ilosc);
    String toString();
    double obliczCalkowitaWartosc();
}
class KoszykZakupowy implements Kosz {
    private ArrayList<Produkt> produkty = new ArrayList<Produkt>();
    private int ilosc;
    public KoszykZakupowy(int ilosc, Produkt... args) {
        this.ilosc = ilosc;
        for(int i=0;i<args.length;i++){
            produkty.add(args[i]);
        }
    }
    @Override
    public void dodajProdukt(Produkt produkt, int ilosc) {
        if(ilosc <= produkt.getIloscNaMagazynie()) {
            produkty.add(produkt);
            produkt.setIloscNaMagazynie(produkt.getIloscNaMagazynie()-ilosc);
        }
        else {
            System.out.println("Nie ma tyle produktu na magazynie!");
        }
    }
    @Override
    public String toString() {
        ArrayList<Produkt> unikalne_produkty = new ArrayList<Produkt>();
        String napis = "";
        for(int i=0;i<produkty.size();i++) {
            if(unikalne_produkty.contains(produkty.get(i))) {
            }
            else {
                unikalne_produkty.add(produkty.get(i));
            }
        }
        for(int i=0;i<unikalne_produkty.size();i++) {
            int ilosc = 0;
            for(int j=0;j<produkty.size();j++) {
                if(unikalne_produkty.get(i)==produkty.get(j)) {
                    ilosc++;
                }
            }
            napis += unikalne_produkty.get(i).getNazwa() + ", ";
            napis += " | Ilosc: " + Integer.toString(ilosc);
        }
        return napis;
    }
    @Override
    public double obliczCalkowitaWartosc() {
        double suma = 0;
        for(int i=0;i<produkty.size();i++) {
            suma += produkty.get(i).getCena();
        }
        return suma;
    }
    public ArrayList<Produkt> getProdukty() {
        return produkty;
    }

    public void setProdukty(ArrayList<Produkt> produkty) {
        try {
            this.produkty = produkty;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        try {
            this.ilosc = ilosc;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}






interface Zamowienia {
    void ustawStatusZamowienia(String nowy_status);
    String toString();
    void finalizujZamowienie();
}
class Zamowienie implements Zamowienia {
    private KoszykZakupowy koszykZakupowy;
    private String statusZamowienia;
    private Platnosc platnosc;
    public Zamowienie(KoszykZakupowy koszykZakupowy, String statusZamowienia) {
        this.koszykZakupowy = koszykZakupowy;
        this.statusZamowienia = statusZamowienia;
    }
    @Override
    public void ustawStatusZamowienia(String nowy_status) {
        this.statusZamowienia = nowy_status;
    }
    @Override
    public String toString() {
        String napis = "Zamowienie: \n";
        napis += koszykZakupowy.toString() + "\n";
        napis += this.statusZamowienia;
        return napis;
    }
    @Override
    public void finalizujZamowienie() {
        if(this.platnosc.getStatusPlatnosci() == "Oplacone") {
            this.statusZamowienia = "Gotowe do wysylki";
        }
    }

    public KoszykZakupowy getKoszykZakupowy() {
        return koszykZakupowy;
    }

    public void setKoszykZakupowy(KoszykZakupowy koszykZakupowy) {
        try {
            this.koszykZakupowy = koszykZakupowy;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getStatusZamowienia() {
        return statusZamowienia;
    }

    public void setStatusZamowienia(String statusZamowienia) {
        try {
            this.statusZamowienia = statusZamowienia;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Platnosc getPlatnosc() {
        return platnosc;
    }

    public void setPlatnosc(Platnosc platnosc) {
        try {
            this.platnosc = platnosc;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}







class Osoba {
    String imie;
    String nazwisko;
}
interface Klienci {
    void dodajZamowienie(Zamowienie zamowienie);
    String toString();
    double obliczLacznyKosztZamowien();
    int hashCode();
    boolean equals(Klient klient2);
}
class Klient extends Osoba implements Klienci {

    private ArrayList<Zamowienie> listaZamowien = new ArrayList<Zamowienie>();
    private Adres adres;
    public Klient(String imie, String nazwisko, Adres adres, Zamowienie... args) {
        super.imie = imie;
        super.nazwisko = nazwisko;
        this.adres = adres;
        for(int i=0;i<args.length;i++) {
            listaZamowien.add(args[i]);
        }
    }
    @Override
    public void dodajZamowienie(Zamowienie zamowienie) {
        listaZamowien.add(zamowienie);
    }
    @Override
    public String toString() {
        String napis = "Historia zamowien: ";
        for(int i=0;i<listaZamowien.size();i++) {
            napis += listaZamowien.get(i).toString();
        }
        return napis;
    }
    @Override
    public double obliczLacznyKosztZamowien() {
        double suma = 0;
        for(int i=0;i<listaZamowien.size();i++) {
            suma += listaZamowien.get(i).getKoszykZakupowy().obliczCalkowitaWartosc();
        }
        return suma;
    }
    @Override
    public int hashCode() {
        return Integer.parseInt(Integer.toString(this.imie.hashCode())+this.nazwisko.hashCode()+Integer.toString(this.adres.hashCode()));
    }
    @Override
    public boolean equals(Klient klient2) {
        if(this.imie != klient2.imie) {
            return false;
        }
        if(this.nazwisko != klient2.nazwisko) {
            return false;
        }
        if(this.adres != klient2.adres) {
            return false;
        }
        return true;
    }
    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        try {
            this.imie = imie;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        try {
            this.nazwisko = nazwisko;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Zamowienie> getListaZamowien() {
        return listaZamowien;
    }

    public void setListaZamowien(ArrayList<Zamowienie> listaZamowien) {
        try {
            this.listaZamowien = listaZamowien;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        try {
            this.adres = adres;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
class KlientFirmowy extends Klient {
    private final int NIP;
    private final int REGON;
    public KlientFirmowy(String imie, String nazwisko, Adres adres, int nip, int regon, Zamowienie... zamowienia) {
        super(imie,nazwisko,adres,zamowienia);
        this.NIP = nip;
        this.REGON = regon;
    }
}
class KlientIndywidualny extends Klient {
    private final int PESEL;
    public KlientIndywidualny(String imie, String nazwisko, Adres adres, int pesel, Zamowienie... zamowienia) {
        super(imie,nazwisko,adres,zamowienia);
        this.PESEL = pesel;
    }
}








interface Sklepy {
    void dodajProdukt(Produkt... args);
    String toString();
    Produkt wyszukajProduktu(String nazwa);
    void zakupy(KoszykZakupowy koszyk, Produkt produkt, int ilosc);
}
class Sklep implements Sklepy {
    private ArrayList<Produkt> produkty = new ArrayList<Produkt>();
    private String nazwaSklepu;
    private Calendar dataPowstania;
    private Magazyn magazynSklepu;
    public Sklep(String nazwaSklepu, Calendar dataPowstania, Magazyn magazyn, Produkt... args) {
        this.nazwaSklepu = nazwaSklepu;
        if(dataPowstania.YEAR > 2024) {
            throw new RuntimeException();
        }
        this.dataPowstania = dataPowstania;
        this.magazynSklepu = magazyn;
        for(int i=0;i<args.length;i++) {
            produkty.add(args[i]);
        }
    }
    @Override
    public void dodajProdukt(Produkt... args) {
        for(int i=0;i<args.length;i++) {
            produkty.add(args[i]);
        }
    }
    @Override
    public String toString() {
        String napis = "";
        for(int i=0;i<produkty.size();i++) {
            napis += produkty.get(i).toString();
        }
        return napis;
    }
    @Override
    public Produkt wyszukajProduktu(String nazwa) {
        for(int i=0;i<produkty.size();i++) {
            if(produkty.get(i).getNazwa().equals(nazwa)) {
                return produkty.get(i);
            }
        }
        System.out.println("Nie znaleziono produktu.");
        return new Produkt("",0,0);
    }
    @Override
    public void zakupy(KoszykZakupowy koszyk, Produkt produkt, int ilosc) {
        for(int i=0;i<ilosc;i++) {
            koszyk.dodajProdukt(produkt,1);
        }
    }

    public ArrayList<Produkt> getProdukty() {
        return produkty;
    }

    public void setProdukty(ArrayList<Produkt> produkty) {
        try {
            this.produkty = produkty;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getNazwaSklepu() {
        return nazwaSklepu;
    }

    public void setNazwaSklepu(String nazwaSklepu) {
        try {
            this.nazwaSklepu = nazwaSklepu;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Calendar getDataPowstania() {
        return dataPowstania;
    }

    public void setDataPowstania(Calendar dataPowstania) {
        try {
            this.dataPowstania = dataPowstania;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Magazyn getMagazynSklepu() {
        return magazynSklepu;
    }

    public void setMagazynSklepu(Magazyn magazynSklepu) {
        try {
            this.magazynSklepu = magazynSklepu;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}







interface Platnosci {
    void zaplac(int kwota);
    int hashCode();
    boolean equals(Platnosc platnosc2);
}
class Platnosc implements Platnosci {
    private double kwota;
    private String statusPlatnosci;
    public Platnosc(double kwota, String statusPlatnosci) {
        this.kwota = kwota;
        this.statusPlatnosci = statusPlatnosci;
    }
    @Override
    public void zaplac(int kwota) {
        this.statusPlatnosci = "Oplacone";
        this.kwota = kwota;
    }
    @Override
    public int hashCode() {
        return Integer.parseInt(Integer.toString(Double.hashCode(this.kwota))+this.statusPlatnosci.hashCode());
    }
    @Override
    public boolean equals(Platnosc platnosc2) {
        if(this.kwota != platnosc2.kwota) {
            return false;
        }
        if(this.statusPlatnosci != platnosc2.statusPlatnosci) {
            return false;
        }
        return true;
    }

    public double getKwota() {
        return kwota;
    }

    public void setKwota(double kwota) {
        try {
            this.kwota = kwota;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getStatusPlatnosci() {
        return statusPlatnosci;
    }

    public void setStatusPlatnosci(String statusPlatnosci) {
        try {
            this.statusPlatnosci = statusPlatnosci;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}







interface Magazyny {
    String toString();
}
class Magazyn implements Magazyny {
    private ArrayList<Produkt> produkty = new ArrayList<Produkt>();
    private int ilosc;
    public Magazyn(Produkt... args) {
        int suma=0;
        for(int i=0;i<args.length;i++) {
            produkty.add(args[i]);
            suma+=args[i].getIloscNaMagazynie();
        }
        this.ilosc = suma;
    }
    @Override
    public String toString() {
        String napis = "";
        napis += "Ilosc produktow: " + this.ilosc + "\n";
        napis += "Produkty:" + "\n";
        for(int i=0;i<produkty.size();i++) {
            napis += produkty.get(i).toString();
        }
        return napis;
    }

    public ArrayList<Produkt> getProdukty() {
        return produkty;
    }

    public void setProdukty(ArrayList<Produkt> produkty) {
        try {
            this.produkty = produkty;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        try {
            this.ilosc = ilosc;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}







interface Adresy {
    String toString();
    boolean przed(Adres adres2);
}
class Adres implements Adresy {
    private String ulica;
    private int numerDomu;
    private int numerMieszkania;
    private String miasto;
    private String kodPocztowy;
    public Adres(String ulica, int numerDomu, int numerMieszkania, String miasto, String kodPocztowy) {
        this.ulica = ulica;
        this.numerDomu = numerDomu;
        this.numerMieszkania = numerMieszkania;
        this.miasto = miasto;
        this.kodPocztowy = kodPocztowy;
    }
    public Adres(String ulica, int numerDomu, String miasto, String kodPocztowy) {
        this.ulica = ulica;
        this.numerDomu = numerDomu;
        this.miasto = miasto;
        this.kodPocztowy = kodPocztowy;
    }
    @Override
    public String toString() {
        String napis = this.kodPocztowy + " " + this.miasto + "\n";
        napis += this.ulica + " " + this. numerDomu + " " + this.numerMieszkania;
        return napis;
    }
    @Override
    public boolean przed(Adres adres2) {
        int first = Integer.parseInt(this.kodPocztowy.substring(0,2)) * 1000 + Integer.parseInt(this.kodPocztowy.substring(3));
        int second = Integer.parseInt(adres2.kodPocztowy.substring(0,2)) * 1000 + Integer.parseInt(adres2.kodPocztowy.substring(3));
        if(first<second) {
            return true;
        }
        else {
            return false;
        }
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        try {
            this.ulica = ulica;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getNumerDomu() {
        return numerDomu;
    }

    public void setNumerDomu(int numerDomu) {
        try {
            this.numerDomu = numerDomu;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getNumerMieszkania() {
        return numerMieszkania;
    }

    public void setNumerMieszkania(int numerMieszkania) {
        try {
            this.numerMieszkania = numerMieszkania;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        try {
            this.miasto = miasto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        try {
            this.kodPocztowy = kodPocztowy;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}






abstract class Owoc extends ProduktSpozywczy {
    public abstract void smak();
    public abstract void umyj();
    public abstract void zjedz();
}
abstract class Warzywo extends ProduktSpozywczy {
    public abstract void smak();
    public abstract void umyj();
    public abstract void zjedz();
}


abstract class UrzadzenieElektroniczne extends ProduktPrzemyslowy {
    public abstract void napraw();
    public abstract void uzyj();
    public abstract void zepsuj();
    public abstract void wlacz();
    public abstract void wylacz();
}
abstract class Ubranie extends ProduktPrzemyslowy {
    public abstract void wypierz();
    public abstract void zaloz();
    public abstract void wyprasuj();
    public abstract void wysusz();
    public abstract void zniszcz();
}


class Jablko extends Owoc {
    private String kolor;
    private double waga;
    public void smak() {
        System.out.println("Smakuje jak jablko.");
    }
    public void umyj() {
        System.out.println("Jablko zostalo umyte.");
    }
    public void zjedz() {
        System.out.println("Jablko zostalo zjedzone.");
    }
    public String getKolor() {
        return this.kolor;
    }
    public void setKolor(String kolor) {
        try {
            this.kolor = kolor;
        } catch (Exception e) {
            throw new RuntimeException("Podano niepoprawne dane!");
        }
    }
    public double getWaga() {
        return this.waga;
    }
    public void setWaga(double waga) {
        try {
            this.waga = waga;
        } catch (Exception e) {
            throw new RuntimeException("Podano niepoprawne dane!");
        }
    }
}
class Telefon extends UrzadzenieElektroniczne {
    String marka;
    String kolor;
    public void napraw() {
        System.out.println("Telefon zostal naprawiony.");
    }
    public void uzyj() {
        System.out.println("Telefon zostal uzyty.");
    }
    public void zepsuj() {
        System.out.println("Telefon zostal zepsuty.");
    }
    public void wlacz() {
        System.out.println("Wlaczono telefon.");
    }
    public void wylacz() {
        System.out.println("Wylaczono telefon.");
    }
    public String getMarka() {
        return this.marka;
    }
    public void setMarka(String marka) {
        try {
            this.marka = marka;
        } catch (Exception e) {
            throw new RuntimeException("Podano niepoprawne dane!");
        }
    }
    public String getKolor() {
        return this.kolor;
    }
    public void setKolor(String kolor) {
        try {
            this.kolor = kolor;
        } catch (Exception e) {
            throw new RuntimeException("Podano niepoprawne dane!");
        }
    }
}