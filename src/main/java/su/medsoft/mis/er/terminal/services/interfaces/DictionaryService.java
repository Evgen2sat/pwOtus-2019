package su.medsoft.mis.er.terminal.services.interfaces;

import io.micronaut.http.HttpResponse;
import io.reactivex.Single;
import su.medsoft.mis.er.terminal.responseMessages.DocumentTypesResponseMessage;
import su.medsoft.mis.er.terminal.responseMessages.InsuranceBlankTypesResponseMessage;

public interface DictionaryService {
    HttpResponse<Single<DocumentTypesResponseMessage>> getDocumentTypes();

    HttpResponse<Single<InsuranceBlankTypesResponseMessage>> getInsuranceBlankTypes();
}
