package com.icoin.Objects;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "Passwords", schema = "dbo", catalog = "Icoin")
public class PasswordsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "Hash")
    private String hash;
    @Basic
    @Column(name = "UserID")
    private int userId;
    @Basic
    @Column(name = "Date")
    private Date date;
    @Basic
    @Column(name = "IsActive")
    private boolean isActive;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PasswordsEntity that = (PasswordsEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (isActive != that.isActive) return false;
        if (hash != null ? !hash.equals(that.hash) : that.hash != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (hash != null ? hash.hashCode() : 0);
        result = 31 * result + userId;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        return result;
    }
}
