package com.kim.dec27.hw;

import java.util.Random;

public class Enemy {
	Random eBrain;

	public Enemy() {
		eBrain = new Random();
	}

	public int eFire() {
		return eBrain.nextInt(3) + 1;

	}

}
