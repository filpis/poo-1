
package Game.KeyboardControllers;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardController implements KeyListener {
    private static final int KEY_COUNT = 256;
    private boolean[] keyStatus;

    public KeyboardController() {
        keyStatus = new boolean[KEY_COUNT];
    }

    public boolean isKeyPressed(int keyCode) {
        return (keyCode >= 0 && keyCode < KEY_COUNT) ? keyStatus[keyCode] : false;
    }

    public void clearKeyStatus() {
        for (int i = 0; i < KEY_COUNT; i++) {
            keyStatus[i] = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Implementação opcional
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < KEY_COUNT) {
            keyStatus[keyCode] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < KEY_COUNT) {
            keyStatus[keyCode] = false;
        }
    }

    public void resetController() {
        clearKeyStatus();
    }

    public boolean getKeyStatus(int keyCode) {
        return (keyCode >= 0 && keyCode < KEY_COUNT) ? keyStatus[keyCode] : false;
    }
}

