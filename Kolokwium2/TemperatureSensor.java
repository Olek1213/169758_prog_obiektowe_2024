public class TemperatureSensor implements Sensor{
    double temperature;
    String status;
    @Override
    public double readValue() {
        return temperature;
    }
    @Override
    public String getStatus() {
        return status;
    }
    @Override
    public void reset() {
        this.temperature = 0;
        System.out.println("Zresetowano termometr.");
    }
}
