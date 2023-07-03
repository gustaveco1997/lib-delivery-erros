package br.com.luminaapps.entities;

import java.util.ArrayList;
import java.util.List;

public class ErrorVo {
    private int code;
    private String message;
    private List<DetalheErroVo> errors = new ArrayList<>();

    public void addError(DetalheErroVo detalheErro){
        errors.add(detalheErro);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DetalheErroVo> getErrors() {
        return errors;
    }

    public void setErrors(List<DetalheErroVo> errors) {
        this.errors = errors;
    }
}
