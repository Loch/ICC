/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javaPlay.GameEngine;
import javaPlay.GameObject;
import javaPlay.Keyboard;

public abstract class  Jogador extends GameObject {

    private int velocidade = 5; //atributo que controla a velocidade

    public Jogador() {
//O Construtor inicia o jogador em uma posição fixa
        this.x = 50;
        this.y = 50;
    }

    public void step(long timeElapsed) {
//pega o objeto responsável pelo teclado
        Keyboard k = GameEngine.getInstance().getKeyboard();
//Verifica se uma tecla direcional está pressionado
//Em caso positivo, altera a posição do jogador
        if (k.keyDown(Keyboard.DOWN_KEY)) {
            this.y += this.velocidade;
        }
        if (k.keyDown(Keyboard.UP_KEY)) {
            this.y -= this.velocidade;
        }
        if (k.keyDown(Keyboard.LEFT_KEY)) {
            this.x -= this.velocidade;
        }
        if (k.keyDown(Keyboard.RIGHT_KEY)) {
            this.x += this.velocidade;
        }
    }

    public void draw(Graphics g) {
//Desenha um círculo com a cor verde.
        g.setColor(Color.black);
        g.drawLine(x, y, 20 , 25);    
        Graphics2D g2= (Graphics2D)g;
        //g2.drawOval(20, 20,20,20);
        g2.drawLine(x, y, x , y+40);
        
        

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getRectangle() {
        return new Rectangle(this.x, this.y, 20, 40);



    }

    public boolean temColisao(Rectangle r2) {
        return this.getRectangle().intersects(r2);
    }
}
