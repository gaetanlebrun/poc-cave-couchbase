/* +-----------------------------------------------------------------------------------------------+
 *                                                                                                
 *  Titre          : DocMappingException                                                                 
 *  Description    : Définition                                         
 *  Copyright      : Cofiroute 2013                                                            
 *  Societe        : Cofiroute                                                                    
 *  Author         : LEBRUN_G                                                                      
 *  Version        : 1.0.0                                                                        
 *  Package        : com.oxiane.caveavin.exception                                                              
 *  Parametre VM   : Neant                                                                        
 *  Parametre main : Neant                                                                        
 *                                                                                                
 * +-----------------------------------------------------------------------------------------------+
 *  Historique des mises a jour :                                                                 
 *  Auteur Date       Description                                                                 
 *  ------- ------------------------------------------------------------------------------------- 
 *  LEBRUN_G 07/10/13 Creation                                                                      
 *                                                                                                
 * +-----------------------------------------------------------------------------------------------+
 *  Remarques :                                                                                   
 *                                                                                                
 * +-----------------------------------------------------------------------------------------------+
 *  RAF : Neant                                                                                   
 *                                                                                                
 * +-----------------------------------------------------------------------------------------------+
 */
package com.oxiane.caveavin.exception;

/**
 * User: LEBRUN_G
 * Date: 07/10/13
 * Time: 18:46
 */
public class DocMappingException extends RuntimeException {
  public DocMappingException(Exception e) {
    super(e);
  }

  public DocMappingException(String message) {
    super(message);
  }

  public DocMappingException(String message, Exception e) {
    super(message, e);
  }
}
