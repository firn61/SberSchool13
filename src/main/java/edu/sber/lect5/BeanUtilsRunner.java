package edu.sber.lect5;

public class BeanUtilsRunner {
    public static void main(String[] args) {
        Class1 cFrom = new Class1("nameFrom", 1, "location", true);
        System.out.println(cFrom);
        Class2 cTo = new Class2("nameTo", 2, false);
        System.out.println(cTo);
        BeanUtils.assign(cTo, cFrom);
        System.out.println(cFrom);
        System.out.println(cTo);
    }
}

class Class1{

    private String name;
    private Integer price;
    private String location;
    private Boolean isActive;

    public Class1(String name, Integer price, String location, Boolean isActive) {
        this.name = name;
        this.price = price;
        this.location = location;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Class1{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", location='" + location + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
class Class2{

    private String name;
    private Number price;
    private Boolean isActive;


    public Class2(String name, Number price, Boolean isActive) {
        this.name = name;
        this.price = price;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getPrice() {
        return price;
    }

    public void setPrice(Number price) {
        this.price = price;
    }

    public Boolean getActive() {
        return isActive;
    }

    private void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Class2{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", isActive=" + isActive +
                '}';
    }
}
