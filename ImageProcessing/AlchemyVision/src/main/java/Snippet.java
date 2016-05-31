import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;
import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyVision;
import com.ibm.watson.developer_cloud.alchemy.v1.model.ImageKeywords;
//After deployment go to the relative URI to test the functionality.
//You would see a form to provide the input values.
@WebServlet("/")
//After deployment go to the relative URI to test the functionality.
//You would see a form to provide the input values.
public class Snippet extends SuperGlue {
	
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args)
			throws MalformedURLException, URISyntaxException, IllegalArgumentException, IllegalAccessException {
		Snippet myclass = new Snippet();
		Parameters params = myclass.new Parameters();
		params.setApiKey("913f155354acfc4810935b58249e5edefa63f9ba");
		//****** Process method contains the key logic ******
		Object processResult = myclass.process(((Parameters) params));
		System.out.println(new Gson().toJson(processResult));
	}
	
	public class Parameters {
		String apiKey;
		public String getApiKey() { return apiKey; }
		public void setApiKey(String apiKey) { this.apiKey = apiKey; }
	}
	
	@Override
	protected Object process(Object myBean) {
		// TODO Auto-generated method stub
		AlchemyVision service = new AlchemyVision();
		URL imageUrl = null;
		try {
			imageUrl = new URI("http://images.indianexpress.com/2015/02/david_beckham-759.jpg").toURL();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.setApiKey(((Parameters) myBean).getApiKey());
		Boolean forceShowAll = false;
		Boolean knowledgeGraph = false;
		ImageKeywords keywords = service.getImageKeywords(imageUrl, forceShowAll, knowledgeGraph);
		
		return keywords.toString();
	}

	@Override
	protected Object getParameters() {
		// TODO Auto-generated method stub
		return new Parameters();
	}
}
