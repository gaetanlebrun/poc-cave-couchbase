package com.oxiane.caveavin.rest;

import com.oxiane.caveavin.provider.ObjectMapperContextResolver;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.LowLevelAppDescriptor;
import com.sun.jersey.test.framework.spi.container.inmemory.InMemoryTestContainerFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: gaetan
 * Date: 10/6/13
 * Time: 2:13 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractResourceTest<V> {
  // ------------------------------ FIELDS ------------------------------

  private CrudResource<V>     resource;
  private JerseyTest          jersey;
  private ObjectMapper        mapper;
  private ResourceTestContext context;
  // --------------------- GETTER / SETTER METHODS ---------------------

  public ResourceTestContext getContext() {
    return context;
  }

  public JerseyTest getJersey() {
    return jersey;
  }

  public ObjectMapper getMapper() {
    return mapper;
  }

  public void setResource(CrudResource<V> resource) {
    this.resource = resource;
  }

  // -------------------------- PUBLIC METHODS --------------------------

  @After
  public void after() throws Exception {
    jersey.tearDown();
  }

  @Before
  public void before() throws Exception {
    context = createContext();
    jersey = new JerseyTest(new InMemoryTestContainerFactory()) {
      protected com.sun.jersey.test.framework.AppDescriptor configure() {
        DefaultResourceConfig rc = new DefaultResourceConfig();
        rc.getSingletons().add(resource);
        rc.getClasses().add(org.codehaus.jackson.jaxrs.JacksonJsonProvider.class);
        rc.getClasses().add(ObjectMapperContextResolver.class);
        return new LowLevelAppDescriptor.Builder(rc).contextPath("backend-pojo").build();
      }
    };
    jersey.setUp();
    mapper = new ObjectMapper();
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

  protected void create() throws IOException {
    String result = jersey.resource().path(context.getUrl()).type(MediaType.APPLICATION_JSON).post(String.class, context.getJson());
    assertNotNull(result);
    assertEquals(expectedJsonMapWithId(), mapper.readValue(result, Map.class));
  }

  protected Map<String, Object> expectedJsonMapWithId() throws IOException {
    return expectedJsonMapWithId(jsonMap());
  }

  protected Map<String, Object> jsonMap() throws IOException {
    //noinspection unchecked
    return (Map<String, Object>) mapper.readValue(context.getJson(), Map.class);
  }

  protected void find() throws IOException {
    String result = jersey.resource().path(context.getIdUrl()).get(String.class);
    assertEquals(expectedJsonMapWithId(), mapper.readValue(result, Map.class));
  }

  protected void update() throws IOException {
    final Map<String, Object> jsonMap = jsonMap();
    applyModification(jsonMap);
    String result = jersey.resource().path(context.getIdUrl()).type(MediaType.APPLICATION_JSON_TYPE).put(String.class, mapper.writeValueAsString(jsonMap));
    assertNotNull(result);
    assertEquals(expectedJsonMapWithId(jsonMap), mapper.readValue(result, Map.class));
  }

  protected abstract void applyModification(Map<String, Object> jsonMap);

  protected Map<String, Object> expectedJsonMapWithId(Map<String, Object> baseJsonMap) throws IOException {
    final Map<String, Object> expectedJsonMap = new LinkedHashMap<String, Object>();
    expectedJsonMap.put("id", context.getId());
    expectedJsonMap.putAll(baseJsonMap);
    return expectedJsonMap;
  }

  protected void findAll() throws IOException {
    String result = jersey.resource().path(context.getUrl()).type(MediaType.APPLICATION_JSON).get(String.class);
    assertNotNull(result);
    //noinspection unchecked
    List<Map<String, Object>> list = (List<Map<String, Object>>) mapper.readValue(result, List.class);
    assertTrue(list.size() > 0);
    for (Map<String, Object> map : list) {
      assertExcerpt(map);
    }
  }

  protected abstract void assertExcerpt(Map<String, Object> map);

  protected void delete() throws IOException {
    ClientResponse response = jersey.resource().path(context.getIdUrl()).type(MediaType.APPLICATION_OCTET_STREAM).delete(ClientResponse.class);
    assertEquals(200, response.getStatus());
  }
}
