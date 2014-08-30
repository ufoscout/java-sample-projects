package springapp.web;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import springapp.BaseTest;
import springapp.domain.Product;
import springapp.repository.InMemoryProductDaoMock;
import springapp.service.SimpleProductManager;

/**
 * 
 * @author Francesco Cina'
 *
 * 23/ott/2010
 */
public class InventoryControllerTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	@SuppressWarnings("unchecked")
	public void testHandleRequestView() throws Exception{
        InventoryController controller = new InventoryController();
        SimpleProductManager spm = new SimpleProductManager();
        spm.setProductDao(new InMemoryProductDaoMock(new ArrayList<Product>()));
        controller.setProductManager(spm);
        //controller.setProductManager(new SimpleProductManager());
        ModelAndView modelAndView = controller.handleRequest(null, null);
        assertEquals("hello", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel());
        Map modelMap = (Map) modelAndView.getModel().get("model");
        String nowValue = (String) modelMap.get("now");
        assertNotNull(nowValue);
    }


}
