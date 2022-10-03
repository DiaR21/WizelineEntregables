package com.wizeline.PDICENIO.factorymethod.factory;

import com.wizeline.PDICENIO.factorymethod.notifications.EmailNotification;
import com.wizeline.PDICENIO.factorymethod.notifications.Notification;

public class NotificationFactory {

    public Notification createNotification(String channel) {

        if (channel == null || channel.isEmpty())
            return null;
        switch (channel) {
            case "EMAIL":
                return new EmailNotification();
            default:
                throw new IllegalArgumentException("Unknown channel " + channel);
        }
    }
}