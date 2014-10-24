package com.oxiane.caveavin.rest;

import com.oxiane.caveavin.security.TokenUtils;
import com.oxiane.caveavin.transfer.UserTransfer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Component
@Path("/auth")
public class AuthenticationResource {
  // ------------------------------ FIELDS ------------------------------

  private UserDetailsService    userService;
  private AuthenticationManager authManager;

  // --------------------- GETTER / SETTER METHODS ---------------------

  public void setAuthManager(AuthenticationManager authManager) {
    this.authManager = authManager;
  }

  public void setUserService(UserDetailsService userService) {
    this.userService = userService;
  }

  // -------------------------- PUBLIC METHODS --------------------------

  @Path("login")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public UserTransfer login(@FormParam("username") String username, @FormParam("password") String password) {
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
    Authentication authentication = this.authManager.authenticate(authenticationToken);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    Map<String, Boolean> roles = new HashMap<String, Boolean>();

		/*
     * Reload user as password of authentication principal will be null after authorization and
		 * password is needed for token generation
		 */
    UserDetails userDetails = this.userService.loadUserByUsername(username);

    for (GrantedAuthority authority : userDetails.getAuthorities()) {
      roles.put(authority.toString(), Boolean.TRUE);
    }

    return new UserTransfer(userDetails.getUsername(), roles, TokenUtils.createToken(userDetails));
  }
}
