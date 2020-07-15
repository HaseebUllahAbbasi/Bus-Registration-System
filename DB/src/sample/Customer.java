package sample;
import javafx.beans.property.SimpleStringProperty;
public class Customer
{
    SimpleStringProperty name;
    SimpleStringProperty cnic;
    SimpleStringProperty route;
    SimpleStringProperty bus;

    Customer(String name,String cnic,String route,String bus)
    {
        this.bus = new SimpleStringProperty(bus);
        this.cnic = new SimpleStringProperty(cnic);
        this.route = new SimpleStringProperty(route);
        this.name = new SimpleStringProperty(name);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getCnic() {
        return cnic.get();
    }

    public SimpleStringProperty cnicProperty() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic.set(cnic);
    }

    public String getRoute() {
        return route.get();
    }

    public SimpleStringProperty routeProperty() {return route;}

    public void setRoute(String route) {
        this.route.set(route);
    }

    public String getBus() {
        return bus.get();
    }

    public SimpleStringProperty busProperty() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus.set(bus);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name=" + name +
                ", cnic=" + cnic +
                ", route=" + route +
                ", bus=" + bus +
                '}';
    }
}
