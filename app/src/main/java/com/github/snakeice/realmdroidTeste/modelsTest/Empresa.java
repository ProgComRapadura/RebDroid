package com.github.snakeice.realmdroidTeste.modelsTest;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Rodrigo on 12/05/2016.
 */
public class Empresa extends RealmObject {
    @PrimaryKey
    private Long id;
    private String Cnpj;
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return Cnpj;
    }

    public void setCnpj(String cnpj) {
        Cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
