package command;

import bean.Build;
import bean.Product;
import context.ResponseContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class DeletePartFromBuildCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        HttpSession session = ((HttpServletRequest)(getRequestContext().getRequest())).getSession();
        Build build = (Build)session.getAttribute("build");
        int partNo = Integer.parseInt(getRequestContext().getParameter("partNo")[0]);

        List<Product> products = build.getProducts();

        for(int i=0 ; i<products.size() ; i++) {
            if(Integer.parseInt(products.get(i).getNo()) == partNo) {
                products.remove(i);
                break;
            }
        }

        build.setProducts(products);
        session.setAttribute("build", build);

        responseContext.setTarget("addbuild");
        return responseContext;
    }
}
