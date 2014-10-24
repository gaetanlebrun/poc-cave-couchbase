package com.oxiane.caveavin.dao.impl;

import com.couchbase.client.CouchbaseClient;
import com.couchbase.client.protocol.views.*;
import com.oxiane.caveavin.dao.ICouchDAO;
import com.oxiane.caveavin.exception.DocMappingException;
import com.oxiane.caveavin.mapper.IDocMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CouchDao implements ICouchDAO {
  // ------------------------------ FIELDS ------------------------------

  private CouchbaseClient client;
  private String          design;

  // --------------------- GETTER / SETTER METHODS ---------------------

  public void setClient(CouchbaseClient client) {
    this.client = client;
  }

  public void setDesign(String design) {
    this.design = design;
  }

  // ------------------------ INTERFACE METHODS ------------------------


  // --------------------- Interface ICouchDAO ---------------------

  @Override
  public Map<String, Object> create(String id, Map<String, Object> map, IDocMapper mapper) throws DocMappingException {
    client.set(id, mapper.toString(map));
    return find(id, mapper);
  }

  @Override
  public void delete(String id) {
    client.delete(id);
  }

  @Override
  public Map<String, Object> find(String id, IDocMapper mapper) throws DocMappingException {
    return mapper.toMap(id, (String) client.get(id));
  }

  @Override
  public List<Map<String, Object>> findAll(String viewName, IDocMapper mapper) throws DocMappingException {
    return executeQuery(client.getView(design, viewName), new Query(), mapper);
  }

  @Override
  public Map<String, Object> update(String id, Map<String, Object> map, IDocMapper mapper) throws DocMappingException {
    client.replace(id, mapper.toString(map));
    return find(id, mapper);
  }

  @Override
  public List<Map<String, Object>> findAll(String viewName, String startKey, String endKey, IDocMapper mapper) throws DocMappingException {
    final Query query = new Query();
    query.setRange(startKey, endKey);
    return executeQuery(client.getView(design, viewName), query, mapper);
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

  // -------------------------- PUBLIC METHODS --------------------------

  public void destroy() {
    client.shutdown(1, TimeUnit.SECONDS);
  }
}
