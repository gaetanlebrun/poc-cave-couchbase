package com.oxiane.caveavin.rest;

import com.oxiane.caveavin.provider.ObjectMapperContextResolver;
import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.LowLevelAppDescriptor;
import com.sun.jersey.test.framework.spi.container.inmemory.InMemoryTestContainerFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public abstract class AbstractResourceTest {
  // ------------------------------ FIELDS ------------------------------

  private AbstractResource resource;
  private JerseyTest          jersey;
  private ObjectMapper        mapper;
  // --------------------- GETTER / SETTER METHODS ---------------------

  public JerseyTest getJersey() {
    return jersey;
  }

  public ObjectMapper getMapper() {
    return mapper;
  }

  public void setResource(AbstractResource resource) {
    this.resource = resource;
  }

  // -------------------------- PUBLIC METHODS --------------------------

  @After
  public void after() throws Exception {
    jersey.tearDown();
  }

  @Before
  public void before() throws Exception {
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
    mapper = new ObjectMapperContextResolver().getContext(null);
  }

  protected List<Map<String, Object>> getMapList(String result) throws IOException {
    //noinspection unchecked
    return (List<Map<String, Object>>) getMapper().readValue(result, List.class);
  }

  protected List<String> getStringList(String result) throws IOException {
    //noinspection unchecked
    return (List<String>) getMapper().readValue(result, List.class);
  }

  protected Map<String, Object> getMap(String result) throws IOException {
    //noinspection unchecked
    return (Map<String, Object>) getMapper().readValue(result, Map.class);
  }
}
