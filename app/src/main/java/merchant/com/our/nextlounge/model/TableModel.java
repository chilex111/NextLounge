
package merchant.com.our.nextlounge.model;

import java.util.List;
import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class TableModel {

    @Expose
    private String status;
    @Expose
    private List<Table> tables;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

}
