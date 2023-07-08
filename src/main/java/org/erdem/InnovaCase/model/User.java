package org.erdem.InnovaCase.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Document(collection = "users")
public class User implements Serializable{
  
  private static final long serialVersionUID = 1L;

  @Id
  private String id;
  private String firstName;
  private String lastName;
  private String email;



  private double totalSpending;
  private String password;
  private String role;
  private boolean isActive;


    @DBRef
  private List<Transaction> transactions = new ArrayList<>();


  public User() {
  }

  public User(String firstName,String lastName,String email, String password) {
	this.firstName=firstName;
	this.lastName=lastName;
    this.email = email;
    this.password = password;
    this.role="USER";
    this.isActive = true;
    this.totalSpending = 0;
  }


    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
	return firstName;
  }
	
  public void setFirstName(String firstName) {
	this.firstName = firstName;
  }
	
  public String getLastName() {
	  return lastName;
  }
	
  public void setLastName(String lastName) {
	this.lastName = lastName;
  }
	
  public String getRole() {
	return role;
  }
	
  public void setRole(String role) {
	this.role = role;
  }
	
  public boolean getIsActive() {
	return isActive;
  }

  public void setIsActive(boolean isActive) {
	this.isActive = isActive;
  }

  public double getTotalSpending() {
    return totalSpending;
  }

  public void setTotalSpending(double totalSpending) {
    this.totalSpending = totalSpending;
  }

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password="
			+ password + ", role=" + role + ", isActive=" + isActive + "totalSpending=" + totalSpending+ "]";
	}



}