/* +-----------------------------------------------------------------------------------------------+
 *                                                                                                
 *  Titre          : Bouteille                                                                 
 *  Description    : D�finition                                         
 *  Copyright      : Cofiroute 2013                                                            
 *  Societe        : Cofiroute                                                                    
 *  Author         : LEBRUN_G                                                                      
 *  Version        : 1.0.0                                                                        
 *  Package        : com.oxiane.tracks.bean                                                              
 *  Parametre VM   : Neant                                                                        
 *  Parametre main : Neant                                                                        
 *                                                                                                
 * +-----------------------------------------------------------------------------------------------+
 *  Historique des mises a jour :                                                                 
 *  Auteur Date       Description                                                                 
 *  ------- ------------------------------------------------------------------------------------- 
 *  LEBRUN_G 01/10/13 Creation                                                                      
 *                                                                                                
 * +-----------------------------------------------------------------------------------------------+
 *  Remarques :                                                                                   
 *                                                                                                
 * +-----------------------------------------------------------------------------------------------+
 *  RAF : Neant                                                                                   
 *                                                                                                
 * +-----------------------------------------------------------------------------------------------+
 */
package com.oxiane.caveavin.bean;

/**
 * User: LEBRUN_G
 * Date: 01/10/13
 * Time: 13:56
 */
public class Vin extends AbstractBean {
  // ------------------------------ FIELDS ------------------------------

  private Domaine domaine;
  private String  nom;
  private Integer millesime;
  private String  appellation;
  private String  region;
  private String  couleur;
  private String  cepages;
  private String  terroirs;
  private String  vendanges;
  private Float   degre;

  // --------------------------- CONSTRUCTORS ---------------------------

  public Vin() {
  }

  // --------------------- GETTER / SETTER METHODS ---------------------

  public String getAppellation() {
    return appellation;
  }

  public void setAppellation(String appellation) {
    this.appellation = appellation;
  }

  public String getCepages() {
    return cepages;
  }

  public void setCepages(String cepages) {
    this.cepages = cepages;
  }

  public String getCouleur() {
    return couleur;
  }

  public void setCouleur(String couleur) {
    this.couleur = couleur;
  }

  public Float getDegre() {
    return degre;
  }

  public void setDegre(Float degre) {
    this.degre = degre;
  }

  public Domaine getDomaine() {
    return domaine;
  }

  public void setDomaine(Domaine domaine) {
    this.domaine = domaine;
  }

  public Integer getMillesime() {
    return millesime;
  }

  public void setMillesime(Integer millesime) {
    this.millesime = millesime;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getTerroirs() {
    return terroirs;
  }

  public void setTerroirs(String terroirs) {
    this.terroirs = terroirs;
  }

  public String getVendanges() {
    return vendanges;
  }

  public void setVendanges(String vendanges) {
    this.vendanges = vendanges;
  }
}

