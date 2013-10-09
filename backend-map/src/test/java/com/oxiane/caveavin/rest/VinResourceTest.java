package com.oxiane.caveavin.rest;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

/**
 * User: LEBRUN_G
 * Date: 30/09/13
 * Time: 14:40
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/WEB-INF/applicationContext.xml"})
public class VinResourceTest extends CrudResourceTest {
  // -------------------------- PRIVATE METHODS --------------------------

  @Override
  protected void applyModification(Map<String, Object> jsonMap) {
    jsonMap.put("cepages", "Chenin");
    jsonMap.put("etiquette", "path/to/etiquette.png");
  }

  @Override
  protected void assertExcerpt(Map<String, Object> map) {
    assertNotNull(map.get("id"));
    assertNotNull(map.get("nom"));
    assertNotNull(map.get("domaine"));
    assertNotNull(map.get("millesime"));
    assertNotNull(map.get("appellation"));
  }

  @Override
  protected ResourceTestContext createContext() {
    return new ResourceTestContext(
            "/rest/vin",
            "chateau_pierre_bise_2::clos_le_grand_beaupreau::2010",
            "{" +
                    "\"domaine\": {\"id\": \"chateau_pierre_bise_2\", \"nom\":\"Château Pierre Bise 2\"}," +
                    "\"nom\":\"Clos Le Grand Beaupréau\"," +
                    "\"millesime\":2010," +
                    "\"appellation\":\"Savennières\"," +
                    "\"region\":\"Loire\"," +
                    "\"couleur\":\"Vin Blanc\"," +
                    "\"cepages\":\"Riesling\"," +
                    "\"terroirs\":\"Plateau situé au plus haut de l'appellation, au pied du moulin du Grand Beaupréau, en légère pente Sud\"," +
                    "\"vendanges\":\"manuelles, par tris successifs en octobre\"," +
                    "\"degre\":13.5" +
                    "}"
    );
  }

  // -------------------------- PUBLIC METHODS --------------------------

  @Resource(name = "vinResource")
  public void setResource(CrudResource resource) {
    super.setResource(resource);
  }
}
