package org.example.menu.action.parser.impl;

import org.example.menu.action.item.MenuItem;
import org.example.menu.action.item.impl.ConsoleCommandItem;
import org.example.menu.action.parser.MenuActionParser;

public class ConsoleCommandParser implements MenuActionParser {

    @Override
    public boolean matches(String raw) {
        return raw.startsWith("[console]");
    }

    @Override
    public MenuItem parse(String raw) {
        String command = raw.replaceFirst("\\[console]", "").trim();
        return new ConsoleCommandItem(command);
    }
}

