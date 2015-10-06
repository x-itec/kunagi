// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.GwtServiceCallGenerator










package scrum.client.core;

import java.util.*;

@com.google.gwt.user.client.rpc.RemoteServiceRelativePath("scrum")
public class SleepServiceCall
            extends ilarkesto.gwt.client.AServiceCall<scrum.client.DataTransferObject> {

    private static scrum.client.ScrumServiceAsync service;

    long millis;

    public SleepServiceCall(long millis) {
        this.millis = millis;
    }

    @Override
    protected void onExecute(int conversationNumber, com.google.gwt.user.client.rpc.AsyncCallback<scrum.client.DataTransferObject> callback) {
        if (service==null) {
            service = (scrum.client.ScrumServiceAsync) com.google.gwt.core.client.GWT.create(scrum.client.ScrumService.class);
            initializeService(service, "scrum");
        }
        service.sleep(conversationNumber, millis, callback);
    }

    @Override
    public String toString() {
        return "sleep";
    }

}