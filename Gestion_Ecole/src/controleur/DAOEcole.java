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
import modele.Ecole;
import modele.*;

/**
 * Classe Dao de Ecole
 * @author coline
 * @author loic
 */
public class DAOEcole extends DAO<Ecole> {

    private ArrayList<Ecole> listEcole;
    
    /**
     *
     */
    public DAOEcole(){
        listEcole=new ArrayList<Ecole>();
    }
    
    /**
     *
     * @param conn
     */
    public DAOEcole(Connection conn) {
        super(conn);
    }
    
    /**
     * methode modifie les donnees
     */
    @Override
    public void setData(){
        
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_ecole", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM ecole");
            while(result.next()){
                Ecole ecole = new Ecole(result.getInt("id_ecole"),result.getString("nom_ecole"),result.getString("adresse_ecole"));
                listEcole.add(ecole);
            }
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOEcole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *
     * @return listEcole
     */
    public ArrayList<Ecole> getList(){
        return listEcole;
    }

    /**
  * Méthode de création
  * @param obj
  * @return boolean 
  */
    @Override
    public boolean create(Ecole obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO ecole(id_ecole,nom_ecole,adresse_ecole) VALUES(?,?,?)"
            );
            //insert param to change the ? into data
            statement.setObject(1, obj.get_ID_Ecole(), Types.INTEGER);
            statement.setObject(2, obj.get_Nom_Ecole(), Types.VARCHAR);
            statement.setObject(3, obj.get_Adresse_Ecole(), Types.VARCHAR);

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
    public boolean delete(Ecole obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "DELETE FROM ecole WHERE id_ecole=?"
            );
            //insert param to change the ? into data
            statement.setObject(1, obj.get_ID_Ecole(), Types.INTEGER);
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
    public boolean update(Ecole obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "UPDATE ecole SET nom_ecole=?, adresse_ecole=?, WHERE id_ecole=?"
            );
            //insert param to change the ? into data
            statement.setObject(1, obj.get_Nom_Ecole(), Types.VARCHAR);
            statement.setObject(2, obj.get_Adresse_Ecole(), Types.VARCHAR);
            statement.setObject(3, obj.get_ID_Ecole(), Types.INTEGER);
            statement.executeUpdate(); //execute update for change in DB and executeQuery for select

        } catch (SQLException ex) {
            Logger.getLogger(DAOPersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    /**
  * Méthode de recherche des informations
  * @param id_ecole
  * @return T
  */
    @Override
    public Ecole find(int id_ecole) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
            Ecole ecole = new Ecole();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM ecole WHERE id_ecole = " + id_ecole);
      if(result.first())
        ecole = new Ecole(
          id_ecole,
          result.getString("nom_ecole"),
          result.getString("adresse_ecole"
        ));         
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return ecole;
    }
    
}
