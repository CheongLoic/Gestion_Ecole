/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controleur.DAOEcole;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modele.*;
import vue.PageConnexion;

/**
 * Classe interface graphique pour un élève avec son bulletin
 * @author LOIC
 */
public class PageEleve2 extends javax.swing.JFrame {

    // Variables declaration - do not modify                     
    private javax.swing.JLabel AdresseEcole;
    private javax.swing.JLabel Bulletin;
    private javax.swing.JLabel Niveau;
    private javax.swing.JLabel NomEcole;
    private javax.swing.JLabel NomPrenomEleve;
    private javax.swing.JTable Tableau;
    private javax.swing.JLabel Titre_page;
    private javax.swing.JButton deconnexion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private int id_personne;
    // End of variables declaration    
    
    
    /**
     * Crée et affiche une fenêtre de la page élève avec ses informations de bulletins à l'intérieur
     * @param P
     */
    public PageEleve2(Personne P) {
        jScrollPane1 = new javax.swing.JScrollPane();
        Tableau = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        Titre_page = new javax.swing.JLabel();
        NomPrenomEleve = new javax.swing.JLabel();
        Niveau = new javax.swing.JLabel();
        NomEcole = new javax.swing.JLabel();
        AdresseEcole = new javax.swing.JLabel();
        Bulletin = new javax.swing.JLabel();
        deconnexion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Tableau.setAutoCreateRowSorter(true);
        Tableau.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Matière", "Coefficient", "Note/Moyenne", "Moyenne classe", "Appréciation"
            }
        ) {
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
        Tableau.getTableHeader().setReorderingAllowed(false);//empêche le réarrangement des colonnes
        jScrollPane1.setViewportView(Tableau);//insertion du JTable du un JScrollPane
        if (Tableau.getColumnModel().getColumnCount() > 0) {//Fixation de la taille des colonnes dans JTable
            Tableau.getColumnModel().getColumn(0).setMinWidth(160);//Matière
            Tableau.getColumnModel().getColumn(0).setMaxWidth(180);//Matière
            Tableau.getColumnModel().getColumn(1).setMinWidth(70);//Coefficient
            Tableau.getColumnModel().getColumn(1).setMaxWidth(70);//Coefficient
            Tableau.getColumnModel().getColumn(2).setMinWidth(100);//Note, moyenne
            Tableau.getColumnModel().getColumn(2).setMaxWidth(100);//Note, moyenne
            Tableau.getColumnModel().getColumn(3).setMinWidth(100);//moyenne classe
            Tableau.getColumnModel().getColumn(3).setMaxWidth(100);//moyenne classe
        }

        Titre_page.setFont(new java.awt.Font("Tahoma", 0, 18)); 
        Titre_page.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titre_page.setText("Bulletin de note élève");

        NomPrenomEleve.setFont(new java.awt.Font("Tahoma", 1, 14));//En gras, de taille 14
        NomPrenomEleve.setText(P.get_Nom_Personne()+"  "+P.get_Prenom_Personne());

        BDD bdd = new BDD();
        id_personne = P.get_ID_Personne();
        String sql = "SELECT * FROM inscription JOIN classe ON inscription.id_classe=classe.id_classe "
                    + "WHERE classe.id_annee_scolaire=(SELECT MAX(id_annee_scolaire) FROM annee_scolaire) AND inscription.id_personne="+P.get_ID_Personne()+"";
        String niveau = "";
        String nomEcole = "";
        String adresseEcole = "";
        try{
            // Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_ecole", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(sql); 
            while(result.next()){
                for(Inscription I : bdd.getListInscription()){
                    //id_inscription de l'élève le plus récent
                    if (I.get_ID_Inscription()==result.getInt("id_inscription")){
                        niveau = I.getClasse().getNiveau().get_Nom_Niveau()+" "+I.getClasse().get_Nom_Classe();
                        nomEcole = I.getClasse().getEcole().get_Nom_Ecole();
                        adresseEcole = I.getClasse().getEcole().get_Adresse_Ecole();
                    }
                }
            }
            stmt.close();
            conn.close();
        }catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOEcole.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Niveau.setText(niveau);
        NomEcole.setText(nomEcole);
        AdresseEcole.setText(adresseEcole);

        
        Bulletin.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Bulletin.setForeground(new java.awt.Color(0, 0, 204));
        Bulletin.setText("Bulletin trimestre 1, année scolaire 2018-2019");
        Bulletin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BulletinMouseClicked(evt);//affiche le bulletin spécifié dans la tableau
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BulletinMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BulletinMouseExited(evt);//Enlève le surlignement lorsque la souris entre dans le JLabel Bulletin
            }
        });

        
        deconnexion.setText("Déconnexion");
        deconnexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deconnexionActionPerformed(evt);
            }
        });

        
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NomPrenomEleve, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Niveau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NomEcole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AdresseEcole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Bulletin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Titre_page, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(182, 182, 182)
                .addComponent(deconnexion, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Titre_page, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deconnexion, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(NomPrenomEleve, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Niveau, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NomEcole, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AdresseEcole, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Bulletin, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        Titre_page.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 841, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        setSize(new java.awt.Dimension(914, 727));
        setResizable(false); //empêche le redimensionnement
        setLocationRelativeTo(null);//fenêtre positionné au centre de l'écran de l'ordinateur
    }// </editor-fold>                        

    private void BulletinMouseClicked(java.awt.event.MouseEvent evt) {
        //affiche le bulletin spécifié dans la tableau
        //le texte du JLabel Bulletin est de la forme : "Bulletin trimestre 1, Année Scolaire 2018-2019"
        String str = Bulletin.getText().toString();
        System.out.println(str);
        str = str.substring(9,str.length()-1-8);//on extrait une partie de la chaine de caractère pour enlever <html><u>
        System.out.println(str);
        String numTrimestre = str.substring(19,20);
        String annee = str.substring(37,41);
        int numeroTrimestre = Integer.valueOf(numTrimestre).intValue();
        int an = Integer.valueOf(annee).intValue();
        //System.out.println(numeroTrimestre);
        //System.out.println(an);
        
        
        BDD bdd = new BDD();
        int id_inscription = 0;
        
        for (Inscription I : bdd.getListInscription()){
            if (I.getClasse().get_ID_Annee_Scolaire()==an && I.get_ID_Personne()==id_personne){
                id_inscription=I.get_ID_Inscription();
            }
        }
        
        //nettoyage du tableau
        Tableau.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Matière", "Coefficient", "Note/Moyenne", "Moyenne classe", "Appréciation"
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
        Tableau.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(Tableau);
        if (Tableau.getColumnModel().getColumnCount() > 0) {
            Tableau.getColumnModel().getColumn(0).setMinWidth(200);//Matière
            Tableau.getColumnModel().getColumn(0).setMaxWidth(220);//Matière
            Tableau.getColumnModel().getColumn(1).setMinWidth(70);//Coefficient
            Tableau.getColumnModel().getColumn(1).setMaxWidth(70);//Coefficient
            Tableau.getColumnModel().getColumn(2).setMinWidth(100);//Note, moyenne
            Tableau.getColumnModel().getColumn(2).setMaxWidth(100);//Note, moyenne
            Tableau.getColumnModel().getColumn(3).setMinWidth(100);//moyenne classe
            Tableau.getColumnModel().getColumn(3).setMaxWidth(100);//moyenne classe
        }
        
        
        
        //Remplissage du tableau
        DefaultTableModel model = (DefaultTableModel) Tableau.getModel();
        //for(int i=0;i<5;i++) model.removeRow(i);
        Object[] row = new Object[5];
        
        for (Bulletin B : bdd.getListBulletin()){
            if (B.getTrimestre().get_ID_Annee_Scolaire()==an 
                && B.getTrimestre().get_Numero_Trimestre()==numeroTrimestre 
                && B.get_ID_Bulletin()==id_inscription){
                for(Detail_Bulletin db : bdd.getListDetailBulletin()){
                    if (db.get_ID_Bulletin()==B.get_ID_Bulletin()){
                        row[0] = db.getEnseignement().getDiscipline().get_Nom_Discipline()+": "+db.getEnseignement().getPersonne().getCivilite()+" "+db.getEnseignement().getPersonne().get_Nom_Personne();
                        row[1] = "";//Coefficient
                        row[2] = Math.round(db.getMoyenneEleve() * 100.0) / 100.0;//moyenne et le matière et notes de DS
                        row[3] = Math.round(db.getMoyenneClasse() * 100.0) / 100.0 ;//Moyenne de la matière dans la classe
                        row[4] = db.get_Appreciation_Detail(); //Appréciation
                        model.addRow(row);
			for(Evaluation E : bdd.getListEvaluation()){
				if(E.get_ID_Detail_Bulletin()==db.get_ID_Detail()){
					row[0] = E.getDS().get_Nom_DS();//Nom du devoir
                        		row[1] = E.getCoeff();//Coefficient
                        		row[2] = Math.round(E.get_Note_Evaluation()* 100.0) / 100.0;//moyenne et le matière et notes de DS
                        		row[3] = Math.round(E.getMoyenneEvaluation()* 100.0) / 100.0;//Moyenne de la matière dans la classe
                        		row[4] = E.get_Appreciation_Evaluation(); //Appréciation
                        		model.addRow(row);
				}
			}
                    }
                }
                row[0] = "Moyenne générale";//Nom du devoir
                row[1] = "";//Coefficient
                row[2] = Math.round(B.getMoyenneGeneraleEleve()* 100.0) / 100.0;//moyenne et le matière et notes de DS
                row[3] = Math.round(B.getMoyenneGeneraleClasse()* 100.0) / 100.0;//Moyenne de la matière dans la classe
                row[4] = B.get_Appreciation_Bulletin(); //Appréciation
                model.addRow(row);
            }
        }
        
        
    }
    
    
    

    private void BulletinMouseEntered(java.awt.event.MouseEvent evt) {
        // Cette méthode est appelée quand la souris entre dans la zone du composant écouté 
        String str = Bulletin.getText().toString();
        Bulletin.setText("<html><u>"+str+"<html><u>");//chaîne de caractères surligné
    }                                     

    private void BulletinMouseExited(java.awt.event.MouseEvent evt) {                                     
        //Enlève le surlignement lorsque la souris entre dans le JLabel Bulletin
        String str = Bulletin.getText().toString();
        str = str.substring(9,str.length()-1-8);//on extrait une partie de la chaine de caractère pour enlever <html><u>
        Bulletin.setText(str);
    }                                    

    private void deconnexionActionPerformed(java.awt.event.ActionEvent evt) {                                            
        //L'elève se déconnecte
        this.setVisible(false);//cache la page élève
        
        PageConnexion pg = new PageConnexion();
        pg.setVisible(true);
        pg.setTitle("Connexion");
        pg.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        pg.setSize(500,430);
        pg.setResizable(false); //empêche le redimensionnement
        pg.setLocationRelativeTo(null); //fenêtre positionné au centre de l'écran de l'ordinateur
    }                                           

    
                   
}

