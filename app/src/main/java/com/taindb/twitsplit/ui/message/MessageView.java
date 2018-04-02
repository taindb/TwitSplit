package com.taindb.twitsplit.ui.message;

import java.util.List;

/**
 * Copyright (C) 2018, Taindb.
 *
 * @author taindb
 * @since 4/2/18
 */

public interface MessageView {
    void appendMessage(List<String> messageData);

    void showError(String error);
}
