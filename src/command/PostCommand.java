package command;

import bean.Build;
import bean.UserFlag;
import context.ResponseContext;
import db.dao.DAOException;
import db.dao.build.BuildDao;
import db.dao.factory.AbstractDaoFactory;

import java.util.List;

public class PostCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        List<Build> posts = null;

        try {
            AbstractDaoFactory daoFactory = AbstractDaoFactory.getFactory();
            // 0218 坂入 「投稿」ページでビルドリストを選択する為にレスポンス内容を変更
//            PostDao dao = daoFactory.getPostsDao();
//            posts = dao.getAllPosts();
            BuildDao bld_dao = daoFactory.getBuildDao();
            UserFlag user = (UserFlag) getRequestContext().getSessionAttribute("loginFlag");
            String userno = user.getUserNo();
            posts = bld_dao.getUserBuilds(userno);

        } catch (DAOException e) {
            throw new CommandException(e);
        }

        responseContext.setResult(posts);
        responseContext.setTarget("post");

        return responseContext;
    }
}
