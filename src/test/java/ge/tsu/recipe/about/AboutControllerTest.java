package ge.tsu.recipe.about;

import ge.tsu.recipe.category.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AboutControllerTest {

    @Mock
    private CategoryService categoryService;

    @Mock
    private Model model;

    @InjectMocks
    private AboutController aboutController;

    @BeforeEach
    void setUp() {

        ReflectionTestUtils.setField(aboutController, "appVersion", "1.0-TEST");
    }

    @Test
    void about_ShouldReturnAboutViewWithCorrectAttributes() {
        List mockCategories = mock(List.class);
        when(categoryService.getAllCategories()).thenReturn(mockCategories);
        String result = aboutController.about(model);
        assertEquals("about", result);
        verify(model).addAttribute("categories", mockCategories);
        verify(model).addAttribute("appVersion", "1.0-TEST");
    }
    @Test
    void about_ShouldHandleEmptyCategories() {
        List emptyCategories = mock(List.class);
        when(categoryService.getAllCategories()).thenReturn(emptyCategories);
        String result = aboutController.about(model);
        assertEquals("about", result);
        verify(model).addAttribute("categories", emptyCategories);
        verify(model).addAttribute("appVersion", "1.0-TEST");
    }
}