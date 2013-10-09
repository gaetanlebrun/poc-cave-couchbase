package com.oxiane.caveavin.rest;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * User: LEBRUN_G
 * Date: 30/09/13
 * Time: 14:40
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/WEB-INF/applicationContext.xml"})
public class DomaineResourceTest extends CrudResourceTest {
  // -------------------------- PRIVATE METHODS --------------------------

  @Override
  protected void applyModification(Map<String, Object> jsonMap) {
    jsonMap.put("pays", "France");
    jsonMap.put("web", "n/a");
  }

  @Override
  protected void assertExcerpt(Map<String, Object> map) {
    assertNotNull(map.get("id"));
    assertNotNull(map.get("nom"));
    assertNotNull(map.get("ville"));
    assertNotNull(map.get("pays"));
  }

  private void assertExcerptByName(Map<String, Object> map) {
    assertNotNull(map.get("id"));
    assertNotNull(map.get("nom"));
  }

  @Override
  protected ResourceTestContext createContext() {
    return new ResourceTestContext(
            "/rest/domaine",
            "chateau_pierre_bise_2",
            "{" +
                    "\"nom\":\"Château Pierre Bise 2\"," +
                    "\"ville\":\"Beaulieu-sur-Layon\"," +
                    "\"pays\":\"Chili\"," +
                    "\"adresse\":\"1 Impasse du Chanoine de Douves\"," +
                    "\"web\":\"www.chateau-pierre-bise.com\"," +
                    "\"telephone\":\"02 41 78 31 44\"" +
                    "}");
  }

  @Override
  protected void findAll() throws IOException {
    super.findAll();

    String result = getJersey().resource().path(getContext().getUrl() + "/by_name")
            .queryParam("startKey", "Châte")
            .queryParam("endKey", "Châte\\u9999")
            .type(MediaType.APPLICATION_JSON)
            .get(String.class);
    assertNotNull(result);
    //noinspection unchecked
    List<Map<String, Object>> list = (List<Map<String, Object>>) getMapper().readValue(result, List.class);
    assertTrue(list.size() > 0);
    for (Map<String, Object> map : list) {
      assertNotNull(map.get("nom"));
    }
  }

  // -------------------------- PUBLIC METHODS --------------------------

  @Resource(name = "domaineResource")
  public void setResource(CrudResource resource) {
    super.setResource(resource);
  }
}
