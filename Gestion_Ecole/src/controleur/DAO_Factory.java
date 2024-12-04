/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;


import controleur.DAOClasse;
import controleur.DAOEvaluation;
import controleur.DAOBulletin;
import controleur.DAOAnnee_Scolaire;
import controleur.DAOPersonne;
import controleur.DAOInscription;
import controleur.DAOTrimestre;
import controleur.DAODiscipline;
import controleur.DAOEnseignement;
import controleur.DAO;
import controleur.DAONiveau;
import controleur.DAOEcole;
import controleur.DAODetail_Bulletin;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe Dao factory, liaison avec DAo
 * @author coline
 */
public class DAO_Factory {
    
     /**
     * constante static containing the connection variable of the class
     */
    protected static final Connection conn;

    /**
     * this is a specific way to init final variable throwing exceptions
     */
    static {
        Connection tmp = null;

        try {
            // subscribe to your DriverManager as we use mysql-connector add this :
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            // jdbc:mysl:// is the protocol URI (like http:// is for http)
            // localhost cause we are from a wamp server
            // ecole is the name of database
            // second parameter the id for connecting to the mysql db ( on phpmyadmin)
            // thirs parameter is the password
            tmp = DriverManager.getConnection("jdbc:mysql://localhost/gestion_ecole", "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAO_Factory.class.getName()).log(Level.SEVERE, null, ex);
        }
        conn = tmp;
    }

    /**
     *
     * @return 
     */
    public static DAO getAnneeScolaireDAO() {
        return new DAOAnnee_Scolaire(conn);
    }

    /**
     *
     * @return
     */
    public static DAO getBulletinDAO() {
        return new DAOBulletin(conn);
    }

    /**
     *
     * @return
     */
    public static DAO getClasseDAO() {
        return new DAOClasse(conn);
    }

    /**
     *
     * @return
     */
    public static DAO getDetailBulletinDAO() {
        return new DAODetail_Bulletin(conn);
    }

    /**
     *
     * @return
     */
    public static DAO getDisciplineDAO() {
        return new DAODiscipline(conn);
    }

    /**
     *
     * @return
     */
    public static DAO getEcoleDAO() {
        return new DAOEcole(conn);
    }

    /**
     *
     * @return
     */
    public static DAO getEnseignementDAO() {
        return new DAOEnseignement(conn);
    }

    /**
     *
     * @return
     */
    public static DAO getEvaluationDAO() {
        return new DAOEvaluation(conn);
    }

    /**
     *
     * @return
     */
    public static DAO getInscriptionDAO() {
        return new DAOInscription(conn);
    }

    /**
     *
     * @return
     */
    public static DAO getNiveauDAO() {
        return new DAONiveau(conn);
    }

    /**
     *
     * @return
     */
    public static DAO getPersonneDAO() {
        return new DAOPersonne(conn);
    }

    /**
     *
     * @return
     */
    public static DAO getTrimestreDAO() {
        return new DAOTrimestre(conn);
    }
    
}
