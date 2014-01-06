/* +-----------------------------------------------------------------------------------------------+
 *                                                                                                
 *  Titre          : CrudDaoHelper                                                                 
 *  Description    : Définition                                         
 *  Copyright      : Cofiroute 2013                                                            
 *  Societe        : Cofiroute                                                                    
 *  Author         : LEBRUN_G                                                                      
 *  Version        : 1.0.0                                                                        
 *  Package        : com.oxiane.caveavin.dao.impl                                                              
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

import com.oxiane.caveavin.dao.ICouchDAO;
import com.oxiane.caveavin.exception.DocMappingException;
import com.oxiane.caveavin.mapper.IDocMapper;

import java.util.List;
import java.util.Map;

/**
 * User: LEBRUN_G
 * Date: 23/10/13
 * Time: 12:43
 */
public class CrudHelper implements ICrudHelper {
  // ------------------------------ FIELDS ------------------------------

  private ICouchDAO  dao;
  private String     allViewName;
  private IDocMapper mapper;

  // --------------------- GETTER / SETTER METHODS ---------------------

  public void setAllViewName(String allViewName) {
    this.allViewName = allViewName;
  }

  public void setDao(ICouchDAO dao) {
    this.dao = dao;
  }

  public void setMapper(IDocMapper mapper) {
    this.mapper = mapper;
  }

  // ------------------------ INTERFACE METHODS ------------------------


  // --------------------- Interface ICrudHelper ---------------------

  @Override
  public Map<String, Object> create(String id, Map<String, Object> entite) throws DocMappingException {
    return dao.create(id, entite, mapper);
  }

  @Override
  public Map<String, Object> find(String id) throws DocMappingException {
    return dao.find(id, mapper);
  }

  @Override
  public List<Map<String, Object>> findAll() throws DocMappingException {
    return dao.findAll(allViewName, mapper);
  }

  @Override
  public Map<String, Object> update(String id, Map<String, Object> entite) throws DocMappingException {
    return dao.update(id, entite, mapper);
  }

  @Override
  public void delete(String id) {
    dao.delete(id);
  }
}
