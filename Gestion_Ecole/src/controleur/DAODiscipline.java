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
 * Classe Dao de discipline
 * @author coline 
 * @author loic
 */
public class DAODiscipline extends DAO<Discipline>{

    private ArrayList<Discipline> listDiscipline;
    
    /**
     *
     */
    public DAODiscipline(){
        listDiscipline=new ArrayList<Discipline>();
    }
    
    /**
     *
     * @param conn
     */
    public DAODiscipline(Connection conn) {
        super(conn);
    }

    /**
     *
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
            ResultSet result = stmt.executeQuery("SELECT * FROM discipline");
            while(result.next()){
                Discipline discipline = new Discipline(result.getInt("id_discipline"),result.getString("nom_discipline"));
                listDiscipline.add(discipline);
            }
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOEcole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *
     * @return listDiscipline
     */
    public ArrayList<Discipline> getList(){
        return listDiscipline;
    }
    
    /**
  * Méthode de création
  * @param obj
  * @return boolean 
  */
    @Override
    public boolean create(Discipline obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
         try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO discipline(id_discipline,nom_discipline) VALUES(?,?)"
            );
            //insert param to change the ? into data
            statement.setObject(1, obj.get_ID_Discipline(), Types.INTEGER);
            statement.setObject(2, obj.get_Nom_Discipline(), Types.VARCHAR);

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
    public boolean delete(Discipline obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
         try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "DELETE FROM discipline WHERE id_discipline=?"
            );
            //insert param to change the ? into data
            statement.setObject(1, obj.get_ID_Discipline(), Types.INTEGER);
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
    public boolean update(Discipline obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "UPDATE discipline SET nom_discipline=?, WHERE id_discipline=?"
            );
            //insert param to change the ? into data
            statement.setObject(1, obj.get_Nom_Discipline(), Types.VARCHAR);
            statement.setObject(2, obj.get_ID_Discipline(), Types.INTEGER);
            statement.executeUpdate(); //execute update for change in DB and executeQuery for select

        } catch (SQLException ex) {
            Logger.getLogger(DAOPersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    /**
  * Méthode de recherche des informations
  * @param id_discipline
  * @return T
  */
    @Override
    public Discipline find(int id_discipline) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
            Discipline discipline = new Discipline();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM discipline WHERE id_discipline = " + id_discipline);
      if(result.first())
        discipline = new Discipline(
          id_discipline,
          result.getString("nom_discipline")
          );         
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return discipline;
    }
    
}
