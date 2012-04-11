package Mapas;


import Boss.Marrowgar;
import Modelo.Direcao;
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
import javax.swing.JOptionPane;

public class LowerCitadel implements GameStateController {
  
  private Hunter hunter;
  ArrayList<Magias> tirosJogador;
  private Marrowgar marrowgar;
  private Healer guerreiro;
  private Warrior warrior;

  

  public void load() {    
    this.hunter = new Hunter();
    this.marrowgar = new Marrowgar(200,150, 100);
    this.guerreiro = new Healer();
    this.tirosJogador = new ArrayList<Magias>();
    this.warrior = new Warrior();
    
    

   
  }

  public void step(long timeElapsed) {     
    this.hunter.step(timeElapsed);
    this.marrowgar.step(timeElapsed);
    this.guerreiro.step(timeElapsed);
    this.lancaTirosJogador();
    this.warrior.step(timeElapsed);
 
    for (Magias magia : this.tirosJogador) {
            magia.step(timeElapsed);
        }

    
   // if( this.jogador.temColisao( this.chegada.getRectangle() )){
    //  GameEngine.getInstance().setNextGameStateController( 200 );
   // }

  }

  public void draw(Graphics g) {
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, 800, 600);

    
    this.hunter.draw(g);
    this.warrior.draw(g);
    this.marrowgar.draw(g);
    this.guerreiro.draw(g);
    for (Magias magia : this.tirosJogador) {
            magia.draw(g);
        }

    
  }
  
  public void start() { }
  public void unload() { }
  public void stop() {  }

  private void verificaColisoesComInimigos(){

    //Inimigo 1
  //  if(this.jogador.temColisao(this.inimigo1.getRectangle())){
  //      this.jogador.setX(50);
   //     this.jogador.setY(50);
   // }

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
    
 // }


  }
    private void lancaTirosJogador() {
        Keyboard teclado = GameEngine.getInstance().getKeyboard();

        if(teclado.keyDown( Keys.ESPACO) ){
            if(this.hunter.podeAtirar()){
                this.tirosJogador.add( this.hunter.getMagia() );
            }            
        }

        if(teclado.keyDown( Keys.E ) ){
            if(this.guerreiro.podeAtirar()){
                this.tirosJogador.add( this.guerreiro.getMagia() );
            }
    
}
    }
}
        
    
    

