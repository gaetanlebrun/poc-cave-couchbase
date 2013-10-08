/* +-----------------------------------------------------------------------------------------------+
 *                                                                                                
 *  Titre          : CouchDao                                                                 
 *  Description    : D�finition                                         
 *  Copyright      : Cofiroute 2013                                                            
 *  Societe        : Cofiroute                                                                    
 *  Author         : LEBRUN_G                                                                      
 *  Version        : 1.0.0                                                                        
 *  Package        : com.oxiane.tracks.dao.impl                                                              
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
package com.oxiane.caveavin.dao.impl;

import com.couchbase.client.CouchbaseClient;
import com.couchbase.client.protocol.views.*;
import com.oxiane.caveavin.dao.ICouchDao;
import com.oxiane.caveavin.exception.DocMappingException;
import com.oxiane.caveavin.mapper.IDocMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * User: LEBRUN_G
 * Date: 30/09/13
 * Time: 14:06
 */
public class CouchDao<V> implements ICouchDao<V> {
  // ------------------------------ FIELDS ------------------------------

  private final String          allView;
  private final CouchbaseClient client;
  private final String          design;
  private final IDocMapper<V>   docMapper;

  // --------------------------- CONSTRUCTORS ---------------------------

  public CouchDao(CouchbaseClient client, IDocMapper<V> docMapper, String design, String allView) {
    this.client = client;
    this.design = design;
    this.allView = allView;
    this.docMapper = docMapper;
  }

  // ------------------------ INTERFACE METHODS ------------------------


  // --------------------- Interface ICouchDao ---------------------

  // -------------------------- Méthodes publiques --------------------------

  @Override
  public V create(String id, V entite) throws DocMappingException {
    client.set(id, docMapper.toString(entite));
    return find(id);
  }

  @Override
  public void delete(String id) {
    client.delete(id);
  }

  @Override
  public V find(String id) throws DocMappingException {
    return docMapper.toEntity(id, (String) client.get(id));
  }

  @Override
  public List<V> findAll() throws DocMappingException {
    View view = client.getView(design, allView);
    final Query query = new Query();
    //query.setIncludeDocs(true);
    return executeQuery(view, query, docMapper);
  }

  @Override
  public List<V> findAll(String viewName, String startKey, String endKey, IDocMapper<V> mapper) throws DocMappingException {
    View view = client.getView(design, viewName);
    final Query query = new Query();
    query.setRange(startKey, endKey);
    //query.setIncludeDocs(true);
    return executeQuery(view, query, mapper);
  }

  @Override
  public V update(String id, V entite) throws DocMappingException {
    client.replace(id, docMapper.toString(entite));
    return find(id);
  }

  // -------------------------- PRIVATE METHODS --------------------------

  private List<V> executeQuery(View view, Query query, IDocMapper<V> mapper) {
    query.setStale(Stale.FALSE);
    ViewResponse response = client.query(view, query);
    List<V> docs = new ArrayList<V>();
    for (ViewRow row : response) {
      docs.add(mapper.toEntity(row.getId(), row.getKey()));
    }
    return docs;
  }
}
