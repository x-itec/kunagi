// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.GwtServiceCallGenerator










package scrum.client.issues;

import java.util.*;

@com.google.gwt.user.client.rpc.RemoteServiceRelativePath("scrum")
public class RequestAcceptedIssuesServiceCall
            extends ilarkesto.gwt.client.AServiceCall<scrum.client.DataTransferObject> {

    private static scrum.client.ScrumServiceAsync service;


    public RequestAcceptedIssuesServiceCall() {
    }

    @Override
    protected void onExecute(int conversationNumber, com.google.gwt.user.client.rpc.AsyncCallback<scrum.client.DataTransferObject> callback) {
        if (service==null) {
            service = (scrum.client.ScrumServiceAsync) com.google.gwt.core.client.GWT.create(scrum.client.ScrumService.class);
            initializeService(service, "scrum");
        }
        service.requestAcceptedIssues(conversationNumber, callback);
    }

    @Override
    public String toString() {
        return "requestAcceptedIssues";
    }

}