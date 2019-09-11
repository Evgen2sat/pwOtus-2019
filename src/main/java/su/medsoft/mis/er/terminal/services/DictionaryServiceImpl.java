package su.medsoft.mis.er.terminal.services;

import io.micronaut.http.HttpResponse;
import io.reactivex.Single;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import su.medsoft.mis.er.terminal.Application;
import su.medsoft.mis.er.terminal.responseMessages.DocumentTypesResponseMessage;
import su.medsoft.mis.er.terminal.responseMessages.InsuranceBlankTypesResponseMessage;
import su.medsoft.mis.er.terminal.services.interfaces.DictionaryService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DictionaryServiceImpl implements DictionaryService {
    private static final Logger LOG = LoggerFactory.getLogger(Application.getLoggerName());


    private final su.medsoft.mis.er.terminal.external_services.DictionaryService dictionaryService;

    @Inject
    public DictionaryServiceImpl(su.medsoft.mis.er.terminal.external_services.DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    public HttpResponse<Single<DocumentTypesResponseMessage>> getDocumentTypes() {
        DocumentTypesResponseMessage responseMessage = null;

        try {
            responseMessage = dictionaryService.getDocumentTypes();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            responseMessage = new DocumentTypesResponseMessage();
            responseMessage.setErrorDto(Application.getError(1, "Внутренняя ошибка сервера"));
        }

        return Application.getResponse(responseMessage);
    }

    public HttpResponse<Single<InsuranceBlankTypesResponseMessage>> getInsuranceBlankTypes() {
        InsuranceBlankTypesResponseMessage responseMessage = null;

        try {
            responseMessage = dictionaryService.getInsuranceBlankTypes();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            responseMessage = new InsuranceBlankTypesResponseMessage();
            responseMessage.setErrorDto(Application.getError(1, "Внутренняя ошибка сервера"));
        }

        return Application.getResponse(responseMessage);
    }
}
