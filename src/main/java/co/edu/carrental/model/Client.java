package co.edu.carrental.model;

import java.time.LocalDate;

public class Client {
    private String fullName;
    private String id;
    private String phone;
    private String email;
    private String age;
    private LocalDate createAt;

    // constructor
    private Client(Builder builder) {
        this.fullName = builder.fullName;
        this.id = builder.id;
        this.phone = builder.phone;
        this.email = builder.email;
        this.age = builder.age;
        this.createAt = builder.createAt;
    }

    //Getters
    public String getFullName(){
        return fullName;
    }

    public String getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAge() {
        return age;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public static class Builder {
        private String fullName;
        private String id;
        private String phone;
        private String email;
        private String age;
        private LocalDate createAt;

        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder age(String age) {
            this.age = age;
            return this;
        }

        public Builder createAt(LocalDate createAt) {
            this.createAt = createAt;
            return this;
        }

        public Client build() {
            if (fullName == null || id == null || phone == null || email == null || age == null || createAt == null) {
                throw new IllegalStateException("Full name, identification, phone, email, age, createdAt are required to be able to create a patient");
            }

            return new Client(this);
        }
    }
}
