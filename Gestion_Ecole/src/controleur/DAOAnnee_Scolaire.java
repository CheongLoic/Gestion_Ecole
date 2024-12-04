/**
 * 
 * controleur contient l'ensemble des DAO qui permettent la connexion avec la BDD
 */
package controleur;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.*;

/**
 * Classe DAO annee scolaire
 * @author coline
 * @author loic
 */
public class DAOAnnee_Scolaire extends DAO<Annee_Scolaire> {
    
    
    private ArrayList<Annee_Scolaire> listAnnee;
    
    /**
     *
     */
    public DAOAnnee_Scolaire(){
        listAnnee=new ArrayList<Annee_Scolaire>();
    }

    /**
     *
     * @param conn
     */
    public DAOAnnee_Scolaire(Connection conn) {
        super(conn);
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Annee_Scolaire> getList(){
        return listAnnee;
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
            ResultSet result = stmt.executeQuery("SELECT * FROM annee_scolaire");
            while(result.next()){
                Annee_Scolaire annee = new Annee_Scolaire(result.getInt("id_annee_scolaire"));
                listAnnee.add(annee);
            }
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOEcole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
    
    /**
  * Méthode de création
  * @param obj
  * @return boolean 
  */
    
    @Override
    public boolean create(Annee_Scolaire obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
          try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO annee_scolaire(id_annee_scolaire) VALUES(?)"
            );
            //insert param to change the ? into data
            statement.setObject(1, obj.get_ID_Annee(), Types.INTEGER);
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
    public boolean delete(Annee_Scolaire obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
         try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "DELETE FROM annee_scolaire WHERE id_annee_scolaire=?"
            );
            //insert param to change the ? into data
            statement.setObject(1, obj.get_ID_Annee(), Types.INTEGER);
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
    public boolean update(Annee_Scolaire obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
  * Méthode de recherche des informations
  * @param id_annee_scolaire
  * @return T
  */
    @Override
    public Annee_Scolaire find(int id_annee_scolaire) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
            Annee_Scolaire annee = new Annee_Scolaire();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM annee_scolaire WHERE id_annee_scolaire = " + id_annee_scolaire);
      if(result.first())
        annee = new Annee_Scolaire(
          id_annee_scolaire
          );         
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return annee;
    }
    
}
