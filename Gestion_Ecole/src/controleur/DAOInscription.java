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
import modele.*;

/**
 * Classe Dao d'inscription
 * @author coline 
 * @author loic
 */
public class DAOInscription extends DAO<Inscription>{

    private ArrayList<Inscription> listInscription;
    private DAOClasse classe;
    private DAOPersonne personne;
    
    /**
     *
     */
    public DAOInscription(){
        listInscription=new ArrayList<Inscription>();
    }
    
    /**
     *
     * @param conn
     */
    public DAOInscription(Connection conn) {
        super(conn);
    }
    
    /**
     *
     * @return listInscription
     */
    public ArrayList<Inscription> getList(){
        return listInscription;
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
     * @param personne
     */
    public void setPersonne(DAOPersonne personne){
        this.personne=personne;
    }
    
    /**
     *
     */
    @Override
    public void Bind(){
        ArrayList<Classe> listClasse = new ArrayList<Classe>();
        listClasse = classe.getList();
        
        ArrayList<Personne> listPersonne = new ArrayList<Personne>();
        listPersonne = personne.getList();
        
        for(Inscription I : listInscription){
            int id_classeI = I.get_ID_Classe();
            int id_personneI = I.get_ID_Personne();
            
            for(Personne P : listPersonne){
                int id_personneP = P.get_ID_Personne();
                if (id_personneP == id_personneI){
                    I.setPersonne(P);
                    //System.out.println("Liaison effectué entre la table Inscription et Personne, nom = "+I.getPersonne().get_Nom_Personne());
                }
            }
            
            for(Classe C : listClasse){
                int id_classeC = C.get_ID_Classe();
                if (id_classeC== id_classeI){
                    I.setClasse(C);
                    //System.out.println("Liaison effectué entre la table classe et Inscription pour la classe = "+I.getClasse().get_Nom_Classe());
                }
            }
            
            //System.out.println("");
        }
    }
    
    /**
     * methode modifie les donnees
     */
    @Override
    public void setData(){
        //Faire  DAOEcole e;
        //e.getData(); pour récupérer les données dans un ArrayList
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_ecole", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM inscription");
            while(result.next()){
                Inscription inscription = new Inscription(result.getInt("id_inscription"),result.getInt("id_classe"),result.getInt("id_personne"));
                listInscription.add(inscription);
            }
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOEcole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    /**
     * Calcule le nb d'elèves dans une classe
     */
        public void setNbEleves(){
        
        ArrayList<Classe> listClasse = new ArrayList<Classe>();
        listClasse = classe.getList();
        
        for(Classe C : listClasse){
            int nbEleves = 0;
            for(Inscription I : listInscription){
                if (I.get_ID_Classe() == C.get_ID_Classe()){
                    nbEleves ++;
                }
            }
            C.setNbEleves(nbEleves);
        }
    }
    
    /**
  * Méthode de création
  * @param obj
  * @return boolean 
  */
    @Override
    public boolean create(Inscription obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO inscription(id_inscription,id_classe,id_personne) VALUES(?,?,?)"
            );
            //insert param to change the ? into data
            statement.setObject(1, obj.get_ID_Inscription(), Types.INTEGER);
            statement.setObject(2, obj.get_ID_Classe(), Types.VARCHAR);
            statement.setObject(3, obj.get_ID_Personne(), Types.VARCHAR);

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
    public boolean delete(Inscription obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "DELETE FROM inscription WHERE id_inscription=?"
            );
            //insert param to change the ? into data
            statement.setObject(1, obj.get_ID_Inscription(), Types.INTEGER);
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
    public boolean update(Inscription obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "UPDATE inscription SET id_classe=?, id_personne=?, WHERE id_inscription=?"
            );
            //insert param to change the ? into data
            statement.setObject(1, obj.get_ID_Classe(), Types.INTEGER);
            statement.setObject(2, obj.get_ID_Personne(), Types.INTEGER);
            statement.setObject(3, obj.get_ID_Inscription(), Types.INTEGER);
            statement.executeUpdate(); //execute update for change in DB and executeQuery for select

        } catch (SQLException ex) {
            Logger.getLogger(DAOPersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    /**
  * Méthode de recherche des informations
  * @param id_inscription
  * @return T
  */
    @Override
    public Inscription find(int id_inscription) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
            Inscription inscription = new Inscription();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM inscription WHERE id_inscription = " + id_inscription);
      if(result.first())
        inscription = new Inscription(
          id_inscription,
          result.getInt("id_classe"),
          result.getInt("id_personne")
        
        );         
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return inscription;
    }
    
}
