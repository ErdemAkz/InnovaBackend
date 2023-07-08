package org.erdem.InnovaCase.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "transactions")
public class Transaction implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String fromWho;
    private String toWho;
    private double amount;
    private LocalDate localDate;


    public Transaction() {
    }

    public Transaction(String fromWho,String toWho, double amount) {
        this.fromWho=fromWho;
        this.toWho=toWho;
        this.amount = amount;
        this.localDate = LocalDate.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFromWho() {
        return fromWho;
    }

    public void setFromWho(String fromWho) {
        this.fromWho = fromWho;
    }

    public String getToWho() {
        return toWho;
    }

    public void setToWho(String toWho) {
        this.toWho = toWho;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", fromWho='" + fromWho + '\'' +
                ", toWho='" + toWho + '\'' +
                ", amount=" + amount + '\'' +
                ", date=" + localDate +
                '}';
    }


}
