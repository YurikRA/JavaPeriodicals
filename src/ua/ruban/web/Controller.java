package ua.ruban.web;

import org.apache.log4j.Logger;
import ua.ruban.Path;
import ua.ruban.exception.AppException;
import ua.ruban.web.command.Command;
import ua.ruban.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Main servlet controller.
 * 
 * @author Y.Ruban
 * 
 */
public class Controller extends HttpServlet {
	
	private static final long serialVersionUID = 2423353715955164816L;

	private static final Logger log = Logger.getLogger(Controller.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            process(request, response);
        } catch (AppException e) {
            e.printStackTrace();
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            process(request, response);
        } catch (AppException e) {
            e.printStackTrace();
        }
    }


	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
		
		log.debug("Controller starts");

		// extract command name from the request
		String commandName = request.getParameter("command");
		log.trace("Request parameter: command --> " + commandName);

		// obtain command object by its name
		Command command = CommandContainer.get(commandName);
		log.trace("Obtained command --> " + command);

		// execute command and get forward address
		String forward = Path.PAGE_ERROR_PAGE;
		try {
			forward = command.execute(request, response);
		} catch (AppException ex) {
			request.setAttribute("errorMessage", ex.getMessage());
		}
		log.trace("Forward address --> " + forward);

		log.debug("Controller finished, now go to forward address --> " + forward);

		// go to forward
		request.getRequestDispatcher(forward).forward(request, response);
	}

}