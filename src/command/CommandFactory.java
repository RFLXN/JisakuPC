package command;
import context.RequestContext;

import java.util.Properties;
import java.util.ResourceBundle;


public abstract class CommandFactory {
    public static AbstractCommand getCommand(RequestContext requestContext) {

        AbstractCommand command = null;

        try {
            ResourceBundle bundle = ResourceBundle.getBundle("resources.commands");
            String name = bundle.getString(requestContext.getCommandPath());

            command = (AbstractCommand)Class.forName(name).newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        return command;
    }
}
