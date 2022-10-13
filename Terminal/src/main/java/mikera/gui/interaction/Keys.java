package mikera.gui.interaction;

import java.util.HashMap;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keys extends KeyAdapter {
	 
	private final HashMap<Integer,Boolean> keys=new HashMap<Integer, Boolean>();
	
	public void setKey(int i, boolean b) {
		keys.put(i, b);
	}
	
	public boolean getKey(int i) {
		Boolean b=keys.get(i);
		return (b!=null)&&(b.booleanValue());
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("Key: " + e);
		keys.put(e.getKeyCode(), true);
	}
	
	@Override
	public void keyReleased (KeyEvent e) {
		keys.put(e.getKeyCode(), false);	
	}
	
}
