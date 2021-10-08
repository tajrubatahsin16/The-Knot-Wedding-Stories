package com.example.knotwedding.Model;

public class Users {
    private String name,passwd,pne;

    public Users(){


    }

    public Users(String name,String passwd,String pne) {
        this.name = name;
        this.passwd = passwd;
        this.pne = pne;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getPne() {
        return pne;
    }

    public void setPne(String pne) {
        this.pne = pne;
    }
}
