/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import modele.BDD;
import modele.Detail_Bulletin;
import modele.Evaluation;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 *Cette classe montre l'histogramme des notes des élèves pour le professeur
 * @author LOIC CHEONG
 */
public class BarChart extends JFrame {

    /**
     * Constructeur qui affiche l'histogramme des notes dans la classe du professeur
      * @param id_personne entier : identifiant du professeur
      * @param numTrimestre entier : numéro du trimestre du bulletin sélectionné dans le ComboBox
      * @param anneeScolaire entier : année scolaire du bulletin sélectionné dans le ComboBox
      * @param NomClasse  entier : nom de la classe sélectionné dans la liste de bulletin
     */
    public BarChart(int id_personne, int numTrimestre,int anneeScolaire,String NomClasse ) {
        if (id_personne==0 || numTrimestre==0 || anneeScolaire==0 || NomClasse.equals("")){
            JOptionPane.showMessageDialog(null, "Vous n'avez pas sélectionné un bulletin !","",WARNING_MESSAGE);
        }
             
        else{
        
            BDD bdd = new BDD();
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for(Detail_Bulletin db : bdd.getListDetailBulletin()){
                if (db.getEnseignement().get_ID_Personne()==id_personne 
                    && db.getBulletin().getTrimestre().get_Numero_Trimestre()==numTrimestre
                    && db.getBulletin().getTrimestre().get_ID_Annee_Scolaire()==anneeScolaire
                    && db.getBulletin().getInscription().getClasse().get_Nom_Classe().equals(NomClasse)
                    && db.getBulletin().getInscription().getClasse().get_ID_Annee_Scolaire()== anneeScolaire){
		for(Evaluation E : bdd.getListEvaluation()){
                    if(E.get_ID_Detail_Bulletin()==db.get_ID_Detail()){
                         //note - DS - Eleve
                         dataset.addValue(E.get_Note_Evaluation(), E.getDS().get_Nom_DS(), 
                            db.getBulletin().getInscription().getPersonne().get_Nom_Personne()+" "+
                            db.getBulletin().getInscription().getPersonne().get_Prenom_Personne());
                    }
                }
                }
            }
            JFreeChart chart = ChartFactory.createBarChart(
            "Note des devoirs", // chart title
            "Category", // axe des abscisses
            "Note", // axe des ordonnées
            dataset, // data
            PlotOrientation.VERTICAL, // orientation
            true, // include legend
            true, // tooltips?
            false // URLs?
            );
    
            ChartPanel chartPanel = new ChartPanel(chart, false);
            chartPanel.setPreferredSize(new Dimension(700, 500));
            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setContentPane(chartPanel);
            setLocationRelativeTo(null);
            setVisible(true);
        }
}

    
}
