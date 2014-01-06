/* +-----------------------------------------------------------------------------------------------+
 *                                                                                                
 *  Titre          : ICouchDAO                                                                 
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
package com.oxiane.caveavin.dao;

import com.oxiane.caveavin.exception.DocMappingException;
import com.oxiane.caveavin.mapper.IDocMapper;

import java.util.List;
import java.util.Map;

/**
 * User: LEBRUN_G
 * Date: 23/10/13
 * Time: 11:26
 */
public interface ICouchDAO {
  Map<String, Object> create(String id, Map<String, Object> map, IDocMapper mapper) throws DocMappingException;

  void delete(String id);

  Map<String, Object> find(String id, IDocMapper mapper) throws DocMappingException;

  List<Map<String, Object>> findAll(String viewName, IDocMapper mapper) throws DocMappingException;

  Map<String, Object> update(String id, Map<String, Object> map, IDocMapper mapper) throws DocMappingException;

  List<Map<String, Object>> findAll(String viewName, String startKey, String endKey, IDocMapper mapper) throws DocMappingException;
}
