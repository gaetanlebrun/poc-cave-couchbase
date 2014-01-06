/* +-----------------------------------------------------------------------------------------------+
 *                                                                                                
 *  Titre          : SimpleDocMapper                                                                 
 *  Description    : Définition                                         
 *  Copyright      : Cofiroute 2013                                                            
 *  Societe        : Cofiroute                                                                    
 *  Author         : LEBRUN_G                                                                      
 *  Version        : 1.0.0                                                                        
 *  Package        : com.oxiane.caveavin.mapper.impl                                                              
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
package com.oxiane.caveavin.mapper.impl;

import org.codehaus.jackson.map.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * User: LEBRUN_G
 * Date: 23/10/13
 * Time: 12:11
 */
public class DefaultDocMapper extends AbstractDocMapper {
  // ------------------------------ FIELDS ------------------------------

  private ObjectMapper mapper;

  // --------------------- GETTER / SETTER METHODS ---------------------

  public ObjectMapper getMapper() {
    return mapper;
  }

  public void setMapper(ObjectMapper mapper) {
    this.mapper = mapper;
  }

  // -------------------------- PRIVATE METHODS --------------------------

  @Override
  protected Map<String, Object> basicToMap(String id, String doc) throws Exception {
    //noinspection unchecked
    final Map<String, Object> map = (Map<String, Object>) mapper.readValue(doc, Map.class);
    map.put("id", id);
    return map;
  }

  @Override
  protected String basicToString(Map<String, Object> map) throws Exception {
    return mapper.writeValueAsString(new LinkedHashMap<String, Object>(map));
  }
}
