/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import controleur.DAOAnnee_Scolaire;
import controleur.DAOBulletin;
import controleur.DAOClasse;
import controleur.DAODS;
import controleur.DAODetail_Bulletin;
import controleur.DAODiscipline;
import controleur.DAOEcole;
import controleur.DAOEnseignement;
import controleur.DAOEvaluation;
import controleur.DAOInscription;
import controleur.DAONiveau;
import controleur.DAOPersonne;
import controleur.DAOTrimestre;
import java.util.ArrayList;

/**
 * Classe BDD déclaration et implementation des ArrayList
 * @author LOIC
 */
public class BDD {
    
    
    
    private ArrayList<Annee_Scolaire> ANNEE_SCOLAIRE;
    private ArrayList<Bulletin> BULLETIN ;
    private ArrayList<Classe> CLASSE ;
    private ArrayList<DS> DEVOIR_SURVEILLE;
    private ArrayList<Detail_Bulletin> DETAIL_BULLETIN;
    private ArrayList<Discipline> DISCIPLINE;
    private ArrayList<Ecole> ECOLE;
    private ArrayList<Enseignement> ENSEIGNEMENT;
    private ArrayList<Evaluation> EVALUATION;
    private ArrayList<Inscription> INSCRIPTION;
    private ArrayList<Niveau> NIVEAU;
    private ArrayList<Personne> PERSONNE;
    private ArrayList<Trimestre> TRIMESTRE;
            
    /**
     * CETTE METHODE EXTRAIT ET MET AUSSI A JOUR LA BDD
     * CALCULE AUSSI TOUTES LES MOYENNES ET RELIS CI-DESSOUS ENTRE EUX
     * C'est en quelque sorte un répertoire
     */
    public BDD(){
           //System.out.println("Table Ecole :");
           DAOEcole e = new DAOEcole();
           e.setData();
           ArrayList<Ecole> ecole = e.getList();
           /*for(Ecole ec : ecole) {
               System.out.println("id = "+ec.get_ID_Ecole()+"\tnom = "+ec.get_Nom_Ecole()+"\tAdresse = "+ec.get_Adresse_Ecole()); 
           }*/
           
           
           //System.out.println("");
           //System.out.println("Table Année_Scolaire :");
           DAOAnnee_Scolaire a = new DAOAnnee_Scolaire();
           a.setData();
           ArrayList<Annee_Scolaire> annee = a.getList();/*
           for(Annee_Scolaire an : annee) {
               System.out.println("annee = "+an.get_ID_Annee()); 
           }
           
           
           System.out.println("");
           System.out.println("Table Personne :");*/
           DAOPersonne p = new DAOPersonne();
           p.setData();
           ArrayList<Personne> personne = p.getList();/*
           for(Personne pers : personne) {
               System.out.println("id = "+pers.get_ID_Personne()+"\tCivilité = "+pers.getCivilite()+"\tnom = "+pers.get_Nom_Personne()+"\tPrénom = "+pers.get_Prenom_Personne()+"\tType = "+pers.get_Type_Personne()+"\tlogin = "+pers.getLogin()); 
           }
           
           
           System.out.println("");
           System.out.println("Table Niveau :");*/
           DAONiveau n = new DAONiveau();
           n.setData();
           ArrayList<Niveau> niveau = n.getList();/*
           for(Niveau niv : niveau) {
               System.out.println("id = "+niv.get_ID_Niveau()+"\tNiveau = "+niv.get_Nom_Niveau()); 
           }
           
           
           System.out.println("");
           System.out.println("Table Discipline :");*/
           DAODiscipline d = new DAODiscipline();
           d.setData();
           ArrayList<Discipline> discipline = d.getList();/*
           for(Discipline dis : discipline) {
               System.out.println("id = "+dis.get_ID_Discipline()+"\tNiveau = "+dis.get_Nom_Discipline()); 
           }
           
           
           System.out.println("");
           System.out.println("Table Enseignement :");*/
           DAOEnseignement ens = new DAOEnseignement();
           ens.setData();
           ArrayList<Enseignement> enseignement = ens.getList();/*
           for(Enseignement prof : enseignement) {
               System.out.println("id = "+prof.get_ID_Enseignement()+"\tclasseID = "+prof.get_ID_Classe()+"\tDisciplineID = "+prof.get_ID_Discipline()+"\tPersonneID = "+prof.get_ID_Personne()); 
           }
           
           System.out.println("");
           System.out.println("Table Classe :");*/
           DAOClasse c = new DAOClasse();
           c.setData();
           ArrayList<Classe> classe = c.getList();/*
           for(Classe C : classe) {
               System.out.println("ClasseID = "+C.get_ID_Classe()+"\tNom Classe = "+C.get_Nom_Classe()+"\tEcoleID = "+C.get_ID_Ecole()+"\tNiveauID = "+C.get_ID_Niveau()+"\tAnnee="+C.get_ID_Annee_Scolaire()); 
           }
           
           
           System.out.println("");
           System.out.println("Table Inscription :");*/
           DAOInscription i = new DAOInscription();
           i.setData();
           ArrayList<Inscription> inscription = i.getList();/*
           for(Inscription I : inscription) {
               System.out.println("InscriptionID = "+I.get_ID_Inscription()+"\tClasseID = "+I.get_ID_Classe()+"\tPersonneID = "+I.get_ID_Personne()); 
           }
           
           System.out.println("");
           System.out.println("Table Trimestre :");*/
           DAOTrimestre t = new DAOTrimestre();
           t.setData();
           ArrayList<Trimestre> trimestre = t.getList();/*
           for(Trimestre I : trimestre) {
               System.out.println("TrimestreID = "+I.get_ID_Trimestre()+"\tTrimestre n°"+I.get_Numero_Trimestre()+"\tDebut = "+I.get_Date_Debut()+"\tFin = "+I.get_Date_Fin()+"\tAnnée = "+I.get_ID_Annee_Scolaire()); 
           }
           
           
           System.out.println("");
           System.out.println("Table Bulletin Global :");*/
           DAOBulletin b = new DAOBulletin();
           b.setData();
           ArrayList<Bulletin> bulletin_global = b.getList();/*
           for(Bulletin B : bulletin_global) {
               System.out.println("Bulletin_Global_ID = "+B.get_ID_Bulletin()+"\tTrimestre ID = "+B.get_ID_Trimestre()+"\tInscriptionID = "+B.get_ID_Inscription()+"\tApréciation = "+B.get_Appreciation_Bulletin()); 
           }
           
           
           System.out.println("");
           System.out.println("Table Bulletin Detail :");*/
           DAODetail_Bulletin db = new DAODetail_Bulletin();
           db.setData();
           ArrayList<Detail_Bulletin> matiere = db.getList();/*
           for(Detail_Bulletin B : matiere) {
               System.out.println("Bulletin_Global_ID = "+B.get_ID_Bulletin()+"\tBulletinID = "+B.get_ID_Bulletin()+"\tEnseignementID = "+B.get_ID_Enseignement()+"\tAppréciation = "+B.get_Appreciation_Detail()); 
           }
           
           
           System.out.println("");
           System.out.println("Table Evaluation :");*/
           DAOEvaluation eval = new DAOEvaluation();
           eval.setData();
           ArrayList<Evaluation> DS = eval.getList();/*
           for(Evaluation B : DS) {
               System.out.println("Evaluation_ID = "+B.get_ID_Evaluation()+"\tDetail_BulletinID = "+B.get_ID_Detail_Bulletin()+"\tID_DS = "+B.get_ID_DS()+"\tNoteID = "+B.get_Note_Evaluation()+"\tCoeff = "+B.getCoeff()+"\tAppréciation = "+B.get_Appreciation_Evaluation()); 
           }
           
           System.out.println("");
           System.out.println("Table DS :");*/
           DAODS ds = new DAODS();
           ds.setData();
           ArrayList<DS> ListNomDS = ds.getList();/*
           for(DS D : ListNomDS) {
               System.out.println("DS_ID = "+D.get_ID_DS()+"\tNom_DS = "+D.get_Nom_DS()); 
           }
           
           
           //LIAISON ENTRE CHAQUE CLASSE
           //System.out.println("");*/
           t.setAnneeScolaire(a);
           t.Bind();
           
           //System.out.println("");
           c.setAnneeScolaire(a);
           c.setNiveau(n);
           c.setEcole(e);
           c.Bind();
           
           //System.out.println("");
           i.setClasse(c);
           i.setPersonne(p);
           i.Bind();
           
           //System.out.println("");
           b.setInscription(i);
           b.setTrimestre(t);
           b.Bind();
           
           //System.out.println("");
           ens.setDiscipline(d);
           ens.setPersonne(p);
           ens.setClasse(c);
           ens.Bind();
           
           //System.out.println("");
           db.setEnseignement(ens);
           db.setBulletin(b);
           db.Bind();
           
           //System.out.println("");
           eval.setDetailBulletin(db);
           eval.setDS(ds);
           eval.Bind();
           
           
           i.setNbEleves(); //Calcule le nb cl'élèves dans chaque classe
           /*System.out.println("");
           for(Classe C : classe) { 
               System.out.println("ClasseID = "+C.get_ID_Classe()+"\tNom Classe = "+C.get_Nom_Classe()+"\tEcoleID = "+C.get_ID_Ecole()+"\tNiveauID = "+C.get_ID_Niveau()+"\tAnnee="+C.get_ID_Annee_Scolaire()+"\tNbEleves = "+C.getNbEleves()); 
           }
           */
           
           eval.setClasse(c);
           eval.setMoyenneDSClasse();
           /*System.out.println("");
           for(Evaluation E : eval.getList()) { 
               System.out.println("evalID = "+E.get_ID_Evaluation()+"\tMoyenne Classe = "+E.getMoyenneEvaluation()); 
           }*/
           
           
           
           db.setEvaluation(eval);
           db.setMoyenneMatiereEleve();
           /*System.out.println("");
           for(Detail_Bulletin DB : db.getList()) { 
               System.out.println("DB_ID = "+DB.get_ID_Detail()+"\tElève = "+DB.getBulletin().getInscription().getPersonne().get_Nom_Personne()
                +"\tMatière ="+DB.getEnseignement().getDiscipline().get_Nom_Discipline()+"\tMoyenne Eleve = "+DB.getMoyenneEleve()); 
           }//MArche bien !
           */
           
           db.setClasse(c);
           db.setMoyenneMatiereClasse();/*
           System.out.println("");
           for(Detail_Bulletin DB : db.getList()) { 
               System.out.println("DB_ID = "+DB.get_ID_Detail()+"\tElève = "+DB.getBulletin().getInscription().getPersonne().get_Nom_Personne()
                +"\tMatière ="+DB.getEnseignement().getDiscipline().get_Nom_Discipline()+"\tMoyenne Eleve = "+DB.getMoyenneEleve()
               +"\tMoyenne Classe="+DB.getMoyenneClasse()); 
           }
           */
           
           b.setDetailBulletin(db);
           b.setMoyenneGeneraleEleve();/*
           System.out.println("");
           for(Bulletin B : b.getList()) { 
               System.out.println("BulletinID="+B.get_ID_Bulletin()+"\tMoyenne Genérale Elève="+B.getMoyenneGeneraleEleve());
           }*/
           
           b.setClasse(c);
           b.setMoyenneGeneraleClasse();/*
           System.out.println("");
           for(Bulletin B : b.getList()) { 
               System.out.println("BulletinID="+B.get_ID_Bulletin()+"\tMoyenne Genérale Elève="+B.getMoyenneGeneraleEleve()+"\tMoyenne Genérale Classe="+B.getMoyenneGeneraleClasse());
           }*/
           
           
           
            this.ANNEE_SCOLAIRE = new ArrayList<>();
            this.BULLETIN = new ArrayList<>();
            this.CLASSE = new ArrayList<>();
            this.DEVOIR_SURVEILLE = new ArrayList<>();
            this.DETAIL_BULLETIN = new ArrayList<>();
            this.DISCIPLINE = new ArrayList<>();
            this.ECOLE = new ArrayList<>();
            this.ENSEIGNEMENT = new ArrayList<>();
            this.EVALUATION = new ArrayList<>();
            this.INSCRIPTION = new ArrayList<>();
            this.NIVEAU = new ArrayList<>();
            this.PERSONNE = new ArrayList<>();
            this.TRIMESTRE = new ArrayList<>();
            
            
            setListAnneeScolaire(a.getList());
            setListBulletin(b.getList());
            setListClasse(c.getList());
            setListDS(ds.getList());
            setListDetailBulletin(db.getList());
            setListDiscipline(d.getList());
            setListEcole(e.getList());
            setListEnseignement(ens.getList());
            setListEvaluation(eval.getList());
            setListInscription(i.getList());
            setListNiveau(n.getList());
            setListPersonne(p.getList());
            setListTrimestre(t.getList());
    }//fin constructeur
    
    
    
    //TOUS LES GETTERS

    /**
     * 
     * @return
     */
        public ArrayList<Annee_Scolaire> getListAnneeScolaire(){
        return ANNEE_SCOLAIRE;
    }

    /**
     *
     * @return
     */
    public ArrayList<Bulletin> getListBulletin() {
        return BULLETIN ;
    }

    /**
     *
     * @return
     */
    public ArrayList<Classe> getListClasse(){
        return CLASSE ;
    }

    /**
     *
     * @return
     */
    public ArrayList<DS> getListDS(){
        return DEVOIR_SURVEILLE;
    }

    /**
     *
     * @return
     */
    public ArrayList<Detail_Bulletin> getListDetailBulletin(){
        return DETAIL_BULLETIN;
    }

    /**
     *
     * @return
     */
    public ArrayList<Discipline> getListDiscipline(){
        return DISCIPLINE;
    }

    /**
     *
     * @return
     */
    public ArrayList<Ecole> getListEcole(){
        return ECOLE;
    }

    /**
     *
     * @return
     */
    public ArrayList<Enseignement> getListEnseignement(){
        return ENSEIGNEMENT;
    }

    /**
     *
     * @return
     */
    public ArrayList<Evaluation> getListEvaluation(){
        return EVALUATION;
    }

    /**
     *
     * @return
     */
    public ArrayList<Inscription> getListInscription(){
        return INSCRIPTION;
    }

    /**
     *
     * @return
     */
    public ArrayList<Niveau> getListNiveau(){
        return NIVEAU;
    }

    /**
     *
     * @return
     */
    public ArrayList<Personne> getListPersonne(){
        return PERSONNE;
    }

    /**
     *
     * @return
     */
    public ArrayList<Trimestre> getListTrimestre(){
        return TRIMESTRE;
    }
    
    
    
     //TOUS LES SETTERS

    /**
     *
     * @param a
     */
        public void setListAnneeScolaire(ArrayList<Annee_Scolaire> a){
        this.ANNEE_SCOLAIRE=a;
    }

    /**
     *
     * @param a
     */
    public void setListBulletin(ArrayList<Bulletin> a) {
        this.BULLETIN=a;
    }

    /**
     *
     * @param a
     */
    public void setListClasse(ArrayList<Classe> a){
        this.CLASSE=a;
    }

    /**
     *
     * @param a
     */
    public void setListDS(ArrayList<DS> a){
        this.DEVOIR_SURVEILLE=a;
    }

    /**
     *
     * @param a
     */
    public void setListDetailBulletin(ArrayList<Detail_Bulletin> a){
        this.DETAIL_BULLETIN=a;
    }

    /**
     *
     * @param a
     */
    public void setListDiscipline(ArrayList<Discipline> a){
        this.DISCIPLINE=a;
    }

    /**
     *
     * @param a
     */
    public void setListEcole(ArrayList<Ecole> a){
        this.ECOLE=a;
    }

    /**
     *
     * @param a
     */
    public void setListEnseignement(ArrayList<Enseignement> a){
        this.ENSEIGNEMENT=a;
    }

    /**
     *
     * @param a
     */
    public void setListEvaluation(ArrayList<Evaluation> a){
        this.EVALUATION=a;
    }

    /**
     *
     * @param a
     */
    public void setListInscription(ArrayList<Inscription> a){
        this.INSCRIPTION=a;
    }

    /**
     *
     * @param a
     */
    public void setListNiveau(ArrayList<Niveau> a){
        this.NIVEAU=a;
    }

    /**
     *
     * @param a
     */
    public void setListPersonne(ArrayList<Personne> a){
        this.PERSONNE=a;
    }

    /**
     *
     * @param a
     */
    public void setListTrimestre(ArrayList<Trimestre> a){
        this.TRIMESTRE=a;
    }
    
     
}
