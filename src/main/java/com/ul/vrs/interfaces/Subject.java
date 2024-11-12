package com.ul.vrs.interfaces;

import java.util.Observer;

public interface Subject {
    void attach(Observer o);
    void detach(Observer o);
    void notifyObservers();
}
