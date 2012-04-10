package hello.gwt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import hello.gwt.client.GreetingService;
import hello.gwt.shared.FieldVerifier;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.BooleanExtensions;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("all")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {
  public String greetServer(final String input) throws IllegalArgumentException {
    boolean _isValidName = FieldVerifier.isValidName(input);
    boolean _not = BooleanExtensions.operator_not(_isValidName);
    if (_not) {
      IllegalArgumentException _illegalArgumentException = new IllegalArgumentException(
        "Name must be at least 4 characters long");
      throw _illegalArgumentException;
    }
    HttpServletRequest _threadLocalRequest = this.getThreadLocalRequest();
    final String userAgent = _threadLocalRequest.getHeader("User-Agent");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Hello, ");
    String _escapeHtml = this.escapeHtml(input);
    _builder.append(_escapeHtml, "");
    _builder.append("!");
    _builder.newLineIfNotEmpty();
    _builder.append("<br><br>");
    _builder.newLine();
    _builder.append("I am running ");
    ServletContext _servletContext = this.getServletContext();
    String _serverInfo = _servletContext.getServerInfo();
    _builder.append(_serverInfo, "");
    _builder.append(".");
    _builder.newLineIfNotEmpty();
    _builder.append("<br><br>");
    _builder.newLine();
    _builder.append("It looks like you are using:<br> ");
    String _escapeHtml_1 = this.escapeHtml(userAgent);
    _builder.append(_escapeHtml_1, "");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  /**
   * Escape an html string. Escaping data received from the client helps to
   * prevent cross-site script vulnerabilities.
   * 
   * @param html the html string to escape
   * @return the escaped string
   */
  private String escapeHtml(final String html) {
    String _replace = html==null?(String)null:html.replace("&", "&amp;");
    String _replace_1 = _replace==null?(String)null:_replace.replace("<", "&lt;");
    return _replace_1==null?(String)null:_replace_1.replace(">", "&gt;");
  }
}
