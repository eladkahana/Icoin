package com.icoin.Objects;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "Dividends", schema = "dbo", catalog = "Icoin")
public class DividendsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "Ticker")
    private String ticker;
    @Basic
    @Column(name = "Amount")
    private double amount;
    @Basic
    @Column(name = "Date")
    private Date date;
    @Basic
    @Column(name = "UserID")
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DividendsEntity that = (DividendsEntity) o;

        if (id != that.id) return false;
        if (Double.compare(amount, that.amount) != 0) return false;
        if (userId != that.userId) return false;
        if (ticker != null ? !ticker.equals(that.ticker) : that.ticker != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (ticker != null ? ticker.hashCode() : 0);
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + userId;
        return result;
    }
}
