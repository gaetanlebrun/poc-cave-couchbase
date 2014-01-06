/* +-----------------------------------------------------------------------------------------------+
 *                                                                                                
 *  Titre          : UserTransfer                                                                 
 *  Description    : Définition                                         
 *  Copyright      : Cofiroute 2013                                                            
 *  Societe        : Cofiroute                                                                    
 *  Author         : LEBRUN_G                                                                      
 *  Version        : 1.0.0                                                                        
 *  Package        : com.oxiane.caveavin.transfer                                                              
 *  Parametre VM   : Neant                                                                        
 *  Parametre main : Neant                                                                        
 *                                                                                                
 * +-----------------------------------------------------------------------------------------------+
 *  Historique des mises a jour :                                                                 
 *  Auteur Date       Description                                                                 
 *  ------- ------------------------------------------------------------------------------------- 
 *  LEBRUN_G 22/10/13 Creation                                                                      
 *                                                                                                
 * +-----------------------------------------------------------------------------------------------+
 *  Remarques :                                                                                   
 *                                                                                                
 * +-----------------------------------------------------------------------------------------------+
 *  RAF : Neant                                                                                   
 *                                                                                                
 * +-----------------------------------------------------------------------------------------------+
 */
package com.oxiane.caveavin.transfer;

import java.util.Map;

/**
 * User: LEBRUN_G
 * Date: 22/10/13
 * Time: 11:02
 */
public class UserTransfer {

  private final String               name;
  private final Map<String, Boolean> roles;
  private final String               token;


  public UserTransfer(String userName, Map<String, Boolean> roles, String token) {

    this.name = userName;
    this.roles = roles;
    this.token = token;
  }

  public String getName() {

    return this.name;
  }

  public Map<String, Boolean> getRoles() {

    return this.roles;
  }

  public String getToken() {

    return this.token;
  }

}
