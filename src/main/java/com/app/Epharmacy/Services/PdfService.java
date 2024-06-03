package com.app.Epharmacy.Services;

import com.app.Epharmacy.Entity.ClientInfo;
import com.app.Epharmacy.Entity.Commande;
import com.app.Epharmacy.Entity.Medicament;
import com.app.Epharmacy.Entity.Pharmacie;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
public class PdfService {

    /*public byte[] generateInvoice(ClientInfo clientInfo, Pharmacie pharmacie, Commande commande, Map<Long, Medicament> cartItems) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Invoice"));
        document.add(new Paragraph("Client Information:"));
        document.add(new Paragraph("Name: " + clientInfo.getFirstName() + " " + clientInfo.getLastName()));
        document.add(new Paragraph("Address: " + clientInfo.getAddress()));
        document.add(new Paragraph("Phone: " + clientInfo.getPhone()));
        document.add(new Paragraph("Email: " + clientInfo.getEmail()));

        document.add(new Paragraph("Pharmacy Information:"));
        document.add(new Paragraph("Name: " + pharmacie.getNom()));
        document.add(new Paragraph("Address: " + pharmacie.getAdresse()));

        document.add(new Paragraph("Order Details:"));
        document.add(new Paragraph("Order Date: " + commande.getOrderDate()));
        document.add(new Paragraph("Total: " + commande.getTotal()));

        document.add(new Paragraph("Items:"));
        for (Medicament medicament : cartItems.values()) {
            document.add(new Paragraph("Medicament: " + medicament.getName() + ", Price: " + medicament.getPrice()));
        }

        document.close();

        return baos.toByteArray();
    }*/






}
