// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.GwtEntityGenerator










package scrum.client.estimation;

import java.util.*;
import ilarkesto.core.base.Utl;
import ilarkesto.core.logging.Log;
import scrum.client.common.*;
import ilarkesto.gwt.client.*;

public abstract class GRequirementEstimationVote
            extends scrum.client.common.AScrumGwtEntity {

    protected scrum.client.Dao getDao() {
        return scrum.client.Dao.get();
    }

    @Override
    protected void doPersist() {
        getDao().createRequirementEstimationVote((RequirementEstimationVote)this);
    }

    public GRequirementEstimationVote() {
    }

    public GRequirementEstimationVote(Map data) {
        super(data);
        updateProperties(data);
    }

    public static final String ENTITY_TYPE = "requirementEstimationVote";

    @Override
    public final String getEntityType() {
        return ENTITY_TYPE;
    }

    // --- requirement ---

    private String requirementId;

    public final scrum.client.project.Requirement getRequirement() {
        if (requirementId == null) return null;
        return getDao().getRequirement(this.requirementId);
    }

    public final boolean isRequirementSet() {
        return requirementId != null;
    }

    public final RequirementEstimationVote setRequirement(scrum.client.project.Requirement requirement) {
        String id = requirement == null ? null : requirement.getId();
        if (equals(this.requirementId, id)) return (RequirementEstimationVote) this;
        this.requirementId = id;
        propertyChanged("requirementId", ilarkesto.core.persistance.Persistence.propertyAsString(this.requirementId));
        return (RequirementEstimationVote)this;
    }

    public final boolean isRequirement(scrum.client.project.Requirement requirement) {
        String id = requirement==null ? null : requirement.getId();
        return equals(this.requirementId, id);
    }

    // --- user ---

    private String userId;

    public final scrum.client.admin.User getUser() {
        if (userId == null) return null;
        return getDao().getUser(this.userId);
    }

    public final boolean isUserSet() {
        return userId != null;
    }

    public final RequirementEstimationVote setUser(scrum.client.admin.User user) {
        String id = user == null ? null : user.getId();
        if (equals(this.userId, id)) return (RequirementEstimationVote) this;
        this.userId = id;
        propertyChanged("userId", ilarkesto.core.persistance.Persistence.propertyAsString(this.userId));
        return (RequirementEstimationVote)this;
    }

    public final boolean isUser(scrum.client.admin.User user) {
        String id = user==null ? null : user.getId();
        return equals(this.userId, id);
    }

    // --- estimatedWork ---

    private java.lang.Float estimatedWork ;

    public final java.lang.Float getEstimatedWork() {
        return this.estimatedWork ;
    }

    public final RequirementEstimationVote setEstimatedWork(java.lang.Float estimatedWork) {
        if (isEstimatedWork(estimatedWork)) return (RequirementEstimationVote)this;
        this.estimatedWork = estimatedWork ;
        propertyChanged("estimatedWork", ilarkesto.core.persistance.Persistence.propertyAsString(this.estimatedWork));
        return (RequirementEstimationVote)this;
    }

    public final boolean isEstimatedWork(java.lang.Float estimatedWork) {
        return equals(this.estimatedWork, estimatedWork);
    }

    private transient EstimatedWorkModel estimatedWorkModel;

    public EstimatedWorkModel getEstimatedWorkModel() {
        if (estimatedWorkModel == null) estimatedWorkModel = createEstimatedWorkModel();
        return estimatedWorkModel;
    }

    protected EstimatedWorkModel createEstimatedWorkModel() { return new EstimatedWorkModel(); }

    protected class EstimatedWorkModel extends ilarkesto.gwt.client.editor.AFloatEditorModel {

        @Override
        public String getId() {
            return "RequirementEstimationVote_estimatedWork";
        }

        @Override
        public java.lang.Float getValue() {
            return getEstimatedWork();
        }

        @Override
        public void setValue(java.lang.Float value) {
            setEstimatedWork(value);
        }

        @Override
        protected void onChangeValue(java.lang.Float oldValue, java.lang.Float newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- update properties by map ---

    public void updateProperties(Map<String, String> properties) {
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            String property = entry.getKey();
            if (property.equals("id")) continue;
            String value = entry.getValue();
            if (property.equals("requirementId")) requirementId = ilarkesto.core.persistance.Persistence.parsePropertyReference(value);
            if (property.equals("userId")) userId = ilarkesto.core.persistance.Persistence.parsePropertyReference(value);
            if (property.equals("estimatedWork")) estimatedWork = ilarkesto.core.persistance.Persistence.parsePropertyFloat(value);
        }
        updateLastModified();
    }

    @Override
    public void storeProperties(Map<String, String> properties) {
        super.storeProperties(properties);
        properties.put("requirementId", ilarkesto.core.persistance.Persistence.propertyAsString(this.requirementId));
        properties.put("userId", ilarkesto.core.persistance.Persistence.propertyAsString(this.userId));
        properties.put("estimatedWork", ilarkesto.core.persistance.Persistence.propertyAsString(this.estimatedWork));
    }

}