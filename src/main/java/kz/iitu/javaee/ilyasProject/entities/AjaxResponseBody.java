package kz.iitu.javaee.ilyasProject.entities;


import com.fasterxml.jackson.annotation.JsonView;
import kz.iitu.javaee.ilyasProject.jsonview.Views;

import java.util.List;

public class AjaxResponseBody {

    @JsonView(Views.Public.class)
    String msg;
    @JsonView(Views.Public.class)
    String code;
    @JsonView(Views.Public.class)
    String passwordError;


    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    @JsonView(Views.Public.class)
    List<Users> result;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setResult(List<Users> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "AjaxResponseResult [msg=" + msg + ", code=" + code + ", result=" + result + "]";
    }

}

