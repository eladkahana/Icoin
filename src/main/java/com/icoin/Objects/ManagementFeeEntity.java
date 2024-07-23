package com.icoin.Objects;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "ManagementFee", schema = "dbo", catalog = "Icoin")
public class ManagementFeeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "Date")
    private Date date;
    @Basic
    @Column(name = "Amount")
    private double amount;
    @Basic
    @Column(name = "UserID")
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

        ManagementFeeEntity that = (ManagementFeeEntity) o;

        if (id != that.id) return false;
        if (Double.compare(amount, that.amount) != 0) return false;
        if (userId != that.userId) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + userId;
        return result;
    }
}
