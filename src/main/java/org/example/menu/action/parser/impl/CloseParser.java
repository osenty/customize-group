package org.example.menu.action.parser.impl;

import org.example.menu.action.item.MenuItem;
import org.example.menu.action.item.impl.CloseMenuItem;
import org.example.menu.action.parser.MenuActionParser;

public class CloseParser implements MenuActionParser {

    @Override
    public boolean matches(String raw) {
        return raw.equalsIgnoreCase("[close]");
    }

    @Override
    public MenuItem parse(String raw) {
        return new CloseMenuItem();
    }
}

