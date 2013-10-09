/* +-----------------------------------------------------------------------------------------------+
 *                                                                                                
 *  Titre          : CaracteritiqueVinResource                                                                 
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
 *  LEBRUN_G 08/10/13 Creation                                                                      
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

import com.oxiane.caveavin.mapper.IDocMapper;
import com.oxiane.caveavin.mapper.impl.AbstractDocMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * User: LEBRUN_G
 * Date: 08/10/13
 * Time: 17:38
 */
@Path("rest/param")
public class ParamResource extends AbstractResource<Map<String, Object>> {

  private final IDocMapper<Map<String, Object>> docMapper = new AbstractDocMapper<Map<String, Object>>() {
    @Override
    public Map<String, Object> toEntity(String id, String doc) {
      Map<String, Object> map = new LinkedHashMap<String, Object>();
      map.put("id", id);
      map.put("nom", doc);
      return map;
    }
  };

  @GET
  @Path("regions/by_name")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Map<String, Object>> find(@QueryParam("startKey") String startKey, @QueryParam("endKey") String endKey) {
    return getDao().findAll("region_by_name", startKey, endKey, docMapper);
  }

  @GET
  @Path("{idRegion}/appellations")
  @Produces(MediaType.APPLICATION_JSON)
  public List<String> find(@PathParam("idRegion") String idRegion) {
    final Map<String, Object> map = getDao().find(idRegion);
    //noinspection unchecked
    return map == null ? Collections.<String>emptyList() : (List<String>) map.get("appellations");
  }

  @GET
  @Path("vin/caracteristiques")
  @Produces(MediaType.APPLICATION_JSON)
  public Map<String, Object> find() {
    return getDao().find("param::vin::caracteristiques");
  }
}
