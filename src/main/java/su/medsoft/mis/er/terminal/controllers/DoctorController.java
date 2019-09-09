package su.medsoft.mis.er.terminal.controllers;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecuredAnnotationRule;
import io.reactivex.Single;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import su.medsoft.mis.er.terminal.Application;
import su.medsoft.mis.er.terminal.responseMessages.DoctorResponseMessage;
import su.medsoft.mis.er.terminal.services.DoctorService;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.text.ParseException;

@Secured(SecuredAnnotationRule.IS_AUTHENTICATED)
@Controller("${endpoints.all.path}/doctors")
public class DoctorController {
    private static final Logger LOG = LoggerFactory.getLogger(Application.getLoggerName());

    @Inject
    private DoctorService doctorService;

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<Single<DoctorResponseMessage>> getDoctors(final HttpHeaders headers, @QueryValue("postCode") String postCode, @QueryValue("source") String source, @Nullable @QueryValue("departmentId") Long departmentId) {
        Long moId = null;

        try {
            JWTClaimsSet claimsJws = JWTParser.parse(headers.getAuthorization().get().replace("Bearer", "")).getJWTClaimsSet();
            moId = claimsJws.getLongClaim("moId");

            if(departmentId == null) {
                departmentId = claimsJws.getLongClaim("departmentId");
            }
        } catch (ParseException e) {
            LOG.error(e.getMessage(), e);

            DoctorResponseMessage responseMessage = new DoctorResponseMessage();
            responseMessage.setErrorDto(Application.getError(1, "Внутренняя ошибка сервера"));

            return HttpResponse.badRequest(Single.just(responseMessage));
        }

        return doctorService.getDoctors(moId, postCode, source, departmentId);
    }
}
