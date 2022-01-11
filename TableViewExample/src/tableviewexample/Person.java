/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableviewexample;

/**
 *
 * @author Dell
 */
public class Person {
    int id;
    String name;
    String lname;
    String gmail;
    String yahoo;
    String phone;
    String country;

    public Person(int id, String name, String lname, String gmail, String yahoo, String phone, String country) {
        this.id = id;
        this.name = name;
        this.lname = lname;
        this.gmail = gmail;
        this.yahoo = yahoo;
        this.phone = phone;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLname() {
        return lname;
    }

    public String getGmail() {
        return gmail;
    }

    public String getYahoo() {
        return yahoo;
    }

    public String getPhone() {
        return phone;
    }

    public String getCountry() {
        return country;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public void setYahoo(String yahoo) {
        this.yahoo = yahoo;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    
}
