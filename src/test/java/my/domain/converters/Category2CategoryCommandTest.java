package my.domain.converters;

import my.domain.commands.CategoryCommand;
import my.domain.models.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by maltyyev on 20.12.17 2:56
 */
public class Category2CategoryCommandTest {

    private Category2CategoryCommand converter;

    private static final Long ID = 1L;
    private static final String DESCRIPTION = "NEW DESCRIPTION";

    @Before
    public void setUp() throws Exception {
        converter = new Category2CategoryCommand();
    }

    @Test
    public void convert() {
        Category category = new Category();
        category.setId(ID);
        category.setDescription(DESCRIPTION);

        CategoryCommand categoryCommand = converter.convert(category);

        assertEquals(category.getId(), categoryCommand.getId());
        assertEquals(category.getDescription(), categoryCommand.getDescription());
    }
}