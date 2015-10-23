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
public class Journalist extends Employee {

    private String workProvince;
    private String workCity;
    private String workDivision;

    public Journalist() {

    }

    public Journalist(String username, String password, String joinDate) {
        super(username, password, joinDate);
    }

    public String getWorkProvince() {
        return workProvince;
    }

    public void setWorkProvince(String workProvince) {
        this.workProvince = workProvince;
    }

    public String getWorkCity() {
        return workCity;
    }

    public void setWorkCity(String workCity) {
        this.workCity = workCity;
    }

    public String getWorkdivision() {
        return workDivision;
    }

    public void setWorkdivision(String workdivision) {
        this.workDivision = workdivision;
    }

}
