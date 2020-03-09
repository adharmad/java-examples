package com.example.javaexamples.xml;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.Pointer;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class TryJXPath1 {
    public static void main(String[] args) {
        Company company = new Company("Acme");
        company.setLocation("somewhere");
        company.setPlace("somewhere");
        company.setpCodErro(new JAXBElement<String>(QName.valueOf("http://string"), String.class, "hello"));

        List departments = new ArrayList();
        departments.add(new Department("Sales"));
        departments.add(new Department("Accounting"));

        List employees = new ArrayList();
        employees.add(new Employee("Johnny", "Sales rep", 45));
        employees.add(new Employee("Sarah", "Sales rep", 33));
        employees.add(new Employee("Magda", "Office assistant", 27));
        ((Department) departments.get(0)).setEmployees(employees);

        employees = new ArrayList();
        employees.add(new Employee("Steve", "Head Controller", 51));
        employees.add(new Employee("Peter", "Assistant Controller", 31));
        employees.add(new Employee("Susan", "Office assistant", 27));
        ((Department) departments.get(1)).setEmployees(employees);

        company.setDepartmentList(departments);

        JXPathContext context = JXPathContext.newContext(company);

        //Simple JXPath queries
        //Company c = (Company) context.getValue(".");
        //System.out.println(c);

        String name = (String) context.getValue("/name");
        System.out.println(name);

        // filter
//        boolean isAcme = (boolean)context.getValue("/name = 'Acme'");
//        System.out.println(isAcme);
//
//        // another filter
//        isAcme = (boolean)context.getValue("(/name) = 'Acme'");
//        System.out.println(isAcme);
//
//        // yet another filter
//        isAcme = (boolean)context.getValue("((/name) = 'Acme')");
//        System.out.println(isAcme);

        // complex filter
//        boolean ret = (boolean)context.getValue("((/location) = (/place))");
//        System.out.println(ret);
//
//        // another complex filter
//        boolean ret1 = (boolean)context.getValue("((/name = 'Acme1') and (/place = 'somewhere'))");
//        System.out.println(ret1);


        // qname based filter
        boolean ret2 = (boolean)context.getValue("/pCodErro = 'hello'");
        System.out.println(ret2);

        /*
        //Using predicates
        Employee emp = (Employee) context.getValue("/departmentList/employees[name='Johnny']");
        System.out.println(emp);
        emp = (Employee) context.getValue("/departmentList/employees[name='Susan' and age=27]");
        System.out.println(emp);

        emp = (Employee) context.getValue("/departmentList/employees[name='Richard']");
        System.out.println(emp);
         */

        /*
        //Using variables
        context.getVariables().declareVariable("name", "Susan");
        context.getVariables().declareVariable("age", new Integer(27));
        emp = (Employee) context.getValue("/departmentList/employees[name=$name and age=$age]");
        System.out.println(emp);

        //Iterating
        for (Iterator iter = context.iterate("/departmentList"); iter.hasNext(); ) {
            Department d = (Department) iter.next();
            System.out.println(d);
        }
        for (Iterator iter = context.iterate("/departmentList/employees"); iter.hasNext(); ) {
            emp = (Employee) iter.next();
            System.out.println(emp);
        }
        for (Iterator iter = context.iterate("/departmentList[name='Sales']/employees[age>30]"); iter.hasNext(); ) {
            emp = (Employee) iter.next();
            System.out.println(emp);
        }

        //Iteratin using variables
        context.getVariables().declareVariable("deptName", "Sales");
        context.getVariables().declareVariable("minAge", new Integer(30));
        for (Iterator iter = context.iterate("/departmentList[name=$deptName]/employees[age>$minAge]"); iter.hasNext(); ) {
            emp = (Employee) iter.next();
            System.out.println(emp);
        }
        */
    }
}
