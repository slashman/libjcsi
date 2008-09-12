package net.slashie.util;

/**
 * 
 * @author Slashie
 *
 * @param <K> The type of the reference param
 */

public class ReferenceParam <K>{
	private K value;

	public K getValue() {
		return value;
	}

	public void setValue(K value) {
		this.value = value;
	}
}
