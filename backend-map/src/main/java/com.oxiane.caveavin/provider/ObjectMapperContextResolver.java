/* +-----------------------------------------------------------------------------------------------+
 *                                                                                                
 *  Titre          : ObjectMapperContextResolver                                                                 
 *  Description    : Définition                                         
 *  Copyright      : Cofiroute 2013                                                            
 *  Societe        : Cofiroute                                                                    
 *  Author         : LEBRUN_G                                                                      
 *  Version        : 1.0.0                                                                        
 *  Package        : com.oxiane.tracks.provider                                                              
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
package com.oxiane.caveavin.provider;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * User: LEBRUN_G
 * Date: 01/10/13
 * Time: 15:45
 */
@Provider
public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {
  // ------------------------------ FIELDS ------------------------------

  private ObjectMapper mapper = null;

  // --------------------------- CONSTRUCTORS ---------------------------

  public ObjectMapperContextResolver() {
    super();
    mapper = new ObjectMapper().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
  }

  // ------------------------ INTERFACE METHODS ------------------------


  // --------------------- Interface ContextResolver ---------------------

  @Override
  public ObjectMapper getContext(Class<?> type) {
    return mapper;
  }
}
