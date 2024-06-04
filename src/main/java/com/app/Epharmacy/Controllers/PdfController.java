package com.app.Epharmacy.Controllers;

import com.app.Epharmacy.Entity.ClientInfo;
import com.app.Epharmacy.Entity.Commande;
import com.app.Epharmacy.Entity.Medicament;
import com.app.Epharmacy.Entity.Pharmacie;
import com.app.Epharmacy.Repository.ClientInfoRepository;
import com.app.Epharmacy.Repository.CommandeRepository;
import com.app.Epharmacy.Repository.PharmacieRepository;
import com.app.Epharmacy.Services.CartService;
import com.app.Epharmacy.Services.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @Autowired
    private CommandeRepository orderRepository;

    @Autowired
    private ClientInfoRepository clientInfoRepository;

    @Autowired
    private PharmacieRepository pharmacieRepository;

    @Autowired
    private CartService cartService;

    @GetMapping("/downloadInvoice")
    public ResponseEntity<byte[]> downloadInvoice(@RequestParam Long orderId) {
        Commande commande = orderRepository.findById(orderId).orElse(null);
        if (commande == null) {
            return ResponseEntity.notFound().build();
        }

        ClientInfo clientInfo = commande.getClientInfo();
        Pharmacie pharmacie = commande.getPharmacie();
        Map<Long, Medicament> cartItems = cartService.getCartItems();

        byte[] pdfBytes = pdfService.generateInvoice(clientInfo, pharmacie, commande, cartItems);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + commande.getId() + "CommandeReçu.pdf\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}
