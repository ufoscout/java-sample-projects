package restgwt.client.gwtp;

import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.DelayedBindRegistry;

import restgwt.client.gwtp.config.MyGinjector;

public class GwtpEntryPoint {

	public final MyGinjector ginjector = GWT.create(MyGinjector.class);

	public void onModuleLoad() {
		DelayedBindRegistry.bind(ginjector);
		ginjector.getPlaceManager().revealCurrentPlace();
	}

}
