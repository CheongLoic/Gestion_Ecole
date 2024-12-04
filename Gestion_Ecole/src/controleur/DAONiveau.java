/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.sql.*;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.*;

/**
 * Classe Dao de niveau
 * @author coline 
 * @author loic
 */
public class DAONiveau extends DAO<Niveau>{

    private ArrayList<Niveau> listNiveau;
    
    /**
     *
     */
    public DAONiveau(){
        listNiveau=new ArrayList<Niveau>();
    }
    
    /**
     *
     * @param conn
     */
    public DAONiveau(Connection conn) {
        super(conn);
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
            ResultSet result = stmt.executeQuery("SELECT * FROM niveau");
            while(result.next()){
                Niveau niveau = new Niveau(result.getInt("id_niveau"),result.getString("nom_niveau"));
                listNiveau.add(niveau);
            }
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOEcole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *
     * @return listNiveau
     */
    public ArrayList<Niveau> getList(){
        return listNiveau;
    }
    
    /**
  * Méthode de création
  * @param obj
  * @return boolean 
  */
    @Override
    public boolean create(Niveau obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO niveau(id_niveau,nom_niveau) VALUES(?,?)"
            );
            //insert param to change the ? into data
            statement.setObject(1, obj.get_ID_Niveau(), Types.INTEGER);
            statement.setObject(2, obj.get_Nom_Niveau(), Types.VARCHAR);
            

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
    public boolean delete(Niveau obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "DELETE FROM niveau WHERE id_niveau=?"
            );
            //insert param to change the ? into data
            statement.setObject(1, obj.get_ID_Niveau(), Types.INTEGER);
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
    public boolean update(Niveau obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "UPDATE niveau SET nom_niveau=?, WHERE id_niveau=?"
            );
            //insert param to change the ? into data
            statement.setObject(1, obj.get_Nom_Niveau(), Types.VARCHAR);
            statement.setObject(3, obj.get_ID_Niveau(), Types.INTEGER);
            statement.executeUpdate(); //execute update for change in DB and executeQuery for select

        } catch (SQLException ex) {
            Logger.getLogger(DAOPersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    /**
  * Méthode de recherche des informations
  * @param id_niveau
  * @return T
  */
    @Override
    public Niveau find(int id_niveau) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
            Niveau niveau = new Niveau();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM niveau WHERE id_niveau = " + id_niveau);
      if(result.first())
        niveau = new Niveau(
          id_niveau,
          result.getString("nom_niveau")
        );         
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return niveau;
    }
    
}
