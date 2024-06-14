package com.coffeerice.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.coffeerice.Assets;
import com.coffeerice.MainGame;
import com.coffeerice.Settings;
import com.coffeerice.game.GameScreen;

public class MainMenuScreen extends Screens {

    Image imgTitle;
    Image imgPlay;
    Image imgHelp;
    Image imgKTeam;

    Button btMusic;
    Button btSFX;

    public MainMenuScreen(final MainGame game) {
        super(game);
        imgTitle = new Image(Assets.title);
        imgTitle.setPosition(SCREEN_WIDTH / 2f - imgTitle.getWidth() / 2f, 500);

        // Play
        imgPlay = new Image(Assets.play);
        imgPlay.setPosition(SCREEN_WIDTH / 2f - imgPlay.getWidth() / 2f, 350);
        addEffectPress(imgPlay);
        imgPlay.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                changeScreenWithFadeOut(GameScreen.class, game);
            }
        });

        // Help
        imgHelp = new Image(Assets.help);
        imgHelp.setPosition(SCREEN_WIDTH / 2f - imgHelp.getWidth() / 2f, 250);
        addEffectPress(imgHelp);
        imgHelp.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                changeScreenWithFadeOut(HelpScreen.class, game);
            }

            ;
        });
        
        // KTeam
        imgKTeam = new Image(Assets.kTeam);
        imgKTeam.setPosition((SCREEN_WIDTH / 2f - imgKTeam.getWidth() / 2f) + 50, 20);
        imgKTeam.setScale(1.8f);
        imgKTeam.setAlign(Align.right);
        
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
        stage.addActor(imgKTeam);
        stage.addActor(imgPlay);
        stage.addActor(imgHelp);
        stage.addActor(btMusic);
        stage.addActor(btSFX);

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(float delta) {
        batcher.begin();
        batcher.draw(Assets.background, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        batcher.end();

    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.ESCAPE || keycode == Keys.BACK)
            Gdx.app.exit();
        return true;

    }
}
