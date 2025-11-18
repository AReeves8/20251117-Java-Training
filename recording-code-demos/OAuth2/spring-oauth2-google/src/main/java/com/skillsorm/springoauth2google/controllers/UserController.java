package com.skillsorm.springoauth2google.controllers;

import java.util.Map;

//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@CrossOrigin(allowCredentials = "true", originPatterns = "http://localhost:5173")
public class UserController {
    

    @Autowired
    private OAuth2AuthorizedClientService clientService;


    @GetMapping("/helloWorld")
    @ResponseBody       // sends data back as JSON
    public String helloWorld() {
        return "Hello World!";
    }


    // get method for retreiving user's info that we have permission to use (scope)
    @GetMapping("/userinfo")
    @ResponseBody
    public Map<String, Object> userInfo(@AuthenticationPrincipal OAuth2User user) {
        return user.getAttributes();
    }


    @GetMapping("/accessToken")
    @ResponseBody
    public String accessToken(Authentication auth) {

        // checking if the auth we pulled from the security context is a OAuth2AuthenticationToken
        if(auth instanceof OAuth2AuthenticationToken) {

            // casting the Authentication object to be a OAuth2AuthenticationToken object
            OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) auth;

            // retrieving the authorized client with *this specific* Authentication Principal (each user is unique)
            OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(authToken.getAuthorizedClientRegistrationId(), authToken.getName());


            /* Something like this for adding access token to http only cookie - make sure to take in HttpServletResponse as parameter
            Cookie cookie = new Cookie("Access Token", client.getAccessToken().getTokenValue());
            cookie.isHttpOnly();
            response.addCookie(cookie);
            */


            // returning the value of the token
            return client.getAccessToken().getTokenValue();
        }

        return "";
    }

    /**
     * Use ths endpoint as a dedicated way to authenticate tte user with the OAuth2 client
     *      - This also provides a convenient way to get JSESSIONID and X-XSRF-TOKEN
     * 
     * 
     *      -Flow:
     *          - frontend redirects to this endpoint
     *          - this endpoint requires auenticatation so OAuth2 interjects
     *          - the user signs in with auth server
     *          - the contine on to this enddpoint
     *          - this endpoint just sends straight back to frontend
     */
    @GetMapping("/signin") 
    public RedirectView redirectView() {
        return new RedirectView("http://localhost:5173");
    }
}
