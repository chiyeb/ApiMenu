package fr.univamu.iut.apimenustpmarche.test.menu;

import fr.univamu.iut.apimenustpmarche.model.dish.Dish;
import fr.univamu.iut.apimenustpmarche.model.menu.Menu;
import fr.univamu.iut.apimenustpmarche.model.menu.MenuRequest;
import fr.univamu.iut.apimenustpmarche.model.user.UserLogin;
import fr.univamu.iut.apimenustpmarche.repository.MenuRepository;
import fr.univamu.iut.apimenustpmarche.services.handler.menu.MenuHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MenuHandlerTest {

    @Mock
    private MenuRepository menuRepository;
    @Mock
    private WebClient webClient;
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;
    @Mock
    private WebClient.ResponseSpec responseSpec;

    private MenuHandler menuHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        menuHandler = new MenuHandler(menuRepository, webClient);
    }

    @Test
    void shouldAddMenu() {
        MenuRequest menuRequest = new MenuRequest("admin", "admin", "Test Menu",
                "Description", Arrays.asList(1, 2));
        menuRequest.setName("Test Menu");
        menuRequest.setDescription("Description");
        menuRequest.setDishesId(Arrays.asList(1, 2));

        Dish dish1 = new Dish();
        dish1.setName("Dish 1");
        Dish dish2 = new Dish();
        dish2.setName("Dish 2");

        when(menuRepository.save(any(Menu.class))).thenAnswer(i -> i.getArguments()[0]);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString(), Optional.ofNullable(any()))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Dish.class)).thenReturn(Mono.just(dish1), Mono.just(dish2));

        Menu addedMenu = menuHandler.addMenu(menuRequest);

        verify(menuRepository, times(1)).save(any(Menu.class));
        verify(webClient, times(2)).get();
    }

    @Test
    void shouldGetMenuById() {
        int menuId = 1;
        Menu menu = new Menu("Test Menu", "Description", Arrays.asList(1, 2));

        when(menuRepository.findById(menuId)).thenReturn(Optional.of(menu));
        menu.setId(menuId);
        menu.setName("Test Menu");

        when(menuRepository.findById(menuId)).thenReturn(Optional.of(menu));

        Menu resultMenu = menuHandler.getMenuById(menuId);

        verify(menuRepository, times(1)).findById(menuId);
        assert resultMenu != null;
        assert resultMenu.getName().equals("Test Menu");
    }

    @Test
    void shouldDeleteMenu() {
        int menuId = 1;
        doNothing().when(menuRepository).deleteById(menuId);
        menuHandler.deleteMenu(menuId, new UserLogin("admin", "admin"));
        verify(menuRepository, times(1)).deleteById(menuId);
    }
}
