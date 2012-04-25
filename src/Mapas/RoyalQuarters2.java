package Mapas;



import Boss.Queen;
import Modelo.Direcao;
import Modelo.Heal;
import Modelo.Healer;
import Modelo.Keys;
import Modelo.Hunter;
import Modelo.Magias;
import Modelo.Warrior;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javaPlay.Keyboard;
import javaPlayExtras.CenarioComColisao;
import javaPlayExtras.CenarioComColisaoCima;
import javax.swing.JOptionPane;

public class RoyalQuarters2 implements GameStateController {

    private Hunter hunter;
    ArrayList<Magias> tirosJogador;
    private Queen blood;
    private Healer healer;
    private Warrior warrior;
    private CenarioComColisaoCima cenario;
    private int vida = 2;
    ArrayList<Heal> healJogador;
    Portal portal;

    public void load() {
        this.hunter = new Hunter();
        this.blood = new Queen(400, 40, 2);
        this.healer = new Healer();
        this.tirosJogador = new ArrayList<Magias>();
        this.warrior = new Warrior();
        this.healJogador = new ArrayList<Heal>();
        this.portal = new Portal (250,100);
        
       


        try {
            this.cenario = new CenarioComColisaoCima("resources/cenario/royalquarters2.scn");
        } catch (Exception ex) {
            System.out.println("Imagem não encontrada: " + ex.getMessage());
        }
        
         this.cenario.adicionaObjeto(healer);
          this.cenario.adicionaObjeto(warrior);
           this.cenario.adicionaObjeto(this.hunter);


    }

    public void step(long timeElapsed) {
        this.hunter.step(timeElapsed);
        this.blood.step(timeElapsed);
        this.healer.step(timeElapsed);
        this.lancaTirosJogador();
        this.warrior.step(timeElapsed);
        this.cenario.step(timeElapsed);
        this.portal.step(timeElapsed);
        

        for (Magias magia : this.tirosJogador) {
            magia.step(timeElapsed);
        }
        for (Heal heala : this.healJogador) {
           heala.step(timeElapsed);
        }


        blood.persegueObjetoMaisProximo(this.hunter, this.warrior);
        healer.gmana();
        healer.rmana();
        healer.gvida();

        this.verificaColisoesComInimigos();
        this.verificaColisaoComTiros();
        this.blood.estaMorto();
       //if( this.warrior.temColisao( this.portal.getRetangulo()) && this.hunter.temColisao(this.portal.getRetangulo()) && this.healer.temColisao(this.portal.getRetangulo())){
            if( this.warrior.temColisao( this.portal.getRetangulo()) ){
            if( this.hunter.temColisao(this.portal.getRetangulo())){
                if(this.healer.temColisao(this.portal.getRetangulo()))
        GameEngine.getInstance().setNextGameStateController( 9 );
         }
            }
    }

        
    

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1024, 760);

        
        this.cenario.draw(g);
        this.portal.draw(g);
        this.hunter.draw(g);
        this.warrior.draw(g);
        this.blood.draw(g);
        this.healer.draw(g);
        for (Magias magia : this.tirosJogador) {
            magia.draw(g);
        }
        
        for (Heal heala : this.healJogador) {
            heala.draw(g);
        }


    }

    public void start() {
        healer.setX(500);
        healer.setY(500);
        hunter.setX(450);
        hunter.setY(500);
        warrior.setX(400);
        warrior.setY(500);
        
        
    }

    public void unload() {
    }

    public void stop() {
    }

    private void verificaColisoesComInimigos() {

        //Inimigo 1

        if (this.blood.estaMorto() || this.warrior.estaMorto()) {
            return;

        } else {

            if (this.blood.temColisao(this.warrior.getRetangulo())) {

                this.warrior.perdeVida(250);
                this.blood.perdeVida(500);
                this.vida -= vida;

            }

            if (this.blood.temColisao(this.hunter.getRetangulo())) {

                this.hunter.perdeVida(1000);
            }
            
            if(this.healer.temColisao(this.blood.getRetangulo())){
            this.healer.perdeVida(1000);
            
            }



            //Inimigo 2
            // if(this.jogador.temColisao(this.inimigo2.getRectangle())){
            //     this.jogador.setX(50);
            //   this.jogador.setY(50);
            // }

            //Inimigo Perseguidor   
            // if(this.jogador.temColisao(this.inimigoPerseguidor.getRectangle())){
            //  this.jogador.setX(50);
            //  this.jogador.setY(50);
            // }

        }
    }

    private void lancaTirosJogador() {
        Keyboard teclado = GameEngine.getInstance().getKeyboard();

        if (teclado.keyDown(Keys.Q)) {
            if (this.hunter.podeAtirar()) {
                this.tirosJogador.add(this.hunter.getMagia());
            }
        }

        if (teclado.keyDown(Keys.U)) {
            if (this.healer.podeAtirar()) {
                this.healJogador.add(this.healer.getMagia());
                this.healer.mana();
                        
            }

        }
    }

    private void verificaColisaoComTiros() {
        Random gerador = new Random();
        int numero = gerador.nextInt(7564) + 2500;

        for (Heal heala : this.healJogador) {
            if (heala.temColisao(warrior.getRetangulo())) {
                warrior.heal(numero);


            }
        }
        for (Heal heal : this.healJogador) {
            if (heal.temColisao(hunter.getRetangulo())) {
                hunter.heal(numero);


            }
        }


        for (Magias flechas : this.tirosJogador) {
            if (flechas.temColisao(blood.getRetangulo())) {
                blood.perdeVida(25000);
                this.vida -= vida;

            }
        }




    }
    
    
   
}            
