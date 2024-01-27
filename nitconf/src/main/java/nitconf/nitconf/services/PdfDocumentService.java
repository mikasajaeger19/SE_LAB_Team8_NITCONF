package nitconf.nitconf.services;

import nitconf.nitconf.models.PdfDocument;
import nitconf.nitconf.repositories.PdfDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PdfDocumentService {

    private final PdfDocumentRepository pdfDocumentRepository;

    @Autowired
    public PdfDocumentService(PdfDocumentRepository pdfDocumentRepository) {
        this.pdfDocumentRepository = pdfDocumentRepository;
    }

}
