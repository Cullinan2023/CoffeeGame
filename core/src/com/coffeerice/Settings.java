package com.coffeerice;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Settings {

	public static boolean isMusicOn;
	public static boolean isSoundOn;
	public static int playCount;
	public static long bestScore;

	private final static Preferences pref = Gdx.app
			.getPreferences("com.coffeerice");

	public static void load() {

		bestScore = pref.getLong("bestScore", 0);
		playCount = pref.getInteger("playCount", 0);
		isMusicOn = pref.getBoolean("isMusicOn", true);
		isSoundOn = pref.getBoolean("isSoundOn", true);
	}

	public static void save() {
		pref.putLong("bestScore", bestScore);
		pref.putInteger("playCount", playCount);
		pref.putBoolean("isMusicOn", isMusicOn);
		pref.putBoolean("isSoundOn", isSoundOn);
		pref.flush();

	}

	public static void setBestScores(long score) {
		if (bestScore < score)
			bestScore = score;
		save();

	}
}