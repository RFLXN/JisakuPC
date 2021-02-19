package command;

/*
 * J2EE環境へ依存があることに注意
 *      -> セッションにログインフラグを登録するため
 */

import bean.UserFlag;
import context.ResponseContext;
import db.dao.DAOException;
import db.dao.factory.AbstractDaoFactory;
import db.dao.user.UserDao;

public class LoginProcessCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        String id = getRequestContext().getParameter("id")[0];
        String password = getRequestContext().getParameter("password")[0];

        try {
            AbstractDaoFactory daoFactory = AbstractDaoFactory.getFactory();
            UserDao dao = daoFactory.getUserDao();

            UserFlag user = dao.getUser(id, password);

            if (user.isCorrectUser()) {
                getRequestContext().setSessionAttribute("loginFlag", user);

                String source = (String) getRequestContext().getSessionAttribute("source");

                if (source == null) {
                    responseContext.setTarget("index");
                } else {
                    if (source.equals("")) {
                        responseContext.setTarget("index");
                    } else {
                        responseContext.setTarget(source);
                    }
                }

            } else {
                responseContext.setResult(user);
                responseContext.setTarget("login");
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        return responseContext;
    }
}
