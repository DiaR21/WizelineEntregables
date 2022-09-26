package com.wizeline.abstractfactory.buttons;

public class LinuxButton implements abstractfactory.buttons.Button {
    @Override
    public void paint() {
        System.out.println("LinuxButton created.");
    }
}
