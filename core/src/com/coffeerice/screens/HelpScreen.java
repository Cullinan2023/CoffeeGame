package com.coffeerice.screens;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.coffeerice.MainGame;
import com.coffeerice.Assets;

public class HelpScreen extends Screens {

    Image imgTextHelp1;
    Image imgTextHelp2;
    
    Image imgPuzzle;

    Button btBack;

    public HelpScreen(final MainGame game) {
        super(game);
        imgTextHelp1 = new Image(Assets.helpText1);
        imgTextHelp1.setPosition(SCREEN_WIDTH / 2f - imgTextHelp1.getWidth() / 2f, 660);

        imgTextHelp2 = new Image(Assets.helpText2);
        imgTextHelp2.setPosition(SCREEN_WIDTH / 2 - imgTextHelp2.getWidth() / 2f, 200);

        imgPuzzle = new Image(Assets.puzzleSolved);
        imgPuzzle.setSize(350, 350);
        imgPuzzle.setPosition(SCREEN_WIDTH / 2 - imgPuzzle.getWidth() / 2f, 290);

        btBack = new Button(Assets.btAtras);
        btBack.setSize(60, 60);
        btBack.setPosition(SCREEN_WIDTH / 2 - 30, 80);
        addEfectoPress(btBack);
        btBack.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                changeScreenWithFadeOut(MainMenuScreen.class, game);
            }
        });
        stage.addActor(imgTextHelp1);
        stage.addActor(imgTextHelp2);
        stage.addActor(btBack);
        stage.addActor(imgPuzzle);
    }

    @Override
    public void draw(float delta) {
        batcher.begin();
        batcher.draw(Assets.fondo, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        batcher.end();
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.ESCAPE || keycode == Keys.BACK) {

            changeScreenWithFadeOut(MainMenuScreen.class, game);
        }
        return super.keyDown(keycode);
    }

}
