/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controleur.DAOEcole;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modele.*;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author LOIC CHEONG
 * @author coline
 */
public class PageEnseignant2 extends javax.swing.JFrame {
    

     // Variables declaration - do not modify                     
    private javax.swing.JLabel AdresseEcole;
    private javax.swing.JLabel Matiere;
    private javax.swing.JLabel NomEcole;
    private javax.swing.JLabel NomPrenomProf;
    private javax.swing.JLabel Titre_page;
    private javax.swing.JButton deconnexion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private int id_personne;
    private String NomClasse;
    private int numTrimestre;
    private int anneeScolaire;
    
    
    /**
     * Constructeur qui affiche la page du professeur
     * @param P Prend en paramètre l'objet type Personne qui a réussi à se connecter
     */
    public PageEnseignant2(Personne P){
        NomClasse ="";
        numTrimestre =0;
        anneeScolaire=0;
        
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        Titre_page = new javax.swing.JLabel();
        NomPrenomProf = new javax.swing.JLabel();
        NomEcole = new javax.swing.JLabel();
        AdresseEcole = new javax.swing.JLabel();
        Matiere = new javax.swing.JLabel();
        deconnexion = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Elève", "Coeff", "Note/Moyenne", "Moyenne Classe", "Appréciation"
            }
        ){
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Float.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        //Fixe la largeur de la taille des colonnes dans le tableau
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(100);//Elève
            jTable1.getColumnModel().getColumn(0).setMaxWidth(130);
            jTable1.getColumnModel().getColumn(1).setMinWidth(50);//coeff
            jTable1.getColumnModel().getColumn(1).setMaxWidth(50);
            jTable1.getColumnModel().getColumn(2).setMinWidth(100);//moyenne élève
            jTable1.getColumnModel().getColumn(2).setMaxWidth(100);
            jTable1.getColumnModel().getColumn(3).setMinWidth(100);//moyenne classe
            jTable1.getColumnModel().getColumn(3).setMaxWidth(100);
        }

        
        //Fixation du texte des JLabel
        BDD bdd = new BDD();
        id_personne = P.get_ID_Personne();
        Titre_page.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Titre_page.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titre_page.setText("\t\tPage Enseignant");
        NomPrenomProf.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        NomPrenomProf.setText(P.get_Prenom_Personne()+"  "+P.get_Nom_Personne());
        String nomEcole = "";
        String adresseEcole = "";
        String matiere = "";
        for(Enseignement Ens : bdd.getListEnseignement()){
            if (Ens.get_ID_Personne()==id_personne){//recherche du professeur dans la liste avec le même identifiant
                matiere = "Professeur de "+Ens.getDiscipline().get_Nom_Discipline();
                nomEcole = Ens.getClasse().getEcole().get_Nom_Ecole();
                adresseEcole = Ens.getClasse().getEcole().get_Adresse_Ecole();
            }
        }
        Matiere.setText(matiere);
        NomEcole.setText(nomEcole);
        AdresseEcole.setText(adresseEcole);

       

        deconnexion.setText("Deconnexion");
        deconnexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deconnexionActionPerformed(evt);
            }
        });

        
        
        /*calcul du nombre de bulletins qu'a accès le professeur
        Exemple :
        Le professeur X a accès aux bulletins :
        - TS1 Trimestre n°2 Année Scolaire 2018-2019
        - TS1 Trimestre n°1 Année Scolaire 2018-2019
        - TS2 Trimestre n°2 Année Scolaire 2018-2019
        - TS2 Trimestre n°1 Année Scolaire 2018-2019
        Il a donc accès à 4 bulletins où il peut rajouter, modifier, supprimer un DS ou une note
        */
        int TotalNbBulletin=0;
        for (Trimestre T : bdd.getListTrimestre()){
            int NbIdTrimestreIdentique = 0;
            for(Bulletin B : bdd.getListBulletin()) {
                if (B.get_ID_Trimestre()==T.get_ID_Trimestre()) NbIdTrimestreIdentique++;
            }
            int NbBulletin=0;
            for (Detail_Bulletin DB : bdd.getListDetailBulletin()){
                if (DB.getEnseignement().get_ID_Personne()==id_personne 
                        && DB.getBulletin().getTrimestre().get_ID_Trimestre()==T.get_ID_Trimestre()){
                    NbBulletin++;
                }
            }
            if (NbIdTrimestreIdentique != 0){
                NbBulletin = NbBulletin/NbIdTrimestreIdentique;
                TotalNbBulletin +=NbBulletin;
            }
        }
        //System.out.print("Le professeur a "+TotalNbBulletin+"bulletins");
        
        
        
        //Le contenu du JComboBox est de la forme "TS2 Trimestre n°X Année Scolaire 2017-2018"
        String[] classes = new String[TotalNbBulletin];
        for (int j=0;j<TotalNbBulletin;j++){
            classes[j]="";//On remplie le tableau statique "classes" pour pouvoir faire une comparaison entre String plus tard 
        }
        int i=1;
        for (Classe C : bdd.getListClasse()){
            int id_classe = C.get_ID_Classe();
            for(Trimestre T : bdd.getListTrimestre()){
                for (Detail_Bulletin DB : bdd.getListDetailBulletin()){
                    if (DB.getEnseignement().get_ID_Personne()==id_personne 
                        && DB.getBulletin().getInscription().get_ID_Classe()==id_classe
                        && DB.getBulletin().get_ID_Trimestre()==T.get_ID_Trimestre()){
                        String NomBulletin = DB.getBulletin().getInscription().getClasse().get_Nom_Classe()+" trimestre n°"+
                                DB.getBulletin().getTrimestre().get_Numero_Trimestre()+" année scolaire "+
                                DB.getBulletin().getTrimestre().get_ID_Annee_Scolaire()+"-"+
                                (DB.getBulletin().getTrimestre().get_ID_Annee_Scolaire()+1);
                        if (classes[i-1].equals(NomBulletin)==false){
                            classes[i-1]=NomBulletin;
                            if (i<TotalNbBulletin) i++;
                        }
                        
                    }
                }
            }
        }
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(classes));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showTableContentActionPerformed(evt);
            }
        });
        
        
        
        
        jButton1.setText("Ajouter un DS");
        jButton2.setText("Supprimer un DS");
        jButton3.setText("Modifier une note");
        jButton4.setText("Modifier coeff");
        jButton5.setText("Modifier appréciation");
        jButton6.setText("Histogramme");
        
        
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjouterDS(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SupprimeDS(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               JOptionPane.showMessageDialog(null, "Option indisponible pour le moment");
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               JOptionPane.showMessageDialog(null, "Option indisponible pour le moment");
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               JOptionPane.showMessageDialog(null, "Option indisponible pour le moment");
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               Histogramme(evt);
            }
        });
        

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NomPrenomProf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Matiere, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(NomEcole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(AdresseEcole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Titre_page, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(462, 462, 462)
                        .addComponent(deconnexion, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2147482747, 2147482747, 2147482747))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Titre_page, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deconnexion, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(NomPrenomProf, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Matiere, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NomEcole, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AdresseEcole, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addGap(56, 56, 56))
        );

        Titre_page.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(40,40,40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(116, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(930, 727));
        setLocationRelativeTo(null);
    }

    
    
    
    /**
     * Méthode qui déconnexion le professeur de sa page et le renvoie vers la page de connection
     * Elle est utilisée dans un actionListenet du bouton de Déconnexion
     * @param evt java.awt.event.ActionEvent
     */
    private void deconnexionActionPerformed(java.awt.event.ActionEvent evt) {                                            
        //Le professeur se déconnecte
        this.setVisible(false);//cache la page professeur
        
        PageConnexion pg = new PageConnexion();
        pg.setVisible(true);
        pg.setTitle("Connexion");
        pg.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        pg.setSize(500,430);
        pg.setResizable(false); //empêche le redimensionnement
        pg.setLocationRelativeTo(null); //fenêtre positionné au centre de l'écran de l'ordinateur
    }
    
    
    /**
     * Le JComboBox de la page du professeur a un ActionListener
     * Selon l'item sélectionné, le tableau est rempli par les notes de DS des élèves avec leurs moyennes
     * @param evt java.awt.event.ActionEvent
     */
    private void showTableContentActionPerformed(java.awt.event.ActionEvent evt) {
        String cb = (String) jComboBox1.getSelectedItem().toString();
        //cb est de la forme : TS2 Trimestre n°1 Année Scolaire 2018-2019
        //cb est formalisé !!
        //System.out.println(cb);
        NomClasse = cb.substring(0,3);//on extrait une partie de la chaine de caractère pour avoir le nom de la classe
        //System.out.println(NomClasse);
        numTrimestre = Integer.valueOf(cb.substring(16,17)).intValue();
        //System.out.println(numTrimestre);
        anneeScolaire = Integer.valueOf(cb.substring(33,37)).intValue();
        //System.out.println(anneeScolaire);
        
        
        
        //nettoyage du tableau
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Elève", "Coeff", "Note/Moyenne", "Moyenne Classe", "Appréciation"
            }
        ){  Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Float.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        //Fixe la largeur de la taille des colonnes dans le tableau
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(100);//Elève
            jTable1.getColumnModel().getColumn(0).setMaxWidth(130);
            jTable1.getColumnModel().getColumn(1).setMinWidth(50);//coeff
            jTable1.getColumnModel().getColumn(1).setMaxWidth(50);
            jTable1.getColumnModel().getColumn(2).setMinWidth(100);//moyenne élève
            jTable1.getColumnModel().getColumn(2).setMaxWidth(100);
            jTable1.getColumnModel().getColumn(3).setMinWidth(100);//moyenne classe
            jTable1.getColumnModel().getColumn(3).setMaxWidth(100);
        }
        
        
        
        
        
        //Remplissage du tableau
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        //for(int i=0;i<5;i++) model.removeRow(i);
        Object[] row = new Object[5];
        BDD bdd = new BDD();
        
        for(Detail_Bulletin db : bdd.getListDetailBulletin()){
            if (db.getEnseignement().get_ID_Personne()==id_personne 
                && db.getBulletin().getTrimestre().get_Numero_Trimestre()==numTrimestre
                && db.getBulletin().getTrimestre().get_ID_Annee_Scolaire()==anneeScolaire
                && db.getBulletin().getInscription().getClasse().get_Nom_Classe().equals(NomClasse)
                && db.getBulletin().getInscription().getClasse().get_ID_Annee_Scolaire()== anneeScolaire){
                
                row[0] = db.getBulletin().getInscription().getPersonne().getCivilite()+" "+
                        db.getBulletin().getInscription().getPersonne().get_Prenom_Personne() + " "+
                        db.getBulletin().getInscription().getPersonne().get_Nom_Personne();
                row[1] = "";//Coefficient
                row[2] = Math.round(db.getMoyenneEleve() * 100.0) /100.0;//moyenne et le matière et notes de DS
                row[3] = Math.round(db.getMoyenneClasse() * 100.0) /100.0;//Moyenne de la matière dans la classe
                row[4] = db.get_Appreciation_Detail(); //Appréciation
                model.addRow(row);
		for(Evaluation E : bdd.getListEvaluation()){
                    if(E.get_ID_Detail_Bulletin()==db.get_ID_Detail()){
			row[0] = E.getDS().get_Nom_DS();//Nom du devoir
                        row[1] = E.getCoeff();//Coefficient
                        row[2] = Math.round(E.get_Note_Evaluation() * 100.0) /100.0;//moyenne et le matière et notes de DS
                        row[3] = Math.round(E.getMoyenneEvaluation() * 100.0) /100.0;//Moyenne de la matière dans la classe
                        row[4] = E.get_Appreciation_Evaluation(); //Appréciation
                        model.addRow(row);
                    }
		}
            }
        }
    }//end of showTableContentActionPerformed method
    
    
    
    /**
     * Méthode privée qui affiche la fenêtre pour ajouter un devoir/DS/contôle/intérogation
     * Et remet à jour le tableau
     * @param evt ActionEvent
     */
    private void AjouterDS(java.awt.event.ActionEvent evt) {
        FrameAjoutDS ds = new FrameAjoutDS(id_personne,numTrimestre,anneeScolaire,NomClasse);
        
        
        //A PARTIR DE CETTE LIGNE, LE CODE EST IDENTIQUE A LA METHODE showTableContentActionPerformed
        //nettoyage du tableau
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Elève", "Coeff", "Note/Moyenne", "Moyenne Classe", "Appréciation"
            }
        ){  Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Float.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        //Fixe la largeur de la taille des colonnes dans le tableau
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(100);//Elève
            jTable1.getColumnModel().getColumn(0).setMaxWidth(130);
            jTable1.getColumnModel().getColumn(1).setMinWidth(50);//coeff
            jTable1.getColumnModel().getColumn(1).setMaxWidth(50);
            jTable1.getColumnModel().getColumn(2).setMinWidth(100);//moyenne élève
            jTable1.getColumnModel().getColumn(2).setMaxWidth(100);
            jTable1.getColumnModel().getColumn(3).setMinWidth(100);//moyenne classe
            jTable1.getColumnModel().getColumn(3).setMaxWidth(100);
        }
        
        
        
        
        
        //Remplissage du tableau
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        //for(int i=0;i<5;i++) model.removeRow(i);
        Object[] row = new Object[5];
        BDD bdd = new BDD();
        
        for(Detail_Bulletin db : bdd.getListDetailBulletin()){
            if (db.getEnseignement().get_ID_Personne()==id_personne 
                && db.getBulletin().getTrimestre().get_Numero_Trimestre()==numTrimestre
                && db.getBulletin().getTrimestre().get_ID_Annee_Scolaire()==anneeScolaire
                && db.getBulletin().getInscription().getClasse().get_Nom_Classe().equals(NomClasse)
                && db.getBulletin().getInscription().getClasse().get_ID_Annee_Scolaire()== anneeScolaire){
                
                row[0] = db.getBulletin().getInscription().getPersonne().getCivilite()+" "+db.getBulletin().getInscription().getPersonne().get_Nom_Personne();
                row[1] = "";//Coefficient
                row[2] = db.getMoyenneEleve();//moyenne et le matière et notes de DS
                row[3] = db.getMoyenneClasse();//Moyenne de la matière dans la classe
                row[4] = db.get_Appreciation_Detail(); //Appréciation
                model.addRow(row);
		for(Evaluation E : bdd.getListEvaluation()){
                    if(E.get_ID_Detail_Bulletin()==db.get_ID_Detail()){
			row[0] = E.getDS().get_Nom_DS();//Nom du devoir
                        row[1] = E.getCoeff();//Coefficient
                        row[2] = E.get_Note_Evaluation();//moyenne et le matière et notes de DS
                        row[3] = E.getMoyenneEvaluation();//Moyenne de la matière dans la classe
                        row[4] = E.get_Appreciation_Evaluation(); //Appréciation
                        model.addRow(row);
                    }
		}
            }
        }
    }
    
    
    /**
     * Méthode privée qui affiche la fenêtre pour supprimer un devoir/DS/contôle/intérogation
     * Et remet à jour le tableau
     * @param evt ActionEvent
     */
    private void SupprimeDS(java.awt.event.ActionEvent evt) {
        FrameSupprimeDS ds = new FrameSupprimeDS(id_personne,numTrimestre,anneeScolaire,NomClasse);
        //A PARTIR DE CETTE LIGNE, LE CODE EST IDENTIQUE A LA METHODE showTableContentActionPerformed
        //nettoyage du tableau
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Elève", "Coeff", "Note/Moyenne", "Moyenne Classe", "Appréciation"
            }
        ){  Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Float.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        //Fixe la largeur de la taille des colonnes dans le tableau
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(100);//Elève
            jTable1.getColumnModel().getColumn(0).setMaxWidth(130);
            jTable1.getColumnModel().getColumn(1).setMinWidth(50);//coeff
            jTable1.getColumnModel().getColumn(1).setMaxWidth(50);
            jTable1.getColumnModel().getColumn(2).setMinWidth(100);//moyenne élève
            jTable1.getColumnModel().getColumn(2).setMaxWidth(100);
            jTable1.getColumnModel().getColumn(3).setMinWidth(100);//moyenne classe
            jTable1.getColumnModel().getColumn(3).setMaxWidth(100);
        }
        
        
        
        
        
        //Remplissage du tableau
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        //for(int i=0;i<5;i++) model.removeRow(i);
        Object[] row = new Object[5];
        BDD bdd = new BDD();
        
        for(Detail_Bulletin db : bdd.getListDetailBulletin()){
            if (db.getEnseignement().get_ID_Personne()==id_personne 
                && db.getBulletin().getTrimestre().get_Numero_Trimestre()==numTrimestre
                && db.getBulletin().getTrimestre().get_ID_Annee_Scolaire()==anneeScolaire
                && db.getBulletin().getInscription().getClasse().get_Nom_Classe().equals(NomClasse)
                && db.getBulletin().getInscription().getClasse().get_ID_Annee_Scolaire()== anneeScolaire){
                
                row[0] = db.getBulletin().getInscription().getPersonne().getCivilite()+" "+db.getBulletin().getInscription().getPersonne().get_Nom_Personne();
                row[1] = "";//Coefficient
                row[2] = db.getMoyenneEleve();//moyenne et le matière et notes de DS
                row[3] = db.getMoyenneClasse();//Moyenne de la matière dans la classe
                row[4] = db.get_Appreciation_Detail(); //Appréciation
                model.addRow(row);
		for(Evaluation E : bdd.getListEvaluation()){
                    if(E.get_ID_Detail_Bulletin()==db.get_ID_Detail()){
			row[0] = E.getDS().get_Nom_DS();//Nom du devoir
                        row[1] = E.getCoeff();//Coefficient
                        row[2] = E.get_Note_Evaluation();//moyenne et le matière et notes de DS
                        row[3] = E.getMoyenneEvaluation();//Moyenne de la matière dans la classe
                        row[4] = E.get_Appreciation_Evaluation(); //Appréciation
                        model.addRow(row);
                    }
		}
            }
        }
    }
    
    /**
     * Méthode privée qui affiche l'histogramme des notes pour une classe selon le trimestre
     * @param evt java.awt.event.ActionEvent
     */
    private void Histogramme(java.awt.event.ActionEvent evt){
        BarChart demo = new BarChart(id_personne,numTrimestre,anneeScolaire,NomClasse);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
    
    }

    
    
    
    
                     
}
