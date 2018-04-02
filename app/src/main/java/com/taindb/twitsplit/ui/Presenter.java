package com.taindb.twitsplit.ui;

/**
 * Copyright (C) 2018, Taindb.
 *
 * @author taindb
 * @since 4/2/18
 */

public interface Presenter<V> {
    void attachView(V view);

    void detachView();

    boolean isViewDetached();
}
