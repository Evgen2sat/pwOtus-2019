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
import su.medsoft.mis.er.terminal.responseMessages.PostResponseMessage;
import su.medsoft.mis.er.terminal.services.interfaces.PostService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

public class PostControllerTest {
    private static HttpHeaders headers;

    @BeforeClass
    public static void initHeader() {
        Map<String, String> header = new HashMap<>();
        header.put(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtb0lkIjoxLCJzeXN0ZW1JZCI6IjA1ZjA0NmRkLTI3Y2YtNDdhOS1hMjA1LWJlYmI0MTNkOGRmMSIsInN5c3RlbU5hbWUiOiLQotC10YDQvNC40L3QsNC7INCc0LXQtNCh0L7RhNGCIiwiaXNzIjoibWVkc29mdCIsInN1YiI6ItCf0LXRgtGA0LXQu9C10LLQuNGHINCh0LXRgNCz0LXQuSJ9.Uyv0SzrNwd82KGUOjiIlkVxt8CghsmTWoI6STV1tBwo");

        headers = new SimpleHttpHeaders(header, new DefaultConversionService());
    }

    @Test
    public void getPostsWithoutErrorTest() {

        PostResponseMessage responseMessage = new PostResponseMessage();
        responseMessage.setPostDtoList(new ArrayList<>());

        PostService postService = Mockito.mock(PostService.class);
        Mockito.when(postService.getPosts(Mockito.anyLong(), Mockito.any(), Mockito.anyString()))
                .thenReturn(HttpResponse.ok(Single.just(responseMessage)));


        PostController controller = new PostController(postService);
        HttpResponse<Single<PostResponseMessage>> response = controller.getPosts(headers, null, "ТЕР");

        assertEquals(response.getStatus(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertNull(response.getBody().get().blockingGet().getErrorDto());
        assertNotNull(response.getBody().get().blockingGet().getPostDtoList());
    }

    @Test
    public void getPostsWithErrorTest_withoutHeaders() {

        PostService service = Mockito.mock(PostService.class);

        PostController controller = new PostController(service);

        HttpResponse<Single<PostResponseMessage>> response = controller.getPosts(null, null, "ТЕР");

        assertNotNull(response.getBody());
        assertNotNull(response.getBody().get().blockingGet().getErrorDto());
        assertEquals(response.getBody().get().blockingGet().getErrorDto().getCode(), 1);
        assertNull(response.getBody().get().blockingGet().getPostDtoList());
    }

    @Test
    public void getPostsWithErrorTest() {

        PostResponseMessage responseMessage = new PostResponseMessage();
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(1);
        responseMessage.setErrorDto(errorDto);

        PostService service = Mockito.mock(PostService.class);

        Mockito.when(service.getPosts(Mockito.anyLong(), Mockito.any(), Mockito.anyString()))
                .thenReturn(HttpResponse.serverError(Single.just(responseMessage)));

        PostController controller = new PostController(service);

        HttpResponse<Single<PostResponseMessage>> response = controller.getPosts(headers, null, "ТЕР");

        assertNotNull(response.getBody());
        assertNotNull(response.getBody().get().blockingGet().getErrorDto());
        assertEquals(1, response.getBody().get().blockingGet().getErrorDto().getCode());
        assertNull(response.getBody().get().blockingGet().getPostDtoList());
    }
}
