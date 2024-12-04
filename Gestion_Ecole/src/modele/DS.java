/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 * Classe DS
 * @author LOIC
 */
public class DS {
    private int id_ds;
    private String nom_ds;
    
    

    /**
     * Constructeur par defaut
     */
        public DS(){
        id_ds=0;
        nom_ds="";
    }
    
    

    /**
     * Constructeur surcharge
     * @param id_ds
     * @param nom_ds
     */
        public DS(int id_ds, String nom_ds){
        this.id_ds=id_ds;
        this.nom_ds=nom_ds;
    }
    
    //getter et setter

    /**
     *
     * @return
     */
        public int get_ID_DS(){
        return id_ds;
    }
    
    /**
     *
     * @param id_ds
     */
    public void set_ID_DS(int id_ds){
        this.id_ds=id_ds;
    }
    
    /**
     *
     * @return
     */
    public String get_Nom_DS(){
        return nom_ds;
    }
    
    /**
     *
     * @param nom_ds
     */
    public void set_Nom_Discipline(String nom_ds){
        this.nom_ds=nom_ds;
    }
    
    
}
