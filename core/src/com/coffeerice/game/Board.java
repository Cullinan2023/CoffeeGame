package com.coffeerice.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Array.ArrayIterator;
import com.coffeerice.Assets;
import com.coffeerice.objetos.Piece;
import com.coffeerice.screens.Screens;

import java.util.Iterator;

public class Board extends Group {
	static public final int STATE_RUNNING = 1;
	static public final int STATE_NO_MORE_MOVES = 2;
	static public final int STATE_GAMEOVER = 3;
	public int state;
	Array<Piece> arrPieces;

	public float time;
	public long score;

	public boolean moveUp, moveDown, moveLeft, moveRight;
	public boolean didWin;

	public Board() {
		setSize(480, 480);
		setPosition(Screens.SCREEN_WIDTH / 2f - getWidth() / 2f, 200);
		addBackground();

		arrPieces = new Array<Piece>(16);

		didWin = false;
		
		for (int i = 0; i < 16; i++) {
			addActor(new Piece(i, 0));
		}

		addPiece();
		addPiece();
		state = STATE_RUNNING;
	}

	private void addBackground() {
		Image background = new Image(Assets.backgroundBoard);
		background.setSize(getWidth(), getHeight());
		background.setPosition(0, 0);
		addActor(background);

	}

	public void addPiece() {
		if (isBoardFull())
			return;
		boolean empty = false;
		int num = 0;
		while (!empty) {
			num = MathUtils.random(15);
			empty = checkEmptySpace(num);
		}
        int randomValue = MathUtils.random(99);

        // 확률에 따른 값 할당
        int valor;
        if (randomValue < 45) {
            valor = 3; // 45% 확률
        } else if (randomValue < 90) {
            valor = 9; // 45% 확률
        } else if (randomValue < 95) {
            valor = -1; // 5% 확률
        } else {
            valor = -2; // 5% 확률
        }

		Piece obj = new Piece(num, valor);
		arrPieces.add(obj);
		addActor(obj);

	}

	public void addPiece(int pos) {
		if (isBoardFull())
			return;

		Piece obj = new Piece(pos, 4);
		arrPieces.add(obj);
		addActor(obj);

	}

	@Override
	public void act(float delta) {
		super.act(delta);

		// Si ya no hay acciones pendientes ahora si me pongo en gameover
		if (state == STATE_NO_MORE_MOVES) {
			int numActions = 0;
			Iterator<Piece> i = arrPieces.iterator();
			while (i.hasNext()) {
				numActions += i.next().getActions().size;
			}
			numActions += getActions().size;
			if (numActions == 0)
				state = STATE_GAMEOVER;
			return;
		}

		boolean didMovePiece = false;
		boolean didMergePiece = false;

		if (moveUp) {
			for (int con = 0; con < 4; con++) {
				Iterator<Piece> i = arrPieces.iterator();
				while (i.hasNext()) {
					Piece obj = i.next();
					int nextPos = obj.position - 4;
					if (checkMergeUp(obj.position, nextPos)) {
						Piece objNext = getPiece(nextPos);
						if (!objNext.justChanged && !obj.justChanged) {
							if (obj.getValue() == -1 || objNext.getValue() == -1) {
								i.remove();
								arrPieces.removeValue(objNext, true);
								removePiece(objNext);
								removePiece(obj);
							} else if (obj.getValue() == -2) {
								i.remove();
								removePiece(obj);
								objNext.setValue(objNext.getValue() * 3);
								score += objNext.getValue();
								objNext.justChanged = true;
							} else if (objNext.getValue() == -2) {
								i.remove();
								removePiece(objNext);
								obj.setValue(obj.getValue() * 3);
								score += obj.getValue();
								obj.justChanged = true;
							} else {
								i.remove();
								removePiece(obj);
								objNext.setValue(objNext.getValue() * 3);
								score += objNext.getValue();
								objNext.justChanged = true;
							}
							didMovePiece = true;
							continue;
						}
					}
					if (checkEmptySpaceUp(nextPos)) {
						obj.moveToPosition(nextPos);
						didMovePiece = true;
					}
				}
			}
		}

		else if (moveDown) {
			for (int con = 0; con < 4; con++) {
				Iterator<Piece> i = arrPieces.iterator();
				while (i.hasNext()) {
					Piece obj = i.next();
					int nextPos = obj.position + 4;
					if (checkMergeUp(obj.position, nextPos)) {
						Piece objNext = getPiece(nextPos);
						if (!objNext.justChanged && !obj.justChanged) {
							if (obj.getValue() == -1 || objNext.getValue() == -1) {
								i.remove();
								arrPieces.removeValue(objNext, true);
								removePiece(objNext);
								removePiece(obj);
							} else if (obj.getValue() == -2) {
								i.remove();
								removePiece(obj);
								objNext.setValue(objNext.getValue() * 3);
								score += objNext.getValue();
								objNext.justChanged = true;
							} else if (objNext.getValue() == -2) {
								i.remove();
								removePiece(objNext);
								obj.setValue(obj.getValue() * 3);
								score += obj.getValue();
								obj.justChanged = true;
							} else {
								i.remove();
								removePiece(obj);
								objNext.setValue(objNext.getValue() * 3);
								score += objNext.getValue();
								objNext.justChanged = true;
							}
							didMergePiece = true;
							didMovePiece = true;
							continue;
						}
					}
					if (checkEmptySpaceDown(nextPos)) {
						obj.moveToPosition(nextPos);
						didMovePiece = true;
					}
				}
			}
		}

		else if (moveLeft) {
			for (int con = 0; con < 4; con++) {
				Iterator<Piece> i = arrPieces.iterator();
				while (i.hasNext()) {
					Piece obj = i.next();
					int nextPos = obj.position - 1;
					if (checkMergeSides(obj.position, nextPos)) {
						Piece objNext = getPiece(nextPos);
						if (!objNext.justChanged && !obj.justChanged) {
							if (obj.getValue() == -1 || objNext.getValue() == -1) {
								i.remove();
								arrPieces.removeValue(objNext, true);
								removePiece(objNext);
								removePiece(obj);
							} else if (obj.getValue() == -2) {
								i.remove();
								removePiece(obj);
								objNext.setValue(objNext.getValue() * 3);
								score += objNext.getValue();
								objNext.justChanged = true;
							} else if (objNext.getValue() == -2) {
								i.remove();
								removePiece(objNext);
								obj.setValue(obj.getValue() * 3);
								score += obj.getValue();
								obj.justChanged = true;
							} else {
								i.remove();
								removePiece(obj);
								objNext.setValue(objNext.getValue() * 3);
								score += objNext.getValue();
								objNext.justChanged = true;
							}
							didMergePiece = true;
							didMovePiece = true;
							continue;
						}
					}
					if (checkEmptySpaceLeft(nextPos)) {
						obj.moveToPosition(nextPos);
						didMovePiece = true;
					}
				}
			}
		}


				else if (moveRight) {
			for (int con = 0; con < 4; con++) {
				Iterator<Piece> i = arrPieces.iterator();
				while (i.hasNext()) {
					Piece obj = i.next();
					int nextPos = obj.position + 1;
					if (checkMergeSides(obj.position, nextPos)) {
						Piece objNext = getPiece(nextPos);
						if (!objNext.justChanged && !obj.justChanged) {
							if (obj.getValue() == -1 || objNext.getValue() == -1) {
								i.remove();
								arrPieces.removeValue(objNext, true);
								removePiece(objNext);
								removePiece(obj);
							} else if (obj.getValue() == -2) {
								i.remove();
								removePiece(obj);
								objNext.setValue(objNext.getValue() * 3);
								score += objNext.getValue();
								objNext.justChanged = true;
							} else if (objNext.getValue() == -2) {
								i.remove();
								removePiece(objNext);
								obj.setValue(obj.getValue() * 3);
								score += obj.getValue();
								obj.justChanged = true;
							} else {
								i.remove();
								removePiece(obj);
								objNext.setValue(objNext.getValue() * 3);
								score += objNext.getValue();
								objNext.justChanged = true;
							}
							didMovePiece = true;
							didMergePiece = true;
							continue;
						}
					}
					if (checkEmptySpaceRight(nextPos)) {
						obj.moveToPosition(nextPos);
						didMovePiece = true;
					}
				}
			}
		}


		if (didWin()) {
			state = STATE_NO_MORE_MOVES;
			didWin = true;
		}

		if (didMovePiece && !didMergePiece) {
			addPiece();
			Assets.playSoundMove();
		}
		if(didMovePiece && didMergePiece) {
			Assets.playSoundMerge();
		}

		if (isBoardFull() && !isPossibleToMove()) {
			state = STATE_NO_MORE_MOVES;
		}

		moveDown = moveLeft = moveRight = moveUp = false;

		time += Gdx.graphics.getRawDeltaTime();

	}

	private boolean checkMergeSides(int posActual, int nextPosition) {
		if ((posActual == 3 || posActual == 7 || posActual == 11) && nextPosition > posActual)
			return false;
		if ((posActual == 12 || posActual == 8 || posActual == 4) && nextPosition < posActual)
			return false;
		Piece obj1 = getPiece(posActual);
		Piece obj2 = getPiece(nextPosition);

		if (obj1 == null || obj2 == null)
			return false;
		else if ((obj1.getValue() != obj2.getValue() && obj1.getValue() != -1 && obj2.getValue() != -1 && obj1.getValue() != -2 && obj2.getValue() != -2))
			return false;
		else
			return true;

	}

	private boolean checkMergeUp(int posActual, int nextPosition) {

		Piece obj1 = getPiece(posActual);
		Piece obj2 = getPiece(nextPosition);

		if (obj1 == null || obj2 == null)
			return false;
		else if ((obj1.getValue() != obj2.getValue() && obj1.getValue() != -1 && obj2.getValue() != -1 && obj1.getValue() != -2 && obj2.getValue() != -2))
			return false;
		else
			return true;

	}

	private boolean checkEmptySpace(int pos) {
		ArrayIterator<Piece> ite = new ArrayIterator<Piece>(arrPieces);
		while (ite.hasNext()) {
			if (ite.next().position == pos)
				return false;
		}
		return true;
	}

	private boolean checkEmptySpaceUp(int pos) {
		if (pos < 0)
			return false;
		return checkEmptySpace(pos);
	}

	private boolean checkEmptySpaceDown(int pos) {
		if (pos > 15)
			return false;
		return checkEmptySpace(pos);
	}

	private boolean checkEmptySpaceRight(int pos) {
		if (pos == 4 || pos == 8 || pos == 12 || pos == 16)
			return false;
		return checkEmptySpace(pos);
	}

	private boolean checkEmptySpaceLeft(int pos) {
		if (pos == 11 || pos == 7 || pos == 3 || pos == -1)
			return false;
		return checkEmptySpace(pos);
	}

	private Piece getPiece(int pos) {
		ArrayIterator<Piece> ite = new ArrayIterator<Piece>(arrPieces);
		while (ite.hasNext()) {
			Piece obj = ite.next();
			if (obj.position == pos)
				return obj;
		}
		return null;
	}

	private boolean isBoardFull() {
		if (arrPieces.size == (16))
			return true;
		else
			return false;
	}

	private boolean didWin() {
		ArrayIterator<Piece> ite = new ArrayIterator<Piece>(arrPieces);
		while (ite.hasNext()) {
			Piece obj = ite.next();
			if (obj.getValue() >= 177140)// si hay una pieza que valga mas de 15 mil se gana
				return true;
		}
		return false;
	}

	private boolean isPossibleToMove() {

		boolean canMove = false;
		if (isPossibleToMoveRightLeft()) {
			canMove = true;

		}

		if (isPossibleToMoveUpDown()) {
			canMove = true;
		}
		return canMove;

	}

	boolean isPossibleToMoveRightLeft() {
		for (int ren = 0; ren < 16; ren += 4) {
			for (int col = ren; col < ren + 3; col++) {
				if (checkMergeSides(col, col + 1))
					return true;
			}
		}
		return false;
	}

	boolean isPossibleToMoveUpDown() {
		for (int col = 0; col < 4; col++) {
			for (int ren = col; ren < col + 16; ren += 4) {
				if (checkMergeUp(ren, ren + 4))
					return true;
			}
		}
		return false;
	}

	private void removePiece(Piece obj) {
		removeActor(obj);
		arrPieces.removeValue(obj, true);
	}
}
