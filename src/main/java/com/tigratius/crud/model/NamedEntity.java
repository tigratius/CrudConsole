package main.java.com.tigratius.crud.model;

public class NamedEntity extends BaseEntity{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
