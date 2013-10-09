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

import com.oxiane.caveavin.exception.DocMappingException;
import com.oxiane.caveavin.mapper.IDocMapper;
import com.oxiane.caveavin.provider.ObjectMapperContextResolver;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * User: LEBRUN_G
 * Date: 07/10/13
 * Time: 18:50
 */
public class DocMapper extends AbstractDocMapper implements IDocMapper {
  private final ObjectMapper mapper;
  private       String       type;

  public DocMapper(String type) {
    this.mapper = new ObjectMapperContextResolver().getContext(Map.class);
    this.type = type;
  }

  @Override
  public Map<String, Object> toMap(String id, String doc) {
    if (doc == null) {
      return null;
    }
    try {
      //noinspection unchecked
      final Map<String, Object> map = (Map<String, Object>) mapper.readValue(doc, Map.class);
      map.put("id", id);
      map.remove("type");
      return map;
    } catch (IOException e) {
      throw new DocMappingException(e);
    }
  }

  @Override
  public String toString(Map<String, Object> map) {
    if (map == null) {
      return null;
    }
    try {
      Map<String, Object> newMap = new LinkedHashMap<String, Object>(map);
      newMap.put("type", this.type);
      return mapper.writeValueAsString(newMap);
    } catch (IOException e) {
      throw new DocMappingException(e);
    }
  }
}
