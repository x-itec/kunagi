package scrum.client.impediments;

public class DeleteImpedimentAction extends GDeleteImpedimentAction {

	public DeleteImpedimentAction(Impediment impediment) {
		super(impediment);
	}

	@Override
	public String getLabel() {
		return "Delete";
	}

	@Override
	public String getTooltip() {
		return "Delete this impediment.";
	}

	@Override
	public boolean isExecutable() {
		return getProject().isPig(getUser());
	}

	@Override
	protected void onExecute() {
		getProject().deleteImpediment(impediment);
	}

}