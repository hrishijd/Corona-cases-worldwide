package com.example.models;

public class Strain {
	private int latestRp;
	private String Province;
	private String Country;
	public int getLatestRp() {
		return latestRp;
	}
	public void setLatestRp(int latestRp) {
		this.latestRp = latestRp;
	}
	public String getProvince() {
		return Province;
	}
	public void setProvince(String province) {
		Province = province;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	@Override
	public String toString() {
		return "Strain [latestRp=" + latestRp + ", Province=" + Province + ", Country=" + Country + "]";
	}

}
