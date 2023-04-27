package models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "lots")
public class Lot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lot_id")
    private Integer lotId;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_owner_id")
    private User userOwner;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "lot", fetch = FetchType.EAGER, orphanRemoval = true) // was LAZY
    // @JoinColumn(name = "property_id") // несет ответственность за отношения
    private LotProperty property;

    @Column(name = "sold_until")
    private Date soldUntil;

    @Column(name = "min_price")
    private Double minPrice;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "last_customer_id")
    private User lastCustomer;

    @Column(name = "current_price")
    private Double currentPrice;

    public Lot() {
        this.property = new LotProperty(this);
    }

    public Integer getLotId() {
        return lotId;
    }

    public User getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(User userOwner) {
        this.userOwner = userOwner;
    }

    public LotProperty getProperty() {
        return property;
    }
    public void setProperty(LotProperty property) {
        this.property = property;
    }

    public Date getSoldUntil() {
        return soldUntil;
    }

    public void setSoldUntil(Date soldUntil) {
        this.soldUntil = soldUntil;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public User getLastCustomer() {
        return lastCustomer;
    }

    public void setLastCustomer(User lastCustomer) {
        this.lastCustomer = lastCustomer;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    // @Override
    // public String toString() {
    //     return "Lot{" +
    //             "lotId=" + lotId +
    //             ", userOwner=" + userOwner.getUserId() +
    //             ", property=" + property +
    //             ", soldUntil=" + soldUntil +
    //             ", minPrice=" + minPrice +
    //             ", lastCustomer=" + (lastCustomer == null ? "not" : lastCustomer.getUserId()) +
    //             ", currentPrice=" + currentPrice +
    //             '}';
    // }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lot lot = (Lot) o;
        return Objects.equals(getLotId(), lot.getLotId()) && Objects.equals(getUserOwner(), lot.getUserOwner()) && Objects.equals(getProperty(), lot.getProperty()) && Objects.equals(getSoldUntil(), lot.getSoldUntil()) && Objects.equals(getMinPrice(), lot.getMinPrice()) && Objects.equals(getLastCustomer(), lot.getLastCustomer()) && Objects.equals(getCurrentPrice(), lot.getCurrentPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLotId(), getSoldUntil(), getMinPrice());
    }
}