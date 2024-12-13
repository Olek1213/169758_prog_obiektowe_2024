import java.util.Objects;

public class Engine {
    private int power;
    private String type;
    private int serialNumber;

    public Engine(int power, String type, int serialNumber) {
        this.power = power;
        this.type = type;
        this.serialNumber = serialNumber;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "power=" + power +
                ", type='" + type + '\'' +
                ", serialNumber=" + serialNumber +
                '}';
    }

    public boolean equals(Engine engine) {
        if(this.power != engine.power) {
            return false;
        }
        if(this.type != engine.type) {
            return false;
        }
        if(this.serialNumber != engine.serialNumber) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(power, type, serialNumber);
    }
}
