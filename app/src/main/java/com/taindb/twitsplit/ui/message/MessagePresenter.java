package com.taindb.twitsplit.ui.message;

import com.taindb.twitsplit.MainApplication;
import com.taindb.twitsplit.R;
import com.taindb.twitsplit.ui.BasePresenter;
import com.taindb.twitsplit.utils.StringUtils;

import java.util.List;

import static com.taindb.twitsplit.utils.StringUtils.SplitMessageException.NON_WHITESPACE_LIMIT_CHARACTER;
import static com.taindb.twitsplit.utils.StringUtils.SplitMessageException.WORD_LENGTH_OVER_LIMIT_CHARACTER;

/**
 * Copyright (C) 2018, Taindb.
 *
 * @author taindb
 * @since 4/2/18
 */

public class MessagePresenter extends BasePresenter<MessageView> {
    private static final int CHARACTER_LIMIT = 50;


    public MessagePresenter() {

    }


    public synchronized void sendMessage(String message) {
        try {
            List<String> list = StringUtils.splitMessage(message, CHARACTER_LIMIT);
            appendMessage(list);
        } catch (StringUtils.SplitMessageException e) {
            e.printStackTrace();
            handleError(e);
        }
    }


    /**
     * notify message list for view adapter to display
     */
    private void appendMessage(List<String> list) {
        if (!isViewDetached()) {
            mView.appendMessage(list);
        }
    }

    private void handleError(StringUtils.SplitMessageException e) {
        if (e == null) {
            return;
        }

        if ((e.getErrorCode() == NON_WHITESPACE_LIMIT_CHARACTER)
                || (e.getErrorCode() == WORD_LENGTH_OVER_LIMIT_CHARACTER)) {
            showError(MainApplication.getInstance().getString(R.string.non_whitespace_error_message, CHARACTER_LIMIT));
        }

        showError(MainApplication.getInstance().getString(R.string.error_occurred_send_message));
    }

    private void showError(String error) {
        if (!isViewDetached()) {
            mView.showError(error);
        }
    }

}
