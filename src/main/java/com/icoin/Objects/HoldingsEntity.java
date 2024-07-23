package com.icoin.Objects;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "Holdings", schema = "dbo", catalog = "Icoin")
public class HoldingsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "Ticket")
    private String ticket;
    @Basic
    @Column(name = "AmountOfShares")
    private double amountOfShares;
    @Basic
    @Column(name = "InvestAmount")
    private double investAmount;
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

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public double getAmountOfShares() {
        return amountOfShares;
    }

    public void setAmountOfShares(double amountOfShares) {
        this.amountOfShares = amountOfShares;
    }

    public double getInvestAmount() {
        return investAmount;
    }

    public void setInvestAmount(double investAmount) {
        this.investAmount = investAmount;
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

        HoldingsEntity that = (HoldingsEntity) o;

        if (id != that.id) return false;
        if (Double.compare(amountOfShares, that.amountOfShares) != 0) return false;
        if (Double.compare(investAmount, that.investAmount) != 0) return false;
        if (userId != that.userId) return false;
        if (ticket != null ? !ticket.equals(that.ticket) : that.ticket != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (ticket != null ? ticket.hashCode() : 0);
        temp = Double.doubleToLongBits(amountOfShares);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(investAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + userId;
        return result;
    }
}
