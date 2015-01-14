package us.kdms.boxes;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
public class KeyboardListener {
    private static boolean paused = false;
    private static boolean isExit = false;
    static {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(
            new KeyEventDispatcher() {
                @Override
				public boolean dispatchKeyEvent(KeyEvent e) {
                	switch (e.getKeyCode()) {
                		case KeyEvent.VK_SPACE:
                			if (!paused) {
                				paused = true;
                			} else {
                				paused = false;
                			}
                		break;
                		case KeyEvent.VK_ESCAPE:
                			isExit = true;
                		break;
                	}
                    return false;
                }
            });
    }
    public static boolean isPaused() {
    	return paused;
    }
    public static boolean isExit() {
    	return isExit;
    }
}