package p12.ocr.files.repository;

import p12.ocr.files.model.Files;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFilesRepository extends JpaRepository<Files,String> {}
