package com.example.api.dto;

import java.sql.Date;

public class CommandeDTO {
    private Integer id;
    private Date date;
    private Integer fk_nerf;
    private Integer fk_user;

    public CommandeDTO(){}


    public CommandeDTO(Integer id, Date date, Integer fk_nerf, Integer fk_user) {
        this.id = id;
        this.date = date;
        this.fk_nerf = fk_nerf;
        this.fk_user = fk_user;
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getFk_nerf() {
        return this.fk_nerf;
    }

    public void setFk_nerf(Integer fk_nerf) {
        this.fk_nerf = fk_nerf;
    }

    public Integer getFk_user() {
        return this.fk_user;
    }

    public void setFk_user(Integer fk_user) {
        this.fk_user = fk_user;
    }

    
}
