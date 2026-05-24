package client.observer;

import java.beans.PropertyChangeListener;

/**
 * Interface for classes that let listeners be added and removed.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public interface UnnamedPropertyChangeSubject {
    /**
     * Adds a listener.
     *
     * @param listener the listener
     */
    void addListener(PropertyChangeListener listener);
    /**
     * Removes a listener.
     *
     * @param listener the listener
     */
    void removeListener(PropertyChangeListener listener);
}
