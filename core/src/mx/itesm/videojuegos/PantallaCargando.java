package mx.itesm.videojuegos;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;




import com.badlogic.gdx.assets.AssetManager;

public class PantallaCargando extends Pantalla{

    private final float TIEMPO_ENTRE_FRAME = 0.05f;
    private Sprite spriteCargando; 
    private float timerAnimacion = TIEMPO_ENTRE_FRAME;
    private Juego juego; 
    private TipoPantalla siguientePantalla; 
    private int avance; //0 - 100% de la carga de recursos. 

    private AssetManager manager; //unico en el juego.
    private String seleccionPersonaje;
    private Integer IDnivel;

    private Texture texturaCargando;

    private Music music;


    public PantallaCargando(Juego juego, TipoPantalla siguiente){
        manager = juego.getManager();
        this.juego = juego; 
        this.siguientePantalla = siguiente; 
    }

    public PantallaCargando(Juego juego, TipoPantalla siguiente, Music musica){
        manager = juego.getManager();
        this.juego = juego;
        this.siguientePantalla = siguiente;
        this.music = musica;
    }

    public PantallaCargando(Juego juego, TipoPantalla siguiente, Music musica, String personajeSeleccionado , Integer IdNivel){
        manager = juego.getManager();
        this.juego = juego;
        this.siguientePantalla = siguiente;
        this.music = musica;
        this.seleccionPersonaje = personajeSeleccionado;
        this.IDnivel = IdNivel;

    }
    
    @Override
    public void show() {
        System.out.println("CARGANDO....");
        texturaCargando = new Texture("loading.png");
        spriteCargando = new Sprite(texturaCargando); 
        spriteCargando.setPosition(ANCHO/2-spriteCargando.getWidth(), ALTO/2 - spriteCargando.getHeight());
        
        cargarRecursosPantalla(); //cargar imagener, mp3, ...

        actializarCargaRecursos();
    }

    private void cargarRecursosPantalla() {
        switch (siguientePantalla){
            case PANTALLAINICIO:
                cargarRecursosPantallaInicio();
                break;
            case MENU:
                cargarRecursosMenu();
                break;
            case PANTALLAACERCADE:
                cargarRecursosPantallaAcercaDe();
                break;
            case PANTALLASELECCIONNIVEL:
                cargaRecursosPantallaSeleccionNivel();
            case NIVEL1:
                cargarTexturasNivel1();
                break;
                  
        }
    }

    private void cargarRecursosPantallaAcercaDe() {
        manager.load("menus/Nivel/prev.png",Texture.class);
        manager.load("menus/acercaDe/desarrollo.png",Texture.class);
        manager.load("menus/acercaDe/arte.png",Texture.class);
        manager.load("fondos/estatua.png", Texture.class);
        manager.load("menus/acercaDe/musicacover.png", Texture.class);
        manager.finishLoading();
    }

    private void cargaRecursosPantallaSeleccionNivel() {
        manager.load("menus/Nivel/prev.png", Texture.class);
        manager.load("menus/Nivel/prev_pr.png", Texture.class);
        manager.load("menus/Nivel/Select_title.png", Texture.class);
        manager.load("menus/Nivel/N1.png", Texture.class);
        manager.load("menus/Nivel/N2.png", Texture.class);
        manager.load("menus/Nivel/N3.png", Texture.class);
        manager.load("menus/Nivel/select.png", Texture.class);
        manager.load("menus/Nivel/flechaDer.png", Texture.class);
        manager.load("menus/Nivel/flechaIz.png", Texture.class);
        manager.load("menus/Nivel/kira.png", Texture.class);
        manager.load("menus/Nivel/raoh.png", Texture.class);
        manager.load("menus/fondoMenu.png", Texture.class);
        manager.finishLoading();
        
    }


    private void cargarTexturasNivel1() {
        //Texturas
        manager.load("fondos/cabezaArena.png", Texture.class);
        manager.load("sprites_personaje/caminaKiraDer.png", Texture.class);
        manager.load("sprites_personaje/golpeKiraDer.png", Texture.class);
        manager.load("sprites_personaje/kiraStuned.png", Texture.class);
        manager.load("sprites_personaje/caminaRaohDer.png", Texture.class);
        manager.load("sprites_personaje/golpeRaohDer.png", Texture.class);
        manager.load("sprites_personaje/rahStuned.png", Texture.class);
        manager.load("sprites_enemigo1/enemigo.png", Texture.class);
        manager.load("sprites_enemigo1/enemigoGolpear.png", Texture.class);
        manager.load("sprites_enemigo1/enemigoStoned.png", Texture.class);
        manager.load("sprites_enemigo1/bigPapa.png", Texture.class);
        manager.load("sprites_enemigo1/bigPapaStuned.png", Texture.class);
        manager.load("Nivel/palanca.png", Texture.class);
        manager.load("Nivel/heart_80x80.png", Texture.class);
        //Sonidos
        manager.load("Audio/enemigoDaño.mp3", Sound.class);
        manager.load("Audio/enemigoSonido1.mp3", Sound.class);
        manager.load("Audio/enemigoSonido2.mp3", Sound.class);
        manager.finishLoading();

    }

    private void cargarRecursosMenu() {
        manager.load("menus/menuPantalla/title.png",Texture.class);
        manager.load("menus/menuPantalla/btn_jugar.png",Texture.class);
        manager.load("menus/menuPantalla/btn_acerca_de.png",Texture.class);
        manager.load("menus/menuPantalla/btn_acerca_de_pr.png",Texture.class);
        manager.load("menus/menuPantalla/btn_opciones.png",Texture.class);
        manager.load("menus/menuPantalla/btn_opciones_pr.png",Texture.class);
        manager.load("menus/menuPantalla/btn_jugar_pr.png",Texture.class);
        manager.load("menus/menuPantalla/exit.png",Texture.class);
        manager.finishLoading();
    }

    private void cargarRecursosPantallaInicio() {
        manager.load("Splash/splashtitle.png", Texture.class);
        manager.finishLoading();
    }

    @Override
    public void render(float delta) {
        actializarCargaRecursos();
        borrarPantalla(0.5f, 0.4f,0.8f);
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        spriteCargando.draw(batch);
        batch.end();

        //ACTUALIZAR
        timerAnimacion -=delta;
        /*if(timerAnimacion<= 0){
            spriteCargando.rotate(-20);
            timerAnimacion = TIEMPO_ENTRE_FRAME;
        }
        */
        actializarCargaRecursos();


    }

    private void actializarCargaRecursos() {
        if(manager.update()){
            switch (siguientePantalla) {
                case PANTALLAINICIO:
                    juego.setScreen(new PantallaInicio(juego));
                    break;
                case MENU:
                    juego.setScreen(new PantallaMenuPrincipal(juego,music));
                    break;
                case  PANTALLAACERCADE:
                    juego.setScreen(new PantallaAcercaDe(juego));
                    break;
                case NIVEL1:
                    juego.setScreen(new Nivel1(juego , music,seleccionPersonaje,IDnivel ));
                    break;
                case NIVEL2:
                    juego.setScreen(new Nivel1(juego , music,seleccionPersonaje,IDnivel ));
                    break;
                case NIVEL3:
                    juego.setScreen(new Nivel1(juego , music,seleccionPersonaje,IDnivel ));
                    break;
                case PANTALLASELECCIONNIVEL:
                    juego.setScreen(new PantallaSeleccionNivel(juego, music));
                    break;
            }
        }
    }


    private void cargarTexturas(){
        /////fondos
        manager.load("fondos/cabezaArena.png", Texture.class);
        manager.load("fondos/estatua.png", Texture.class);
        manager.load("fondos/rocas.png", Texture.class);
        manager.load("fondos/T/fondoT.png", Texture.class);

        //personaje, kira
        manager.load("sprites_personaje/caminaKiraDer.png", Texture.class);
        manager.load("sprites_personaje/golpeKiraDer.png", Texture.class);
        manager.load("sprites_personaje/kiraStuned.png", Texture.class);

        //personake. rah
        manager.load("sprites_personaje/caminaRaohDer.png", Texture.class);
        manager.load("sprites_personaje/golpeRaohDer.png", Texture.class);
        manager.load("sprites_personaje/rahStuned.png", Texture.class);

        ///personaje enemigos
        manager.load("sprites_enemigo1/enemigo.png", Texture.class);
        manager.load("sprites_enemigo1/enemigoGolpear.png", Texture.class);
        manager.load("sprites_enemigo1/enemigoStoned.png", Texture.class);
        manager.load("sprites_enemigo1/bigPapa.png", Texture.class);
        manager.load("sprites_enemigo1/bigPapaStuned.png", Texture.class);


        ///PALANCA
        manager.load("Nivel/palanca.png", Texture.class);
        manager.load("Nivel/heart_80x80.png", Texture.class);

        manager.load("Audio/enemigoSonido1.mp3",Sound.class );
        manager.load("Audio/enemigoSonido2.mp3",Sound.class );

    }

    private void cargarSFX(){

    }
    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
