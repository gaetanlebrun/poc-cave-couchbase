/* +-----------------------------------------------------------------------------------------------+
 *                                                                                                
 *  Titre          : ParamResourceTest                                                                 
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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * User: LEBRUN_G
 * Date: 09/10/13
 * Time: 11:58
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/WEB-INF/applicationContext.xml","/WEB-INF/securityContext.xml"})
public class ParamResourceTest extends AbstractResourceTest {
  // -------------------------- PUBLIC METHODS --------------------------

  @Test
  public void appellationsValleeDeLaLoire() throws IOException {
    String result = getJersey().resource().path("/rest/param/param::region::vallee_de_la_loire/appellations")
            .type(MediaType.APPLICATION_JSON)
            .get(String.class);
    assertNotNull(result);
    List<String> list = getStringList(result);
    assertEquals(58, list.size());
    assertEquals("Anjou", list.get(0));
  }

  @Test
  public void regionsCommencantParVall() throws IOException {
    String result = getJersey().resource().path("/rest/param/regions/by_name")
            .queryParam("startKey", "Vall")
            .queryParam("endKey", "Vall\\u9999")
            .type(MediaType.APPLICATION_JSON)
            .get(String.class);
    assertNotNull(result);
    //noinspection unchecked
    List<Map<String, Object>> list = getMapList(result);
    assertEquals(2, list.size());
    for (Map<String, Object> map : list) {
      assertNotNull(map.get("id"));
      assertNotNull(map.get("nom"));
    }
  }

  @Resource(name = "paramResource")
  public void setResource(AbstractResource resource) {
    super.setResource(resource);
  }

  @Test
  public void typesDeVin() throws IOException {
    String result = getJersey().resource().path("/rest/param/vin/caracteristiques")
            .type(MediaType.APPLICATION_JSON)
            .get(String.class);
    assertNotNull(result);
    Map<String, Object> map = getMap(result);
    assertNotNull(map);
    assertNotNull(map.get("cepages"));
    //noinspection unchecked
    assertEquals(387, ((List<String>) map.get("cepages")).size());
    assertNotNull(map.get("types"));
    //noinspection unchecked
    assertEquals(57, ((List<String>) map.get("types")).size());
  }
}
