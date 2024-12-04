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
 * Classe Dao de enseignement
 * @author coline
 * @author loic
 */
public class DAOEnseignement extends DAO<Enseignement>{

    private ArrayList<Enseignement> listEnseignement;
    private DAODiscipline discipline;
    private DAOClasse classe;
    private DAOPersonne personne;
    
    /**
     *
     */
    public DAOEnseignement(){
        listEnseignement=new ArrayList<Enseignement>();
    }
    
    /**
     *
     * @param conn
     */
    public DAOEnseignement(Connection conn) {
        super(conn);
    }
    
    /**
     *
     * @return listEnseignement
     */
    public ArrayList<Enseignement> getList(){
        return listEnseignement;
    }
    
    /**
     *
     * @param discipline
     */
    public void setDiscipline(DAODiscipline discipline){
        this.discipline=discipline;
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
        ArrayList<Discipline> listDiscipline = new ArrayList<Discipline>();
        listDiscipline = discipline.getList();
        
        ArrayList<Classe> listClasse = new ArrayList<Classe>();
        listClasse = classe.getList();
        
        ArrayList<Personne> listPersonne = new ArrayList<Personne>();
        listPersonne = personne.getList();
        
        for(Enseignement E : listEnseignement){
            int id_DisciplineE= E.get_ID_Discipline();
            int id_ClasseE = E.get_ID_Classe();
            int id_PersonneE = E.get_ID_Personne();
            
            for(Discipline D : listDiscipline){
                int id_DisciplineD = D.get_ID_Discipline();
                if (id_DisciplineD == id_DisciplineE){
                    E.setDiscipline(D);
                    //System.out.println("Liaison effectué entre la Table Enseignement et Discipline, matière = "+E.getDiscipline().get_Nom_Discipline());
                }
            }
            
            for(Classe C : listClasse){
                int id_ClasseC = C.get_ID_Classe();
                if (id_ClasseC == id_ClasseE){
                    E.setClasse(C);
                    //System.out.println("Liaison effectué entre la Table Enseignement et Classe pour l'année = "+E.getClasse().get_Nom_Classe());
                }
            }
            
            for(Personne P : listPersonne){
                int id_PersonneP = P.get_ID_Personne();
                if (id_PersonneP == id_PersonneE){
                    E.setPersonne(P);
                    //System.out.println("Liaison effectué entre la Table Enseignement et Personne, prof = "+E.getPersonne().get_Prenom_Personne()+" "+E.getPersonne().get_Nom_Personne());
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
            ResultSet result = stmt.executeQuery("SELECT * FROM enseignement");
            while(result.next()){
                Enseignement prof = new Enseignement(result.getInt("id_enseignement"),result.getInt("id_classe"),result.getInt("id_discipline"),result.getInt("id_personne"));
                listEnseignement.add(prof);
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
    public boolean create(Enseignement obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
         try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO enseignement(id_enseignement,id_classe,id_discipline,id_personne) VALUES(?,?,?,?)"
            );
            //insert param to change the ? into data
            statement.setObject(1, obj.get_ID_Enseignement(), Types.INTEGER);
            statement.setObject(2, obj.get_ID_Classe(), Types.INTEGER);
            statement.setObject(3, obj.get_ID_Discipline(), Types.INTEGER);
            statement.setObject(4, obj.get_ID_Personne(), Types.INTEGER);
            

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
    public boolean delete(Enseignement obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "DELETE FROM enseignement WHERE id_enseignement=?"
            );
            //insert param to change the ? into data
            statement.setObject(1, obj.get_ID_Enseignement(), Types.INTEGER);
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
    public boolean update(Enseignement obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "UPDATE enseignement SET id_classe=?, id_discipline=?, id_personne=?, WHERE id_enseignement=?"
            );
            //insert param to change the ? into data
            statement.setObject(1, obj.get_ID_Classe(), Types.VARCHAR);
            statement.setObject(2, obj.get_ID_Discipline(), Types.VARCHAR);
            statement.setObject(3, obj.get_ID_Personne(), Types.INTEGER);
            statement.setObject(4, obj.get_ID_Enseignement(), Types.INTEGER);
            statement.executeUpdate(); //execute update for change in DB and executeQuery for select

        } catch (SQLException ex) {
            Logger.getLogger(DAOPersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    /**
  * Méthode de recherche des informations
  * @param id_enseignement
  * @return T
  */
    @Override
    public Enseignement find(int id_enseignement) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
            Enseignement enseignement = new Enseignement();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM enseignement WHERE id_enseignement = " + id_enseignement);
      if(result.first())
        enseignement = new Enseignement(
          id_enseignement,
          result.getInt("id_classe"),
          result.getInt("id_discipline"),
          result.getInt("id_personne")
         );         
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return enseignement;
    }
    
}
