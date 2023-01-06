import java.util.concurrent.*;
import java.util.stream.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Supplier; 

class Employee{
	
	private String name;
	private int age;
	private double salary;
	private char gender;
	
	Employee(String name, int age, double amount, char gender){
		this.name = name;
		this.age=age;
		this.salary=amount;
		this.gender = gender;
	}
	
	char getGender() {
		return this.gender;
	}
	void setGender(char gender) {
		this.gender = gender;
	}
	
	String getName() {
		return this.name;
	}
	void setName(String name) {
		this.name = name;
	}
	
	double getSalary() {
		return this.salary;
	}
	
	void setSalary(int salary) {
		this.salary = salary;
	}
	
	int getAge() {
		return this.age;
	}
}
public class sequencialStream {

	  public static void main(String[] args) {
		  
		  ArrayList<Employee> employeeData = new ArrayList<Employee>();
		  employeeData.add(new Employee("hritik", 22, 25000, 'M'));
		  employeeData.add(new Employee("hardik", 21, 30000, 'M'));
		  employeeData.add(new Employee("abhishek", 22, 35000, 'M'));
		  employeeData.add(new Employee("shreya", 22, 25000, 'F'));
		  employeeData.add(new Employee("divyesh", 23, 50000, 'M'));
		  employeeData.add(new Employee("swaraj", 20, 80000, 'M'));
		  employeeData.add(new Employee("Asmita", 24, 20000, 'F'));
		  employeeData.add(new Employee("Ginni", 22, 70000, 'F'));
		  employeeData.add(new Employee("Rakshita", 21, 50000, 'F'));
		  
		  // increment the salary of female employees having less than 40000 by 10%
          ArrayList<Employee> newList = employeeData.stream().filter(emp -> (emp.getGender()=='F'))
        		                        .filter(emp->(emp.getSalary() <= 40000)).map(emp -> new Employee(emp.getName(),
        		                         emp.getAge(), emp.getSalary()*1.10 , emp.getGender())).collect(Collectors.toCollection(ArrayList::new)) ;
		  
          // print the newList
          newList.forEach(emp -> System.out.println(emp.getName() + "  " + emp.getSalary()));
          
          // finding sum of salaries of all employees

//          int totalSalary = employeeData.stream().reduce((ans.toDouble() , emp.getSalary()) = ans.toDouble() + emp.getSalary());
//          System.out.println("total Salary of All employees is : " + totalSalary);

          // sorting all employees on the basis of salary
          employeeData.stream().sorted(Comparator.comparing(Employee::getSalary) ).forEach(emp -> System.out.println(emp.getName() + " " + emp.getSalary()));;
	  
	      // find the employee with minimum salary
          
         Optional<Employee> minSalaryEmployee = employeeData.stream().min(Comparator.comparing(Employee::getSalary));
         minSalaryEmployee.ifPresent(emp -> System.out.println(emp.getName()));
         
          // ways to generate infinite stream
         
         // i) using generate method  :- supplier function
         Stream.generate(Math::random).limit(5).forEach(x -> System.out.println(x));

         
         // ii). using iterate method :- seed value , logic
          
         Stream<Integer> pow2NumStream = Stream.iterate(2, i -> i * 2);
         
         ArrayList<Integer> pow2List = pow2NumStream.limit(5).collect(Collectors.toCollection(ArrayList::new));
         System.out.println(pow2List);
	  }
}
