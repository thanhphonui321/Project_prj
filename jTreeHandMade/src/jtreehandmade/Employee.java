/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtreehandmade;

/**
 *
 * @author nguyenducthanh
 */
public class Employee {
    private String code;
    private String name;
    private int salary;

    public Employee() {
    }

    public Employee(String code, String name, int salary) {
        this.code = code;
        this.name = name;
        this.salary = salary;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return code+"-"+name;
    }
    

    
}
