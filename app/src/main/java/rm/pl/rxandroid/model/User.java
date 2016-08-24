package rm.pl.rxandroid.model;

import java.util.Arrays;

/**
 * Created by rafal on 8/23/16.
 */
public class User {

    private String name;
    private String surname;
    private String email;
    private String phone;
    private Message[] messages;
    private Notification[] notifications;

    private User(Builder builder) {
        name = builder.name;
        surname = builder.surname;
        email = builder.email;
        phone = builder.phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", messages=" + Arrays.toString(messages) +
                ", notifications=" + Arrays.toString(notifications) +
                '}';
    }

    public static User makeOne() {
        return new User.Builder()
                .name("Mock")
                .surname("User")
                .email("test@makeOne.com")
                .phone("+971554303251")
                .build();
    }

    public Message[] getMessages() {
        return messages;
    }

    public Notification[] getNotifications() {
        return notifications;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMessages(Message[] messages) {
        this.messages = messages;
    }

    public void setNotifications(Notification[] notifications) {
        this.notifications = notifications;
    }

    public static final class Builder {
        private String name;
        private String surname;
        private String email;
        private String phone;

        public Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder surname(String val) {
            surname = val;
            return this;
        }

        public Builder email(String val) {
            email = val;
            return this;
        }

        public Builder phone(String val) {
            phone = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
