package su.medsoft.mis.er.terminal.services;

import io.micronaut.http.HttpResponse;
import io.reactivex.Single;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import su.medsoft.mis.er.terminal.Application;
import su.medsoft.mis.er.terminal.external_services.ErService;
import su.medsoft.mis.er.terminal.responseMessages.PostResponseMessage;
import su.medsoft.mis.er.terminal.services.interfaces.PostService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PostServiceImpl implements PostService {
    private static final Logger LOG = LoggerFactory.getLogger(Application.getLoggerName());

    @Inject
    private ErService erService;

    @Override
    public HttpResponse<Single<PostResponseMessage>> getPosts(long moId, Long separateDepartId, String channel) {
        PostResponseMessage responseMessage = null;

        try {
            responseMessage = erService.getPosts(moId, separateDepartId, channel);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            responseMessage = new PostResponseMessage();
            responseMessage.setErrorDto(Application.getError(1, "Внутренняя ошибка сервера"));
        }

        return Application.getResponse(responseMessage);
    }
}
