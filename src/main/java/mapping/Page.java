package mapping;

import java.util.List;

/**
 * Модель данных
 * @author Константин Рыбаков
 */
public class Page {

    private String page;
    private List<User> data;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }
}
