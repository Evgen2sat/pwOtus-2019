package su.medsoft.mis.er.terminal.services.interfaces;

import io.micronaut.http.HttpResponse;
import io.reactivex.Single;
import su.medsoft.mis.er.terminal.responseMessages.PostResponseMessage;

public interface PostService {
    HttpResponse<Single<PostResponseMessage>> getPosts(long moId, Long separateDepartId, String channel);
}
