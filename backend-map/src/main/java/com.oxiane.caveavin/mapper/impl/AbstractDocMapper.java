/* +-----------------------------------------------------------------------------------------------+
 *                                                                                                
 *  Titre          : AbstractDocMapper                                                                 
 *  Description    : D�finition                                         
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
 *  LEBRUN_G 09/10/13 Creation                                                                      
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

import java.util.Map;

/**
 * User: LEBRUN_G
 * Date: 09/10/13
 * Time: 11:34
 */
public abstract class AbstractDocMapper implements IDocMapper {
  @Override
  public Map<String, Object> toMap(String id, String doc) {
    if (doc == null) {
      return null;
    }
    try {
      return basicToMap(id, doc);
    } catch (Exception e) {
      throw new DocMappingException(e);
    }
  }

  protected Map<String, Object> basicToMap(String id, String doc) throws Exception {
    throw new UnsupportedOperationException("A r�d�finir dans les sous-classes");
  }

  protected String basicToString(Map<String, Object> map) throws Exception {
    throw new UnsupportedOperationException("A r�d�finir dans les sous-classes");
  }

  @Override
  public String toString(Map<String, Object> map) {
    if (map == null) {
      return null;
    }
    try {
      return basicToString(map);
    } catch (Exception e) {
      throw new DocMappingException(e);
    }
  }
}
