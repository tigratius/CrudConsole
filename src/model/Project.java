package model;

import java.util.Set;

public class Project extends NamedEntity {

    public ProjectStatus projectStatus;
    public Long customerId;
    public Set<Long> categoryIds;

    public Customer customer;
    public Set<Category> categories;
}
