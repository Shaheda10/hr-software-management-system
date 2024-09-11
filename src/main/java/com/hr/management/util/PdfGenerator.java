package com.hr.management.util;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfGenerator {

	public static void generateSalarySlip() {
        Document document = new Document();

        try {
            // Replace this path with your desired file path and name
            PdfWriter.getInstance(document, new FileOutputStream("/Users/cipher/Documents/salary_slip.pdf"));
            document.open();

            // Sample content for the salary slip
            document.add(new Paragraph("Employee Name: John Doe"));
            document.add(new Paragraph("Salary: $5000"));
            document.add(new Paragraph("Month: November"));

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        generateSalarySlip();
    }

}
