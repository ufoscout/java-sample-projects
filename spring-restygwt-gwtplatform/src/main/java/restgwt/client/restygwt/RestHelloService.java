package restgwt.client.restygwt;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestService;
import org.fusesource.restygwt.client.RestServiceProxy;

import restgwt.shared.RestURLPath;

import com.google.gwt.core.client.GWT;

public interface RestHelloService extends RestService {

	public static class New {
		private static RestHelloService instance;
		public static RestHelloService get() {
			if (instance == null) {
				instance = GWT.create(RestHelloService.class);
			}
			Resource resource = new Resource(GWT.getModuleBaseURL().replace(GWT.getModuleName() + "/", "") + RestURLPath.BASE + RestURLPath.HELLO_CONTROLLER );
			((RestServiceProxy) instance).setResource(resource);

			return instance;
		}
	}


	@POST
	@Path(RestURLPath.HELLO_CONTROLLER_LOAD_INFO)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void getInfoAsync(UserData userData, MethodCallback<UserElaboratedData> callback);

}
