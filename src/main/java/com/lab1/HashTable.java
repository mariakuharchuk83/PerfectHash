package com.lab1;

import java.security.SecureRandom;
import java.util.*;

public class HashTable {
	private final List<List<RationalNumber>> hashTable;
	private final Hasher[] hashers;
	private final Hasher mainHasher;

	public HashTable(List<RationalNumber> list) {
		hashTable = new ArrayList<>(list.size());
		hashers = new Hasher[list.size()];
		for (int i = 0; i < list.size(); ++i) {
			hashTable.add(new LinkedList<>());
		}
		SecureRandom secureRandom = new SecureRandom();
		mainHasher = new Hasher(list.size(), getCoeff(secureRandom), getCoeff(secureRandom), getCoeff(secureRandom));
		for (RationalNumber num : list) {
			int hash = mainHasher.hash(num);
			boolean repeated = false;
			for (RationalNumber cellItem : hashTable.get(hash)) {
				if (num.equals(cellItem)) {
					repeated = true;
					break;
				}
			}
			if (!repeated) {
				hashTable.get(hash).add(num);
			}
		}

		fixTable();
	}

	private void fixTable() {
		for (int i = 0; i < hashTable.size(); ++i) {
			if (hashTable.get(i).size() < 2) {
				continue;
			}
			List<RationalNumber> cell = hashTable.get(i);
			int squareSize = cell.size() * cell.size();
			List<RationalNumber> newCell = new ArrayList<>(squareSize);
			for (int j = 0; j < squareSize; j++) {
				newCell.add(null);
			}
			boolean hasCollisions;
			hashers[i] = new Hasher(squareSize);
			SecureRandom secureRandom = new SecureRandom();
			do {
				hasCollisions = false;
				int a = getCoeff(secureRandom); 
				int b = getCoeff(secureRandom);
				int c = getCoeff(secureRandom);
				hashers[i].setCoeffs(a, b, c);
				for (RationalNumber num : cell) {
					int hash = hashers[i].hash(num);
					if (newCell.get(hash) != null) {
						hasCollisions = true;
						break;
					}
					newCell.set(hash, num);
				}
				if (hasCollisions) {
					Collections.fill(newCell, null);
					//System.out.println(a + " " + b + " " + c);
				}	
			} while (hasCollisions);
			hashTable.set(i, newCell);
		}
	}

	private int getCoeff(SecureRandom secureRandom) {
		return Math.abs(secureRandom.nextInt()) % 100;
	}

	public void print() {
		for (List<RationalNumber> cell : hashTable) {
			if (cell.size() == 0) {
				System.out.println("#");
				continue;
			}
			for (RationalNumber num : cell) {
				System.out.print(Objects.requireNonNullElse(num, "#"));
				System.out.print(" | ");
			}
			System.out.println();
		}
	}

	public boolean contains(RationalNumber num) {
        int mainHash = mainHasher.hash(num);
        if (hashTable.get(mainHash).size() == 0) {
            return false;
        }
        if (hashTable.get(mainHash).size() == 1) {
            return hashTable.get(mainHash).get(0).equals(num);
        }
        int hash = hashers[mainHash].hash(num);
        return hashTable.get(mainHash).get(hash) != null
                && hashTable.get(mainHash).get(hash).equals(num);
    }
}