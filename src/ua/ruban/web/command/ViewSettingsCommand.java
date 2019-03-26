package ua.ruban.web.command;

import org.apache.log4j.Logger;
import ua.ruban.Path;
import ua.ruban.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewSettingsCommand extends Command {

    private static final long serialVersionUID = -3453136887778065376L;

    private static final Logger log = Logger.getLogger(ViewSettingsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        log.debug("Command starts");

        log.debug("Command finished");
        return Path.PAGE_SETTINGS;
    }
}
