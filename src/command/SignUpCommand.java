package command;

import bean.UserFlag;
import context.RequestContext;
import context.ResponseContext;
import db.dao.DAOException;
import db.dao.factory.AbstractDaoFactory;
import db.dao.user.UserDao;

import javax.servlet.http.HttpServletRequest;

public class SignUpCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        RequestContext requestContext = getRequestContext();

        String id = requestContext.getParameter("id")[0];
        String password = requestContext.getParameter("password")[0];

        try {
            AbstractDaoFactory daoFactory = AbstractDaoFactory.getFactory();
            UserDao userDao = daoFactory.getUserDao();

            userDao.addUser(id, password);

            UserFlag user = userDao.getUser(id, password);

            HttpServletRequest servletRequest = (HttpServletRequest) (requestContext.getRequest());
            servletRequest.getSession().setAttribute("loginFlag", user);

            responseContext.setResult(user);
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        responseContext.setTarget("signup-result");

        return responseContext;
    }
}
