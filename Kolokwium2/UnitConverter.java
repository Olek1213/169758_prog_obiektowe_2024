public class UnitConverter implements Converter{
    @Override
    public double convertToEuro(double amount) {
        return amount / 1.05;
    }
    @Override
    public double convertToUSD(double amount) {
        return amount * 1.05;
    }
    @Override
    public double getConversionRate(String currency) {
        if(currency.equals("Euro")) {
            return 0.95;
        }
        else if(currency.equals("USD")) {
            return 1.05;
        }
        else {
            System.out.println("Niepoprawna waluta!");
            return 0;
        }
    }
}
