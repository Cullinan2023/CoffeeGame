package com.coffeerice.objetos;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.coffeerice.Assets;

import java.util.LinkedHashMap;

public class Piece extends Actor {
	public boolean justChanged = false;
	
	final static LinkedHashMap<Integer, Vector2> mapPositions = new LinkedHashMap<Integer, Vector2>();
	static {
		mapPositions.put(0, new Vector2(20, 350));
		mapPositions.put(1, new Vector2(130, 350));
		mapPositions.put(2, new Vector2(240, 350));
		mapPositions.put(3, new Vector2(350, 350));
		mapPositions.put(4, new Vector2(20, 240));
		mapPositions.put(5, new Vector2(130, 240));
		mapPositions.put(6, new Vector2(240, 240));
		mapPositions.put(7, new Vector2(350, 240));
		mapPositions.put(8, new Vector2(20, 130));
		mapPositions.put(9, new Vector2(130, 130));
		mapPositions.put(10, new Vector2(240, 130));
		mapPositions.put(11, new Vector2(350, 130));
		mapPositions.put(12, new Vector2(20, 20));
		mapPositions.put(13, new Vector2(130, 20));
		mapPositions.put(14, new Vector2(240, 20));
		mapPositions.put(15, new Vector2(350, 20));
	}

	final float SIZE = 110;
	public int position;

	private int value;
	TextureRegion keyframe;

		public Piece(int position, int value) {
		this.position = position;
		setWidth(SIZE);
		setHeight(SIZE);
		setOrigin(SIZE / 2f, SIZE / 2f);

		setPosition(mapPositions.get(position).x, mapPositions.get(position).y);
		setValue(value);

		if (value != 0) {
			setScale(.8f);
			addAction(Actions.scaleTo(1, 1, .25f));
		}

	}

	public int getValue() {
		return value;
	};

	public void setValue(int value) {
		this.value = value;
		switch (value) {
		default:
		case 0:
			keyframe = Assets.pieceEmpty;
			break;
		case 3:
			keyframe = Assets.piece3;
			break;
		case 9:
			keyframe = Assets.piece9;
			break;
		case 27:
			keyframe = Assets.piece27;
			break;
		case 81:
			keyframe = Assets.piece81;
			break;
		case 243:
			keyframe = Assets.piece243;
			break;
		case 729:
			keyframe = Assets.piece729;
			break;
		case 2187:
			keyframe = Assets.piece2187;
			break;
		case 6561:
			keyframe = Assets.piece6561;
			break;
		case 19683:
			keyframe = Assets.piece19683;
			break;
		case 59049:
			keyframe = Assets.piece59049;
			break;
		case 177147:
			keyframe = Assets.piece177147;
			break;
		case -1:
			keyframe = Assets.bombItemPiece;
			break;
		case -2:
			keyframe = Assets.x3ItemPiece;
			break;
		}

	}

	@Override
	public void act(float delta) {
		justChanged = false;
		super.act(delta);
	}

	public void moveToPosition(int pos) {
		this.position = pos;
		// Gdx.app.log("Move to ", pos + "");
		addAction(Actions.moveTo(mapPositions.get(position).x, mapPositions.get(position).y, .075f));
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(keyframe, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}

}
