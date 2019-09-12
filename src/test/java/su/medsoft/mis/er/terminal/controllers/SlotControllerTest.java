package su.medsoft.mis.er.terminal.controllers;

import io.micronaut.core.convert.DefaultConversionService;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.simple.SimpleHttpHeaders;
import io.reactivex.Single;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import su.medsoft.mis.er.terminal.dto.ErrorDto;
import su.medsoft.mis.er.terminal.responseMessages.SlotResponseMessage;
import su.medsoft.mis.er.terminal.services.interfaces.SlotService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

public class SlotControllerTest {
    private static HttpHeaders headers;

    @BeforeClass
    public static void initHeader() {
        Map<String, String> header = new HashMap<>();
        header.put(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtb0lkIjoxLCJzeXN0ZW1JZCI6IjA1ZjA0NmRkLTI3Y2YtNDdhOS1hMjA1LWJlYmI0MTNkOGRmMSIsInN5c3RlbU5hbWUiOiLQotC10YDQvNC40L3QsNC7INCc0LXQtNCh0L7RhNGCIiwiaXNzIjoibWVkc29mdCIsInN1YiI6ItCf0LXRgtGA0LXQu9C10LLQuNGHINCh0LXRgNCz0LXQuSJ9.Uyv0SzrNwd82KGUOjiIlkVxt8CghsmTWoI6STV1tBwo");

        headers = new SimpleHttpHeaders(header, new DefaultConversionService());
    }

    @Test
    public void getSlotsWithoutErrorTest() {

        SlotResponseMessage responseMessage = new SlotResponseMessage();
        responseMessage.setSlotDtoList(new ArrayList<>());

        SlotService service = Mockito.mock(SlotService.class);
        Mockito.when(service.getSlots(Mockito.anyString(), Mockito.any(), Mockito.anyLong(), Mockito.anyString(), Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(HttpResponse.ok(Single.just(responseMessage)));


        SlotController controller = new SlotController(service);
        HttpResponse<Single<SlotResponseMessage>> response = controller.getSlots(headers, "ТЕР", null, 1, "2019-09-12", "2019-09-12");

        assertEquals(response.getStatus(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertNull(response.getBody().get().blockingGet().getErrorDto());
        assertNotNull(response.getBody().get().blockingGet().getSlotDtoList());
    }

    @Test
    public void getSlotsWithErrorTest_withoutHeaders() {

        SlotService service = Mockito.mock(SlotService.class);

        SlotController controller = new SlotController(service);

        HttpResponse<Single<SlotResponseMessage>> response = controller.getSlots(null, null, null, 0, null, null);

        assertNotNull(response.getBody());
        assertNotNull(response.getBody().get().blockingGet().getErrorDto());
        assertEquals(response.getBody().get().blockingGet().getErrorDto().getCode(), 1);
        assertNull(response.getBody().get().blockingGet().getSlotDtoList());
    }

    @Test
    public void getSlotsWithErrorTest() {

        SlotResponseMessage responseMessage = new SlotResponseMessage();
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(1);
        responseMessage.setErrorDto(errorDto);

        SlotService service = Mockito.mock(SlotService.class);

        Mockito.when(service.getSlots(Mockito.anyString(), Mockito.any(), Mockito.anyLong(), Mockito.anyString(), Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(HttpResponse.serverError(Single.just(responseMessage)));

        SlotController controller = new SlotController(service);

        HttpResponse<Single<SlotResponseMessage>> response = controller.getSlots(headers, "ТЕР", null, 0, "2019-09-12", "2019-09-12");

        assertNotNull(response.getBody());
        assertNotNull(response.getBody().get().blockingGet().getErrorDto());
        assertEquals(1, response.getBody().get().blockingGet().getErrorDto().getCode());
        assertNull(response.getBody().get().blockingGet().getSlotDtoList());
    }
}
