package hello.gwt.server

import com.google.gwt.user.server.rpc.RemoteServiceServlet
import hello.gwt.client.GreetingService

import static extension hello.gwt.shared.FieldVerifier.*

/**
 * The server side implementation of the RPC service.
 */
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	override String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!input.validName) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}

		val userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		return '''
			Hello, «input.escapeHtml»!
			<br><br>
			I am running «servletContext.serverInfo».
			<br><br>
			It looks like you are using:<br> «userAgent.escapeHtml»
		'''.toString
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	def private String escapeHtml(String html) {
		return html?.replace("&", "&amp;")?.replace("<", "&lt;")?.replace(">", "&gt;");
	}
}