package ru.itis.protocol.message;

import java.io.Serializable;

// запрос на получение страницы видосов
public class PageVideoGetRequestMessage extends Message implements Serializable {
    public final static int type = 7;
    private final int pageSize;

    private final int numberPage;

    public PageVideoGetRequestMessage(int numberPage, int pageSize) {
        super(type);
        this.numberPage = numberPage;
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getNumberPage() {
        return numberPage;
    }
}
