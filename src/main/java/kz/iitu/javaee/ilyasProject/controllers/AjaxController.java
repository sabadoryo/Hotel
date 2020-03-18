package kz.iitu.javaee.ilyasProject.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import kz.iitu.javaee.ilyasProject.entities.AjaxResponseBody;
import kz.iitu.javaee.ilyasProject.entities.SearchCriteria;
import kz.iitu.javaee.ilyasProject.entities.Users;
import kz.iitu.javaee.ilyasProject.jsonview.Views;
import kz.iitu.javaee.ilyasProject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AjaxController {

    @Autowired
    private UserRepository userRepository;

    // @ResponseBody, not necessary, since class is annotated with @RestController
    // @RequestBody - Convert the json data into object (SearchCriteria) mapped by field name.
    // @JsonView(Views.Public.class) - Optional, limited the json data display to client.
    @JsonView(Views.Public.class)
    @CrossOrigin(origins = "http://localhost:8004")
    @RequestMapping(value = "/search/api/getSearchResult")
    public AjaxResponseBody getSearchResultViaAjax(@RequestBody SearchCriteria search) {
        System.out.println("ASDASDASDASDASDASDASDAS");
        AjaxResponseBody result = new AjaxResponseBody();


        if (isValidSearchCriteria(search)) {
            List<Users> users = findByUserNameOrEmail(search.getUsername(), search.getEmail());


            if (users.size() > 0) {
                result.setCode("200");
                result.setMsg("Email already in use! Choose another");
                result.setResult(users);
            } else {
                result.setCode("204");
                result.setMsg("");
            }

        } else {
            result.setCode("400");
            result.setMsg("Field is empty!");
        }
        if(isValidPassword(search)){
            if (search.getPassword().equals(search.getRepassword())) {
                result.setPasswordError("");
            } else {
                result.setPasswordError("Your password and repassword are not same");
            }
        }

        else{
            result.setPasswordError("Your password is less than 6 letters");
        }
        //AjaxResponseBody will be converted into json format and send back to client.
        return result;

    }

    private boolean isValidPassword(SearchCriteria search) {

        boolean valid = true;

        if (search == null) {
            valid = false;
        }
        if(search.getPassword().length()<6){
            valid=false;
        }
        if(StringUtils.isEmpty(search.getPassword())){
            valid=false;
        }

        return valid;
    }

    private boolean isValidSearchCriteria(SearchCriteria search) {

        boolean valid = true;

        if (search == null) {
            valid = false;
        }

        if ((StringUtils.isEmpty(search.getUsername())) && (StringUtils.isEmpty(search.getEmail()))) {
            valid = false;
        }


        return valid;
    }

    // Init some users for testing


    // Simulate the search function
    private List<Users> findByUserNameOrEmail(String username, String email) {

        List<Users> result = new ArrayList<Users>();
        List<Users> users = userRepository.findAll();
        for (Users user : users) {

            if ((!StringUtils.isEmpty(email))) {

                if (email.equals(user.getEmail())) {
                    result.add(user);
                    continue;
                } else {
                    continue;
                }

            }
            if (!StringUtils.isEmpty(username)) {
                if (username.equals(user.getFullName())) {
                    result.add(user);
                    continue;
                }
            }

            if (!StringUtils.isEmpty(email)) {
                if (email.equals(user.getEmail())) {
                    result.add(user);
                    continue;
                }
            }

        }

        return result;

    }
}
