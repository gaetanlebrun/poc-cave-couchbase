package com.oxiane.caveavin.mapper;

import java.util.Map;

public interface IDocMapper {
  Map<String, Object> toMap(String id, String doc);

  String toString(Map<String, Object> map);
}