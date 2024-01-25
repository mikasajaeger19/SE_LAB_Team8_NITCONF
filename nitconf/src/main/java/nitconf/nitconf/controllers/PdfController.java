package nitconf.nitconf.controllers;

import nitconf.nitconf.models.PdfDocument;
import nitconf.nitconf.repositories.PdfDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/pdfs")
public class PdfController {

    private final PdfDocumentRepository pdfDocumentRepository;

    @Autowired
    public PdfController(PdfDocumentRepository pdfDocumentRepository) {
        this.pdfDocumentRepository = pdfDocumentRepository;
    }

    @PostMapping("/upload")
    public String uploadPdf(@RequestParam("file") MultipartFile file, @RequestParam("fileName") String fileName) {
        try {
            PdfDocument pdfDocument = new PdfDocument();
            pdfDocument.setFileName(fileName);
            pdfDocument.setContent(file.getBytes());
            pdfDocumentRepository.save(pdfDocument);
            return "PDF uploaded successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to upload PDF.";
        }
    }

    @GetMapping("/hello")
    public String hello() {
        return "GET PDF";
    }
    
}
