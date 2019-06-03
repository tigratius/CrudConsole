package main.java.com.tigratius.crud.repository.io;

import main.java.com.tigratius.crud.model.BaseEntity;
import main.java.com.tigratius.crud.model.Customer;
import main.java.com.tigratius.crud.model.Message;
import main.java.com.tigratius.crud.repository.CustomerRepository;
import main.java.com.tigratius.crud.util.IOUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class JavaIOCustomerRepositoryImpl implements CustomerRepository {

    private final static String FILE_NAME = "customers.txt";

    public JavaIOCustomerRepositoryImpl() {
    }

    @Override
    public Customer getById(Long id) throws Exception {

        List<Customer> customers = stringToData( IOUtil.read(FILE_NAME));

        Customer current = null;
        for (Customer c : customers
        ) {
            if (c.getId().equals(id)) {
                current = c;
                break;
            }
        }

        if (current != null) {
            return current;
        }

        throw new Exception(Message.NOT_FIND_ID.getMessage() + id);
    }

    @Override
    public void delete(Customer item) {
        List<Customer> customers = stringToData( IOUtil.read(FILE_NAME));
        Customer removeCustomer = null;
        for (Customer c: customers
        ) {
            if (c.getId().equals(item.getId()))
            {
                removeCustomer = c;
                break;
            }
        }
        customers.remove(removeCustomer);
        IOUtil.writeList(FILE_NAME, dataToString(customers));
    }

    @Override
    public void update(Customer item) throws Exception {
        delete(getById(item.getId()));
        save(item);
    }

    @Override
    public void save(Customer item) {
        IOUtil.write(FILE_NAME, dataToString(item));
    }

    @Override
    public List<Customer> getAll() {

        return stringToData(IOUtil.read(FILE_NAME));
    }

    @Override
    public Long getLastId() {
        List<Customer> customers = stringToData( IOUtil.read(FILE_NAME));
        Collections.sort(customers, Comparator.comparing(BaseEntity::getId));

        if (customers.size() != 0) {
            return customers.get(customers.size() - 1).getId();
        }

        return 0L;
    }

    @Override
    public List<Customer> stringToData(List<String> items) {
        List<Customer> customers = new ArrayList<>();

        for (String str : items
        ) {
            String[] parts = str.split(",");
            Customer customer = new Customer();
            customer.setId(Long.parseLong(parts[0]));
            customer.setName(parts[1]);
            customers.add(customer);
        }

        return customers;
    }

    @Override
    public List<String> dataToString(List<Customer> items) {
        List<String> data = new ArrayList<>();
        for (Customer c : items) {
            data.add(dataToString(c));
        }

        return data;
    }

    @Override
    public String dataToString(Customer customer) {
        return customer.getId() + "," + customer.getName();
    }
}
