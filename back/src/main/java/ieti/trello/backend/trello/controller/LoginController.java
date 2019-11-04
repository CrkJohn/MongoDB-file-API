package ieti.trello.backend.trello.controller;


import ieti.trello.backend.trello.entities.User;
import ieti.trello.backend.trello.services.IUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.Date;

@RestController
@RequestMapping(value = "v1/login")
@CrossOrigin
public class LoginController {

    @Autowired
    IUserService userService;

    @PostMapping
    public Token login(@RequestBody User login) throws ServletException {

        String jwtToken = "";

        if (login.getUserName() == null || login.getPassword() == null) {
            throw new ServletException("Please fill in username and password");
        }

        String username = login.getUserName();
        String password = login.getPassword();

        //TODO implement logic to verify user credentials
        User user = userService.getUserById(username);

        if (user == null) {
            throw new ServletException("User username not found.");
        }

        String pwd = user.getPassword();

        if (!password.equals(pwd)) {
            throw new ServletException("Invalid login. Please check your name and password.");
        }
        //
        jwtToken = Jwts.builder().setSubject(username).claim("roles", "user").setIssuedAt(new Date()).signWith(
                SignatureAlgorithm.HS256, "secretkey").compact();

        return new Token(jwtToken);
    }


    public class Token{

        String accessToken;

        public Token( String accessToken )
        {
            this.accessToken = accessToken;
        }

        public String getAccessToken()
        {
            return accessToken;
        }

        public void setAccessToken( String access_token )
        {
            this.accessToken = access_token;
        }
    }

}
