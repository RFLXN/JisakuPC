package command;
import context.RequestContext;

import java.util.Properties;
import java.util.ResourceBundle;


public abstract class CommandFactory {
    public static AbstractCommand getCommand(RequestContext requestContext) throws CommandException {

        AbstractCommand command = null;

        try {
            ResourceBundle bundle = ResourceBundle.getBundle("resources.commands");
            String name = bundle.getString(requestContext.getCommandPath());

            command = (AbstractCommand)Class.forName(name).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new CommandException(e);
        }

        return command;
    }
}
