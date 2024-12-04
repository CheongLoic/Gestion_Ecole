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
 * Classe Dao de Evaluation
 * @author coline
 * @author loic
 */
public class DAOEvaluation extends DAO<Evaluation>{

    private ArrayList<Evaluation> listEvaluation;
    private DAODetail_Bulletin detail_bulletin;
    private DAODS DS;
    private DAOClasse classe;
    
    /**
     *
     */
    public DAOEvaluation(){
        listEvaluation=new ArrayList<Evaluation>();
    }
    
    /**
     *
     * @param conn
     */
    public DAOEvaluation(Connection conn) {
        super(conn);
    }
    
    /**
     *
     * @return listEvaluation
     */
    public ArrayList<Evaluation> getList(){
        return listEvaluation;
    }
    
    /**
     *
     * @param db
     */
    public void setDetailBulletin(DAODetail_Bulletin db){
        this.detail_bulletin=db;
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
     * @param ds
     */
    public void setDS(DAODS ds){
        this.DS=ds;
    }

    /**
     *
     */
    @Override
    public void Bind(){
        ArrayList<Detail_Bulletin> listDetail_Bulletin = new ArrayList<Detail_Bulletin>();
        listDetail_Bulletin = detail_bulletin.getList();
        
        ArrayList<DS> listDS = new ArrayList<DS>();
        listDS = DS.getList();
        
        for(Evaluation E : listEvaluation){
            int id_Detail_BulletinB = E.get_ID_Detail_Bulletin();
            int id_DS_Eval = E.get_ID_DS();
            
            for(Detail_Bulletin DB : listDetail_Bulletin){
                int id_Detail_BulletinDB = DB.get_ID_Detail();
                if (id_Detail_BulletinDB == id_Detail_BulletinB){
                    E.setDetailBulletin(DB);
                    //System.out.println("Liaison effectué entre la table Evaluation et Detail Bulletin, = "+E.getDetailBulletin().get_Appreciation_Detail());
                }
            }
            
            for(DS ds : listDS){
                int id_DS_ds = ds.get_ID_DS();
                if (id_DS_ds == id_DS_Eval){
                    E.setDS(ds);
                    //System.out.println("Liaison effectué entre la table Evaluation et DS, id_ds= "+E.getDS().get_ID_DS()+"\tNom_DS"+E.getDS().get_Nom_DS());
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
        
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_ecole", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM evaluation");
            while(result.next()){
                
                Evaluation DS = new Evaluation(result.getInt("id_evaluation"),result.getInt("id_detail_bulletin"),result.getInt("id_ds"),result.getFloat("note_evaluation"),result.getInt("coeff"),result.getString("appreciation_evaluation"));
                listEvaluation.add(DS);
            }
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOEcole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * methode modifie le moyenne de Ds de la classe
     */
    public void setMoyenneDSClasse(){
        ArrayList<Classe> listClasse = new ArrayList<Classe>();
        listClasse = classe.getList();
        ArrayList<Detail_Bulletin> listDetail_Bulletin = new ArrayList<Detail_Bulletin>();
        listDetail_Bulletin = detail_bulletin.getList();
        
        for(Classe C : listClasse){
            
            for(Evaluation E : listEvaluation){
                int id_ds = E.get_ID_DS();
                int id_enseignement = E.getDetailBulletin().get_ID_Enseignement();
                float moyenneDS=0;
                float moyenneDS_retenue=0;
                int nbNote=0;
                //System.out.println(moyenneDS);
                for(Evaluation E2 : listEvaluation){
                    boolean b1 = (E2.get_ID_DS()==id_ds); //même id_ds
                    //boolean b2 = C.getNbEleves()!=0;
                    boolean b3 = E2.getDetailBulletin().getBulletin().getInscription().getClasse().get_ID_Classe() == C.get_ID_Classe(); //si appartient à la même classe
                    boolean b4 = E2.getDetailBulletin().get_ID_Enseignement() == id_enseignement;//si même matière
                    //System.out.println("id eval = "+E2.get_ID_Evaluation()+" b1 = "+b1+" b3 = "+b3+" b4 = "+b4);
                    if (b1 && b3 && b4){
                         //Si la copie du DS appartient à une même classe d'élèves
                         moyenneDS = moyenneDS + E2.get_Note_Evaluation();
                         nbNote+=1;
                         //System.out.print(moyenneDS+" => ");
                    }
                    //System.out.print("  "+moyenneDS);
                }
                moyenneDS_retenue=moyenneDS/nbNote;
                //System.out.println("  ");
                for(Evaluation E3 : listEvaluation){
                    //on reparcours une nouvelle fois l'ArrayList listEvaluation pou mettre la moyenne du DS
                    boolean B1 = (E3.get_ID_DS()==id_ds);
                    //boolean b2 = C.getNbEleves()!=0;
                    boolean B3 = E3.getDetailBulletin().getBulletin().getInscription().getClasse().get_ID_Classe() == C.get_ID_Classe();
                    boolean B4 = E3.getDetailBulletin().get_ID_Enseignement() == id_enseignement;
                    //System.out.println("id eval = "+E2.get_ID_Evaluation()+" b1 = "+b1+" b3 = "+b3+" b4 = "+b4);
                    if (B1 && B3 && B4){
                        //System.out.println("");
                       // moyenneDS_retenue /=C.getNbEleves();
                        E3.setMoyenneEvaluation(moyenneDS_retenue);
                        
                    }
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
    public boolean create(Evaluation obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO evaluation(id_evaluation,id_detail_bulletin,note_evaluation,appreciation_evaluation) VALUES(?,?,?,?)"
            );
            //insert param to change the ? into data
            statement.setObject(1, obj.get_ID_Evaluation(), Types.INTEGER);
            statement.setObject(2, obj.get_ID_Detail_Bulletin(), Types.INTEGER);
            statement.setObject(3, obj.get_Note_Evaluation(), Types.DOUBLE);
            statement.setObject(4, obj.get_Appreciation_Evaluation(), Types.VARCHAR);

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
    public boolean delete(Evaluation obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
         try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "DELETE FROM evaluation WHERE id_evaluation=?"
            );
            //insert param to change the ? into data
            statement.setObject(1, obj.get_ID_Evaluation(), Types.INTEGER);
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
    public boolean update(Evaluation obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "UPDATE evaluation SET id_detail_bulletin=?, note_evaluation=?, appreciation_evaluation=?, WHERE id_evaluation=?"
            );
            //insert param to change the ? into data
            statement.setObject(1, obj.get_ID_Detail_Bulletin(), Types.INTEGER);
            statement.setObject(2, obj.get_Note_Evaluation(), Types.DOUBLE);
            statement.setObject(3, obj.get_Appreciation_Evaluation(), Types.VARCHAR);
            statement.setObject(4, obj.get_ID_Evaluation(), Types.INTEGER);
            statement.executeUpdate(); //execute update for change in DB and executeQuery for select

        } catch (SQLException ex) {
            Logger.getLogger(DAOPersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    /**
  * Méthode de recherche des informations
  * @param id_evaluation
  * @return T
  */
    @Override
    public Evaluation find(int id_evaluation) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
            Evaluation evaluation = new Evaluation();      
      
    try {
        ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM evaluation WHERE id_evaluation = " + id_evaluation);
        if(result.first())
            evaluation = new Evaluation(
            id_evaluation,
            result.getInt("id_detail_bulletin"),
            result.getInt("id_ds"),
            result.getFloat("note_evaluation"),
            result.getInt("coeff"),
            result.getString("appreciation_evalution")
            );         
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return evaluation;
    }
    
}
