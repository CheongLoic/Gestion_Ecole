/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.sql.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import javax.swing.JTextField;
import modele.*;

/**
 *Cette classe et ses méthodes affiche la fenêtre pour l'ajout d'un DS
 * @author LOIC CHEONG
 */
public class FrameAjoutDS extends JFrame{
    int id_personne;
     int numTrimestre;
     int anneeScolaire;
     String NomClasse;
             
     /**
      * constructeur qui affiche la fenêtre pour ajouter un devoir/DS/contôle/intérogation et son coefficient
      * @param id_personne entier : identifiant du professeur
      * @param numTrimestre entier : numéro du trimestre du bulletin sélectionné dans le ComboBox
      * @param anneeScolaire entier : année scolaire du bulletin sélectionné dans le ComboBox
      * @param NomClasse  entier : nom de la classe sélectionné dans la liste de bulletin
      */
    public FrameAjoutDS(int id_personne, int numTrimestre,int anneeScolaire,String NomClasse){
        if (id_personne==0 || numTrimestre==0 || anneeScolaire==0 || NomClasse.equals("")){
            JOptionPane.showMessageDialog(null, "Vous n'avez pas sélectionné un bulletin !","",WARNING_MESSAGE);
        }
             
        else{
            
        
        JLabel jLabel1 = new JLabel();
        JLabel Coeff = new JLabel();
        JButton ValideBoutonAjoutDS = new JButton();
        JTextField jTextFieldCoeff = new JTextField();
        JTextField jTextFieldNomDS = new JTextField();
        JLabel nameDS = new javax.swing.JLabel();            
        

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Entrez les caractéristiques du devoir");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Coeff.setText("Coefficient");
        nameDS.setText("Nom du devoir");
        ValideBoutonAjoutDS.setText("Ajouter");
        
        
        ValideBoutonAjoutDS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String coeff = jTextFieldCoeff.getText().toString();
                String NameDS = jTextFieldNomDS.getText().toString();
                if (coeff.equals("")||NameDS.equals("")){
                    JOptionPane.showMessageDialog(null, "Vous n'avez pas rempli un des champs !","",WARNING_MESSAGE);
                }
                else {
                    int COEFF = Integer.valueOf(coeff).intValue();
                    try{
                        BDD bdd= new BDD();
                        //Class.forName("com.mysql.jdbc.Driver");
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_ecole", "root", "");
                        Statement stmt = conn.createStatement();
                        stmt.executeUpdate("INSERT INTO ds (nom_ds) VALUES ('"+NameDS+"')"); 
                        ResultSet result = stmt.executeQuery("SELECT MAX(id_ds) AS max FROM ds"); 
                        int id_ds=0;
                        while(result.next()){
                            id_ds=result.getInt("max");
                        }
                        
                        for(Detail_Bulletin db : bdd.getListDetailBulletin()){
                            if (db.getEnseignement().get_ID_Personne()==id_personne 
                                && db.getBulletin().getTrimestre().get_Numero_Trimestre()==numTrimestre
                                && db.getBulletin().getTrimestre().get_ID_Annee_Scolaire()==anneeScolaire
                                && db.getBulletin().getInscription().getClasse().get_Nom_Classe().equals(NomClasse)
                            && db.getBulletin().getInscription().getClasse().get_ID_Annee_Scolaire()== anneeScolaire){
                                
                                stmt.executeUpdate("INSERT INTO evaluation (id_detail_bulletin,id_ds,note_evaluation,coeff,appreciation_evaluation)"
                                + " VALUES ("+db.get_ID_Detail()+",'"+id_ds+"',0,"+COEFF+",'')"); 
                            }
                        }
                        stmt.close();
                        conn.close();
                    }catch (ClassNotFoundException | SQLException ex) {
                       ex.printStackTrace();
                    }
                    setVisible(false);
                }
            }
        });


        javax.swing.GroupLayout layout1 = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout1);
        layout1.setHorizontalGroup(
            layout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout1.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout1.createSequentialGroup()
                        .addComponent(nameDS, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldNomDS, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout1.createSequentialGroup()
                        .addComponent(Coeff, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldCoeff, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(55, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout1.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout1.createSequentialGroup()
                        .addComponent(ValideBoutonAjoutDS, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(151, 151, 151))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout1.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))))
        );
        layout1.setVerticalGroup(
            layout1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout1.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout1.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameDS, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNomDS, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout1.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Coeff, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCoeff, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(ValideBoutonAjoutDS, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false); //empêche le redimensionnement
        setSize(381,270);
        setLocationRelativeTo(null);
        setVisible(true);
    }}
}

