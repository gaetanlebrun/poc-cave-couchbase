/* +-----------------------------------------------------------------------------------------------+
 *                                                                                                
 *  Titre          : DocMapperImpl                                                                 
 *  Description    : D�finition                                         
 *  Copyright      : Cofiroute 2013                                                            
 *  Societe        : Cofiroute                                                                    
 *  Author         : LEBRUN_G                                                                      
 *  Version        : 1.0.0                                                                        
 *  Package        : com.oxiane.tracks.dao                                                              
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
package com.oxiane.caveavin.mapper.impl;

import com.oxiane.caveavin.exception.DocMappingException;
import com.oxiane.caveavin.mapper.IDocMapper;
import com.oxiane.caveavin.provider.ObjectMapperContextResolver;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.Map;

/**
 * User: LEBRUN_G
 * Date: 30/09/13
 * Time: 13:47
 */
public class DocMapper<V> implements IDocMapper<V> {
  // ------------------------------ FIELDS ------------------------------

  private Class<V>     classeEntite;
  private ObjectMapper mapper;

  // --------------------------- CONSTRUCTORS ---------------------------

  public DocMapper(Class<V> classeEntite) {
    this.classeEntite = classeEntite;
    this.mapper = new ObjectMapperContextResolver().getContext(classeEntite);
  }

  // --------------------- GETTER / SETTER METHODS ---------------------

  public Class<V> getClasseEntite() {
    return classeEntite;
  }

  public ObjectMapper getMapper() {
    return mapper;
  }

  // ------------------------ INTERFACE METHODS ------------------------


  // --------------------- Interface IDocMapper ---------------------

  @Override
  public V toEntity(String id, String doc) throws DocMappingException {
    if (doc == null) {
      return null;
    }
    try {
      @SuppressWarnings("unchecked") Map<String, Object> map = (Map<String, Object>) getMapper().readValue(doc, Map.class);
      map.put("id", id);
      // Suppression type, inutile dans les POJOs
      map.remove("type");
      return buildEntityFrom(map);
    } catch (Exception e) {
      throw new DocMappingException(e);
    }
  }

  @Override
  public String toString(V entity) throws DocMappingException {
    if (entity == null) {
      return null;
    }
    try {
      final Map<String, Object> map = buildMapFrom(entity);
      map.put("type", getClasseEntite().getSimpleName());
      return mapper.writeValueAsString(map);
    } catch (Exception e) {
      throw new DocMappingException(e);
    }
  }

  // -------------------------- Méthodes privées --------------------------

  private V buildEntityFrom(Map<String, Object> map) {
    return getMapper().convertValue(map, getClasseEntite());
  }

  private Map<String, Object> buildMapFrom(V entity) {
    //noinspection unchecked
    return (Map<String, Object>) getMapper().convertValue(entity, Map.class);
  }
}
