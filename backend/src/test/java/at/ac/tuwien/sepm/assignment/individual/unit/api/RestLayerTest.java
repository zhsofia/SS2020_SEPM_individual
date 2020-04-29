package at.ac.tuwien.sepm.assignment.individual.unit.api;

import at.ac.tuwien.sepm.assignment.individual.endpoint.HorseEndpoint;
import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.HorseDto;
import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.DataProcessingException;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.service.HorseService;
import at.ac.tuwien.sepm.assignment.individual.util.ValidationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class RestLayerTest {

    @Autowired
    MockMvc mockMvc;

    @Qualifier("SimpleHorseService")
    @MockBean
    private HorseService horseService;

    @Test
    @DisplayName("Horse endpoint NotFound Test.")
    public void horseEndpoint_get_NotFoundTest() throws Exception {
        when(horseService.getHorse(0L)).thenThrow(new NotFoundException("Test Message!"));
        this.mockMvc.perform(get("/horses/0")).andExpect(status().isNotFound())
            .andExpect(status().reason("Test Message!"));
    }

    @Test
    @DisplayName("Horse endpoint Success Test.")
    public void horseEndpoint_get_Success() throws Exception {
        when(horseService.getHorse(1L)).thenReturn(new Horse());
        this.mockMvc.perform(get("/horses/1")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Horse endpoint BadRequest Test.")
    public void horseEndpoint_insert_Invalid() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        HorseDto horseDto = new HorseDto();
        doThrow(ValidationException.class).when(horseService).insertHorse(isA(Horse.class));
        this.mockMvc.perform(post("/horses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(horseDto)))
            .andExpect(status().isBadRequest());
    }

   @Test
   @DisplayName("Horse endpoint IsCreated Test.")
    public void horseEndpoint_insert_Valid() throws Exception{
       ObjectMapper mapper = new ObjectMapper();
       HorseDto horseDto = new HorseDto();
        doNothing().when(horseService).insertHorse(isA(Horse.class));
        this.mockMvc.perform(post("/horses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(horseDto)))
            .andExpect(status().isCreated());
    }
}
