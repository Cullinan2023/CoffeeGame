package com.coffeerice.game;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.coffeerice.MainGame;
import com.coffeerice.screens.MainMenuScreen;
import com.coffeerice.Assets;
import com.coffeerice.Settings;
import com.coffeerice.scene2d.GameOver;
import com.coffeerice.scene2d.Paused;
import com.coffeerice.screens.Screens;

public class GameScreen extends Screens {
    static final int STATE_READY = 0;
    static final int STATE_RUNNING = 1;
    static final int STATE_PAUSED = 2;
    static final int STATE_GAME_OVER = 3;
    public int state;

    Board board;

    private Stage stageGame;

    Table tableMarker;
    Label labelTime, labelScore, labelBestScore;

    Button buttonPause;
    Paused paused;

    public GameScreen(MainGame game) {
        super(game);
        stageGame = new Stage(new StretchViewport(SCREEN_WIDTH, SCREEN_HEIGHT));
        board = new Board();
        stageGame.addActor(board);

        initUI();

        Settings.playCount++;

    }

    private void initUI() {
        tableMarker = new Table();
        tableMarker.setSize(SCREEN_WIDTH, 100);
        tableMarker.setPosition(0, 680);

        labelTime = new Label(Assets.language.get("score") + "\n0", Assets.labelStyleChico);
        labelTime.setAlignment(Align.center);
        labelTime.setFontScale(1.15f);

        labelScore = new Label(Assets.language.get("score") + "\n0", Assets.labelStyleChico);
        labelScore.setFontScale(1.15f);
        labelScore.setAlignment(Align.center);

        labelBestScore = new Label(Assets.language.get("best") + "\n" + Settings.bestScore, Assets.labelStyleChico);
        labelBestScore.setAlignment(Align.center);
        labelBestScore.setFontScale(1.15f);

        tableMarker.row().expand().uniform().center();
        tableMarker.add(labelTime);
        tableMarker.add(labelScore);
        tableMarker.add(labelBestScore);

        paused = new Paused(this);

        buttonPause = new Button(Assets.styleButtonPause);
        buttonPause.setPosition(SCREEN_WIDTH / 2 - buttonPause.getWidth() / 2, 110);
        buttonPause.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setPaused();
            }

        });

        stage.addActor(tableMarker);
        stage.addActor(buttonPause);

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stageGame.getViewport().update(width, height, true);
    }

    @Override
    public void update(float delta) {

        switch (state) {
            case STATE_RUNNING:
                updateRunning(delta);
                break;
        }

        labelTime.setText(Assets.language.get("time") + "\n" + ((int) board.time));
        labelScore.setText(Assets.language.get("score") + "\n" + (board.score));

    }

    private void updateRunning(float delta) {
        stageGame.act(delta);

        if (board.state == Board.STATE_GAMEOVER) {
            setGameover();
        }
    }

    @Override
    public void draw(float delta) {

        batcher.begin();
        batcher.draw(Assets.background, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        batcher.end();
        stageGame.draw();

    }

    @Override
    public void pause() {
        setPaused();
        super.pause();
    }

    @Override
    public void hide() {
        super.hide();
        stageGame.dispose();
    }

    private void setPaused() {
        if (state == STATE_GAME_OVER || state == STATE_PAUSED)
            return;
        state = STATE_PAUSED;
        stage.addActor(paused);

    }

    public void setRunning() {
        if (state == STATE_GAME_OVER)
            return;
        state = STATE_RUNNING;
    }

    private void setGameover() {
        state = STATE_GAME_OVER;
        Settings.setBestScores(board.score);
        GameOver oGameOver = new GameOver(this, board.didWin, (int) board.time, board.score);
        stage.addActor(oGameOver);

    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        if (state != STATE_PAUSED)
            setRunning();
        return super.fling(velocityX, velocityY, button);
    }

    @Override
    public void up() {
        board.moveUp = true;
        super.up();
    }

    @Override
    public void down() {
        board.moveDown = true;
        super.down();
    }

    @Override
    public void right() {
        board.moveRight = true;
        super.right();
    }

    @Override
    public void left() {
        board.moveLeft = true;
        super.left();
    }
    

    @Override
    public boolean keyDown(int keycode) {
        if (state != STATE_PAUSED) {
            if (keycode == Keys.LEFT) {
                board.moveLeft = true;
                setRunning();
            } else if (keycode == Keys.RIGHT) {
                board.moveRight = true;
                setRunning();
            } else if (keycode == Keys.UP) {
                board.moveUp = true;
                setRunning();
            } else if (keycode == Keys.DOWN) {
                board.moveDown = true;
                setRunning();
            }
        } else if (keycode == Keys.ESCAPE || keycode == Keys.BACK) {

            changeScreenWithFadeOut(MainMenuScreen.class, game);
        }

        return true;
    }

}
