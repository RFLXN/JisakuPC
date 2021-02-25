package command;

import bean.Build;
import bean.Product;
import context.ResponseContext;

import java.util.List;

public class DeletePartFromBuildCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        Build build = (Build) getRequestContext().getSessionAttribute("build");
        int partNo = Integer.parseInt(getRequestContext().getParameter("partNo")[0]);

        List<Product> products = build.getProducts();

        for (int i = 0; i < products.size(); i++) {
            if (Integer.parseInt(products.get(i).getNo()) == partNo) {
                products.remove(i);
                break;
            }
        }

        build.setProducts(products);
        getRequestContext().setSessionAttribute("build", build);

        responseContext.setTarget("addbuild");
        return responseContext;
    }
}
