package Mapas;


import Boss.Lady;
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

public class LowerCitadel2 implements GameStateController {
  
  private Hunter hunter;
  ArrayList<Magias> tirosJogador;
  private Healer healer;
  private Warrior warrior;
  private CenarioComColisao cenario;
  private int vida =2;
  private Lady lady;
    ArrayList<Heal> healJogador;
  

  

  public void load() {    
    this.hunter = new Hunter();
    this.lady = new Lady(800,500, 2);
    this.healer = new Healer();
    this.tirosJogador = new ArrayList<Magias>();
    this.warrior = new Warrior();
    
     try {            
            this.cenario = new CenarioComColisao("resources/cenario/lowercitadel2.scn");                        
        } catch (Exception ex) {
           System.out.println("Imagem não encontrada: " + ex.getMessage());
       }

   
  }

  public void step(long timeElapsed) {     
    this.hunter.step(timeElapsed);
    this.lady.step(timeElapsed);
    this.healer.step(timeElapsed);
    this.lancaTirosJogador();
    this.warrior.step(timeElapsed);
    this.cenario.step(timeElapsed);
 
    for (Magias magia : this.tirosJogador) {
            magia.step(timeElapsed);
        }
    

    lady.persegueObjetoMaisProximo(this.hunter , this.warrior);
     
  
    this.verificaColisoesComInimigos();
    this.verificaColisaoComTiros();
    this.lady.estaMorto();

    
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
    this.lady.draw(g);
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
    
      if(this.lady.estaMorto() ||  this.warrior.estaMorto()){
           return;
      
      }else{
      
      if(this.lady.temColisao(this.warrior.getRetangulo())){
        
        this.warrior.perdeVida(250);
        this.lady.perdeVida(100);
        this.vida -= vida;
    
    }
      
    if(this.lady.temColisao(this.hunter.getRetangulo())){
        
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
                this.healJogador.add( this.healer.getMagia() );
            }
    
}
    }
    
    
      private void verificaColisaoComTiros(){
        Random gerador = new Random();
        int numero = gerador.nextInt(5524)+1500;
        
        for(Heal heal : this.healJogador){                        
            if(heal.temColisao(warrior.getRetangulo())){                
                warrior.heal(numero);
                
                
            }
        }
        for(Heal heal : this.healJogador){                        
            if(heal.temColisao(hunter.getRetangulo())){                
                hunter.heal(numero);
        
        
    }
}
        
        
        for(Magias flechas : this.tirosJogador){                        
            if(flechas.temColisao(lady.getRetangulo())){                
                lady.perdeVida(6000);
                  this.vida -= vida;
                    
      }
}
        

        
    
      }
}

