package com.nopalsoft.dosmil.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.nopalsoft.dosmil.Assets;
import com.nopalsoft.dosmil.MainGame;
import com.nopalsoft.dosmil.Settings;
import com.nopalsoft.dosmil.game.GameScreen;

public class MainMenuScreen extends Screens {

    Image imgTitle;

    Label lbPlay;
    Label lbHelp;
    Label lbMore;

    Button btMusic;
    Button btSFX;
    Button btFacebook;

    public MainMenuScreen(final MainGame game) {
        super(game);
        imgTitle = new Image(Assets.title);
        imgTitle.setPosition(SCREEN_WIDTH / 2f - imgTitle.getWidth() / 2f, 580);

        lbPlay = new Label(Assets.idiomas.get("play"), Assets.labelStyleGrande);
        lbPlay.setPosition(SCREEN_WIDTH / 2f - lbPlay.getWidth() / 2f, 350);
        addEfectoPress(lbPlay);
        lbPlay.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                changeScreenWithFadeOut(GameScreen.class, game);
            }

            ;
        });

        // Help
        lbHelp = new Label(Assets.idiomas.get("help"), Assets.labelStyleGrande);
        lbHelp.setPosition(SCREEN_WIDTH / 2f - lbHelp.getWidth() / 2f, 250);
        addEfectoPress(lbHelp);
        lbHelp.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                changeScreenWithFadeOut(HelpScreen.class, game);
            }

            ;
        });
        
        btMusic = new Button(Assets.styleButtonMusic);
        btMusic.setPosition(5, 5);
        btMusic.setChecked(!Settings.isMusicOn);
        btMusic.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Settings.isMusicOn = !Settings.isMusicOn;
                btMusic.setChecked(!Settings.isMusicOn);
                if (Settings.isMusicOn)
                    Assets.playMusic();
                else
                    Assets.pauseMusic();

            }
        });

        btSFX = new Button(Assets.styleButtonSFX);
        btSFX.setPosition(75, 5);
        btSFX.setChecked(!Settings.isSoundOn);
        btSFX.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Settings.isSoundOn = !Settings.isSoundOn;
                btSFX.setChecked(!Settings.isSoundOn);
            }
        });
        

        stage.addActor(imgTitle);
        stage.addActor(lbPlay);
        stage.addActor(lbHelp);
        stage.addActor(btMusic);
        stage.addActor(btSFX);

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(float delta) {
        batcher.begin();
        batcher.draw(Assets.fondo, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        batcher.end();

    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.ESCAPE || keycode == Keys.BACK)
            Gdx.app.exit();
        return true;

    }
}
