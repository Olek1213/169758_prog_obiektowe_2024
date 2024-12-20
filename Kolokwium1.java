public class Kolokwium1 {
    public static void main(String[] args) {
        double[] tab = {2};
        wypiszTablice(ciagArytmetycznyRodzajuM(5,1,5,tab));
        System.out.println();
        System.out.println(czyCiagArytmetyczny(ciagArytmetycznyRodzajuM(3,1,5,tab)));
        System.out.println();
        double[] tab2 = {1,3,1,1,5};
        System.out.println(podciag(tab2,2));
        System.out.println();
        sekwencjaCollatza(5,3);
        System.out.println();
        minMaxSekwencjaCollatza(5,3);
    }
    public static void wypiszTablice(double[] tab) {
        for(int i=0;i<tab.length;i++) {
            System.out.println(tab[i]);
        }
    }
    public static double[] ciagArytmetycznyRodzajuM(int n, int m, double a1, double[] r) {
        double wyraz = a1;
        double[] tab = new double[n];
        for(int i=0;i<n;i++) {
            for(int j=1;j<=m;j++) {
                wyraz += Math.pow(i,j)*r[j-1];
            }
            tab[i] = wyraz;
            wyraz = a1;
        }
        return tab;
    }
    public static boolean czyCiagArytmetyczny(double[] tab) {
        boolean flag = true;
        if(tab.length > 1) {
            double r = tab[1] - tab[0];
            for(int i=1;i<=tab.length-1;i++) {
                if(tab[i]-tab[i-1] != r) {
                    flag = false;
                }
            }
        }
        return flag;
    }
//    public static boolean czyCiagArytmetycznyRodzajuM(double[] tab, int m) {
//        boolean flag = true;
//        if(tab.length > 1) {
//            double[] r = tab[1] - tab[0];
//            for(int j=1;j<=m;)
//            for(int i=1;i<=tab.length-1;i++) {
//                if(tab[i]-tab[i-1] != r) {
//                    flag = false;
//                }
//            }
//        }
//        return flag;
//    }
    public static int podciag(double[] tab) {
        int maxlen = 1;
        int len;
        for(int i=0;i<tab.length-1;i++) {
            int j=i;
            len=1;
            while (j<tab.length-1 && tab[j]<tab[j+1]) {
                j++;
                len++;
                i++;
            }
            if(len>maxlen) {
                maxlen=len;
            }
        }
        return maxlen;
    }
    public static int podciag(double[] tab, int r) {
        int maxlen = 1;
        int len;
        for(int i=0;i<tab.length-1;i++) {
            int j=i;
            len=1;
            while (j<tab.length-1 && tab[j]<tab[j+1] && tab[j+1]-tab[j]==r) {
                j++;
                len++;
                i++;
            }
            if(len>maxlen) {
                maxlen=len;
            }
        }
        return maxlen;
    }
    public static void sekwencjaCollatza(int n, int c) {
        int liczba = c;
        for(int i=0;i<n;i++) {
            System.out.println(liczba);
            if(liczba % 2 == 0) {
                liczba /= 2;
            }
            else {
                liczba *= 3;
                liczba++;
            }
        }
    }
    public static void minMaxSekwencjaCollatza(int n, int c) {
        int liczba = c;
        int min = c;
        int max = c;
        for(int i=0;i<n;i++) {
            if(liczba<min) {
                min = liczba;
            }
            if(liczba>max) {
                max = liczba;
            }
            if(liczba % 2 == 0) {
                liczba /= 2;
            }
            else {
                liczba *= 3;
                liczba++;
            }
        }
        System.out.print("min=");
        System.out.println(min);
        System.out.print("max=");
        System.out.println(max);
    }
}