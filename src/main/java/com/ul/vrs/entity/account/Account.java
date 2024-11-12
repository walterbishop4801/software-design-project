package com.ul.vrs.entity.account;

public interface Account {
    void handle(Customer customer);

    // Active state implementation
    public static class Active implements Account {
        @Override
        public void handle(Customer customer) {
            System.out.println("Account is active for customer: " + customer.getCustomerId());
        }
    }

    // Suspended state implementation
    public static class Suspended implements Account {
        @Override
        public void handle(Customer customer) {
            System.out.println("Account is suspended for customer: " + customer.getCustomerId());
        }
    }

    // Closed state implementation
    public static class Closed implements Account {
        @Override
        public void handle(Customer customer) {
            System.out.println("Account is closed for customer: " + customer.getCustomerId());
        }
    }
}
