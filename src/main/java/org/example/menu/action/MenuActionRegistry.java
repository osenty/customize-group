package org.example.menu.action;

import org.example.menu.action.item.MenuItem;
import org.example.menu.action.item.impl.CompositeItem;
import org.example.menu.action.parser.MenuActionParser;
import org.example.menu.action.parser.impl.CloseParser;
import org.example.menu.action.parser.impl.ConsoleCommandParser;
import org.example.menu.action.parser.impl.MessageParser;

import java.util.ArrayList;
import java.util.List;

public class MenuActionRegistry {

    private final List<MenuActionParser> parsers = new ArrayList<>();

    public MenuActionRegistry() {
        register(new ConsoleCommandParser());
        register(new MessageParser());
        register(new CloseParser());
    }

    public void register(MenuActionParser parser) {
        parsers.add(parser);
    }

    public MenuItem parseAll(List<String> rawActions) {
        List<MenuItem> actions = new ArrayList<>();
        for (String raw : rawActions) {
            actions.add(parse(raw));
        }
        return new CompositeItem(actions);
    }

    private MenuItem parse(String raw) {
        for (MenuActionParser parser : parsers) {
            if (parser.matches(raw)) {
                return parser.parse(raw);
            }
        }
        return event -> {
            System.out.println("Произошла печалька");
        };
    }
}
