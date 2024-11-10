package com.ul.vrs.nterfaces;

import java.util.Observer;

public interface Subject {
    void attach(Observer o);
    void detach(Observer o);
    void notifyObservers();
}
