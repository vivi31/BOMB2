package mx.itesm.videojuegos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

class PantallaInicio implements Screen {

    private final Juego juego;
    private OrthographicCamera camara;
    private Viewport vista;
    private SpriteBatch batch;

    //fondo
    private Texture texturaFondo;
    private Texture title;
    private Texture textoTocar;

    //escena
    private Stage escenaMenu;

    public PantallaInicio(Juego juego) {
        this.juego = juego;   //this de pantalla inicio consrtuctor
    }

    @Override
    public void show() { //mostrar en pantalla fisica. ini items, texturas.
        configurarVista();
        crearMenu();
        //cargar fondo
        texturaFondo = new Texture( "Fondos/FondoMenu.jpeg");
    }

    private void crearMenu() {
        escenaMenu = new Stage(vista);
        title = new Texture("title.png");
        textoTocar = new Texture("TextoTocaContinuar.png");

        Gdx.input.setInputProcessor(new ProcesadorEntrada());
    }

    private void configurarVista() {
        camara = new OrthographicCamera();
        camara.position.set(Juego.ANCHO/2,Juego.ALTO/2,0);
        camara.update();

        vista = new StretchViewport(Juego.ANCHO, Juego.ALTO, camara);

        batch = new SpriteBatch(); //administra los trazos.
    }


    @Override
    public void render(float delta) {
        borrarPantalla();
        //batch escalaTodo de acuerdo a la visat y la camara
        batch.setProjectionMatrix(camara.combined);

        batch.begin();
        batch.draw(texturaFondo, 0, 0);
        batch.draw(title, Juego.ANCHO/2-(title.getWidth()/2) , 2*Juego.ALTO/3);
        batch.draw(textoTocar, Juego.ANCHO/2-(title.getWidth()/2+(textoTocar.getWidth()/2) -200) , 2*Juego.ALTO/3 - 300);
        batch.end();
        escenaMenu.draw();
    }

    private void borrarPantalla() {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    }

    @Override
    public void resize(int width, int height) {
        vista.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        texturaFondo.dispose(); //liberar
    }

        //  Escucha y atiende eventos de touch
        class ProcesadorEntrada implements InputProcessor {
            @Override
            public boolean keyDown(int keycode) {
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                Vector3 v = new Vector3(screenX,screenY,0);
                camara.unproject(v);
                juego.setScreen(new PantallaMenu(juego));
                return true;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(int amount) {
                return false;
            }
        }
    }
