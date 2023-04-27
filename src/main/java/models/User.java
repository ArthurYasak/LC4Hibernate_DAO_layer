package models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;

    @Column(name = "user_balance")
    private Double userBalance;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_owner_id")
    private List<Lot> userLots;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "last_customer_id")
    private List<Lot> lastCustomerIn;

    public User() {

    }

    public User(UserType userType) {
        this.userType = userType;
        userLots = new ArrayList<>();
        lastCustomerIn = new ArrayList<>();
    }

    public void addUserLot(Lot lot) {
        lot.setUserOwner(this);
        userLots.add(lot);
    }

    public void removeUserLot(Lot lot) {
        userLots.remove(lot);
    }

    public void stayLastCustomerIn(Lot lot) {
        lot.setLastCustomer(this);
        lastCustomerIn.add(lot);
    }

    public void stopLastCustomerIn(Lot lot) {
        lastCustomerIn.remove(lot);
    }

    public Integer getUserId() {
        return userId;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Double getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(Double userBalance) {
        this.userBalance = userBalance;
    }

    public List<Lot> getUserLots() {
        return userLots;
    }

    public void setUserLots(List<Lot> userLots) {
        this.userLots = userLots;
    }

    public List<Lot> getLastCustomerIn() {
        return lastCustomerIn;
    }

    public void setLastCustomerIn(List<Lot> lastCustomerIn) {
        this.lastCustomerIn = lastCustomerIn;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userType=" + userType +
                ", userBalance=" + userBalance +
                ", userLots=" + userLots +
                ", lastCustomerIn=" + lastCustomerIn +
                '}' + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getUserId(), user.getUserId()) && getUserType() == user.getUserType() && Objects.equals(getUserBalance(), user.getUserBalance()) && Objects.equals(getUserLots(), user.getUserLots()) && Objects.equals(getLastCustomerIn(), user.getLastCustomerIn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getUserType(), getUserBalance());
    }
}
