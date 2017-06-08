//UIUC CS125 SPRING 2014 MP. File: Person.java, CS125 Project: Challenge6-RecursionSee, Version: 2014-04-04T10:07:09-0500.853918000
/**
 * @author dkaraca2
 *
 */
public class Person
{
private final String name;
private final int age;
private final char gender;
private final Person child1; //left child
private final Person child2; //right child

public Person(String name, int age, char gender, Person c1, Person c2)
{
    this.name=name;
    this.age=age;
    this.gender=gender;
    this.child1 = c1;
    this.child2 = c2;
}

public String toString()
{
    return name+"*"+age+"*"+gender;
}

public String getName() 
{
	return name;
}

public int getAge() 
{
	return age;
}

public char getGender() 
{
	return gender;
}

public boolean equals(Person p)
{
	return (this.name.equals(p.name)) &&
			(this.age==p.age) &&
			(this.gender==p.gender);
}


public void print() 
{
   System.out.println(this);
   if(child1 != null)
       child1.print();
   if(child2 != null)
       child2.print();
   
}

public int count() // total person count including this object
{
	int r = 1;
	if(child1 != null) r+= child1.count();
	if(child2 != null) r+= child2.count();
	return r;
}
public int countGrandChildren() // but not greatGrandChildren
{
	int r = 0;
	if(child1!= null && child1.child1 != null) r++;
	if(child2!= null && child2.child1 != null) r++;
	if(child1!= null && child1.child2 != null) r++;
	if(child2!= null && child2.child2 != null) r++;
	return r;
}

public int countMaxGenerations()
{
	int r = 1;
	if(child1 != null) r = Math.max(r, child1.countMaxGenerations()+1);
	if(child2 != null) r = Math.max(r, child2.countMaxGenerations()+1);
	return r;
}

public int countGender(char gen)
{ int r = 0;
if(this.gender == gen) r++;
if(child1 != null) r+= child1.countGender(gen);
if(child2 != null) r+= child2.countGender(gen);
return r;

}


public Person search(String name, int maxGeneration)
{ 	Person tmp = null;
	if(maxGeneration > 0){ 
	if(this.name.equals(name)) return this;
	if(child1!=null) tmp = child1.search(name,maxGeneration-1);
	if(tmp!=null) return tmp;
	if(child2!= null)tmp = child2.search(name, maxGeneration-1);
	return tmp;}
	return tmp;
	

	
}

} // end of class
