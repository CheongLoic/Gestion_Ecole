/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 * Declaration et implementation de Personne
 * @author coline
 */
public class Personne {
        
    //Attributs
    private int id_personne;
    private String civilite;
    private String nom_personne;
    private String prenom_personne;
    private String type_personne;
    private String login;
    private String mdp;
    
    

    /**
     * Constructeur par defaut
     */
        public Personne(){
        id_personne=0;
        nom_personne=null;
        prenom_personne=null;
        type_personne=null;
    }
    
    

    /**
     * Constructeur surcharge
     * @param id_personne
     * @param civilite
     * @param nom_personne
     * @param prenom_personne
     * @param type_personne
     * @param login
     * @param mdp
     */
        public Personne(int id_personne, String nom_personne, String prenom_personne,  String civilite, String type_personne, String login, String mdp){
        this.id_personne=id_personne;
        this.civilite=civilite;
        this.nom_personne=nom_personne;
        this.prenom_personne=prenom_personne;
        this.type_personne=type_personne;
        this.login=login;
        this.mdp=mdp;
    }
    
    //getter et setter

    /**
     *
     * @return
     */
        public int get_ID_Personne(){
        return id_personne;
    }
    
    /**
     *
     * @param id_personne
     */
    public void set_ID_Personne(int id_personne){
        this.id_personne=id_personne;
    }
    
    /**
     *
     * @return
     */
    public String get_Nom_Personne(){
        return nom_personne;
    }
    
    /**
     *
     * @param nom_personne
     */
    public void set_Nom_Personne(String nom_personne){
        this.nom_personne=nom_personne;
    }
    
    /**
     *
     * @return
     */
    public String get_Prenom_Personne(){
        return prenom_personne;
    }
    
    /**
     *
     * @param prenom_personne
     */
    public void set_Prenom_Personne(String prenom_personne){
        this.prenom_personne=prenom_personne;
    }
    
    /**
     *
     * @return
     */
    public String get_Type_Personne(){
        return type_personne;
    }
    
    /**
     *
     * @param type_personne
     */
    public void set_Type_Personne(String type_personne){
        this.type_personne=type_personne;
    }
    
    /**
     *
     * @return
     */
    public String getCivilite(){
        return civilite;
    }
    
    /**
     *
     * @param civilite
     */
    public void setCivilite(String civilite){
        this.civilite=civilite;
    }

    /**
     *
     * @return
     */
    public String getLogin(){
        return login;
    }
    
    /**
     *
     * @param login
     */
    public void setLogin(String login){
        this.login=login;
    }

    /**
     *
     * @return
     */
    public String getMdp(){
        return mdp;
    }
    
    /**
     *
     * @param Mdp
     */
    public void setMdp(String Mdp){
        this.mdp=Mdp;
    }
    
}
