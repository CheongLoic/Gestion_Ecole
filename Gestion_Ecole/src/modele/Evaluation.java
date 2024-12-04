/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 * Declaration et implementation de Evaluation
 * @author coline 
 * @author loic
 */
public class Evaluation {
        
    //Attributs
    private int id_evaluation;
    private int id_detail_bulletin;
    private int id_ds;
    private float note_evaluation;
    private float moyenne_evaluation;
    private int coeff;
    private String appreciation_evaluation;
    private Detail_Bulletin db;
    private DS ds;
    
    

    /**
     * Constructeur par defaut
     */
        public Evaluation(){
        id_evaluation=0;
        note_evaluation=0;
        appreciation_evaluation=null;
        id_detail_bulletin=0;
    }
    
    

    /**
     * Constructeur surcharge
     * @param id_evaluation
     * @param id_detail_bulletin
     * @param id_ds
     * @param note_evaluation
     * @param coeff
     * @param appreciation_evaluation
     */
        public Evaluation(int id_evaluation, int id_detail_bulletin,int id_ds,float note_evaluation,int coeff, String appreciation_evaluation){
        this.id_evaluation=id_evaluation;
        this.id_detail_bulletin=id_detail_bulletin;
        this.id_ds=id_ds;
        this.note_evaluation=note_evaluation;
        this.coeff=coeff;
        this.appreciation_evaluation=appreciation_evaluation;
    }
    
    //getter et setter

    /**
     *
     * @return
     */
        public int get_ID_Evaluation(){
        return id_evaluation;
    }
    
    /**
     *
     * @param id_evaluation
     */
    public void set_ID_Evaluation(int id_evaluation){
        this.id_evaluation=id_evaluation;
    }
    
    /**
     *
     * @return
     */
    public float get_Note_Evaluation(){
        return note_evaluation;
    }
    
    /**
     *
     * @param note_evaluation
     */
    public void set_Note_Evaluation(float note_evaluation){
        this.note_evaluation=note_evaluation;
    }
    
    /**
     *
     * @return
     */
    public String get_Appreciation_Evaluation(){
        return appreciation_evaluation;
    }
    
    /**
     *
     * @param appreciation_evaluation
     */
    public void set_Appreciation_Evaluation(String appreciation_evaluation){
        this.appreciation_evaluation=appreciation_evaluation;
    }

    /**
     *
     * @return
     */
    public int get_ID_Detail_Bulletin() {
        return id_detail_bulletin;
    }

    /**
     *
     * @param id_detail_bulletin
     */
    public void set_ID_Detail_Bulletin(int id_detail_bulletin) {
        this.id_detail_bulletin = id_detail_bulletin;
    }
    
    /**
     *
     * @param db
     */
    public void setDetailBulletin(Detail_Bulletin db) {
        this.db = db;
    }
    
    /**
     *
     * @return
     */
    public Detail_Bulletin getDetailBulletin(){
        return db;
    }
    
    /**
     *
     * @param ds
     */
    public void setDS(DS ds) {
        this.ds = ds;
    }
    
    /**
     *
     * @return
     */
    public DS getDS(){
        return ds;
    }
    
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
    public void set_ID_Ds(int id_ds){
        this.id_ds=id_ds;
    }
    
    /**
     *
     * @return
     */
    public int getCoeff(){
        return coeff;
    }
    
    /**
     *
     * @param coeff
     */
    public void setCoeff(int coeff){
        this.coeff=coeff;
    }
    
    /**
     *
     * @return
     */
    public float getMoyenneEvaluation(){
        return moyenne_evaluation;
    }
    
    /**
     *
     * @param moyenne_evaluation
     */
    public void setMoyenneEvaluation(float moyenne_evaluation){
        this.moyenne_evaluation=moyenne_evaluation;
    }

}
