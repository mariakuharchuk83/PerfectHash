package com.lab1;

public class Hasher {
	private final int m;
	private int a;
	private int b;
	private int c;

	public Hasher(int m, int a, int b, int c) {
		this.m = m;
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public Hasher(int m) {
		this.m = m;
	}

	public int hash(RationalNumber num) { 
		int p = findP();
		double val = (double) num.getNumenator() / num.getDenominator();
		int one = Math.abs(a * num.getNumenator());
        int two = Math.abs(b * num.getDenominator());
		int three = c * f(val);	
		int hash = ((one + two + three) % p) % m;
		return hash;
	}

	public int f(double d) {
		return d >= 0 ? 1 : 0;
	}

	public void setCoeffs(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	private int findP() {
		int p = m + 1;
		while (!isPrime(p)) {
			p++;
		}
		return p;
	}

	private boolean isPrime(int num) {
		for (int i = (int) Math.sqrt(num); i >= 2; i--) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
}