/* +-----------------------------------------------------------------------------------------------+
 *                                                                                                
 *  Titre          : AbstractBean                                                                 
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
 * Time: 14:57
 */
public abstract class AbstractBean {
  // ------------------------------ FIELDS ------------------------------

  String id;

  // --------------------- GETTER / SETTER METHODS ---------------------

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

}
