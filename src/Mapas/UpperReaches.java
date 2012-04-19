package Mapas;

import Boss.Festergut;
import Boss.Marrowgar;
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
import javax.swing.JOptionPane;

public class UpperReaches implements GameStateController {

    private Hunter hunter;
    ArrayList<Magias> tirosJogador;
    private Festergut festergut;
    private Healer healer;
    private Warrior warrior;
    private CenarioComColisao cenario;
    private int vida = 2;
    ArrayList<Heal> healJogador;
    Portal portal;

    public void load() {
        this.hunter = new Hunter();
        this.festergut = new Festergut(300, 100, 2);
        this.healer = new Healer();
        this.tirosJogador = new ArrayList<Magias>();
        this.warrior = new Warrior();
        this.healJogador = new ArrayList<Heal>();
        this.portal = new Portal (800,200);

        try {
            this.cenario = new CenarioComColisao("resources/cenario/lowercitadel.scn");
        } catch (Exception ex) {
            System.out.println("Imagem não encontrada: " + ex.getMessage());
        }


    }

    public void step(long timeElapsed) {
        this.hunter.step(timeElapsed);
        this.festergut.step(timeElapsed);
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


        festergut.persegueObjetoMaisProximo(this.hunter, this.warrior);
        healer.gmana();
        healer.rmana();
        healer.gvida();

        this.verificaColisoesComInimigos();
        this.verificaColisaoComTiros();
        this.festergut.estaMorto();
        this.verificaColisaoComPortal();
        

        if(this.festergut.estaMorto()){
        if( this.warrior.temColisao( this.portal.getRetangulo())){
            
            GameEngine.getInstance().setNextGameStateController( 2 );
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
        this.festergut.draw(g);
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

        if (this.festergut.estaMorto() || this.warrior.estaMorto()) {
            return;

        } else {

            if (this.festergut.temColisao(this.warrior.getRetangulo())) {

                this.warrior.perdeVida(250);
                this.festergut.perdeVida(500);
                this.vida -= vida;

            }

            if (this.festergut.temColisao(this.hunter.getRetangulo())) {

                this.hunter.perdeVida(1000);
            }
            
            if(this.healer.temColisao(this.festergut.getRetangulo())){
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
            if (flechas.temColisao(festergut.getRetangulo())) {
                festergut.perdeVida(600000);
                this.vida -= vida;

            }
        }




    }
    
    
    public void verificaColisaoComPortal(){
    
         if(this.portal.temColisao(this.hunter.getRetangulo())){
             
             if(this.festergut.estaMorto()){
             
             GameEngine.getInstance().setNextGameStateController( 2 );
             
             }
             }
         
           if(this.festergut.estaMorto()){
        if( this.warrior.temColisao( this.portal.getRetangulo())){
            
            GameEngine.getInstance().setNextGameStateController( 2 );
        }
        } 
           
              if(this.festergut.estaMorto()){
        if( this.healer.temColisao( this.portal.getRetangulo())){
            
            GameEngine.getInstance().setNextGameStateController( 2 );
        }
        }
    }
}
