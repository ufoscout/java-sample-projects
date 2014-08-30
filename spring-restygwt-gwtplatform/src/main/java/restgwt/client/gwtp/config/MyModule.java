package restgwt.client.gwtp.config;

import restgwt.client.gwtp.view.MainPagePresenter;
import restgwt.client.gwtp.view.MainPageView;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

public class MyModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		install(new DefaultModule(MyPlaceManager.class));

		// Presenters
		bindPresenter(MainPagePresenter.class,
				MainPagePresenter.MyView.class,
				MainPageView.class,
				MainPagePresenter.MyProxy.class);

	}
}
