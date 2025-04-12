package org.example.menu.action.parser;

import org.example.menu.action.item.MenuItem;

public interface MenuActionParser {
    boolean matches(String raw);
    MenuItem parse(String raw);
}

