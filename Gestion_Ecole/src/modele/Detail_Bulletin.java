/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 * Declaration et implementation de Detail Bulletin
 * @author coline
 */
public class Detail_Bulletin {
    
    //Attributs
    private int id_detail_bulletin;
    private int id_bulletin;
    private int id_enseignement;
    private float moyenne_matiere_eleve;
    private float moyenne_matiere_classe;
    private String appreciation_detail;
    private Bulletin bulletin;
    private Enseignement enseignement;
    
    

    /**
     * Constructeur par defaut
     */
        public Detail_Bulletin(){
        id_detail_bulletin=0;
        appreciation_detail=null;
        id_bulletin=0;
        id_enseignement=0;
        moyenne_matiere_eleve=0;
        moyenne_matiere_classe=0;
    }
    
    

    /**
     * Constructeur surcharge
     * @param id_detail_bulletin
     * @param id_bulletin
     * @param id_enseignement
     * @param appreciation_detail
     */
        public Detail_Bulletin(int id_detail_bulletin, int id_bulletin, int id_enseignement, String appreciation_detail){
        this.id_detail_bulletin=id_detail_bulletin;
        this.appreciation_detail=appreciation_detail;
        this.id_bulletin=id_bulletin;
        this.id_enseignement=id_enseignement;
        moyenne_matiere_eleve=0;
        moyenne_matiere_classe=0;
    }
    
    //getter et setter

    /**
     *
     * @return
     */
        public int get_ID_Detail(){
        return id_detail_bulletin;
    }
    
    /**
     *
     * @param id_detail_bulletin
     */
    public void set_ID_Detail(int id_detail_bulletin){
        this.id_detail_bulletin=id_detail_bulletin;
    }
    
    /**
     *
     * @return
     */
    public String get_Appreciation_Detail(){
        return appreciation_detail;
    }
    
    /**
     *
     * @param appreciation_detail
     */
    public void set_Appreciation_Detail(String appreciation_detail){
        this.appreciation_detail=appreciation_detail;
    }

    /**
     *
     * @return
     */
    public int get_ID_Bulletin() {
        return id_bulletin;
    }

    /**
     *
     * @param id_bulletin
     */
    public void set_ID_Bulletin(int id_bulletin) {
        this.id_bulletin = id_bulletin;
    }

    /**
     *
     * @return
     */
    public int get_ID_Enseignement() {
        return id_enseignement;
    }

    /**
     *
     * @param id_enseignement
     */
    public void set_ID_Enseignement(int id_enseignement) {
        this.id_enseignement = id_enseignement;
    }

    /**
     *
     * @param bulletin
     */
    public void setBulletin(Bulletin bulletin) {
        this.bulletin = bulletin;
    }
    
    /**
     *
     * @return
     */
    public Bulletin getBulletin(){
        return bulletin;
    }
    
    /**
     *
     * @param enseignement
     */
    public void setEnseignement(Enseignement enseignement) {
        this.enseignement = enseignement;
    }
    
    /**
     *
     * @return
     */
    public Enseignement getEnseignement(){
        return enseignement;
    }
    
    /**
     *
     * @param moyenne_matiere_eleve
     */
    public void setMoyenneEleve(float moyenne_matiere_eleve) {
        this.moyenne_matiere_eleve = moyenne_matiere_eleve;
    }
    
    /**
     *
     * @return
     */
    public float getMoyenneEleve(){
        return moyenne_matiere_eleve;
    }
    
    /**
     *
     * @param moyenne_matiere_classe
     */
    public void setMoyenneClasse(float moyenne_matiere_classe) {
        this.moyenne_matiere_classe = moyenne_matiere_classe;
    }
    
    /**
     *
     * @return
     */
    public float getMoyenneClasse(){
        return moyenne_matiere_classe;
    }
}
