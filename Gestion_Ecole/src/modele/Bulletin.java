/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 * Bulletin Global
 * @author coline
 * 
 */
public class Bulletin {
       
    //Attributs
    private int id_bulletin;
    private int id_trimestre;
    private int id_inscription;
    private String appreciation_bulletin;
    private float moyenne_generale_eleve;
    private float moyenne_generale_classe;
    private Trimestre trimestre;
    private Inscription inscription;
    
    

    /**
     * Constructeur par defaut
     */
        public Bulletin(){
        id_bulletin=0;
        appreciation_bulletin=null;
        moyenne_generale_eleve=0;
        moyenne_generale_classe=0;
        id_trimestre=0;
        id_inscription=0;
    }
    
    

    /**
     * Constructeur surcharge
     * @param id_bulletin
     * @param id_trimestre
     * @param id_inscription
     * @param appreciation_bulletin
     */
        public Bulletin(int id_bulletin, int id_trimestre, int id_inscription, String appreciation_bulletin){
        
        this.id_bulletin=id_bulletin;
        this.appreciation_bulletin=appreciation_bulletin;
        this.id_trimestre=id_trimestre;
        this.id_inscription=id_inscription;
        moyenne_generale_eleve=0;
        moyenne_generale_classe=0;
    }
    
    //getter et setter

    /**
     *
     * @return id_bulletin
     */
        public int get_ID_Bulletin(){
        return id_bulletin;
    }
    
    /**
     *
     * @param id_bulletin
     */
    public void set_ID_Bulletin(int id_bulletin){
        this.id_bulletin=id_bulletin;
    }
    
    /**
     *
     * @return appreciation_bulletin
     */
    public String get_Appreciation_Bulletin(){
        return appreciation_bulletin;
    }
    
    /**
     *
     * @param appreciation_bulletin
     */
    public void set_Appreciation_Bulletin(String appreciation_bulletin){
        this.appreciation_bulletin=appreciation_bulletin;
    }
    
    /**
     *
     * @return moyenne_generale_eleve
     */
    public float getMoyenneGeneraleEleve(){
        return moyenne_generale_eleve;
    }
    
    /**
     *
     * @param moyenne
     */
    public void setMoyenneGeneraleEleve(float moyenne){
        this.moyenne_generale_eleve=moyenne;
    }
    
    /**
     *
     * @return moyenne_generale_classe
     */
    public float getMoyenneGeneraleClasse(){
        return moyenne_generale_classe;
    }
    
    /**
     *
     * @param moyenne
     */
    public void setMoyenneGeneraleClasse(float moyenne){
        this.moyenne_generale_classe=moyenne;
    }

    /**
     *
     * @return
     */
    public int get_ID_Trimestre() {
        return id_trimestre;
    }

    /**
     *
     * @param id_trimestre
     */
    public void set_ID_Trimestre(int id_trimestre) {
        this.id_trimestre = id_trimestre;
    }

    /**
     *
     * @return
     */
    public int get_ID_Inscription() {
        return id_inscription;
    }

    /**
     *
     * @param id_inscription
     */
    public void set_ID_Inscription(int id_inscription) {
        this.id_inscription = id_inscription;
    }
    
    /**
     *
     * @param trimestre
     */
    public void setTrimestre(Trimestre trimestre) {
        this.trimestre = trimestre;
    }
    
    /**
     *
     * @return
     */
    public Trimestre getTrimestre(){
        return trimestre;
    }
    
    /**
     *
     * @param inscription
     */
    public void setInscription(Inscription inscription) {
        this.inscription = inscription;
    }
    
    /**
     *
     * @return
     */
    public Inscription getInscription(){
        return inscription;
    }
    
}
