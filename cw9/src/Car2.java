import java.util.Objects;

public class Car2 {
    private String make;
    private String model;
    private Engine engine;

    public Car2(String make, String model, Engine engine) {
        this.make = make;
        this.model = model;
        this.engine = engine;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "Car2{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", engine=" + engine +
                '}';
    }

    public boolean equals(Car2 car) {
        if(this.make != car.make) {
            return false;
        }
        if(this.model != car.model) {
            return false;
        }
        if(this.engine != car.engine) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, engine);
    }
}
