package com.wizeline.abstractfactory.buttons;

public class MacOsButton implements abstractfactory.buttons.Button {

    @Override
    public void paint() {
        System.out.println("MacOSButton created.");
    }
}
