/* +-----------------------------------------------------------------------------------------------+
 *                                                                                                
 *  Titre          : CrudResourceTest                                                                 
 *  Description    : Définition                                         
 *  Copyright      : Cofiroute 2013                                                            
 *  Societe        : Cofiroute                                                                    
 *  Author         : LEBRUN_G                                                                      
 *  Version        : 1.0.0                                                                        
 *  Package        : com.oxiane.caveavin.rest                                                              
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
package com.oxiane.caveavin.rest;

import com.sun.jersey.api.client.ClientResponse;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * User: LEBRUN_G
 * Date: 09/10/13
 * Time: 11:59
 */
public abstract class CrudResourceTest<V> extends AbstractResourceTest<V> {
  // ------------------------------ FIELDS ------------------------------

  private ResourceTestContext context;

  // --------------------- GETTER / SETTER METHODS ---------------------

  public ResourceTestContext getContext() {
    return context;
  }

  // -------------------------- PUBLIC METHODS --------------------------

  @Before
  public void before() throws Exception {
    super.before();
    context = createContext();
  }

  protected abstract ResourceTestContext createContext();

  @Test
  public void testSuite() throws IOException {
    create();
    find();
    update();
    findAll();
    delete();
  }

  /**
   * Test insertion
   * @throws java.io.IOException
   */
  protected void create() throws IOException {
    String result = getJersey().resource().path(context.getUrl()).type(MediaType.APPLICATION_JSON).post(String.class, context.getJson());
    assertNotNull(result);
    assertEquals(expectedJsonMapWithId(), getMapper().readValue(result, Map.class));
  }

  protected Map<String, Object> expectedJsonMapWithId() throws IOException {
    return expectedJsonMapWithId(jsonMap());
  }

  protected Map<String, Object> jsonMap() throws IOException {
    //noinspection unchecked
    return getMap(context.getJson());
  }

  /**
   * Test récupération via id
   * @throws java.io.IOException
   */
  protected void find() throws IOException {
    String result = getJersey().resource().path(context.getIdUrl()).get(String.class);
    assertEquals(expectedJsonMapWithId(), getMapper().readValue(result, Map.class));
  }

  /**
   * Test mise à jour
   * @throws java.io.IOException
   */
  protected void update() throws IOException {
    final Map<String, Object> jsonMap = jsonMap();
    applyModification(jsonMap);
    String result = getJersey().resource().path(context.getIdUrl()).type(MediaType.APPLICATION_JSON_TYPE).put(String.class, getMapper().writeValueAsString(jsonMap));
    assertNotNull(result);
    assertEquals(expectedJsonMapWithId(jsonMap), getMapper().readValue(result, Map.class));
  }

  protected abstract void applyModification(Map<String, Object> jsonMap);

  protected Map<String, Object> expectedJsonMapWithId(Map<String, Object> baseJsonMap) throws IOException {
    final Map<String, Object> expectedJsonMap = new LinkedHashMap<String, Object>();
    expectedJsonMap.put("id", context.getId());
    expectedJsonMap.putAll(baseJsonMap);
    return expectedJsonMap;
  }

  /**
   * Test récupération de tous les documents.
   * @throws java.io.IOException
   */
  protected void findAll() throws IOException {
    String result = getJersey().resource().path(context.getUrl()).type(MediaType.APPLICATION_JSON).get(String.class);
    assertNotNull(result);
    //noinspection unchecked
    List<Map<String, Object>> list = getMapList(result);
    assertTrue(list.size() > 0);
    for (Map<String, Object> map : list) {
      assertExcerpt(map);
    }
  }

  protected abstract void assertExcerpt(Map<String, Object> map);

  /**
   * Test suppression
   * @throws java.io.IOException
   */
  protected void delete() throws IOException {
    ClientResponse response = getJersey().resource().path(context.getIdUrl()).type(MediaType.APPLICATION_OCTET_STREAM).delete(ClientResponse.class);
    assertEquals(200, response.getStatus());
  }
}
