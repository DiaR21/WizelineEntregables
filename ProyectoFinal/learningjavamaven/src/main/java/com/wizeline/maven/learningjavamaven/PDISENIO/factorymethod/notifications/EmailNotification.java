package com.wizeline.maven.learningjavamaven.PDISENIO.factorymethod.notifications;

import javax.management.Notification;

public class EmailNotification  implements Notification {

    @Override
    public void notifyUser() {
        System.out.println("Sending an Email notification ...");
    }
}