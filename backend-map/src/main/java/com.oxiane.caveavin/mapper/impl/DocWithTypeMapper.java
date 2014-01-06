/* +-----------------------------------------------------------------------------------------------+
 *                                                                                                
 *  Titre          : DocMapper                                                                 
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
package com.oxiane.caveavin.mapper.impl;

import com.oxiane.caveavin.mapper.IDocMapper;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * User: LEBRUN_G
 * Date: 07/10/13
 * Time: 18:50
 */
public class DocWithTypeMapper extends DefaultDocMapper implements IDocMapper {
  // ------------------------------ FIELDS ------------------------------

  private String type;

  // --------------------- GETTER / SETTER METHODS ---------------------

  public void setType(String type) {
    this.type = type;
  }

  // -------------------------- PUBLIC METHODS --------------------------

  @Override
  public Map<String, Object> basicToMap(String id, String doc) throws Exception {
    final Map<String, Object> map = super.basicToMap(id, doc);
    map.remove("type");
    return map;
  }

  @Override
  public String basicToString(Map<String, Object> map) throws Exception {
    Map<String, Object> newMap = new LinkedHashMap<String, Object>(map);
    newMap.put("type", this.type);
    return getMapper().writeValueAsString(newMap);
  }
}
