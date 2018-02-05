package com.Dao;

import java.util.List;

import com.model.Notification;

public interface NotificationDao {
public List<Notification> getNotification(String username,int viewed);
public Notification updateNotification(int notificationId);
}
