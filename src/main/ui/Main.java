package ui;

import model.EventLog;

import java.util.Iterator;

public class Main {
    private static DisplayList displayList = new DisplayList();

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                displayList.createAndShowGUI(displayList);
            }
        });
//        FinderApp friendFinder = new FinderApp();
    }
}
