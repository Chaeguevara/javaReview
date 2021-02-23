package com.class3;

public class ex6_2 {

	public static void main(String[] args) {
		Tv t1 = new Tv();
		Tv t2 = new Tv();
		
		System.out.println("Current channel of t1: " + t1.channel);
		System.out.println("Current channel of t2: " + t2.channel);
		
		t1.channel = 7;
		System.out.println("Current channel of t1: " + t1.channel);
		System.out.println("Current channel of t2: " + t2.channel);


	}

}

