/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 * Declaration et implementation de Classe
 * @author coline
 */
public class Classe {
        
    //Attributs
    private int id_classe;
    private String nom_classe;
    private int id_ecole;
    private int id_niveau;
    private int id_annee_scolaire;
    private int nb_eleves;
    private Annee_Scolaire annee_scolaire;
    private Ecole ecole;
    private Niveau niveau;
    
    

    /**
     * Constructeur par defaut
     */
        public Classe(){
        id_classe=0;
        nom_classe=null;
        id_ecole=0;
        id_niveau=0;
        id_annee_scolaire=0;
    }
    
    

    /**
     * Constructeur surcharge
     * @param id_classe
     * @param nom_classe
     * @param id_ecole
     * @param id_niveau
     * @param id_annee_scolaire
     */
        public Classe(int id_classe, String nom_classe, int id_ecole, int id_niveau, int id_annee_scolaire){
        this.id_classe=id_classe;
        this.nom_classe=nom_classe;
        this.id_ecole=id_ecole;
        this.id_niveau=id_niveau;
        this.id_annee_scolaire=id_annee_scolaire;
        nb_eleves=0;
    }
    
    //getter et setter

    /**
     *
     * @return
     */
        public int get_ID_Classe(){
        return id_classe;
    }
    
    /**
     *
     * @param id_classe
     */
    public void set_ID_Classe(int id_classe){
        this.id_classe=id_classe;
    }
    
    /**
     *
     * @return
     */
    public String get_Nom_Classe(){
        return nom_classe;
    }
    
    /**
     *
     * @param nom_classe
     */
    public void set_Nom_Classe(String nom_classe){
        this.nom_classe=nom_classe;
    }

    /**
     *
     * @return
     */
    public int get_ID_Ecole() {
        return id_ecole;
    }

    /**
     *
     * @param id_ecole
     */
    public void set_ID_Ecole(int id_ecole) {
        this.id_ecole = id_ecole;
    }

    /**
     *
     * @return
     */
    public int get_ID_Niveau() {
        return id_niveau;
    }

    /**
     *
     * @param id_niveau
     */
    public void set_ID_Niveau(int id_niveau) {
        this.id_niveau = id_niveau;
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
     * @param ecole
     */
    public void setEcole(Ecole ecole) {
        this.ecole = ecole;
    }
    
    /**
     *
     * @param niveau
     */
    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }
    
    /**
     *
     * @return
     */
    public Annee_Scolaire getAnneeScolaire() {
        return annee_scolaire;
    }
    
    /**
     *
     * @return
     */
    public Ecole getEcole() {
        return ecole;
    }
    
    /**
     *
     * @return
     */
    public Niveau getNiveau() {
        return niveau;
    }
    
    /**
     *
     * @return
     */
    public int getNbEleves() {
        return nb_eleves;
    }
    
    /**
     *
     * @param nb_eleves
     */
    public void setNbEleves(int nb_eleves) {
        this.nb_eleves = nb_eleves;
    }
}
