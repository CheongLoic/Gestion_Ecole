/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.*;

/**
 * Classe Dao de trimestre
 * @author coline
 * @author loic
 */
public class DAOTrimestre extends DAO<Trimestre>{

    private ArrayList<Trimestre> listTrimestre;
    private DAOAnnee_Scolaire annee;
    
    /**
     *
     */
    public DAOTrimestre(){
        listTrimestre=new ArrayList<Trimestre>();
    }
    
    /**
     *
     * @param conn
     */
    public DAOTrimestre(Connection conn) {
        super(conn);
    }
    
    /**
     *
     * @return listTrimestre
     */
    public ArrayList<Trimestre> getList(){
        return listTrimestre;
    }
    
    /**
     *
     * @param an
     */
    public void setAnneeScolaire(DAOAnnee_Scolaire an){
        this.annee=an;
    }
    
    /**
     *
     */
    @Override
    public void Bind(){
        ArrayList<Annee_Scolaire> listAnnee = new ArrayList<Annee_Scolaire>();
        listAnnee = annee.getList();
        for(Trimestre T : listTrimestre){
            int id_annee_scolaireT = T.get_ID_Annee_Scolaire();
            for(Annee_Scolaire A : listAnnee){
                int id_annee_scolaireA = A.get_ID_Annee();
                if (id_annee_scolaireA == id_annee_scolaireT){
                    T.setAnneeScolaire(A);
                    //System.out.println("Liaison effectué entre la Table Trimestre et Anne_Scolaire pour l'id trimestre = "+T.get_ID_Trimestre());
                }
            }
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
            ResultSet result = stmt.executeQuery("SELECT * FROM trimestre");
            while(result.next()){
                SimpleDateFormat formater = null;
                java.sql.Date SqlDateDebut = result.getDate("debut_trimestre");
                java.sql.Date SqlDateFin = result.getDate("fin_trimestre");
                java.util.Date SqlDateDebutConverted = new java.util.Date(SqlDateDebut.getTime());
                java.util.Date SqlDateFinConverted = new java.util.Date(SqlDateFin.getTime());
               
                //System.out.println("SqlDateDebut = " + SqlDateDebut);
                //System.out.println("SqlDateFin = " + SqlDateFin);
                //System.out.println(SqlDateDebutConverted);
                //formater = new SimpleDateFormat("yyyy-MM-dd");
                //System.out.println(formater.format(SqlDateDebutConverted));
                Trimestre trimestre = new Trimestre(result.getInt("id_trimestre"),result.getInt("numero_trimestre"),SqlDateDebutConverted,SqlDateFinConverted,result.getInt("id_annee_scolaire"));
                listTrimestre.add(trimestre);
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
    public boolean create(Trimestre obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO trimestre(id_trimestre,numero_trimestre,debut_trimestre, fin_trimestre,id_annee_scolaire) VALUES(?,?,?,?,?)"
            );
            //insert param to change the ? into data
            statement.setObject(1, obj.get_ID_Trimestre(), Types.INTEGER);
            statement.setObject(2, obj.get_Numero_Trimestre(), Types.INTEGER);
            statement.setObject(3, obj.get_Date_Debut(), Types.DATE);
            statement.setObject(4, obj.get_Date_Fin(), Types.DATE);
            statement.setObject(5, obj.get_ID_Annee_Scolaire(), Types.INTEGER);

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
    public boolean delete(Trimestre obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
         try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "DELETE FROM trimestre WHERE id_trimestre=?"
            );
            //insert param to change the ? into data
            statement.setObject(1, obj.get_ID_Trimestre(), Types.INTEGER);
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
    public boolean update(Trimestre obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
         try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "UPDATE trimestre SET numero_trimestre=?, debut_trimestre=?, fin_trimestre=?, id_annee_scolaire=?,  WHERE id_trimestre=?"
            );
            //insert param to change the ? into data
            statement.setObject(1, obj.get_Numero_Trimestre(), Types.INTEGER);
            statement.setObject(2, obj.get_Date_Debut(), Types.DATE);
            statement.setObject(3, obj.get_Date_Fin(), Types.DATE);
            statement.setObject(4, obj.get_ID_Annee_Scolaire(), Types.INTEGER);
            statement.setObject(5, obj.get_ID_Trimestre(), Types.INTEGER);
            statement.executeUpdate(); //execute update for change in DB and executeQuery for select

        } catch (SQLException ex) {
            Logger.getLogger(DAOPersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    /**
  * Méthode de recherche des informations
  * @param id_trimestre
  * @return T
  */
    @Override
    public Trimestre find(int id_trimestre) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
                Trimestre trimestre = new Trimestre();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM trimestre WHERE id_trimestre = " + id_trimestre);
      if(result.first())
        trimestre = new Trimestre(
          id_trimestre,
          result.getInt("numero_trimestre"),
          result.getDate("debut_trimestre"),
          result.getDate("fin_trimestre"),
          result.getInt("id_annee_scolaire")
          );         
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return trimestre;
    }
    
}
    

