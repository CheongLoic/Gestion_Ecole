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
 * Classe Dao Detail bulletin
 * @author coline 
 * @author loic
 */
public class DAODetail_Bulletin extends DAO<Detail_Bulletin>{

    private ArrayList<Detail_Bulletin> listDetailBulletin;
    private DAOBulletin bulletin;
    private DAOEnseignement enseignement;
    private DAOEvaluation evaluation;
    private DAOClasse classe;
    
    /**
     *
     */
    public DAODetail_Bulletin(){
        listDetailBulletin=new ArrayList<Detail_Bulletin>();
    }
    
    /**
     *
     * @param conn
     */
    public DAODetail_Bulletin(Connection conn) {
        super(conn);
    }
    
    /**
     *
     * @return listDetailBulletin
     */
    public ArrayList<Detail_Bulletin> getList(){
        return listDetailBulletin;
    }
    
    /**
     *
     * @param bulletin
     */
    public void setBulletin(DAOBulletin bulletin){
        this.bulletin=bulletin;
    }
    
    /**
     *
     * @param enseignement
     */
    public void setEnseignement(DAOEnseignement enseignement){
        this.enseignement=enseignement;
    }
    
    /**
     *
     * @param evaluation
     */
    public void setEvaluation(DAOEvaluation evaluation){
        this.evaluation=evaluation;
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
     */
    @Override
    public void Bind(){
        ArrayList<Bulletin> listBulletin = new ArrayList<Bulletin>();
        listBulletin = bulletin.getList();
        
        ArrayList<Enseignement> listEnseignement = new ArrayList<Enseignement>();
        listEnseignement = enseignement.getList();
        
        for(Detail_Bulletin DB : listDetailBulletin){
            int id_BulletinDB = DB.get_ID_Bulletin();
            int id_EnseignementDB = DB.get_ID_Enseignement();
            
            for(Bulletin B : listBulletin){
                int id_BulletinB = B.get_ID_Bulletin();
                if (id_BulletinB == id_BulletinDB){
                    DB.setBulletin(B);
                    //System.out.println("Liaison effectué entre la table Detail Bulletin et Bulletin global, appreciation global= "+DB.getBulletin().get_Appreciation_Bulletin());
                }
            }
            
            for(Enseignement E : listEnseignement){
                int id_EnseignementE = E.get_ID_Enseignement();
                if (id_EnseignementE== id_EnseignementDB){
                    DB.setEnseignement(E);
                    //System.out.println("Liaison effectué entre la table Detail Bulletin et Enseignement,  = "+DB.getEnseignement().getDiscipline().get_Nom_Discipline());
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
            ResultSet result = stmt.executeQuery("SELECT * FROM detail_bulletin");
            while(result.next()){
                
                Detail_Bulletin matiere = new Detail_Bulletin(result.getInt("id_detail_bulletin"),result.getInt("id_bulletin"),result.getInt("id_enseignement"),result.getString("appreciation_detail"));
                listDetailBulletin.add(matiere);
            }
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOEcole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * methode de test
     */
    public void test(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_ecole", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT COUNT(*) AS tot_eval FROM detail_bulletin JOIN evaluation ON detail_bulletin.id_detail_bulletin=evaluation.id_detail_bulletin");
            ResultSet res = stmt.executeQuery("SELECT * FROM detail_bulletin JOIN evaluation ON detail_bulletin.id_detail_bulletin=evaluation.id_detail_bulletin");
            while(result.next()){
                int tot_ds = result.getInt("tot_ds");
                int i=0;
                
                do{
                    
                }while(i<tot_ds);
            }
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOEcole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    

    /**
     * Cette mthode calcule la moyenne de chaque élève
     */
        public void setMoyenneMatiereEleve(){

        ArrayList<Bulletin> listBulletin = new ArrayList<Bulletin>();
        listBulletin = bulletin.getList();
        
        ArrayList<Evaluation> listEvaluation = new ArrayList<Evaluation>();
        listEvaluation = evaluation.getList();
        
        for(Bulletin B : listBulletin){
             //nombre de copies de DS
            int id_bulletin = B.get_ID_Bulletin();
            for(Detail_Bulletin DB : listDetailBulletin){
                int nbDS = 0;
                float SommeNote=0;
                float moyenne_matiere_eleve=0;
                int id_detail_bulletin = DB.get_ID_Detail();
                int id_enseignement = DB.get_ID_Enseignement();
                
                for(Evaluation E : listEvaluation){
                    boolean b1 = E.getDetailBulletin().getBulletin().get_ID_Bulletin() == id_bulletin; //même id_bulletin
                    boolean b2 = E.get_ID_Detail_Bulletin() == id_detail_bulletin;
                   
                    if (b1&&b2){
                        //System.out.print("id_detail_bulletin="+DB.get_ID_Detail()+" => ");
                        SommeNote+=E.get_Note_Evaluation()*E.getCoeff();
                        //System.out.print(SommeNote+" => ");
                        nbDS+=E.getCoeff();
                        //System.out.print(" nbDS="+nbDS+"  ");
                        
                        if (SommeNote!=0){
                            moyenne_matiere_eleve = SommeNote/nbDS;
                            //System.out.println("moyenne="+moyenne_matiere_eleve);
                            DB.setMoyenneEleve(moyenne_matiere_eleve);
                            //System.out.println("moyenne="+DB.getMoyenneEleve());
                        }
                         else{
                             //System.out.print("id_detail_bulletin="+DB.get_ID_Detail()+" => ");
                         //System.out.println("moyenne="+moyenne_matiere_eleve);
                            DB.setMoyenneEleve(0); //aucune note, donc zero de moyenne
                        }
                    }
                }
                
                
            }
        }
    }
    
    /**
     * methode qui modifie la moyenne de la matière de la classe
     */
    public void setMoyenneMatiereClasse(){
        ArrayList<Classe> listClasse = new ArrayList<Classe>();
        listClasse = classe.getList();
        
        for(Classe C : listClasse){
            
            int nbEleves = C.getNbEleves();
            
            for(Detail_Bulletin DB : listDetailBulletin){
                int nbDS = 0; //nombre de copies de DS
                int id_enseignement = DB.get_ID_Enseignement();
                float SommeNoteMatiereClasse=0;
                float NoteMatiereClasse=0;
                for(Detail_Bulletin DB2 : listDetailBulletin){
                    //1er parcours de listDetailBulletin pour calculer sommer la moyenne des elèves pour une matière
                    boolean b1 = DB2.getBulletin().getInscription().get_ID_Classe()==C.get_ID_Classe(); //même classe
                    boolean b2 = DB2.get_ID_Enseignement()==id_enseignement;
                    if (b1 && b2){
                        SommeNoteMatiereClasse+=DB2.getMoyenneEleve();
                        nbDS+=1;
                        //System.out.print("DB_ID="+DB2.get_ID_Detail()+"  Moyennes="+SommeNoteMatiereClasse+" => ");
                    }
                }
                //System.out.println("");
                if (nbEleves!=0) NoteMatiereClasse=SommeNoteMatiereClasse/nbDS;
                for(Detail_Bulletin DB3 : listDetailBulletin){
                    //2ème parcours de listDetailBulletin pour affecter la moyenne de la classe calculée pour la matière
                    boolean b1 = DB3.getBulletin().getInscription().get_ID_Classe()==C.get_ID_Classe(); //même classe
                    boolean b2 = DB3.get_ID_Enseignement()==id_enseignement;
                    if (b1 && b2){
                        DB3.setMoyenneClasse(NoteMatiereClasse);
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
    public boolean create(Detail_Bulletin obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
         try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO detail_bulletin(id_detail_bulletin,id_bulletin,id_enseignement,appreciation_detail) VALUES(?,?,?,?)"
            );
            //insert param to change the ? into data
            statement.setObject(1, obj.get_ID_Detail(), Types.INTEGER);            
            statement.setObject(2, obj.get_ID_Bulletin(), Types.INTEGER);
            statement.setObject(3, obj.get_ID_Enseignement(), Types.INTEGER);
            statement.setObject(4, obj.get_Appreciation_Detail(), Types.VARCHAR);
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
    public boolean delete(Detail_Bulletin obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
         try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "DELETE FROM detail_bulletin WHERE id_bulletin=?"
            );
            //insert param to change the ? into data
            statement.setObject(1, obj.get_ID_Detail(), Types.INTEGER);
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
    public boolean update(Detail_Bulletin obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        try {
            // prefer prepareStatement as statement to avoid SQL injection
            PreparedStatement statement = this.connect.prepareStatement(
                    "UPDATE detail_bulletin SET id_bulletin=?,id_enseignement=?, appreciation_detail=?, WHERE id_bulletin=?"
            );
            //insert param to change the ? into data
            
            statement.setObject(1, obj.get_ID_Bulletin(), Types.INTEGER);
            statement.setObject(2, obj.get_ID_Enseignement(), Types.INTEGER);
            statement.setObject(3, obj.get_Appreciation_Detail(), Types.VARCHAR);
            statement.setObject(5, obj.get_ID_Detail(), Types.INTEGER);
            statement.executeUpdate(); //execute update for change in DB and executeQuery for select

        } catch (SQLException ex) {
            Logger.getLogger(DAOPersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    /**
  * Méthode de recherche des informations
  * @param id_detail_bulletin
  * @return T
  */
    @Override
    public Detail_Bulletin find(int id_detail_bulletin) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
            Detail_Bulletin detail_bulletin = new Detail_Bulletin();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM detail_bulletin WHERE id_bulletin = " + id_detail_bulletin);
      if(result.first())
        detail_bulletin = new Detail_Bulletin(
          id_detail_bulletin,          
          result.getInt("id_bulletin"),
          result.getInt("id_enseignement"),
          result.getString("appreciation_detail")
          );         
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return detail_bulletin;
    }
    
}
