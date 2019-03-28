package tooPots.servicio;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class GestionFicheros {

    private String carpeta_carga = ".//src//main//resources//files//";

    public void guardaFichero(MultipartFile file) throws IOException {
        if(!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(carpeta_carga + file.getOriginalFilename());
            Files.write(path, bytes);
        }
    }

  /*  public void cargaFichero(String nombre) throws IOException {
        List<MultipartFile> certificados = new ArrayList();

        if(!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(carpeta_carga + file.getOriginalFilename());
            Files.write(path, bytes);
        }
    } */
}
