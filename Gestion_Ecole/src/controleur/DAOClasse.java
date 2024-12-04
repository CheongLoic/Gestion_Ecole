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
 * Classe Dao classe
 * @author coline 
 * @author loic
 */
public class DAOClasse extends DAO<Classe> {

    private ArrayList<Classe> listClasse;
    private DAOAnnee_Scolaire annee;
    private DAOEcole ecole;
    private DAONiveau niveau;
    
    /**
     * Classe contient declaration et implémentation des attributs de Classe
     */
    public DAOClasse(){
        listClasse=new ArrayList<Classe>();
    }
    
    /**
     *
     * @param conn
     */
    public DAOClasse(Connection conn) {
        super(conn);
    }

    /**
     *
     * @return listClasse
     */
    public ArrayList<Classe> getList(){
        return listClasse;
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
     * @param ecole
     */
    public void setEcole(DAOEcole ecole){
        this.ecole=ecole;
    }
    
    /**
     *
     * @param niveau
     */
    public void setNiveau(DAONiveau niveau){
        this.niveau=niveau;
    }
    
    /**
     *
     */
    @Override
    public void Bind(){
        ArrayList<Annee_Scolaire> listAnnee = new ArrayList<Annee_Scolaire>();
        listAnnee = annee.getList();
        
        ArrayList<Ecole> listEcole = new ArrayList<Ecole>();
        listEcole = ecole.getList();
        
        ArrayList<Niveau> listNiveau = new ArrayList<Niveau>();
        listNiveau = niveau.getList();
        
        for(Classe C : listClasse){
            int id_annee_scolaireC = C.get_ID_Annee_Scolaire();
            int id_ecoleC = C.get_ID_Ecole();
            int id_niveauC = C.get_ID_Niveau();
            
            for(Ecole E : listEcole){
                int id_ecoleE = E.get_ID_Ecole();
                if (id_ecoleE == id_ecoleC){
                    C.setEcole(E);
                    //System.out.println("Liaison effectué entre la Table Classe et Ecole pour l'id ecole = "+C.get_ID_Ecole() + " de " +C.getEcole().get_Nom_Ecole());
                }
            }
            
            for(Annee_Scolaire A : listAnnee){
                int id_annee_scolaireA = A.get_ID_Annee();
                if (id_annee_scolaireA == id_annee_scolaireC){
                    C.setAnneeScolaire(A);
                    //System.out.println("Liaison effectué entre la Table Classe et Ecole pour l'année = "+C.getAnneeScolaire().get_ID_Annee());
                }
            }
            
            for(Niveau N : listNiveau){
                int id_niveauN = N.get_ID_Niveau();
                if (id_niveauN == id_niveauC){
                    C.setNiveau(N);
                   // System.out.println("Liaison effectué entre la Table classe et Niveau pour le niveau = "+C.getNiveau().get_Nom_Niveau());
                }
            }
            
          //  System.out.println("");
        }
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
            ResultSet result = stmt.executeQuery("SELECT * FROM classe");
            while(result.next()){
                Classe classe = new Classe(result.getInt("id_classe"),result.getString("nom_classe"),result.getInt("id_ecole"),result.getInt("id_niveau"),result.getInt("id_annee_scolaire"));
                listClasse.add(classe);
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
    public boolean create(Classe obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO classe(id_classe,nom_classe,id_ecole,id_niveau,id_annee_scolaire) VALUES(?,?,?,?,?)"
            );
            //insert param to change the ? into data
            statement.setObject(1, obj.get_ID_Classe(), Types.INTEGER);
            statement.setObject(2, obj.get_Nom_Classe(), Types.VARCHAR);
            statement.setObject(3, obj.get_ID_Ecole(), Types.INTEGER);
            statement.setObject(4, obj.get_ID_Niveau(), Types.INTEGER);
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
    public boolean delete(Classe obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "DELETE FROM classe WHERE id_classe=?"
            );
            //insert param to change the ? into data
            statement.setObject(1, obj.get_ID_Classe(), Types.INTEGER);
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
    public boolean update(Classe obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "UPDATE classe SET nom_classe=?, id_ecole=?,id_niveau=?,id_annee_scolaire=? WHERE id_classe=?"
            );
            //insert param to change the ? into data
            
            statement.setObject(1, obj.get_Nom_Classe(), Types.VARCHAR);
            statement.setObject(2, obj.get_ID_Ecole(), Types.INTEGER);
            statement.setObject(3, obj.get_ID_Niveau(), Types.INTEGER);
            statement.setObject(4, obj.get_ID_Annee_Scolaire(), Types.INTEGER);
            statement.setObject(5, obj.get_ID_Classe(), Types.INTEGER);
            statement.executeUpdate(); //execute update for change in DB and executeQuery for select

        } catch (SQLException ex) {
            Logger.getLogger(DAOPersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    /**
  * Méthode de recherche des informations
  * @param id_classe
  * @return T
  */
    @Override
    public Classe find(int id_classe) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
            Classe classe = new Classe();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM classe WHERE id_classe = " + id_classe);
      if(result.first())
        classe = new Classe(
          id_classe,
          result.getString("nom_classe"),
          result.getInt("id_ecole"),
          result.getInt("id_niveau"),
          result.getInt("id_annee_scolaire")
          );         
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return classe;
    }
    
}
