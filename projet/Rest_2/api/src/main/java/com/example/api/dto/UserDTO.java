package com.example.api.dto;

public class UserDTO {
    

    private Integer id;
      private Integer admin;
    private String mdp;
    private Integer solde;
    private String user_name;


    public UserDTO() {
    }


    public UserDTO(Integer id, Integer admin, String mdp, Integer solde, String user_name) {
        this.id = id;
        this.admin = admin;
        this.mdp = mdp;
        this.solde = solde;
        this.user_name = user_name;
    }
    

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdmin() {
        return this.admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public String getMdp() {
        return this.mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Integer getSolde() {
        return this.solde;
    }

    public void setSolde(Integer solde) {
        this.solde = solde;
    }

    public String getUser_name() {
        return this.user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

  
}
