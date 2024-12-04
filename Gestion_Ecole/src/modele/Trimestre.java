/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.Date;

/**
 * Declaration et implementation de Trimestre
 * @author coline
 * @author loic
 */
public class Trimestre {
    
    //Attributs
    private int id_trimestre;
    private int numero_trimestre;
    private Date date_debut;
    private Date date_fin;
    private int id_annee_scolaire;
    private Annee_Scolaire annee_scolaire;
    
    
    

    /**
     * Constructeur par defaut
     */
        public Trimestre(){
        id_trimestre=0;
        numero_trimestre=0;
        date_debut=null;
        date_fin=null;
        id_annee_scolaire=0;
    }
    
    

    /**
     * Constructeur surcharge
     * @param id_trimestre
     * @param numero_trimestre
     * @param date_debut
     * @param date_fin
     * @param id_annee_scolaire
     */
        public Trimestre(int id_trimestre, int numero_trimestre, Date date_debut, Date date_fin, int id_annee_scolaire){
        this.id_trimestre=id_trimestre;
        this.numero_trimestre=numero_trimestre;
        this.date_debut=date_debut;
        this.date_fin=date_fin;
        this.id_annee_scolaire=id_annee_scolaire;
    }
    
    //getter et setter

    /**
     *
     * @return
     */
        public int get_ID_Trimestre(){
        return id_trimestre;
    }
    
    /**
     *
     * @param id_trimestre
     */
    public void set_ID_Trimestre(int id_trimestre){
        this.id_trimestre=id_trimestre;
    }
    
    /**
     *
     * @return
     */
    public int get_Numero_Trimestre(){
        return numero_trimestre;
    }
    
    /**
     *
     * @param numero_trimestre
     */
    public void set_Numero_Trimestre(int numero_trimestre){
        this.numero_trimestre=numero_trimestre;
    }
    
    /**
     *
     * @return
     */
    public Date get_Date_Debut(){
        return date_debut;
    }
    
    /**
     *
     * @param date_debut
     */
    public void set_Date_Debut(Date date_debut){
        this.date_debut=date_debut;
    }
    
    /**
     *
     * @return
     */
    public Date get_Date_Fin(){
        return date_fin;
    }
    
    /**
     *
     * @param date_fin
     */
    public void set_Date_Fin(Date date_fin){
        this.date_fin=date_fin;
    }

    /**
     *
     * @return
     */
    public int get_ID_Annee_Scolaire() {
        return id_annee_scolaire;
    }

    /**
     *
     * @param id_annee_scolaire
     */
    public void set_ID_Annee_Scolaire(int id_annee_scolaire) {
        this.id_annee_scolaire = id_annee_scolaire;
    }
    
    /**
     *
     * @param annee_scolaire
     */
    public void setAnneeScolaire(Annee_Scolaire annee_scolaire) {
        this.annee_scolaire = annee_scolaire;
    }
    
    /**
     *
     * @return
     */
    public Annee_Scolaire getAnneeScolaire(){
        return annee_scolaire;
    }
    
}
