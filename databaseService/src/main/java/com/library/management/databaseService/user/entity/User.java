package com.library.management.databaseService.user.entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name="user_details")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    private String name;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @Email
    private String email_id;


    private String verification_id;


    private  String phone_number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName( String name) {
        this.name = name;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress( String address) {
        this.address = address;
    }


    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id( String email_id) {
        this.email_id = email_id;
    }


    public String getVerification_id() {
        return verification_id;
    }

    public void setVerification_id( String verification_id) {
        this.verification_id = verification_id;
    }


    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number( String phone_number) {
        this.phone_number = phone_number;
    }
}
