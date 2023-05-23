package com.example.elly31;

public class Psych {
    //правила в firebase на серве

    public String id,name,sec_name,third_name,email,education,password,specializatiom;
    public Psych(){
    }
    public Psych(String id, String  name, String sec_name, String third_name, String email,
                 String education,String password ) {
        this.id = id;
        this.name = name;
        this.sec_name = sec_name;
        this.third_name = third_name;
        this.email = email;
        this.education = education;
        this.password = password;
        //this.specializatiom =specializatiom;
    }

}
