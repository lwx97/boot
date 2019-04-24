/**
 * 
 */
package com.boot.model;
 
 
public class Dept {
	private int id;
	private String name;
	private String addr;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return super.toString();
    }
}