package com.app.Epharmacy.Controllers;

import com.app.Epharmacy.Entity.Commande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendOrderNotification(Commande commande) {
        messagingTemplate.convertAndSend("/topic/orders", commande);
    }
}