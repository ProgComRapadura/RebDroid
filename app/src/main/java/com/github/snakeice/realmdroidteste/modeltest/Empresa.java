package com.github.snakeice.realmdroidteste.modeltest;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Test
 * Created by Rodrigo on 12/05/2016.
 */
public class Empresa extends RealmObject {
    @PrimaryKey
    private Long id;
    private String Cnpj;
    private String nome;
    private String nome2;
    private String nome3;
    private String nome4;
    private String nome5;
    private String nome6;
    private String nome7;
    private String nome8;
    private String nome9;
    private String nome10;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome2() {
        return nome2;
    }

    public void setNome2(String nome2) {
        this.nome2 = nome2;
    }

    public String getNome3() {
        return nome3;
    }

    public void setNome3(String nome3) {
        this.nome3 = nome3;
    }

    public String getNome4() {
        return nome4;
    }

    public void setNome4(String nome4) {
        this.nome4 = nome4;
    }

    public String getNome5() {
        return nome5;
    }

    public void setNome5(String nome5) {
        this.nome5 = nome5;
    }

    public String getNome6() {
        return nome6;
    }

    public void setNome6(String nome6) {
        this.nome6 = nome6;
    }

    public String getNome7() {
        return nome7;
    }

    public void setNome7(String nome7) {
        this.nome7 = nome7;
    }

    public String getNome8() {
        return nome8;
    }

    public void setNome8(String nome8) {
        this.nome8 = nome8;
    }

    public String getNome9() {
        return nome9;
    }

    public void setNome9(String nome9) {
        this.nome9 = nome9;
    }

    public String getNome10() {
        return nome10;
    }

    public void setNome10(String nome10) {
        this.nome10 = nome10;
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
