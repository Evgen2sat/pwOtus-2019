package su.medsoft.mis.er.terminal.controllers;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecuredAnnotationRule;
import io.reactivex.Single;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import su.medsoft.mis.er.terminal.Application;
import su.medsoft.mis.er.terminal.dto.CreateAppointmentBodyDto;
import su.medsoft.mis.er.terminal.responseMessages.AppointmentResponseMessage;
import su.medsoft.mis.er.terminal.responseMessages.CreatedAppointmentResponseMessage;
import su.medsoft.mis.er.terminal.services.AppointmentService;

import javax.inject.Inject;
import java.text.ParseException;

@Secured(SecuredAnnotationRule.IS_AUTHENTICATED)
@Controller("${endpoints.all.path}/appointments")
public class AppointmentController {

    private static final Logger LOG = LoggerFactory.getLogger(Application.getLoggerName());

    @Inject
    private AppointmentService appointmentService;

    @Get("/patients/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<Single<AppointmentResponseMessage>> getAppointmentsByPatient(long id, @QueryValue("beginDate") String beginDate, @QueryValue("endDate") String endDate) {
        return appointmentService.getAppointmentsByPatient(id, beginDate, endDate);
    }

    @Post
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<Single<CreatedAppointmentResponseMessage>> createdAppointment(final HttpHeaders headers, @Body CreateAppointmentBodyDto createAppointmentBodyDto) {
        Long moId = null;

        try {
            JWTClaimsSet claimsJws = JWTParser.parse(headers.getAuthorization().get().replace("Bearer", "")).getJWTClaimsSet();
            moId = claimsJws.getLongClaim("moId");
        } catch (ParseException e) {
            LOG.error(e.getMessage(), e);

            CreatedAppointmentResponseMessage responseMessage = new CreatedAppointmentResponseMessage();
            responseMessage.setErrorDto(Application.getError(1, "Внутренняя ошибка сервера"));

            return Application.getResponse(responseMessage);
        }

        createAppointmentBodyDto.setAuthor("Терминал");
        createAppointmentBodyDto.setChannel("ТЕР");
        createAppointmentBodyDto.setMoId(moId);
        return appointmentService.createdAppointment(createAppointmentBodyDto);
    }
}
