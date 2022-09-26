package com.wizeline.abstractfactory.buttons;

public class WindowsButton implements abstractfactory.buttons.Button {

    @Override
    public void paint() {
        System.out.println("WindowsButton created.");
    }
}
