package su.medsoft.mis.er.terminal.controllers;

import io.micronaut.core.convert.DefaultConversionService;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.simple.SimpleHttpHeaders;
import io.reactivex.Single;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import su.medsoft.mis.er.terminal.dto.ErrorDto;
import su.medsoft.mis.er.terminal.responseMessages.DocumentTypesResponseMessage;
import su.medsoft.mis.er.terminal.responseMessages.InsuranceBlankTypesResponseMessage;
import su.medsoft.mis.er.terminal.services.interfaces.DictionaryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

public class DictionaryControllerTest {

    @Test
    public void getDocumentTypesWithoutErrorTest() {

        DocumentTypesResponseMessage responseMessage = new DocumentTypesResponseMessage();
        responseMessage.setDocumentTypeDtoList(new ArrayList<>());

        DictionaryService dictionaryService = Mockito.mock(DictionaryService.class);

        Mockito.when(dictionaryService.getDocumentTypes())
                .thenReturn(HttpResponse.ok(Single.just(responseMessage)));

        DictionaryController controller = new DictionaryController(dictionaryService);

        HttpResponse<Single<DocumentTypesResponseMessage>> response = controller.getDocumentTypes();

        assertEquals(response.getStatus(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertNull(response.getBody().get().blockingGet().getErrorDto());
        assertNotNull(response.getBody().get().blockingGet().getDocumentTypeDtoList());
    }

    @Test
    public void getDocumentTypesWithErrorTest() {

        DocumentTypesResponseMessage responseMessage = new DocumentTypesResponseMessage();
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(1);
        responseMessage.setErrorDto(errorDto);

        DictionaryService dictionaryService = Mockito.mock(DictionaryService.class);
        Mockito.when(dictionaryService.getDocumentTypes())
                .thenReturn(HttpResponse.serverError(Single.just(responseMessage)));

        DictionaryController controller = new DictionaryController(dictionaryService);

        HttpResponse<Single<DocumentTypesResponseMessage>> response = controller.getDocumentTypes();

        assertNotNull(response.getBody());
        assertNotNull(response.getBody().get().blockingGet().getErrorDto());
        assertEquals(1, response.getBody().get().blockingGet().getErrorDto().getCode());
        assertNull(response.getBody().get().blockingGet().getDocumentTypeDtoList());
    }

    @Test
    public void getInsuranceBlankTypesWithoutErrorTest() {

        InsuranceBlankTypesResponseMessage responseMessage = new InsuranceBlankTypesResponseMessage();
        responseMessage.setInsuranceBlankTypeDtoList(new ArrayList<>());

        DictionaryService dictionaryService = Mockito.mock(DictionaryService.class);

        Mockito.when(dictionaryService.getInsuranceBlankTypes())
                .thenReturn(HttpResponse.ok(Single.just(responseMessage)));

        DictionaryController controller = new DictionaryController(dictionaryService);

        HttpResponse<Single<InsuranceBlankTypesResponseMessage>> response = controller.getInsuranceBlankTypes();

        assertEquals(response.getStatus(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertNull(response.getBody().get().blockingGet().getErrorDto());
        assertNotNull(response.getBody().get().blockingGet().getInsuranceBlankTypeDtoList());
    }

    @Test
    public void getInsuranceBlankTypesWithErrorTest() {

        InsuranceBlankTypesResponseMessage responseMessage = new InsuranceBlankTypesResponseMessage();
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(1);
        responseMessage.setErrorDto(errorDto);

        DictionaryService dictionaryService = Mockito.mock(DictionaryService.class);
        Mockito.when(dictionaryService.getInsuranceBlankTypes())
                .thenReturn(HttpResponse.serverError(Single.just(responseMessage)));

        DictionaryController controller = new DictionaryController(dictionaryService);

        HttpResponse<Single<InsuranceBlankTypesResponseMessage>> response = controller.getInsuranceBlankTypes();

        assertNotNull(response.getBody());
        assertNotNull(response.getBody().get().blockingGet().getErrorDto());
        assertEquals(1, response.getBody().get().blockingGet().getErrorDto().getCode());
        assertNull(response.getBody().get().blockingGet().getInsuranceBlankTypeDtoList());
    }
}
