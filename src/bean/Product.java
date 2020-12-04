package bean;

import java.io.Serializable;

public class Product implements Serializable {
    private String no;
    private String name;
    private String spec;
    private String brand;
    private String type;

    public Product() {}

    public String getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public String getSpec() {
        return spec;
    }

    public String getBrand() {
        return brand;
    }

    public String getType() {
        return type;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setType(String type) {
        this.type = type;
    }
}
