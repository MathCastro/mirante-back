package com.example.mirante.phone;

import com.example.mirante.operator.Operator;
import com.example.mirante.person.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @NonNull
    @Enumerated(EnumType.ORDINAL)
    @Column
    private PhoneType type;

    @Column
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "id_operator")
    private Operator operator;

    @JsonIgnore
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "TB_PHONE_PERSON",
            joinColumns = @JoinColumn(name = "id_phone"),
            inverseJoinColumns = @JoinColumn(name = "id_person"))
    private List<Person> persons;

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

    @NonNull
    public PhoneType getType() {
        return type;
    }

    public void setType(@NonNull PhoneType phoneType) {
        this.type = phoneType;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
