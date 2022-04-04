package com.lab1;

public class RationalNumber {
	private int numenator;
	private int denominator;

	public RationalNumber(int numenator, int denominator) {
		this.numenator = numenator;
		this.denominator = denominator;
	}

	public int getNumenator() {
		return numenator;
	}

	public int getDenominator() {
		return denominator;
	}

	@Override
	public String toString() {
		return String.format("(%d/%d)", numenator, denominator);
	}

@Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof RationalNumber)) {
      return false;
    }
    RationalNumber c = (RationalNumber) o;
    return this.numenator == c.getNumenator() && this.denominator == c.getDenominator();
  }
}