public class CurrencyConverter implements Converter{
    // metoda convertToEuro konwertuje liczbe zlotych na euro
    @Override
    public double convertToEuro(double amount) {
        return amount / 4.21;
    }
    @Override
    public double convertToUSD(double amount) {
        return amount / 4.02;
    }
    @Override
    public double getConversionRate(String currency) {
        if(currency.equals("Euro")) {
            return 4.21;
        }
        else if(currency.equals("USD")) {
            return 4.02;
        }
        else {
            System.out.println("Niepoprawna waluta!");
            return 0;
        }
    }
}
