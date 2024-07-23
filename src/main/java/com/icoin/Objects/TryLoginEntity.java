package com.icoin.Objects;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "TryLogin", schema = "dbo", catalog = "Icoin")
public class TryLoginEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "Hash")
    private String hash;
    @Basic
    @Column(name = "IP")
    private String ip;
    @Basic
    @Column(name = "Date")
    private Date date;
    @Basic
    @Column(name = "UserID")
    private Integer userId;
    @Basic
    @Column(name = "Token")
    private String token;

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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TryLoginEntity that = (TryLoginEntity) o;

        if (id != that.id) return false;
        if (hash != null ? !hash.equals(that.hash) : that.hash != null) return false;
        if (ip != null ? !ip.equals(that.ip) : that.ip != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (token != null ? !token.equals(that.token) : that.token != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (hash != null ? hash.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        return result;
    }
}
