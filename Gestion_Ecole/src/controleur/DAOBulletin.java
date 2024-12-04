/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Bulletin;
import modele.*;

/**
 * Classe Dao annee scolaire
 * @author coline
 * @author loic
 */
public class DAOBulletin extends DAO<Bulletin> {
    
    private ArrayList<Bulletin> listBulletin;
    private DAOTrimestre trimestre;
    private DAOInscription inscription;
    private DAODetail_Bulletin detail_bulletin;
    private DAOClasse classe;

    /**
     *
     */
    public DAOBulletin(){
        listBulletin=new ArrayList<Bulletin>();
    }
    
    /**
     *
     * @param conn
     */
    public DAOBulletin(Connection conn) {
        super(conn);
    }

    /**
     *
     * @return listBulletin
     */
    public ArrayList<Bulletin> getList(){
        return listBulletin;
    }
    
    /**
     *
     * @param trimestre
     */
    public void setTrimestre(DAOTrimestre trimestre){
        this.trimestre=trimestre;
    }
    
    /**
     *
     * @param inscription
     */
    public void setInscription(DAOInscription inscription){
        this.inscription=inscription;
    }
    
    /**
     *
     * @param detail_bulletin
     */
    public void setDetailBulletin(DAODetail_Bulletin detail_bulletin){
        this.detail_bulletin=detail_bulletin;
    }
    
    /**
     *
     * @param classe
     */
    public void setClasse(DAOClasse classe){
        this.classe=classe;
    }

    /**
     *
     */
    @Override
    public void Bind(){
        ArrayList<Trimestre> listTrimestre = new ArrayList<Trimestre>();
        listTrimestre = trimestre.getList();
        
        ArrayList<Inscription> listInscription = new ArrayList<Inscription>();
        listInscription = inscription.getList();
        
        for(Bulletin B : listBulletin){
            int id_TrimestreB = B.get_ID_Trimestre();
            int id_InscriptionB = B.get_ID_Inscription();
            
            for(Trimestre T : listTrimestre){
                int id_trimestreT = T.get_ID_Trimestre();
                if (id_trimestreT == id_TrimestreB){
                    B.setTrimestre(T);
                    //System.out.println("Liaison effectué entre la table Bulletin Global et Trimestre, date= "+B.getTrimestre().get_Date_Debut());
                }
            }
            
            for(Inscription I : listInscription){
                int id_InscriptionI = I.get_ID_Inscription();
                if (id_InscriptionI== id_InscriptionB){
                    B.setInscription(I);
                    //System.out.println("Liaison effectué entre la table Bulletin Global et Inscription pour la classe = "+B.getInscription().getPersonne().get_Nom_Personne());
                }
            }
            //System.out.println("");
        }
    }
    
    /**
     * methode qui modifie les donnees
     */
    @Override
    public void setData(){
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_ecole", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM bulletin");
            while(result.next()){
                Bulletin bulletin = new Bulletin(result.getInt("id_bulletin"),result.getInt("id_trimestre"),result.getInt("id_inscription"),result.getString("appreciation_bulletin"));
                listBulletin.add(bulletin);
            }
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOEcole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    /**
     * Méthode qui calcule la moyenne générale d'un élève
     */
        public void setMoyenneGeneraleEleve(){
        
        for(Bulletin B : listBulletin){
            int id_bulletin = B.get_ID_Bulletin();
            float SommeMoyenne=0;
            float MGE = 0;//MoyenneGeneraleEleve
            int nbMatiere = 0;
            for (Detail_Bulletin DB : detail_bulletin.getList()){
                if (DB.get_ID_Bulletin()==id_bulletin){
                    SommeMoyenne+=DB.getMoyenneEleve();
                    nbMatiere++;
                }
            }
            MGE = SommeMoyenne/nbMatiere;
            B.setMoyenneGeneraleEleve(MGE);
        }
    }
    
    

    /**
     * Méthode qui calcule la moyenne générale de la classe
     */
        public void setMoyenneGeneraleClasse(){
        
        for(Classe C : classe.getList()){
            int id_classe = C.get_ID_Classe();
            int nbEleves = C.getNbEleves();
            float SommeMoyenne=0;
            float MGC = 0;//MoyenneGeneraleClasse
            
            for(Bulletin B : listBulletin){
                if (B.getInscription().get_ID_Classe() == id_classe){
                    SommeMoyenne+=B.getMoyenneGeneraleEleve();
                }
            }
            MGC = SommeMoyenne/nbEleves;
            for(Bulletin B : listBulletin){
                if (B.getInscription().get_ID_Classe() == id_classe){
                    B.setMoyenneGeneraleClasse(MGC);
                }
            }
        }
    }
    
    /**
  * Méthode de création
  * @param obj
  * @return boolean 
  */    
    
    @Override
    public boolean create(Bulletin obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO bulletin(id_bulletin,id_trimestre,id_inscription,appreciation_bulletin) VALUES(?,?,?,?,?)"
            );
            //insert param to change the ? into data
            statement.setObject(1, obj.get_ID_Bulletin(), Types.INTEGER);
            statement.setObject(2, obj.get_ID_Trimestre(), Types.INTEGER);
            statement.setObject(3, obj.get_ID_Inscription(), Types.INTEGER);
            statement.setObject(4, obj.get_Appreciation_Bulletin(), Types.VARCHAR);
            statement.executeUpdate(); //execute update for change in DB and executeQuery for select

        } catch (SQLException ex) {
            Logger.getLogger(DAOPersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    /**
  * Méthode pour effacer
  * @param obj
  * @return boolean 
  */
    @Override
    public boolean delete(Bulletin obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "DELETE FROM bulletin WHERE id_bulletin=?"
            );
            //insert param to change the ? into data
            statement.setObject(1, obj.get_ID_Bulletin(), Types.INTEGER);
            statement.executeUpdate(); //execute update for change in DB and executeQuery for select

        } catch (SQLException ex) {
            Logger.getLogger(DAOPersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    /**
  * Méthode de mise à jour
  * @param obj
  * @return boolean
  */
    @Override
    public boolean update(Bulletin obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
            try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "UPDATE bulletin SET id_trimestre=?,id_inscription=?,appreciation_bulletin=?, WHERE id_bulletin=?"
            );
            //insert param to change the ? into data
            
            statement.setObject(1, obj.get_ID_Trimestre(), Types.INTEGER);
            statement.setObject(2, obj.get_ID_Inscription(), Types.INTEGER);            
            statement.setObject(3, obj.get_Appreciation_Bulletin(), Types.VARCHAR);
            statement.setObject(5, obj.get_ID_Bulletin(), Types.INTEGER);
            
            statement.executeUpdate(); //execute update for change in DB and executeQuery for select

        } catch (SQLException ex) {
            Logger.getLogger(DAOPersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    /**
  * Méthode de recherche des informations
  * @param id_bulletin
  * @return T
  */
    @Override
    public Bulletin find(int id_bulletin) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
            Bulletin bulletin = new Bulletin();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM bulletin WHERE id_bulletin = " + id_bulletin);
      if(result.first())
        bulletin = new Bulletin(
          id_bulletin,          
          result.getInt("id_trimestre"),
          result.getInt("id_inscription"),
          result.getString("appreciaton_bulletin")
        );         
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return bulletin;
    }
    
}
