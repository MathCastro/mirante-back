package com.example.mirante.phone;

import com.example.mirante.operator.Operator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_phone")
@JsonIgnoreProperties(value = { "creationDate" }, allowGetters = true)
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private String ddd;

    @Column
    private String number;

    @Column
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "id_operator")
    private Operator operator;

    @PrePersist
    protected void onCreate() {
        creationDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        creationDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }
}
