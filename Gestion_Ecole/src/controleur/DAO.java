/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;


import java.sql.Connection;

/**
 * Classe DAO qui initialise les métodes créer, supprimer, modifier et rechercher
 * @author coline
 * @param <T>
 */
public abstract class DAO<T> {

    /**
     *
     */
    protected Connection connect = null;
   
    /**
     *
     */
    public DAO(){
  }
  
    /**
     *
     * @param conn
     */
    public DAO(Connection conn){
    this.connect = conn;
  }
  
    /**
     * modifie les donnees
     */
    public void setData(){
  }
  
    /**
     *
     */
    public void Bind(){
  }
  
   
  
  
   
  /**
  * Méthode de création
  * @param obj
  * @return boolean 
  */
  public abstract boolean create(T obj);

  /**
  * Méthode pour effacer
  * @param obj
  * @return boolean 
  */
  public abstract boolean delete(T obj);

  /**
  * Méthode de mise à jour
  * @param obj
  * @return boolean
  */
  public abstract boolean update(T obj);

  /**
  * Méthode de recherche des informations
  * @param id
  * @return T
  */
  public abstract T find(int id);
}

  
    