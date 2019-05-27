package repository;

import model.Customer;
import util.CustomerFileHelper;

import java.util.ArrayList;

public class JavaIOCustomerRepositoryImpl implements CustomerRepository {

    private ArrayList<Customer> customers;

    public JavaIOCustomerRepositoryImpl() {

        customers = CustomerFileHelper.read();
    }

    @Override
    public Customer get(Long id) throws Exception {
        Customer current = null;
        for (Customer c : customers
        ) {
            if (c.id == id) {
                current = c;
                break;
            }
        }

        if (current != null) {
            return current;
        }

        throw new Exception("ID = " + id + " нет!");
    }

    @Override
    public void add(Customer item) {
        item.id = getLastId() + 1;
        customers.add(item);
    }

    @Override
    public void delete(Long id) throws Exception {
        customers.remove(get(id));
    }

    @Override
    public void update(Customer item) throws Exception {
        Customer customer = get(item.id);
        customer.name = item.name;
    }

    @Override
    public void save() {

        CustomerFileHelper.write(customers);
    }

    @Override
    public ArrayList<Customer> getAll() {

        return customers;
    }

    @Override
    public Long getLastId() {
        if (customers.size() != 0) {
            return customers.get(customers.size() - 1).id;
        }

        return 0L;
    }
}
