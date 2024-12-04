/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.sql.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import javax.swing.JTextField;
import modele.*;

/**
 *Cette classe et ses méthodes affiche la fenêtre pour supprimer d'un DS
 * @author LOIC CHEONG
 */
public class FrameSupprimeDS extends JFrame{
    int id_personne;
     int numTrimestre;
     int anneeScolaire;
     String NomClasse;
     String NomDS;
     int nbDS;
     int id_ds;
             
     /**
      * constructeur qui affiche la fenêtre pour supprimer un devoir/DS/contôle/intérogation
      * @param id_personne entier : identifiant du professeur
      * @param numTrimestre entier : numéro du trimestre du bulletin sélectionné dans le ComboBox
      * @param anneeScolaire entier : année scolaire du bulletin sélectionné dans le ComboBox
      * @param NomClasse  entier : nom de la classe sélectionné dans la liste de bulletin
      */
    public FrameSupprimeDS(int id_personne, int numTrimestre,int anneeScolaire,String NomClasse){
        NomDS = "";
        if (id_personne==0 || numTrimestre==0 || anneeScolaire==0 || NomClasse.equals("")){
            JOptionPane.showMessageDialog(null, "Vous n'avez pas sélectionné un bulletin !","",WARNING_MESSAGE);
        }
             
        else{
        JLabel jLabel1 = new JLabel();
        JButton ValideBoutonSupprimeDS = new JButton(); 
        JComboBox jComboBox1 = new JComboBox();        
        
        
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Quel devoir voulez-vous supprimer ?");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        BDD bdd= new BDD();
        
        
        for(Detail_Bulletin db : bdd.getListDetailBulletin()){
            if (db.getEnseignement().get_ID_Personne()==id_personne 
                && db.getBulletin().getTrimestre().get_Numero_Trimestre()==numTrimestre
                && db.getBulletin().getTrimestre().get_ID_Annee_Scolaire()==anneeScolaire
                && db.getBulletin().getInscription().getClasse().get_Nom_Classe().equals(NomClasse)
                && db.getBulletin().getInscription().getClasse().get_ID_Annee_Scolaire()== anneeScolaire){
                nbDS =0;
		for(Evaluation E : bdd.getListEvaluation()){
                    if(E.get_ID_Detail_Bulletin()==db.get_ID_Detail()){
                        nbDS++;
                    }
		}
            }
        }
        String[] ds = new String[nbDS];
        for (int j=0;j<nbDS;j++){
            ds[j]="";//On remplie le tableau statique "ds" pour pouvoir faire une comparaison entre String plus tard 
        }
        for(Detail_Bulletin db : bdd.getListDetailBulletin()){
            if (db.getEnseignement().get_ID_Personne()==id_personne 
                && db.getBulletin().getTrimestre().get_Numero_Trimestre()==numTrimestre
                && db.getBulletin().getTrimestre().get_ID_Annee_Scolaire()==anneeScolaire
                && db.getBulletin().getInscription().getClasse().get_Nom_Classe().equals(NomClasse)
                && db.getBulletin().getInscription().getClasse().get_ID_Annee_Scolaire()== anneeScolaire){
                int i=0;
		for(Evaluation E : bdd.getListEvaluation()){
                    if(E.get_ID_Detail_Bulletin()==db.get_ID_Detail()){
                        ds[i]=E.getDS().get_Nom_DS();
                        i++;
                    }
		}
            }
        }
        
        
        
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(ds));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NomDS=(String) jComboBox1.getSelectedItem().toString();
            }
        });
        
        ValideBoutonSupprimeDS.setText("Supprimer");
        ValideBoutonSupprimeDS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (NomDS.equals("")==true) JOptionPane.showMessageDialog(null, "Veuillez sélectionner un devoir s'il-vous-plaît !","",WARNING_MESSAGE);
                
                else{
                    try{
                        BDD bdd= new BDD();
                        // Class.forName("com.mysql.jdbc.Driver");
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_ecole", "root", "");
                        Statement stmt = conn.createStatement();
                        for(Detail_Bulletin db : bdd.getListDetailBulletin()){
                            if (db.getEnseignement().get_ID_Personne()==id_personne 
                                && db.getBulletin().getTrimestre().get_Numero_Trimestre()==numTrimestre
                                && db.getBulletin().getTrimestre().get_ID_Annee_Scolaire()==anneeScolaire
                                && db.getBulletin().getInscription().getClasse().get_Nom_Classe().equals(NomClasse)
                            && db.getBulletin().getInscription().getClasse().get_ID_Annee_Scolaire()== anneeScolaire){
                                for(Evaluation E : bdd.getListEvaluation()){
                                    if(E.get_ID_Detail_Bulletin()==db.get_ID_Detail() && E.getDS().get_Nom_DS().equals(NomDS)){
                                        id_ds=E.get_ID_DS();
                                        stmt.executeUpdate("DELETE FROM evaluation WHERE id_ds="+id_ds+"");
                                        System.out.println(id_ds);
                                    }
                                }
                            }
                        }
                        stmt.executeUpdate("DELETE FROM ds WHERE id_ds="+id_ds+"");
                        stmt.close();
                        conn.close();
                    }catch (ClassNotFoundException | SQLException ex) {
                       ex.printStackTrace();
                    }
                    setVisible(false);
                }
                
            }
        });

        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 55, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, 0, 130, Short.MAX_VALUE)
                    .addComponent(ValideBoutonSupprimeDS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(123, 123, 123))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(ValideBoutonSupprimeDS, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); //empêche le redimensionnement
        setSize(381,270);
        setLocationRelativeTo(null);
        setVisible(true);
    }}
}
