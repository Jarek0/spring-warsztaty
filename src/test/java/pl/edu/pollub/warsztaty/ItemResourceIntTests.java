package pl.edu.pollub.warsztaty;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.edu.pollub.warsztaty.item.dao.ItemDao;
import pl.edu.pollub.warsztaty.item.domain.ItemEntity;
import pl.edu.pollub.warsztaty.item.dto.ItemReq;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import static pl.edu.pollub.warsztaty.ItemReqFactory.of;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
public class ItemResourceIntTests {

    @Autowired
    private ItemDao dao;

    @Autowired
    private MockMvc mvc;

    @Before
    public void cleanDb() {
        dao.deleteAll();
    }

    @Test
    public void shouldCreateItem() throws Exception {
        ItemReq createItemReq = of("Obraz0", "Obrazy");

        mvc.perform(post("/api/items")
                .content(asJson(createItemReq))
                .contentType(APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Obraz0")))
                .andExpect(jsonPath("$.category", is("Obrazy")))
                .andExpect(jsonPath("$.id", notNullValue()));

        Optional<ItemEntity> maybeFoundItem = dao.findByName("Obraz0");
        assertTrue(maybeFoundItem.isPresent());

        ItemEntity foundItem = maybeFoundItem.get();
        assertEquals(foundItem.getName(), "Obraz0");
        assertEquals(foundItem.getCategory(), "Obrazy");

        Long countInCategory = dao.countByCategory("Obrazy");
        assertEquals(Long.valueOf(1L), countInCategory);
    }

    public static String asJson(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
