
package merchant.com.our.nextlounge.model;

import java.util.List;
import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class DrinkModel {

    @Expose
    private List<Drink> drinks;
    @Expose
    private String status;

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
