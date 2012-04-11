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
import javaPlayExtras.CenarioComColisao;
import javax.swing.JOptionPane;

public class LowerCitadel implements GameStateController {
  
  private Hunter hunter;
  ArrayList<Magias> tirosJogador;
  private Marrowgar marrowgar;
  private Healer healer;
  private Warrior warrior;
  private CenarioComColisao cenario;
  private int vida =2;
  

  

  public void load() {    
    this.hunter = new Hunter();
    this.marrowgar = new Marrowgar(600,400, 2);
    this.healer = new Healer();
    this.tirosJogador = new ArrayList<Magias>();
    this.warrior = new Warrior();
    
     try {            
            this.cenario = new CenarioComColisao("resources/cenario/lowercitadel.scn");                        
        } catch (Exception ex) {
           System.out.println("Imagem não encontrada: " + ex.getMessage());
       }

   
  }

  public void step(long timeElapsed) {     
    this.hunter.step(timeElapsed);
    this.marrowgar.step(timeElapsed);
    this.healer.step(timeElapsed);
    this.lancaTirosJogador();
    this.warrior.step(timeElapsed);
    this.cenario.step(timeElapsed);
 
    for (Magias magia : this.tirosJogador) {
            magia.step(timeElapsed);
        }
    

    marrowgar.persegueObjetoMaisProximo(this.hunter , this.warrior);
     
  
    this.verificaColisoesComInimigos();
    this.verificaColisaoComTiros();
    this.marrowgar.estaMorto();

    
   // if( this.jogador.temColisao( this.chegada.getRectangle() )){
    //  GameEngine.getInstance().setNextGameStateController( 200 );
   // }

  }

  public void draw(Graphics g) {
   g.setColor(Color.BLACK);
    g.fillRect(0, 0,1024, 760);


    this.cenario.draw(g);
    this.hunter.draw(g);
    this.warrior.draw(g);
    this.marrowgar.draw(g);
    this.healer.draw(g);
    for (Magias magia : this.tirosJogador) {
            magia.draw(g);
        }

    
  }
  
  public void start() { }
  public void unload() { }
  public void stop() {  }

  private void verificaColisoesComInimigos(){

    //Inimigo 1
    
      if(this.marrowgar.estaMorto() ||  this.warrior.estaMorto()){
           return;
      
      }else{
      
      if(this.marrowgar.temColisao(this.warrior.getRetangulo())){
        
        this.warrior.perdeVida(250);
        this.marrowgar.perdeVida(100);
        this.vida -= vida;
    
    }
      
    if(this.marrowgar.temColisao(this.hunter.getRetangulo())){
        
        this.hunter.perdeVida(1000);
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

        if(teclado.keyDown( Keys.Q) ){
            if(this.hunter.podeAtirar()){
                this.tirosJogador.add( this.hunter.getMagia() );
            }            
        }

        if(teclado.keyDown( Keys.U ) ){
            if(this.healer.podeAtirar()){
                this.tirosJogador.add( this.healer.getMagia() );
            }
    
}
    }
    
    
      private void verificaColisaoComTiros(){
        Random gerador = new Random();
        int numero = gerador.nextInt(5524)+1500;
        
        for(Magias heal : this.tirosJogador){                        
            if(heal.temColisao(warrior.getRetangulo())){                
                warrior.heal(numero);
                
                
            }
        }
        for(Magias heal : this.tirosJogador){                        
            if(heal.temColisao(hunter.getRetangulo())){                
                hunter.heal(3555);
        
        
    }
}
        
        
        for(Magias heal : this.tirosJogador){                        
            if(heal.temColisao(marrowgar.getRetangulo())){                
                marrowgar.perdeVida(6000);
                  this.vida -= vida;
                    
      }
}
        

        
    
      }
}

