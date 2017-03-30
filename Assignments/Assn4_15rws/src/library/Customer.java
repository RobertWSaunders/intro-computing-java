/**
 * Customer is a class that represents a customer who would use the library system.
 * NOTE: This class is an extension to the assignment.
 *
 * @author Robert Saunders (NetID: 15rws, Student #: 10194030)
 * @version 1.0.0
 */

package library;

public class Customer {

    //////////////////
    /* DEFINITIONS */
    ////////////////

    /**
     * Define the enum for the different type of customers.
     */
    enum type {
        student,
        employee
    }

    ///////////////////////
    /* CLASS ATTRIBUTES */
    /////////////////////

    //the id of the customer
    private int id;
    //the name of the customer
    private String name;
    //define an attribute that declares the type of customer
    private type customerType;
    //define an attribute that declares the customers department
    //NOTE: Could implement department using enums but flexibility is bad
    private String customerDepartment;

    ///////////////////
    /* CONSTRUCTORS */
    /////////////////

    //define a incrementer for the item id, increments every time an item is created.
    private static int idIncrementer = -1;

    /**
     * Default constructor for the customer class.
     */
    Customer() {
        //set customer name to an unknown value
        setName("Unknown");
        //set the type of the customer, defaults to student
        setCustomerType(type.student);
        //set the department of the customer, defaults to none
        setCustomerDepartment("None");
        //set the id of the customer
        setId();
    }

    /**
     * Constructor for the customer class that takes a name, type, and department as parameters.
     * @param name The name of the customer to be set.
     * @param customerDepartment The customer's department.
     * @param customerType The type of customer.
     */
    Customer(String name, type customerType, String customerDepartment) {
        //set the name of the customer
        setName(name);
        //set the type of the customer
        setCustomerType(customerType);
        //set the department of the customer
        setCustomerDepartment(customerDepartment);
        //set the id of the customer
        setId();
    }

    /**
     * Copy constructor for the customer class.
     * @param copyCustomer The customer to be copied.
     */
    Customer(Customer copyCustomer) {
        if (copyCustomer == null) {
            //let the user know the error and tell them where it is in the code
            System.out.print("Passing null object to copy, fatal error. [Customer --> Customer(Customer copyCustomer)]");
            //exit the program as a result of a fatal error.
            System.exit(0);
        }
        //set the name of the customer
        setName(copyCustomer.getName());
        //set the type of the customer
        setCustomerType(copyCustomer.getCustomerType());
        //set the department of the customer
        setCustomerDepartment(copyCustomer.getCustomerDepartment());
        //set the id of the customer
        setId();
    }

    //////////////
    /* SETTERS */
    ////////////

    /**
     * Sets the name of the customer.
     * @param name The name of the customer to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the type of the customer.
     * @param customerType The type of the customer to be set.
     */
    public void setCustomerType(type customerType) {
        this.customerType = customerType;
    }

    /**
     * Sets the customer department.
     * @param customerDepartment The department the customer is in.
     */
    public void setCustomerDepartment(String customerDepartment) {
        this.customerDepartment = customerDepartment;
    }

    //******************//
    /* SPECIAL SETTER */
    //*****************//

    /**
     * Sets the id of the customer by incrementing the idIncrementer variable.
     */
    public void setId() {
        this.id = ++idIncrementer;
    }

    //////////////
    /* GETTERS */
    ////////////

    /**
     * Gets the id of the customer.
     * @return The id as an integer.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the name of the customer.
     * @return The name as a string.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the type of customer.
     * @return The type of customer.
     */
    public type getCustomerType() {
        return customerType;
    }

    /**
     * Gets the department of the customer.
     * @return The department the customer belongs to.
     */
    public String getCustomerDepartment() {
        return customerDepartment;
    }

    ////////////////
    /* OVERRIDES */
    //////////////

    /**
     * Overrides default clone method in Object class.
     * @return Returns a clone of current instance object.
     * NOTE: Using copy constructor to create a clone.
     */
    @Override
    public Customer clone() {
        return new Customer(this);
    }

    /**
     * Overrides default toString method in Object class.
     * @return A string to represent the Device object.
     */
    @Override
    public String toString() {
        return "\nCustomer Name: "+name+"\n"+"Customer ID: "+customerId;
    }

    /**
     * Overrides default equals method in Object class.
     * @param obj The object to compare against current instance.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        Customer customer = (Customer) obj;
        return (this.getName() == customer.getName());
    }
}
