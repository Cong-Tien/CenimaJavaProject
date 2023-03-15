package com.lecongtien.cinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class FaceBookCtl {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/data-fb")
    public String getDataFb(){
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("access_token", "EAAJatW8NPrUBAC0ZCwy3JYnpF4IvZBFJwreL0QFd3INtwjs5wSzfR0dOi6OmdQPnVNNDYlcriJgMCAA7h3IBTcr1jTl5iyZAfsic9xalMZBNhQXfjzCYOHEZBmb5KxzCsaB65zdXYrC6I07QScBUbxiCjAnT3GedpYamnfauw8UF8kmxYdZB6XZCHwkqVyJIcV56nO9laDTa9x7hfOBfO6w");

        //String o = restTemplate.postForObject("https://graph.facebook.com/me", map, String.class);
        final String baseUrl = "https://graph.facebook.com/me?access_token=" + "EAAJatW8NPrUBAKZCZBeo3cZCFg58muB4jE7DoBZBjHlO7hFhTWd815g7auda4UJSlvJCZBqaotqKTSAPUIAEFmjZB4D4bPlLr9sCLsHEsQvML6P2Y8NBPZAq10uGllzh1H9SFE4mjKlfKgsO7GAQE6gqJALtGqvOsQjlqqiEGyGgphrent7AXgrNLXrc8ZCMTdVnIhNjzXn9pIhJgO2F51qZA";


        ResponseEntity<String> result = restTemplate.getForEntity(baseUrl, String.class);
        //System.out.println(result.ge);
        return result.getBody();
    }
}
