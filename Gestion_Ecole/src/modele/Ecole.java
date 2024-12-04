/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 * Declaration et implementtion de Ecole
 * @author coline
 */
public class Ecole {
        
    //Attributs
    private int id_ecole;
    private String nom_ecole;
    private String adresse_ecole;
    
    

    /**
     * Constructeur par defaut
     */
        public Ecole(){
        id_ecole=0;
        nom_ecole=null;
        adresse_ecole=null;
    }
    
    

    /**
     * Constructeur surcharge
     * @param id_ecole
     * @param nom_ecole
     * @param adresse_ecole
     */
        public Ecole(int id_ecole, String nom_ecole, String adresse_ecole){
        this.id_ecole=id_ecole;
        this.nom_ecole=nom_ecole;
        this.adresse_ecole=adresse_ecole;
    }
    
    //getter et setter

    /**
     *
     * @return
     */
        public int get_ID_Ecole(){
        return id_ecole;
    }
    
    /**
     *
     * @param id_ecole
     */
    public void set_ID_Ecole(int id_ecole){
        this.id_ecole=id_ecole;
    }
    
    /**
     *
     * @return
     */
    public String get_Nom_Ecole(){
        return nom_ecole;
    }
    
    /**
     *
     * @param nom_ecole
     */
    public void set_Nom_Ecole(String nom_ecole){
        this.nom_ecole=nom_ecole;
    }
    
    /**
     *
     * @return
     */
    public String get_Adresse_Ecole(){
        return adresse_ecole;
    }
    
    /**
     *
     * @param adresse_ecole
     */
    public void set_Adresse_Ecole(String adresse_ecole){
        this.adresse_ecole=adresse_ecole;
    }
    
    
}
