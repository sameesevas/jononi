package com.example.idp_jononi_final_version;

public class backup {
    String fullname,spousename,email,password,condate, dof, height, emergency;


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getSpousename() {
        return spousename;
    }

    public void setSpousename(String spousename) {
        this.spousename = spousename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCondate() {
        return condate;
    }

    public void setCondate(String condate) {
        this.condate = condate;
    }

    public String getDof() {
        return dof;
    }

    public void setDof(String dof) {
        this.dof = dof;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getEmergency() {
        return emergency;
    }

    public void setEmergency(String emergency) {
        this.emergency = emergency;
    }



    public backup(String fullname, String spousename, String email, String password, String condate, String dof, String height, String emergency) {
        this.fullname = fullname;
        this.spousename = spousename;
        this.email = email;
        this.password = password;
        this.condate = condate;
        this.dof = dof;
        this.height = height;
        this.emergency = emergency;
    }
    public backup() {
    }

}
