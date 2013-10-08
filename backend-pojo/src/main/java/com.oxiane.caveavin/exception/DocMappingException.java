/* +-----------------------------------------------------------------------------------------------+
 *                                                                                                
 *  Titre          : MappingException                                                                 
 *  Description    : D�finition                                         
 *  Copyright      : Cofiroute 2013                                                            
 *  Societe        : Cofiroute                                                                    
 *  Author         : LEBRUN_G                                                                      
 *  Version        : 1.0.0                                                                        
 *  Package        : com.oxiane.tracks.mapper.exception
 *  Parametre VM   : Neant                                                                        
 *  Parametre main : Neant                                                                        
 *                                                                                                
 * +-----------------------------------------------------------------------------------------------+
 *  Historique des mises a jour :                                                                 
 *  Auteur Date       Description                                                                 
 *  ------- ------------------------------------------------------------------------------------- 
 *  LEBRUN_G 30/09/13 Creation                                                                      
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
 * Date: 30/09/13
 * Time: 13:41
 */
public class DocMappingException extends RuntimeException {
  // --------------------------- CONSTRUCTORS ---------------------------

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
