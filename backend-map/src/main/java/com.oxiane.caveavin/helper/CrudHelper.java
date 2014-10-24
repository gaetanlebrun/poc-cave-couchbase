package com.oxiane.caveavin.helper;

import com.oxiane.caveavin.dao.ICouchDAO;
import com.oxiane.caveavin.exception.DocMappingException;
import com.oxiane.caveavin.mapper.IDocMapper;

import java.util.List;
import java.util.Map;

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
