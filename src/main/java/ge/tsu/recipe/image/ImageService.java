package ge.tsu.recipe.image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Value("${app.upload.dir:${user.home}/recipe-uploads}")
    private String uploadDir;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<ImageDTO> getImagesByRecipeId(Long recipeId) {
        return imageRepository.findByRecipeId(recipeId)
                .stream()
                .map(ImageDTO::fromImage)
                .collect(Collectors.toList());
    }

    @Transactional
    public String saveImageFile(MultipartFile file) {
        try {
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename != null ?
                    originalFilename.substring(originalFilename.lastIndexOf(".")) : ".jpg";
            String newFilename = UUID.randomUUID().toString() + fileExtension;

            Path filePath = uploadPath.resolve(newFilename);
            Files.copy(file.getInputStream(), filePath);

            return "/uploads/" + newFilename;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store image file", e);
        }
    }

    @Transactional
    public void deleteImage(Long imageId) {
        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Image not found with id: " + imageId));

        try {
            Path filePath = Paths.get(uploadDir).resolve(
                    Paths.get(image.getPath()).getFileName().toString());
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            System.err.println("Could not delete file: " + e.getMessage());
        }

        imageRepository.delete(image);
    }
}