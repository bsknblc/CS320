package com.OzUFlix.CS320.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_AVAILABLE")
public class Available {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @Column(name = "INFO")
    @NotNull
    private int info;

    public Available(){}

    public Available(int info) {
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

}
