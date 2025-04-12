package org.example.menu.action.parser.impl;

import org.example.menu.action.item.MenuItem;
import org.example.menu.action.item.impl.MessageItem;
import org.example.menu.action.parser.MenuActionParser;

public class MessageParser implements MenuActionParser {

    @Override
    public boolean matches(String raw) {
        return raw.startsWith("[message]");
    }

    @Override
    public MenuItem parse(String raw) {
        String msg = raw.replaceFirst("\\[message\\]", "").trim();
        return new MessageItem(msg);
    }
}

