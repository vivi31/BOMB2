package mx.itesm.videojuegos;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import static com.badlogic.gdx.Input.Keys.X;

public class Enemigo {
    //Stats
    private int salud = 20;
    private int daño;   //recibe
    public float velocidad;
    private float rangoDeAtaque = 20;
    private float probabilidadAtaqueCritico;
    private float probabilidadAtaque;
    public int fuerza; //de daño/ ataque

    //FISICAS
    private Body body;
    private Box2DDebugRenderer debugRenderer;


    //SPRITES
    private Sprite sprite;
    private TextureRegion texturaCompleta;
    private TextureRegion[][] texturas;
    private Animation spriteAnimado;         // Animación caminando
    private float timerAnimacion = 0;
    private Animation animacionDerecha;

    private Animation GOLPE;
    private  TextureRegion texturaCompletaGOLPE;
    private TextureRegion[][] texturasGOLPES;
// Tiempo para cambiar frames de la animación


    EstadosEnemigo estadosEnemigo = EstadosEnemigo.NEUTRAL;
    Personaje.mirandoA mirandoA;


    public Enemigo(Texture textura, float x, int fuerzaPersonaje){
        daño = fuerzaPersonaje;
        cargarTexturas(textura,x);

    }

    private void cargarTexturas(Texture textura,float x) {
        texturaCompleta = new TextureRegion(textura);
        TextureRegion[][] texturaEnemigo = texturaCompleta.split(23,32);  // ejemplo para la vivi del futuro = texturaCompleta.split(32,64);

        spriteAnimado = new Animation(0.15f, texturaEnemigo[0][1], texturaEnemigo[0][2], texturaEnemigo[0][3],texturaEnemigo[0][4]
                ,texturaEnemigo[0][5],texturaEnemigo[0][6],texturaEnemigo[0][7],texturaEnemigo[0][8],texturaEnemigo[0][9],texturaEnemigo[0][10]
                ,texturaEnemigo[0][11],texturaEnemigo[0][12],texturaEnemigo[0][13]);

        // Animación infinita
        spriteAnimado.setPlayMode(Animation.PlayMode.LOOP);
        // Inicia el timer que contará tiempo para saber qué frame se dibuja
        timerAnimacion = 0;
        // Crea el sprite con el personaje quieto (idle)
        sprite = new Sprite(texturaEnemigo[0][0]);    // QUIETO
        sprite.setPosition(X,0);    // Posición inicial
        this.estadosEnemigo = Enemigo.EstadosEnemigo.NEUTRAL;
    }
    public void render(SpriteBatch batch){
        //Dibujar eal enemigo
        timerAnimacion += Gdx.graphics.getDeltaTime();
        switch (estadosEnemigo) {
            case MOV_DERECHA:
            case MOV_IZQUIERDA:
                //System.out.println("Dibujando, moviendo" );

                timerAnimacion += Gdx.graphics.getDeltaTime();

                TextureRegion region = (TextureRegion) spriteAnimado.getKeyFrame(timerAnimacion);

                if (estadosEnemigo == Enemigo.EstadosEnemigo.MOV_IZQUIERDA) {
                    if (!region.isFlipX()) {
                        region.flip(true,false);
                    }
                } else {
                    if (region.isFlipX()) {
                        region.flip(true,false);
                    }
                }
                batch.draw(region,sprite.getX(),sprite.getY());
                break;
            case ATACANDO:

                timerAnimacion += Gdx.graphics.getDeltaTime();
                region= (TextureRegion) GOLPE.getKeyFrame(timerAnimacion);

                if (mirandoA == mirandoA.IZQUIERDA) {
                    if (!region.isFlipX()) {
                        region.flip(true,false);
                    }
                } else {
                    if (region.isFlipX()) {
                        region.flip(true,false);
                    }
                }
                batch.draw(region,sprite.getX(),sprite.getY());
                break;

            case NEUTRAL:
                //System.out.println("DIBUJANDO, NEUTERAL");
                region = (TextureRegion) animacionDerecha.getKeyFrame(timerAnimacion) ;
                batch.draw(region, sprite.getX(), sprite.getY());
                sprite.draw(batch);
                break;

        }
    }

    public float atacarJugador(int daño){

        return rangoDeAtaque; //Se llama en nivel y con este valor se calcula en personaje si esta denro del area de ataque.
    }

    public void identificalAreaDeDaño (float rangoDeAtaque){
        //if (enemigo.getX )

    }

    private void seguirJugador(){

    }
    private void stun (){

    }

    public void recivirDaño (int daño){
        if(salud >0){
            salud -= daño;
        }else{
            estadosEnemigo = EstadosEnemigo.ATACANDO.MUERTO;
        }
    }


    public void perseguir(float posicionDeJugador){     //Se llama en nivel con el personaje.getX
        float xP = posicionDeJugador;
        if ((sprite.getX() > xP)) {
            sprite.setX(sprite.getX() - 6);
        } if(sprite.getX() < xP) {
            sprite.setX(sprite.getX() + 6);
        }
    }
    protected enum EstadosEnemigo{
        NEUTRAL,
        ATACANDO,
        STUNNED,
        MUERTO,
        MOV_IZQUIERDA,
        MOV_DERECHA
    }
    protected  enum mirandoA{
        DERECHA,
        IZQUIERDA
    }
}


