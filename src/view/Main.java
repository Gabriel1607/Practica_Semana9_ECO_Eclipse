package view;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import events.OnMessage;
import model.Pedido;
import processing.core.PApplet;
import processing.core.PImage;


public class Main  extends PApplet implements OnMessage {

	public static void main(String[] args) {
	
		PApplet.main(Main.class.getName());
	}
	private PImage banano,banano2,fresa,aguacate;
	private ArrayList<Pedido> pedidos;
	UDPConnection udp;
	private int order;

public void settings() {
	  size(1000, 700);
}
	public void setup() {
		
		udp = new UDPConnection();
		udp.setObserver(this);
		udp.start();

	banano = loadImage("img/banano.png");
	banano2 = loadImage("img/bananopelao.png");
	fresa = loadImage("img/mediafresa.png");
	aguacate = loadImage("img/aguacate.png");
	
	pedidos = new ArrayList<Pedido>();
	order=0;
	}

	public void draw() {
	background(0);
	drawPedidos();
	}
	public void mousePressed() {
		deletePedidos();

	}
	
	public void drawPedidos() {
		for (int i = 0; i < pedidos.size(); i++) {
			pedidos.get(i).draw();
		}	
	}
	public void deletePedidos() {
		for (int i = 0; i < pedidos.size(); i++) {

			if (mouseX > pedidos.get(i).getPosX() && mouseX < pedidos.get(i).getPosX()+70 && 
					mouseY >pedidos.get(i).getPosY() && mouseY <pedidos.get(i).getPosY()+70) {
					udp.sendMessage("Su " + pedidos.get(i).getItem() + " ya está listo");
					order--;

				pedidos.remove(i);
			}

		}
	}
	
	@Override
	public void onMessageReceived(String msg) {
		System.out.println(pedidos.size());
		if (order < 6) {
			order++;
			int n = order;

			
			Date date = new Date();
		switch (msg) {
		case "BANANO":
			date = new Date();
			pedidos.add(new Pedido(80+(90*n),20,msg,this, banano, date,n));
			break;
		case "BANANOP":
			date = new Date();
			pedidos.add(new Pedido(80+(90*n),20,msg,this, banano2, date,n));
			break;
		case "FRESA":
			date = new Date();
			pedidos.add(new Pedido(80+(90*n),20,msg,this, fresa, date,n));
			break;
		case "AGUACATE":
			date = new Date();
			pedidos.add(new Pedido(80+(90*n),20,msg,this, aguacate, date,n));
			break;
		default:
			break;
		}
		} else {
			JOptionPane.showMessageDialog(null, "# de pedidos máximo alcanzado, por favor despachar",
				      "Cuidado!", JOptionPane.ERROR_MESSAGE);
		}
	}
	}

	



