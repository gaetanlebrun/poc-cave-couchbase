/* +-----------------------------------------------------------------------------------------------+
 *                                                                                                
 *  Titre          : AbstractDocMapper                                                                 
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

/**
 * User: LEBRUN_G
 * Date: 09/10/13
 * Time: 11:34
 */
public class AbstractDocMapper<V> implements IDocMapper<V> {

  @Override
  public V toEntity(String id, String doc) throws DocMappingException {
    throw new UnsupportedOperationException("A rédéfinir dans les sous-classes");
  }

  @Override
  public String toString(V entity) throws DocMappingException {
    throw new UnsupportedOperationException("A rédéfinir dans les sous-classes");
  }
}
