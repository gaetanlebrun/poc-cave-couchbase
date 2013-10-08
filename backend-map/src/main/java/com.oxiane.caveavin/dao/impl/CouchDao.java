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
import com.oxiane.caveavin.mapper.impl.DocMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: LEBRUN_G
 * Date: 30/09/13
 * Time: 14:06
 */
public class CouchDao implements ICouchDao {
  // ------------------------------ FIELDS ------------------------------

  private final String          allView;
  private final CouchbaseClient client;
  private final String          design;
  private       IDocMapper      docMapper;

  // --------------------------- CONSTRUCTORS ---------------------------
  public CouchDao(CouchbaseClient client, String design) {
    this(client, design, null, null);
  }

  public CouchDao(CouchbaseClient client, String design, String allView, String type) {
    this.client = client;
    this.design = design;
    this.allView = allView;
    this.docMapper = new DocMapper(type);
  }

  // ------------------------ INTERFACE METHODS ------------------------


  // --------------------- Interface ICouchDao ---------------------

  @Override
  public Map<String, Object> create(String id, Map<String, Object> map) throws DocMappingException {
    client.set(id, docMapper.toString(map));
    return find(id);
  }

  @Override
  public void delete(String id) {
    client.delete(id);
  }

  @Override
  public Map<String, Object> find(String id) throws DocMappingException {
    return docMapper.toMap(id, (String) client.get(id));
  }

  @Override
  public List<Map<String, Object>> findAll() throws DocMappingException {
    View view = client.getView(design, allView);
    final Query query = new Query();
    //query.setIncludeDocs(true);
    return executeQuery(view, query, docMapper);
  }

  @Override
  public Map<String, Object> update(String id, Map<String, Object> map) throws DocMappingException {
    client.replace(id, docMapper.toString(map));
    return find(id);
  }

  @Override
  public List<Map<String, Object>> findAll(String viewName, String startKey, String endKey, IDocMapper mapper) throws DocMappingException {
    View view = client.getView(design, viewName);
    final Query query = new Query();
    query.setRange(startKey, endKey);
    //query.setIncludeDocs(true);
    return executeQuery(view, query, mapper);
  }

  // -------------------------- PRIVATE METHODS --------------------------

  private List<Map<String, Object>> executeQuery(View view, Query query, IDocMapper mapper) {
    query.setStale(Stale.FALSE);
    ViewResponse response = client.query(view, query);
    List<Map<String, Object>> docs = new ArrayList<Map<String, Object>>();
    for (ViewRow row : response) {
      docs.add(mapper.toMap(row.getId(), row.getKey()));
    }
    return docs;
  }
}
