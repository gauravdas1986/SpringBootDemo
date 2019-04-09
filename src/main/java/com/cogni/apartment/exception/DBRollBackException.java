package com.cogni.apartment.exception;

public class DBRollBackException extends Exception{
	public DBRollBackException() {
		// TODO Auto-generated constructor stub
	}
	public DBRollBackException(String str) {
		System.out.println("EXCEPTION OBJECT "+str);
	}

	public void display() {
	    throw new RuntimeException();    
	}
}
