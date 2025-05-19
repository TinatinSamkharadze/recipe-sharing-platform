package ge.tsu.recipe.image;

import org.springframework.beans.BeanUtils;

public class ImageDTO {
    private Long id;
    private String path;

    public static ImageDTO fromImage(Image image) {
        ImageDTO dto = new ImageDTO();
        BeanUtils.copyProperties(image, dto);
        return dto;
    }
}