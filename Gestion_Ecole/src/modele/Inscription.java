/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 * Declaration et implementation de Inscription
 * @author coline
 */
public class Inscription {
        
    //Attributs
    private int id_inscription;
    private int id_classe;
    private int id_personne;
    private Classe classe;
    private Personne personne;
    
    

    /**
     * Constructeur par defaut
     */
        public Inscription(){
        id_inscription=0;
        id_classe=0;
        id_personne=0;
    }
    
    

    /**
     * Constructeur surcharge
     * @param id_inscription
     * @param id_classe
     * @param id_personne
     */
        public Inscription(int id_inscription, int id_classe, int id_personne){
        this.id_inscription=id_inscription;
        this.id_classe=id_classe;
        this.id_personne=id_personne;
    }
    
    //getter et setter

    /**
     *
     * @return
     */
        public int get_ID_Inscription(){
        return id_inscription;
    }
    
    /**
     *
     * @param id_inscription
     */
    public void set_ID_Inscription(int id_inscription){
        this.id_inscription=id_inscription;
    }

    /**
     *
     * @return
     */
    public int get_ID_Classe() {
        return id_classe;
    }

    /**
     *
     * @param id_classe
     */
    public void set_ID_Classe(int id_classe) {
        this.id_classe = id_classe;
    }

    /**
     *
     * @return
     */
    public int get_ID_Personne() {
        return id_personne;
    }

    /**
     *
     * @param id_personne
     */
    public void set_ID_Personne(int id_personne) {
        this.id_personne = id_personne;
    }
 
    /**
     *
     * @param classe
     */
    public void setClasse(Classe classe) {
        this.classe = classe;
    }
    
    /**
     *
     * @return
     */
    public Classe getClasse(){
        return classe;
    }
    
    /**
     *
     * @param personne
     */
    public void setPersonne(Personne personne) {
        this.personne = personne;
    }
    
    /**
     *
     * @return
     */
    public Personne getPersonne(){
        return personne;
    }
}
