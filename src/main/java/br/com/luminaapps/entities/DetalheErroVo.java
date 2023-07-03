package br.com.luminaapps.entities;

public class DetalheErroVo {
    private String description;
    private String parametro;

    public DetalheErroVo() {

    }

    public DetalheErroVo(String description, String parametro) {
        this.description = description;
        this.parametro = parametro;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }
}
