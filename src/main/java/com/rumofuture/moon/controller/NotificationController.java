package com.rumofuture.moon.controller;

import com.rumofuture.moon.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping(value = "/uncheckedTotal")
    public int getUncheckedNotificationTotal(@RequestParam("id") Integer id) {
        return notificationService.getUncheckedNotificationTotal(id);
    }
}
