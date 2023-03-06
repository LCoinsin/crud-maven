package com.jee.crud.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "command")
public class Command {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "payment_status_command_id", referencedColumnName = "id")
    private PaymentStatusCommand paymentStatusCommand;

    @ManyToOne
    @JoinColumn(name = "book_isbn", referencedColumnName = "isbn")
    private Book book;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    // constructeurs, getters et setters

    public Command() {}

    public Command(PaymentStatusCommand paymentStatusCommand, Book book, int quantity, Customer customer) {
        this.paymentStatusCommand = paymentStatusCommand;
        this.book = book;
        this.quantity = quantity;
        this.customer = customer;
    }

    // getters et setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PaymentStatusCommand getPaymentStatusCommand() {
        return paymentStatusCommand;
    }

    public void setPaymentStatusCommand(PaymentStatusCommand paymentStatusCommand) {
        this.paymentStatusCommand = paymentStatusCommand;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
