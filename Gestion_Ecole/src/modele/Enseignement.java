/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 * Declaration et implementation de Enseignement
 * @author coline
 */
public class Enseignement {
        
    //Attribut
    private int id_enseignement;
    private int id_classe;
    private int id_discipline;
    private int id_personne;
    private Classe classe;
    private Discipline discipline;
    private Personne personne;
    
    

    /**
     * Constructeur par defaut
     */
        public Enseignement(){
        id_enseignement=0;
        id_classe=0;
        id_discipline=0;
        id_personne=0;
    }
    
    

    /**
     * Constructeur surcharge
     * @param id_enseignement
     * @param id_classe
     * @param id_discipline
     * @param id_personne
     */
        public Enseignement(int id_enseignement, int id_classe, int id_discipline, int id_personne){
        this.id_enseignement=id_enseignement;
        this.id_classe=id_classe;
        this.id_discipline=id_discipline;
        this.id_personne=id_personne;
    }
    
    //getter et setter

    /**
     *
     * @return
     */
        public int get_ID_Enseignement(){
        return id_enseignement;
    }
    
    /**
     *
     * @param id_enseignement
     */
    public void set_ID_Enseignement(int id_enseignement){
        this.id_enseignement=id_enseignement;
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
    public int get_ID_Discipline() {
        return id_discipline;
    }

    /**
     *
     * @param id_discipline
     */
    public void set_ID_Discipline(int id_discipline) {
        this.id_discipline = id_discipline;
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
     * @param discipline
     */
    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
    
    /**
     *
     * @return
     */
    public Discipline getDiscipline(){
        return discipline;
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
}
