package com.nopalsoft.dosmil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.I18NBundle;

public class Assets {

    public static I18NBundle idiomas;

    public static BitmapFont fontChico;
    public static BitmapFont fontGrande;

    public static AtlasRegion fondo;
    public static AtlasRegion fondoTablero;
    public static AtlasRegion puzzleSolved;

    public static AtlasRegion title;

    public static NinePatchDrawable pixelNegro;
    public static AtlasRegion fondoPuntuaciones;

    public static TextureRegionDrawable btAtras;

    public static AtlasRegion pieceEmpty;
    public static AtlasRegion piece3;
    public static AtlasRegion piece9;
    public static AtlasRegion piece27;
    public static AtlasRegion piece81;
    public static AtlasRegion piece243;
    public static AtlasRegion piece729;
    public static AtlasRegion piece2187;
    public static AtlasRegion piece6561;
    public static AtlasRegion piece19683;
    public static AtlasRegion piece59049;
    public static AtlasRegion piece177147;

    public static LabelStyle labelStyleChico;
    public static LabelStyle labelStyleGrande;
    public static ButtonStyle styleButtonMusic;
    public static ButtonStyle styleButtonPause;
    public static ButtonStyle styleButtonSFX;

    static TextureAtlas atlas;

    private static Music music2;

    static Sound move1;
    static Sound move2;

    public static void loadFont() {
        fontChico = new BitmapFont(Gdx.files.internal("data/font25.fnt"),
                atlas.findRegion("font25"));

        fontGrande = new BitmapFont(Gdx.files.internal("data/font45.fnt"),
                atlas.findRegion("font45"));
    }

    private static void cargarEstilos() {
        labelStyleChico = new LabelStyle(fontChico, Color.WHITE);
        labelStyleGrande = new LabelStyle(fontGrande, Color.WHITE);

		/* Button Musica */
        TextureRegionDrawable btMusicOn = new TextureRegionDrawable(
                atlas.findRegion("btMusic"));
        TextureRegionDrawable btMusicOff = new TextureRegionDrawable(
                atlas.findRegion("btSinMusic"));
        styleButtonMusic = new ButtonStyle(btMusicOn, null, btMusicOff);

		/* Boton Sonido */
        TextureRegionDrawable botonSonidoOn = new TextureRegionDrawable(
                atlas.findRegion("btSFX"));
        TextureRegionDrawable botonSonidoOff = new TextureRegionDrawable(
                atlas.findRegion("btSinSFX"));
        styleButtonSFX = new ButtonStyle(botonSonidoOn, null, botonSonidoOff);

		/* ImageButton Pause */
        TextureRegionDrawable btPauseUp = new TextureRegionDrawable(
                atlas.findRegion("btPause"));
        TextureRegionDrawable btPauseDown = new TextureRegionDrawable(
                atlas.findRegion("btPauseDown"));
        styleButtonPause = new ButtonStyle(btPauseUp, btPauseDown, null);

    }

    public static void load() {
        atlas = new TextureAtlas(Gdx.files.internal("data/atlasMap.txt"));

        loadFont();
        cargarEstilos();

        if (MathUtils.randomBoolean())
            fondo = atlas.findRegion("fondo");
        else
            fondo = atlas.findRegion("fondo2");
        fondoTablero = atlas.findRegion("fondoPuntuaciones");

        title = atlas.findRegion("titulo");

        pixelNegro = new NinePatchDrawable(new NinePatch(
                atlas.findRegion("pixelNegro"), 1, 1, 0, 0));
        fondoPuntuaciones = atlas.findRegion("fondoPuntuaciones");

        puzzleSolved = atlas.findRegion("puzzleSolved");

        pieceEmpty = atlas.findRegion("pieceEmpty");

        piece3 = atlas.findRegion("piece3");
        piece9 = atlas.findRegion("piece9");
        piece27 = atlas.findRegion("piece27");
        piece81 = atlas.findRegion("piece81");
        piece243 = atlas.findRegion("piece243");
        piece729 = atlas.findRegion("piece729");
        piece2187 = atlas.findRegion("piece2187");
        piece6561 = atlas.findRegion("piece6561");
        piece19683 = atlas.findRegion("piece19683");
        piece59049 = atlas.findRegion("piece59049");
        piece177147 = atlas.findRegion("piece177147");

        btAtras = new TextureRegionDrawable(atlas.findRegion("btAtras2"));

        move1 = Gdx.audio.newSound(Gdx.files.internal("data/Sounds/move1.mp3"));
        move2 = Gdx.audio.newSound(Gdx.files.internal("data/Sounds/move2.mp3"));

        music2 = Gdx.audio.newMusic(Gdx.files
                .internal("data/Sounds/music2.mp3"));

        Settings.load();

        music2.setVolume(.1f);

        playMusic();

        idiomas = I18NBundle.createBundle(Gdx.files.internal("strings/strings"));
    }

    public static void playMusic() {
        if (Settings.isMusicOn)

            music2.play();
    }

    public static void pauseMusic() {
        music2.stop();
    }

    public static void playSoundMove() {
        if (Settings.isSoundOn) {
            if (MathUtils.randomBoolean())
                move1.play(.3f);
            else
                move2.play(.3f);
        }
    }
}
