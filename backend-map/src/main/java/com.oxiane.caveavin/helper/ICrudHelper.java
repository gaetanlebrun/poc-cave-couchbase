/* +-----------------------------------------------------------------------------------------------+
 *                                                                                                
 *  Titre          : ICrudHelper                                                                 
 *  Description    : Définition                                         
 *  Copyright      : Cofiroute 2013                                                            
 *  Societe        : Cofiroute                                                                    
 *  Author         : LEBRUN_G                                                                      
 *  Version        : 1.0.0                                                                        
 *  Package        : com.oxiane.caveavin.dao                                                              
 *  Parametre VM   : Neant                                                                        
 *  Parametre main : Neant                                                                        
 *                                                                                                
 * +-----------------------------------------------------------------------------------------------+
 *  Historique des mises a jour :                                                                 
 *  Auteur Date       Description                                                                 
 *  ------- ------------------------------------------------------------------------------------- 
 *  LEBRUN_G 23/10/13 Creation                                                                      
 *                                                                                                
 * +-----------------------------------------------------------------------------------------------+
 *  Remarques :                                                                                   
 *                                                                                                
 * +-----------------------------------------------------------------------------------------------+
 *  RAF : Neant                                                                                   
 *                                                                                                
 * +-----------------------------------------------------------------------------------------------+
 */
package com.oxiane.caveavin.helper;

import com.oxiane.caveavin.exception.DocMappingException;

import java.util.List;
import java.util.Map;

/**
 * User: LEBRUN_G
 * Date: 23/10/13
 * Time: 12:44
 */
public interface ICrudHelper {
  Map<String, Object> create(String id, Map<String, Object> entite) throws DocMappingException;

  Map<String, Object> find(String id) throws DocMappingException;

  List<Map<String, Object>> findAll() throws DocMappingException;

  Map<String, Object> update(String id, Map<String, Object> entite) throws DocMappingException;

  void delete(String id);
}
