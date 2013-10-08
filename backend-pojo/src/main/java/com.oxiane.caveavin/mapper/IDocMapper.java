package com.oxiane.caveavin.mapper;

import com.oxiane.caveavin.exception.DocMappingException;

public interface IDocMapper<V> {
  public V toEntity(String id, String doc) throws DocMappingException;

  public String toString(V entity) throws DocMappingException;
}