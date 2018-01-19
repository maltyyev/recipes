package my.domain.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by maltyyev on 18.01.18 3:20
 */
public interface ImageService {

    void saveImageFile(Long recipeId, MultipartFile file);
}
