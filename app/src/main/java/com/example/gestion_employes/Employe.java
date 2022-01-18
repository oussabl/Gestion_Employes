package com.example.gestion_employes;

public class Employe {


    private String  nom ;
    private int  id ;
    private String  prenom ;
    private String tel ;
    private String  date_depart ;
    private String  mission ;
    private String  date_arrive ;

    public Employe(int id, String nom, String prenom, String tel,String mission, String date_depart,  String date_arrive) {

        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.mission = mission;
        this.date_depart = date_depart;
        this.date_arrive = date_arrive;
    }
    public Employe(String nom, String prenom, String tel , String mission, String date_depart, String date_arrive) {

        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.mission = mission;
        this.date_depart = date_depart;
        this.date_arrive = date_arrive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    @Override
    public String toString() {
        return "Employes{   "+id+" " +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", tel=" + tel +
                ", mission=" + mission +
                ", date_depart='" + date_depart + '\'' +
                ", date_arrive='" + date_arrive + '\'' +
                '}';
    }



    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tele) {
        this.tel = tele;
    }

    public String getDate_depart() {
        return date_depart;
    }

    public void setDate_depart(String date_depart) {
        this.date_depart = date_depart;
    }

    public String getDate_arrive() {
        return date_arrive;
    }

    public void setDate_arrive(String date_arrive) {
        this.date_arrive = date_arrive;
    }
}


