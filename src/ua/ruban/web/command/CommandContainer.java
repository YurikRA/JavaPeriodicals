package ua.ruban.web.command;

import org.apache.log4j.Logger;
import ua.ruban.web.command.admin.AddCategoryCommand;
import ua.ruban.web.command.admin.EditEditionsCommand;
import ua.ruban.web.command.admin.ListAllEditionsCommand;
import ua.ruban.web.command.admin.ListUsersActionCommand;
import ua.ruban.web.command.user.ListCategoryCommand;
import ua.ruban.web.command.user.ListEditionsCommand;
import ua.ruban.web.command.user.ListSubscriptionsCommand;
import ua.ruban.web.command.user.ListUserSubscriptionsCommand;
import ua.ruban.web.command.user.Registration;
import ua.ruban.web.command.user.TopUpAccountCommand;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {

    private static final Logger log = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap<String, Command>();

    static {
        // common commands
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("noCommand", new NoCommand());
        commands.put("viewSettings", new ViewSettingsCommand());
        commands.put("updateSettings", new UpdateSettingsCommand());

        // client commands
        commands.put("listCategory", new ListCategoryCommand());
        commands.put("listEditions", new ListEditionsCommand());
        commands.put("listSubscriptions", new ListSubscriptionsCommand());
        commands.put("userSubscriptions", new ListUserSubscriptionsCommand());
        commands.put("userTopUpAccount", new TopUpAccountCommand());
        commands.put("registration", new Registration());

        // admin commands
        commands.put("listUsers", new ListUsersActionCommand());
        commands.put("listAllEditions", new ListAllEditionsCommand());
        commands.put("editEdition", new EditEditionsCommand());
        commands.put("addCategory", new AddCategoryCommand());

        log.debug("Command container was successfully initialized");
        log.trace("Number of commands --> " + commands.size());
    }

    /**
     * Returns command object with the given name.
     *
     * @param commandName
     *            Name of the command.
     * @return Command object.
     */
    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            log.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }
}
