import java.util.ArrayList;
import java.util.Calendar;

public class zestaw7 {
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

        System.out.println("Laczny koszt zamowien: " + klient1.obliczLacznyKoszZamowien());
        System.out.println();

        Magazyn magazyn = new Magazyn(pomidor, ziemniak);
        System.out.println(magazyn.toString());
        System.out.println();

    }
}

class Produkt {
    private String nazwa;
    private double cena;
    private int iloscNaMagazynie;
    public Produkt(String nazwa, double cena, int iloscNaMagazynie) {
        this.nazwa = nazwa;
        this.cena = cena;
        this.iloscNaMagazynie = iloscNaMagazynie;
    }
    @Override
    public String toString() {
        String napis = "";
        napis += "Nazwa: " + this.nazwa;
        napis += "Cena: " + this.cena;
        napis += "Ilosc na magazynie: " + this.iloscNaMagazynie;
        return napis;
    }
    public void dodajDoMagazynu(int liczba) {
        this.iloscNaMagazynie+=liczba;
    }
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

class KoszykZakupowy {
    private ArrayList<Produkt> produkty = new ArrayList<Produkt>();
    private int ilosc;
    public KoszykZakupowy(int ilosc, Produkt... args) {
        this.ilosc = ilosc;
        for(int i=0;i<args.length;i++){
            produkty.add(args[i]);
        }
    }
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

class Zamowienie {
    private KoszykZakupowy koszykZakupowy;
    private String statusZamowienia;
    private Platnosc platnosc;
    public Zamowienie(KoszykZakupowy koszykZakupowy, String statusZamowienia) {
        this.koszykZakupowy = koszykZakupowy;
        this.statusZamowienia = statusZamowienia;
    }
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

class Klient {
    private String imie;
    private String nazwisko;
    private ArrayList<Zamowienie> listaZamowien = new ArrayList<Zamowienie>();
    private Adres adres;
    public Klient(String imie, String nazwisko, Adres adres, Zamowienie... args) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.adres = adres;
        for(int i=0;i<args.length;i++) {
            listaZamowien.add(args[i]);
        }
    }
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
    public double obliczLacznyKoszZamowien() {
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

class Sklep {
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
    public Produkt wyszukajProduktu(String nazwa) {
        for(int i=0;i<produkty.size();i++) {
            if(produkty.get(i).getNazwa().equals(nazwa)) {
                return produkty.get(i);
            }
        }
        System.out.println("Nie znaleziono produktu.");
        return new Produkt("",0,0);
    }
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

class Platnosc {
    private double kwota;
    private String statusPlatnosci;
    public Platnosc(double kwota, String statusPlatnosci) {
        this.kwota = kwota;
        this.statusPlatnosci = statusPlatnosci;
    }
    public void zaplac(int kwota) {
        this.statusPlatnosci = "Oplacone";
        this.kwota = kwota;
    }
    @Override
    public int hashCode() {
        return Integer.parseInt(Integer.toString(Double.hashCode(this.kwota))+this.statusPlatnosci.hashCode());
    }

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

class Magazyn {
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

class Adres {
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
