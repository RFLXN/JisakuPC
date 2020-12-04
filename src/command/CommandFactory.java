package command;
import context.RequestContext;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public abstract class CommandFactory {
    public static AbstractCommand getCommand(RequestContext requestContext) {

        AbstractCommand command = null;

        Properties prop = new Properties();

        try {
            prop.load(new FileInputStream("../resources/commands.properties"));
            String name = prop.getProperty(requestContext.getCommandPath());

            command = (AbstractCommand)Class.forName(name).newInstance();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
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
