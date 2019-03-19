
package merchant.com.our.nextlounge.model;

import java.util.List;
import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class FoodModel {

    @Expose
    private List<Menu> menu;
    @Expose
    private String status;

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
