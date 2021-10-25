package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import processing.core.PApplet;
import processing.core.PImage;

public class Pedido {
	private int posX,posY;
	private String item;
	private PApplet app;
	private PImage image;
	private Date date;
	private int numOrder;
	private String time;
	private SimpleDateFormat datePrintter;
	public Pedido(int posX, int posY, String item, PApplet app, PImage image, Date date, int numOrder) {
		this.posX = posX;
		this.posY = posY;
		this.item = item;
		this.app = app;
		this.image = image;
		this.date = date;
		this.numOrder = numOrder;
		datePrintter = new SimpleDateFormat("HH:mm:ss");
		time = datePrintter.format(date);
	}
	public void draw() {
		app.fill(255);
		app.textSize(12);
		app.text("Pedido: " +numOrder, posX, posY+80);
		app.text("Hora: " +time, posX, posY+120);
		app.image(image, posX, posY,70,70);

	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getNumOrder() {
		return numOrder;
	}
	public void setNumOrder(int numOrder) {
		this.numOrder = numOrder;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	

}
