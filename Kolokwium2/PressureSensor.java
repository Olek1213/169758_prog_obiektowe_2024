public class PressureSensor implements Sensor{
    double pressure;
    String status;
    @Override
    public double readValue() {
        return pressure;
    }
    @Override
    public String getStatus() {
        return status;
    }
    @Override
    public void reset() {
        this.pressure = 0;
        System.out.println("Zresetowano cisnieniomierz.");
    }
}
