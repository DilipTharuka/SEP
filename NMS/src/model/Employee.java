/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Dilip
 */
import java.util.ArrayList;

public class Employee {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String country;
    private String state;
    private String joinDate;
    private String leftDate;
    private ArrayList<String> mobiles;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Employee() {
        this.mobiles = new ArrayList<String>();
    }

    public Employee(String username, String password, String joinDate) {
        this.username = username;
        this.password = password;
        this.joinDate = joinDate;
        mobiles = new ArrayList<String>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getLeftDate() {
        return leftDate;
    }

    public void setLeftDate(String leftDate) {
        this.leftDate = leftDate;
    }

    public void addMobile(String mobile) {
        this.mobiles.add(mobile);
    }

    public void setMobileList(ArrayList<String> mobiles) {
        this.mobiles = mobiles;
    }

    public ArrayList<String> getMobileList() {
        return this.mobiles;
    }

}
