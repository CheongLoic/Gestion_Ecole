/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.*;

/**
 * Classe Dao de personne
 * @author coline 
 * @author loic
 */
public class DAOPersonne extends DAO<Personne>{

    private ArrayList<Personne> listPersonne;
    
    /**
     *
     */
    public DAOPersonne(){
        listPersonne=new ArrayList<Personne>();
    }
    
    /**
     *
     * @param conn
     */
    public DAOPersonne(Connection conn) {
        super(conn);
    }
    
    /**
     * methode modifie les donnees
     */
    @Override
    public void setData(){
        //Faire  DAOEcole e;
        //e.getData(); pour récupérer les données dans un ArrayList
        
        // Informations de connexion à la base de données
        String url = "jdbc:mysql://localhost/gestion_ecole";
        String username = "root";  // Utilisateur MySQL (par défaut sur WampServer : root)
        String password = "";      // Mot de passe MySQL (par défaut sur WampServer : vide)

        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM personne GROUP BY nom_personne"); //On fait un tri en sql pour éviter de le faire plus tard sur un ArrayList
            while(result.next()){
                Personne personne = new Personne(result.getInt("id_personne"),result.getString("nom_personne"),result.getString("prenom_personne"),result.getString("civilite"),result.getString("type_personne"),result.getString("identifiant"),result.getString("mdp"));
                listPersonne.add(personne);
            }
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOEcole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *
     * @return listPersonne
     */
    public ArrayList<Personne> getList(){
        return listPersonne;
    }
    
    /**
  * Méthode de création
  * @param obj
  * @return boolean 
  */
    @Override
    public boolean create(Personne obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO personne(id_personne,nom_personne,prenom_personne,type_personne) VALUES(?,?,?,?)"
            );
            //insert param to change the ? into data
            statement.setObject(1, obj.get_ID_Personne(), Types.INTEGER);
            statement.setObject(2, obj.get_Nom_Personne(), Types.VARCHAR);
            statement.setObject(3, obj.get_Prenom_Personne(), Types.VARCHAR);
            statement.setObject(4, obj.get_Type_Personne(), Types.VARCHAR);

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
    public boolean delete(Personne obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "DELETE FROM personne WHERE id_personne=?"
            );
            //insert param to change the ? into data
            statement.setObject(1, obj.get_ID_Personne(), Types.INTEGER);
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
    public boolean update(Personne obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "UPDATE personne SET nom_personne=?, prenom_personne=?, type_personne=?, WHERE id_personne=?"
            );
            //insert param to change the ? into data
            statement.setObject(1, obj.get_Nom_Personne(), Types.VARCHAR);
            statement.setObject(2, obj.get_Prenom_Personne(), Types.VARCHAR);
            statement.setObject(3, obj.get_Type_Personne(), Types.VARCHAR);
            statement.setObject(4, obj.get_ID_Personne(), Types.INTEGER);
            statement.executeUpdate(); //execute update for change in DB and executeQuery for select

        } catch (SQLException ex) {
            Logger.getLogger(DAOPersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    /**
  * Méthode de recherche des informations
  * @param id_personne
  * @return T
  */
    @Override
    public Personne find(int id_personne) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
            Personne personne = new Personne();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM personne WHERE id_personne = " + id_personne);
        if(result.first())
            personne = new Personne(
            id_personne,
            result.getString("civilite"),
            result.getString("nom_personne"),
            result.getString("prenom_personne"),
            result.getString("type_personne"),
            result.getString("identifiant"),
            result.getString("mdp")
        );         
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return personne;
    }
    
}
