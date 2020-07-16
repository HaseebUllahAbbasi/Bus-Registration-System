package sample;
import javafx.beans.property.SimpleStringProperty;
public class Customer
{
    SimpleStringProperty name;
    SimpleStringProperty cnic;
    SimpleStringProperty route;
    SimpleStringProperty bus;
    SimpleStringProperty date;

    Customer(String name,String cnic,String route,String bus,String data)
    {
        this.bus = new SimpleStringProperty(bus);
        this.cnic = new SimpleStringProperty(cnic);
        this.route = new SimpleStringProperty(route);
        this.name = new SimpleStringProperty(name);
        this.date = new SimpleStringProperty(data);
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

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
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


}
