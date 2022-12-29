package com.github.eikefab.libs.menu;

import java.util.Map;

public class PageMenuContext extends MenuContext {

    private final int lastPage;
    private int currentPage;

    protected PageMenuContext(Menu menu, int lastPage, Map<String, Object> data) {
        super(menu, data);

        this.currentPage = 0;
        this.lastPage = lastPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

}
