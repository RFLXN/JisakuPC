package command;

import context.RequestContext;
import context.ResponseContext;
import db.dao.DAOException;
import db.dao.factory.AbstractDaoFactory;
import db.dao.user.UserDao;
import org.json.JSONObject;

public class UsedIdJsonCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        RequestContext requestContext = getRequestContext();

        String id = requestContext.getParameter("id")[0];

        JSONObject jsonData = new JSONObject();

        try {
            AbstractDaoFactory daoFactory = AbstractDaoFactory.getFactory();
            UserDao userDao = daoFactory.getUserDao();

             boolean isAlreadyUsedId = userDao.isAlreadyUsedId(id);

             jsonData.put("isAlreadyUsedId", isAlreadyUsedId);
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        responseContext.setResult(jsonData);

        return responseContext;
    }
}
