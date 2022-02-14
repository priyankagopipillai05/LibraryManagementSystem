package com.library.management.userRegistrationService.model;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;


@Component
    public class User {

        private Integer id;

        @NotBlank(message = "Name is Mandatory")
        private String name;

        private String address;

        @NotBlank(message ="Give valid email id")
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

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEmail_id() {
            return email_id;
        }

        public void setEmail_id(String email_id) {
            this.email_id = email_id;
        }

        public String getVerification_id() {
            return verification_id;
        }

        public void setVerification_id(String verification_id) {
            this.verification_id = verification_id;
        }

        public String getPhone_number() {
            return phone_number;
        }

        public void setPhone_number(String phone_number) {
            this.phone_number = phone_number;
        }
    }


