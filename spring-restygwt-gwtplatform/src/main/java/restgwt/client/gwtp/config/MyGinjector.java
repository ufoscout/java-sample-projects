package restgwt.client.gwtp.config;

import restgwt.client.gwtp.view.MainPagePresenter;

import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

@GinModules({ MyModule.class })
public interface MyGinjector extends Ginjector {

	PlaceManager getPlaceManager();
	EventBus getEventBus();
	AsyncProvider<MainPagePresenter> getMainPagePresenter();

}