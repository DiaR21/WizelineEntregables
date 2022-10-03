package com.wizeline.PDICENIO.builder;


public class User{

    private String user;
    private String password;
    private String phone;

    public User(UserBuilder builder) {
        this.user = builder.user;
        this.password = builder.password;
        this.phone = builder.phone;
    }

    public String getUser() {
        return user;
    }
    public String getPassword() {
        return password;
    }
    public String getPhone() {
        return phone;
    }


    public String toString() {
        return "User: "+this.user+", "+this.password+", "+this.phone;
    }
    public static final class UserBuilder {
        private String user;
        private String password;
        private String phone;


        public UserBuilder() {
        }

        public UserBuilder user(String user) {
            this.user = user;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public User build() {
            User user =  new User(this);
            validateUserObject(user);
            return user;
        }
        private void validateUserObject(User user) {

        }
    }
}