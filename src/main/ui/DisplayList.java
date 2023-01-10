package ui;

import model.Event;
import model.EventLog;
import model.ListOfStudent;
import model.Student;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

// Referenced the ListDemo and IconDemo projects provided in the Phase 3 description.

public class DisplayList extends JPanel implements ListSelectionListener {
    private JList<Student> list;
    private ListOfStudent generalList;

    private static final String addStudentString = "Add Student";
    private static final String removeStudentString = "Remove Student";
    private static final String loadString = "Load List Of Students";
    private static final String saveString = "Save List Of Students";
    private static final String residenceButtonString = "Filter By Residence";
    private static final String interestButtonString = "Filter By Interest";
    private JScrollPane listScrollPane;
    private JButton addButton;
    private JButton removeButton;
    private JButton loadButton;
    private JButton saveButton;
    private JButton residenceButton;
    private JTextField residenceFilter;
    private JButton interestButton;
    private JTextField interestFilter;
    private JTextField studentName;
    private JTextField residence;
    private JTextField interest1;
    private JTextField interest2;
    private JTextField username;
    private static final String JSON_STORE = "./data/students.json";
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);
    private AddStudentListener addStudentListener;
    private LoadListListener loadListListener;
    private SaveListListener saveListListener;
    private ResidenceFilterListener residenceFilterListener;
    private InterestFilterListener interestFilterListener;

    public DisplayList() {
        super(new BorderLayout());

        initializeGeneralList();

        initializeJList(generalList);

        listScrollPane = new JScrollPane(list);

        initializeAddButton();

        initializeRemoveButton();

        initLoadButton();

        initSaveButton();

        initJTextFields();

        initInterestFilter();

        initResidenceFilter();

        JPanel buttonPane = initButtonPanel();
        JPanel bottomButtonPane = initBottomButtonPanel();

        add(listScrollPane, BorderLayout.PAGE_START);
        add(buttonPane, BorderLayout.PAGE_END);
        add(bottomButtonPane, BorderLayout.CENTER);
    }

    private JPanel initBottomButtonPanel() {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(studentName);
        buttonPane.add(residence);
        buttonPane.add(interest1);
        buttonPane.add(interest2);
        buttonPane.add(username);
        buttonPane.add(addButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(interestFilter);
        buttonPane.add(interestButton);
        return buttonPane;
    }

    private JPanel initButtonPanel() {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(removeButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(loadButton);
        buttonPane.add(saveButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(residenceFilter);
        buttonPane.add(residenceButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return buttonPane;
    }

    private void initJTextFields() {
        studentName = new JTextField("Name", 5);
        studentName.addActionListener(addStudentListener);
        studentName.getDocument().addDocumentListener(addStudentListener);
        residence = new JTextField("Res #", 5);
        residence.addActionListener(addStudentListener);
        residence.getDocument().addDocumentListener(addStudentListener);
        interest1 = new JTextField("Interest", 5);
        interest1.addActionListener(addStudentListener);
        interest1.getDocument().addDocumentListener(addStudentListener);
        interest2 = new JTextField("Interest", 5);
        interest2.addActionListener(addStudentListener);
        interest2.getDocument().addDocumentListener(addStudentListener);
        username = new JTextField("Username", 5);
        username.addActionListener(addStudentListener);
        username.getDocument().addDocumentListener(addStudentListener);
    }

    private void initResidenceFilter() {
        residenceButton = new JButton(residenceButtonString);
        residenceFilterListener = new ResidenceFilterListener(residenceButton);

        residenceButton.setActionCommand(residenceButtonString);
        residenceButton.addActionListener(residenceFilterListener);

        residenceFilter = new JTextField("", 5);
        residenceFilter.addActionListener(residenceFilterListener);
        residenceFilter.getDocument().addDocumentListener(residenceFilterListener);
    }

    private void initInterestFilter() {
        interestButton = new JButton(interestButtonString);
        interestFilterListener = new InterestFilterListener(interestButton);

        interestButton.setActionCommand(interestButtonString);
        interestButton.addActionListener(interestFilterListener);

        interestFilter = new JTextField("", 5);
        interestFilter.addActionListener(interestFilterListener);
        interestFilter.getDocument().addDocumentListener(interestFilterListener);
    }

    private void initSaveButton() {
        saveButton = new JButton(saveString);
        saveListListener = new SaveListListener(saveButton);
        saveButton.setActionCommand(saveString);
        saveButton.addActionListener(saveListListener);
    }

    private void initLoadButton() {
        loadButton = new JButton(loadString);
        loadListListener = new LoadListListener(loadButton);
        loadButton.setActionCommand(loadString);
        loadButton.addActionListener(loadListListener);
    }

    private void initializeRemoveButton() {
        removeButton = new JButton(removeStudentString);
        removeButton.setActionCommand(removeStudentString);
        removeButton.addActionListener(new RemoveStudentListener());
    }

    private void initializeAddButton() {
        addButton = new JButton(addStudentString);
        addStudentListener = new AddStudentListener(addButton);
        addButton.setActionCommand(addStudentString);
        addButton.addActionListener(addStudentListener);
        addButton.setEnabled(false);
    }

    private void initializeJList(ListOfStudent student) {
        list = new JList(student);
        list.setSelectedIndex(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(10);
    }

    private void initializeGeneralList() {
        generalList = new ListOfStudent();
        Student beta1 = new Student("Grady",
                2, "skiing", "japan", "@gradyboger");
        Student beta2 = new Student("Jacob",
                2, "backcountry", "skiing", "@jacobsurks31");
        Student beta3 = new Student("Oli",
                1, "trees", "skiing", "@oli_olson");
        Student beta4 = new Student("Harper",
                2, "skiing", "hiking", "@harper.lea");
        Student beta5 = new Student("Ben",
                1, "skiing", "california", "@wen_berner");
        Student beta6 = new Student("Griffin",
                2, "skiing", "bucs", "@g.velichko13");
        generalList.addStudent(beta1, true);
        generalList.addStudent(beta2, true);
        generalList.addStudent(beta3, true);
        generalList.addStudent(beta4, true);
        generalList.addStudent(beta5, true);
        generalList.addStudent(beta6, true);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                removeButton.setEnabled(false);
            } else {
                removeButton.setEnabled(true);
            }
        }
    }

    static void createAndShowGUI(JComponent newContentPane) {
        JFrame frame = new JFrame("First Year Finder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);

                for (Event next : EventLog.getInstance()) {
                    System.out.println(next.toString());
                }
            }
        });


//        JComponent newContentPane = new DisplayList();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        frame.pack();
        frame.setVisible(true);
    }

    public class InterestFilterListener implements ActionListener, DocumentListener {
        private JButton button;
        private boolean alreadyEnabled = false;

        public InterestFilterListener(JButton button) {
            this.button = button;
        }

        @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
        @Override
        public void actionPerformed(ActionEvent e) {
            String interest = interestFilter.getText();

            if (interest.equals("")) {
                somethingWrong(interestFilter);
                return;
            }

            ListOfStudent filteredList = generalList.filterByInterest(interest);
            if (!(filteredList.isEmpty())) {
                generalList.removeAllStudents();

                for (Student s : filteredList.getStudentList()) {
                    generalList.addStudent(s, true);
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "There are no students with an interest in " + interest.toLowerCase(),
                        "Filter Error", JOptionPane.INFORMATION_MESSAGE, null);
            }

            interestFilter.requestFocusInWindow();
            interestFilter.setText("");
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        public void somethingWrong(JTextField field) {
            Toolkit.getDefaultToolkit().beep();
            field.requestFocusInWindow();
            field.selectAll();
        }

        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    public class ResidenceFilterListener implements ActionListener, DocumentListener {
        private JButton button;
        private boolean alreadyEnabled = false;

        public ResidenceFilterListener(JButton button) {
            this.button = button;
        }

        @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
        @Override
        public void actionPerformed(ActionEvent e) {
            String residence = residenceFilter.getText();

            if (residence.equals("")) {
                somethingWrong(residenceFilter);
                return;
            }

            try {
                int resNum = Integer.parseInt(residence);
                if (resNum > 3 | resNum <= 0) {
                    throw new Exception();
                }
                switch (resNum) {
                    case 1:
                        residence = "Orchard Commons";
                        break;
                    case 2:
                        residence = "Totem Park";
                        break;
                    case 3:
                        residence = "Place Vanier";
                }

                ListOfStudent filteredList = generalList.filterByResidence(resNum);
                if (!(filteredList.isEmpty())) {
                    generalList.removeAllStudents();

                    for (Student s : filteredList.getStudentList()) {
                        generalList.addStudent(s, true);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "There are no students living in " + residence,
                            "Filter Error", JOptionPane.INFORMATION_MESSAGE, null);
                }
            } catch (Exception err) {
                JOptionPane.showMessageDialog(null,
                        "Please enter a valid filter: \n"
                                + "1: Orchard Commons\n" + "2: Totem Park\n" + "3: Place Vanier",
                        "Filter Error", JOptionPane.INFORMATION_MESSAGE, null);
            }

            residenceFilter.requestFocusInWindow();
            residenceFilter.setText("");
            list.setVisible(true);
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        public void somethingWrong(JTextField field) {
            Toolkit.getDefaultToolkit().beep();
            field.requestFocusInWindow();
            field.selectAll();
        }

        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    public class SaveListListener implements ActionListener {
        private JButton button;

        public SaveListListener(JButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
                jsonWriter.write(generalList);
                jsonWriter.close();
            } catch (FileNotFoundException error) {
                JOptionPane.showMessageDialog(null,
                        "File Not Found",
                        "Save Error", JOptionPane.INFORMATION_MESSAGE, null);
            }
        }
    }

    public class LoadListListener implements ActionListener {
        private JButton button;

        public LoadListListener(JButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ListOfStudent temp;

                temp = jsonReader.read();

                generalList.removeAllStudents();

                for (Student s : temp.getStudentList()) {
                    generalList.addStudent(s, true);
                }
            } catch (IOException error) {
                JOptionPane.showMessageDialog(null,
                        "Unable To Read File",
                        "Save Error", JOptionPane.INFORMATION_MESSAGE, null);
            }
        }
    }

    public class AddStudentListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public AddStudentListener(JButton button) {
            this.button = button;
        }

        @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = studentName.getText();

            if (name.equals("") || generalList.hasStudent(name)) {
                somethingWrong(studentName);
                return;
            }

            int index = list.getSelectedIndex();
            if (index == -1) {
                index = 0;
            } else {
                index++;
            }

            try {
                if (Integer.parseInt(residence.getText()) <= 3 && Integer.parseInt(residence.getText()) > 0) {
                    generalList.addStudent(new Student(studentName.getText(), Integer.parseInt(residence.getText()),
                            interest1.getText(), interest2.getText(), username.getText()), true);
                } else {
                    somethingWrong(residence);
                    return;
                }
            } catch (Exception err) {
                somethingWrong(residence);
                return;
            }

            resetFields();
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }

        public void resetFields() {
            studentName.requestFocusInWindow();
            studentName.setText("");
            residence.setText("");
            interest1.setText("");
            interest2.setText("");
            username.setText("");
        }

        public void somethingWrong(JTextField field) {
            Toolkit.getDefaultToolkit().beep();
            field.requestFocusInWindow();
            field.selectAll();
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    public class RemoveStudentListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            String temp = generalList.get(index);
            java.net.URL imgURL = getClass().getResource("image/StudentRemovedImage.png");
            ImageIcon studentRemoved = new ImageIcon(imgURL);
            Image smallStudentRemoved = studentRemoved.getImage().getScaledInstance(150, 150,Image.SCALE_SMOOTH);
            studentRemoved = new ImageIcon(smallStudentRemoved);

            generalList.remove(index);

            int size = generalList.getSize();

            if (size == 0) {
                removeButton.setEnabled(false);
            } else {
                if (index == generalList.getSize()) {
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
            JOptionPane.showMessageDialog(null, "You Successfully Removed " + temp,
                    "Student Removed", JOptionPane.INFORMATION_MESSAGE, studentRemoved);
        }
    }
}
