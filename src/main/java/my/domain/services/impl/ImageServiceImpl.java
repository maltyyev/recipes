package my.domain.services.impl;

import my.domain.models.Recipe;
import my.domain.repositories.RecipeRepository;
import my.domain.services.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by maltyyev on 18.01.18 3:20
 */
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) {
        try {
            Recipe recipe = recipeRepository.findByIdAndPresentIsTrue(recipeId).get();

            Byte[] bytes = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes())
                bytes[i++] = b;

            recipe.setImage(bytes);

            recipeRepository.save(recipe);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
