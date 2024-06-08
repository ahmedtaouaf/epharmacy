package com.app.Epharmacy.Services;

import com.app.Epharmacy.Entity.ClientInfo;
import com.app.Epharmacy.Entity.Commande;
import com.app.Epharmacy.Entity.Medicament;
import com.app.Epharmacy.Entity.Pharmacie;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Service
public class PdfService {

    private final Path imageStoragePath = Paths.get("src/main/resources/static/images");

    public byte[] generateInvoice(ClientInfo clientInfo, Pharmacie pharmacie, Commande commande, Map<Long, Medicament> cartItems) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        Path backgroundImagePath = imageStoragePath.resolve("invoice_bg.jpg");
        pdf.addNewPage();

        try {
            ImageData backgroundImageData = ImageDataFactory.create(backgroundImagePath.toAbsolutePath().toString());
            Image backgroundImage = new Image(backgroundImageData);
            backgroundImage.scaleAbsolute(PageSize.A4.getWidth(), PageSize.A4.getHeight());
            backgroundImage.setFixedPosition(0, 0);

            PdfCanvas pdfCanvas = new PdfCanvas(pdf.getFirstPage());
            Canvas canvas = new Canvas(pdfCanvas, pdf, PageSize.A4);
            canvas.add(backgroundImage);
            canvas.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Title
        Paragraph title = new Paragraph("Bon De Commande")
                .setFontColor(new DeviceRgb(0, 0, 0))
                .setFontSize(20)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(20);
        document.add(title);

        // Client and Pharmacy Information in two columns
        float[] columnWidths = {1, 1}; // Two columns of equal width
        Table infoTable = new Table(columnWidths);
        infoTable.setWidthPercent(100); // Full width of the page

        // Client Information
        Cell clientCell = new Cell();
        clientCell.add(createSection("Informations Client:"));
        clientCell.add(createKeyValue("Nom", clientInfo.getFirstName() + " " + clientInfo.getLastName()));
        clientCell.add(createKeyValue("Adresse", clientInfo.getAddress()));
        clientCell.add(createKeyValue("Téléphone", clientInfo.getPhone()));
        clientCell.add(createKeyValue("Email", clientInfo.getEmail()));
        clientCell.setTextAlignment(TextAlignment.CENTER);
        infoTable.addCell(clientCell);

        // Pharmacy Information
        Cell pharmacyCell = new Cell();
        pharmacyCell.add(createSection("Informations Pharmacie:"));
        pharmacyCell.add(createKeyValue("Nom", pharmacie.getNom()));
        pharmacyCell.add(createKeyValue("Adresse", pharmacie.getAdresse()));
        pharmacyCell.add(createKeyValue("Téléphone", pharmacie.getTelephone()));
        pharmacyCell.add(createKeyValue("Fix", pharmacie.getFix()));
        pharmacyCell.setTextAlignment(TextAlignment.CENTER);
        infoTable.addCell(pharmacyCell);

        document.add(infoTable);

        // Order Details
        document.add(createSection("Détails Commande:").setTextAlignment(TextAlignment.CENTER));
        document.add(createKeyValue("Date Commande", String.valueOf(commande.getOrderDate())).setTextAlignment(TextAlignment.CENTER));
        document.add(createKeyValue("Nombre Produits", String.valueOf(commande.getNbrproduit())).setTextAlignment(TextAlignment.CENTER));
        document.add(createKeyValue("Total", String.valueOf(commande.getTotal())).setTextAlignment(TextAlignment.CENTER));

        // Items
        document.add(createSection("Produits:").setTextAlignment(TextAlignment.CENTER));

        // Create table with column headers for items
        float[] itemColumnWidths = {2, 1, 1}; // Adjust column widths as needed
        Table itemsTable = new Table(itemColumnWidths);
        itemsTable.setWidthPercent(100); // Full width of the page
        itemsTable.addHeaderCell(new Cell().add(new Paragraph("Image").setBold()).setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));
        itemsTable.addHeaderCell(new Cell().add(new Paragraph("Medicament").setBold()).setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));
        itemsTable.addHeaderCell(new Cell().add(new Paragraph("Prix").setBold()).setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));


        for (Medicament medicament : cartItems.values()) {

            Path imagePath = imageStoragePath.resolve(medicament.getImageFileName());
            try {
                ImageData imageData = ImageDataFactory.create(imagePath.toAbsolutePath().toString());
                Image image = new Image(imageData);
                image.setWidth(50);
                image.setHeight(50);
                image.setHorizontalAlignment(HorizontalAlignment.CENTER);
                itemsTable.addCell(new Cell().add(image).setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));
            } catch (Exception e) {
                itemsTable.addCell(new Cell().add(new Paragraph("Image not found")).setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));
            }

            itemsTable.addCell(new Cell().add(new Paragraph(medicament.getName())).setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));
            itemsTable.addCell(new Cell().add(new Paragraph(String.valueOf(medicament.getPrice()+ "DHS"))).setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));

        }

        document.add(itemsTable);
        document.close();

        return baos.toByteArray();
    }

    private Paragraph createSection(String title) {
        return new Paragraph(title)
                .setBold()
                .setFontColor(new DeviceRgb(0, 0, 0))
                .setFontSize(14)
                .setMarginTop(15)
                .setMarginBottom(10)
                .setTextAlignment(TextAlignment.CENTER);
    }

    private Paragraph createKeyValue(String key, String value) {
        return new Paragraph()
                .add(new Text(key + ": ").setBold())
                .add(value)
                .setMarginBottom(5)
                .setTextAlignment(TextAlignment.CENTER);
    }
}
