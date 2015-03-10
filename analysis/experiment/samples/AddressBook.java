import java.util.*;
import java.io.*;

/**
 * @author Rian Shambaugh
 * Project 1
 * CS 6212
 * Spring 2015
 */

class Contact{
    private String first;
    private String last;
    private String phone;
    private String company;
    Contact(String first, String last, String phone, String company){
	this.first = first;
	this.last = last;
	this.phone = phone;
	this.company = company;
    }
    public String getFirst(){
	return first;
    }
    public String getLast(){
	return last;
    }
    public String getPhone(){
	return phone;
    }
    public String getCompany(){
	return company;
    }
    public String toString(){
	return first + ", " + last + ", " + phone + ", " + company;
    }
}

class SearchItem{
    private String value;
    private int index;
    
    SearchItem(String v, int i){
	this.value = v;
	this.index = i;
    }
    public String getValue(){
	return value;
    }
    public int getIndex(){
	return index;
    }
    public int compareTo(SearchItem b){
	if(value.startsWith(b.getValue())){
	    //matches partially at beginning
	    return 0;
	}
	return value.compareTo(b.getValue());
    }
    public String toString(){
	return value + ": " + index;
    }
}

public class AddressBook{
    private static ArrayList<Contact> contacts;
    private static SearchItem[] f;
    private static SearchItem[] l;
    private static SearchItem[] p;
    private static SearchItem[] c;

    public AddressBook(String filename){
	contacts = new ArrayList<Contact>();
	try{
	    Scanner in = new Scanner(new File(filename));
	    String line;

	    while(in.hasNext()){
		line = in.nextLine();
		String[] contactInfo = line.split(",");
		Contact c = new Contact(contactInfo[0], contactInfo[1], 
					contactInfo[2], contactInfo[3]);
		contacts.add(c);
	    }

	}catch(FileNotFoundException e){
	    e.printStackTrace();
	}	
	f = initFirst(contacts.size());
	l = initLast(contacts.size());
	p = initPhone(contacts.size());
	c = initCompany(contacts.size());
    }

    private SearchItem[] initFirst(int length){
	SearchItem[] ret = new SearchItem[length];
	for(int i=0; i<length; i++){
	    ret[i] = new SearchItem(contacts.get(i).getFirst(), i);
	}
	quicksort(ret, 0, ret.length-1);
	return ret;
    }

    private SearchItem[] initLast(int length){
	SearchItem[] ret = new SearchItem[length];
	for(int i=0; i<length; i++){
	    ret[i] = new SearchItem(contacts.get(i).getLast(), i);
	}
	quicksort(ret, 0, ret.length-1);
	return ret;
    }

    private SearchItem[] initPhone(int length){
	SearchItem[] ret = new SearchItem[length];
	for(int i=0; i<length; i++){
	    ret[i] = new SearchItem(contacts.get(i).getPhone(), i);
	}
	quicksort(ret, 0, ret.length-1);
	return ret;
    }

    private SearchItem[] initCompany(int length){
	SearchItem[] ret = new SearchItem[length];
	for(int i=0; i<length; i++){
	    ret[i] = new SearchItem(contacts.get(i).getCompany(), i);
	}
	quicksort(ret, 0, ret.length-1);
	return ret;
    }

    static void quicksort(SearchItem[] arr, int p, int r){
	int q;
	if(p < r){
	    q = partition(arr, p, r);
	    quicksort(arr, p, q-1);
	    quicksort(arr, q+1, r);
	}
    }

    static int partition(SearchItem[] arr, int p, int r){
	int pivotI = (p+r)/2;
	SearchItem pivotVal = arr[pivotI];
	//swap pivot element with A[r]
	SearchItem tmp = arr[pivotI];
	arr[pivotI] = arr[r];
	arr[r] =  tmp;
	int storeIndex = p;
	//compare remaining elements against pivotValue
	for(int i=p; i<r; i++){
	    if(arr[i].compareTo(pivotVal) < 0){
		tmp = arr[i];
		arr[i] = arr[storeIndex];
		arr[storeIndex] = tmp;
		storeIndex++;
	    }
	}
	tmp = arr[storeIndex];
	arr[storeIndex] = arr[r];
	arr[r] = tmp; //move pivot to final position
	return storeIndex;
    }

    static ArrayList<Contact> search(String key, String searchCriteria){
	SearchItem[] a;
	SearchItem k = new SearchItem(key, -1);
	if(searchCriteria.equals("F")){
	    a = f;
	}else if(searchCriteria.equals("L")){
	    a = l;
	}else if(searchCriteria.equals("P")){
	    a = p;
	}else{
	    a = c;
	}
	return binarySearch(a, 0, a.length, k);
    }

    static ArrayList<Contact> binarySearch(SearchItem[] A, int low, int high, SearchItem key){
	ArrayList<Contact> list = new ArrayList<Contact>();
	int mid = (low+high)/2;
	if(high < low || mid > A.length-1) return new ArrayList<Contact>(); //not found
	if(A[mid].compareTo(key) == 0){
	    //found it
	    list.add(contacts.get(A[mid].getIndex()));
	    //search both on left and right
	    list.addAll(binarySearch(A, low, mid-1, key));
	    list.addAll(binarySearch(A, mid+1, high, key));
	    return list;
	}else if(A[mid].compareTo(key) > 0){
	    //search on left
	    list.addAll(binarySearch(A, low, mid-1, key));
	    return list;
	}else{
	    //search on right
	    list.addAll(binarySearch(A, mid+1, high, key));
	    return list;
	}
    }
  
    public static void main(String[] args){
	String filename = args[0];
	Scanner in = new Scanner(System.in);

	//initialize Address book
	AddressBook book = new AddressBook(filename);
	System.out.println("Welcome to Addressbook, initialized");
	while(true){
	    long startTime = 0;
	    System.out.println("What would you like to search on? (F,L,P,C)");
	    //F = first name; L = last name; P = phone number; C = company name
	    String searchCriteria = in.next();
	    if(searchCriteria.equals("F")){
		System.out.println("Enter the partial First Name");
		String search = in.next();
		//search for |search| in first name list
		startTime = System.currentTimeMillis();
		ArrayList<Contact> list = search(search, "F");
		System.out.println("Results:");
		for(int i=0; i<list.size(); i++){
		    System.out.println(list.get(i));
		}
	    }else if(searchCriteria.equals("L")){
		System.out.println("Enter the partial Last Name");
		String search = in.next();
		//search for |search| in last name list	
		startTime = System.currentTimeMillis();
		ArrayList<Contact> list = search(search, "L");
		System.out.println("Results:");
		for(int i=0; i<list.size(); i++){
		    System.out.println(list.get(i));
		}	
	    }else if(searchCriteria.equals("P")){
		System.out.println("Enter the partial Phone Number");
		String search = in.next();
		//search for |search| in phone number name list
		startTime = System.currentTimeMillis();	
		ArrayList<Contact> list = search(search, "P");
		System.out.println("Results:");
		for(int i=0; i<list.size(); i++){
		    System.out.println(list.get(i));
		}	
	    }else if(searchCriteria.equals("C")){
		System.out.println("Enter the partial Company Name");
		String search = in.next();
		//search for |search| in company name list
		startTime = System.currentTimeMillis();
		ArrayList<Contact> list = search(search, "C");
		System.out.println("Results:");
		for(int i=0; i<list.size(); i++){
		    System.out.println(list.get(i));
		}
	    }else{
		System.out.println("Invalid input. Try again.");
	    }
	    System.out.println((System.currentTimeMillis() - startTime) + "ms");
	    System.out.println("Another Search? (Y,N)");
	    String s = in.next();
	    if(s.equals("N")) break;
	}
    }
}
