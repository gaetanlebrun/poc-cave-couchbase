/* +-----------------------------------------------------------------------------------------------+
 *                                                                                                
 *  Titre          : AbstractResource                                                                 
 *  Description    : Définition                                         
 *  Copyright      : Cofiroute 2013                                                            
 *  Societe        : Cofiroute                                                                    
 *  Author         : LEBRUN_G                                                                      
 *  Version        : 1.0.0                                                                        
 *  Package        : com.oxiane.caveavin.rest                                                              
 *  Parametre VM   : Neant                                                                        
 *  Parametre main : Neant                                                                        
 *                                                                                                
 * +-----------------------------------------------------------------------------------------------+
 *  Historique des mises a jour :                                                                 
 *  Auteur Date       Description                                                                 
 *  ------- ------------------------------------------------------------------------------------- 
 *  LEBRUN_G 08/10/13 Creation                                                                      
 *                                                                                                
 * +-----------------------------------------------------------------------------------------------+
 *  Remarques :                                                                                   
 *                                                                                                
 * +-----------------------------------------------------------------------------------------------+
 *  RAF : Neant                                                                                   
 *                                                                                                
 * +-----------------------------------------------------------------------------------------------+
 */
package com.oxiane.caveavin.rest;

import com.oxiane.caveavin.dao.ICouchDao;

/**
 * User: LEBRUN_G
 * Date: 08/10/13
 * Time: 17:40
 */
public class AbstractResource {
  // ------------------------------ FIELDS ------------------------------

  private ICouchDao dao;

  // --------------------- GETTER / SETTER METHODS ---------------------

  public void setDao(ICouchDao dao) {
    this.dao = dao;
  }

  protected ICouchDao getDao() {
    return dao;
  }
}
