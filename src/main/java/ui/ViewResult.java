package ui;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.JBColor;
import com.intellij.ui.SearchTextField;
import com.intellij.ui.components.JBScrollPane;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import models.bean.TestData;

import com.intellij.ui.table.JBTable;

import static javax.swing.ListSelectionModel.SINGLE_SELECTION;

import javax.swing.*;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.awt.event.ActionEvent;

import java.awt.event.*;

import services.FlServiceImpl;
import services.Resources;
import ui.viewResultTableModels.*;
import modules.PluginModule;

/**
 * This class represents the view table.
 */
public class ViewResult extends DialogWrapper {
    private final FlServiceImpl flService;
    private final TestData testData;
    private final TreeTableModel treeViewTableModel;
    private final ClassTableModel classTableModel;
    private final MethodTableModel methodTableModel;
    private final StatementTableModel statementTableModel;
    public ViewResult() {
        super(true);

        String rankType = "";
        if(PluginModule.isAverageSelected()){
            rankType = Resources.get("titles", "average_button");
        }else if (PluginModule.isMinimumSelected()){
            rankType = Resources.get("titles", "minimum_button");
        }else if(PluginModule.isMaximumSelected()){
            rankType = Resources.get("titles", "maximum_button");
        }

        String title = "SBFL Results";
        String spectraMetrics = "";
        if (PluginModule.isTarantulaSelected()) {
            spectraMetrics = " (Tarantula)";
        } else if (PluginModule.isOchiaiSelected()) {
            spectraMetrics = " (Ochiai)";
        } else if (PluginModule.isDStarSelected() || PluginModule.isWongIISelected()) {
            spectraMetrics = " (WongII)";
        }
        title += spectraMetrics;

        setTitle(title);

        flService = new FlServiceImpl();
        testData = flService.getTestData();
        treeViewTableModel = new TreeTableModel(testData);
        classTableModel = new ClassTableModel(testData, spectraMetrics, rankType);
        methodTableModel = new MethodTableModel(testData, spectraMetrics, rankType);
        statementTableModel = new StatementTableModel(testData, spectraMetrics, rankType);

        setModal(false);
        getWindow().addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                closeWindow();
            }
        });
        init();
    }

    private static class SearchField{
        SearchTextField searchTextField;
        String placeholderText;

        public SearchField(String placeholderText) {
            this.searchTextField = new SearchTextField();
            this.placeholderText = placeholderText;
            setPlaceholder();
        }

        private void setPlaceholder(){
            this.searchTextField.setText(this.placeholderText);
            this.searchTextField.getTextEditor().setForeground(JBColor.GRAY);

            this.searchTextField.getTextEditor().addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (searchTextField.getText().equals(placeholderText)) {
                        searchTextField.setText("");
                        searchTextField.getTextEditor().setForeground(JBColor.BLACK);
                    }
                }
                @Override
                public void focusLost(FocusEvent e) {
                    if (searchTextField.getText().isEmpty()) {
                        searchTextField.getTextEditor().setForeground(JBColor.GRAY);
                        searchTextField.setText(placeholderText);
                    }
                }
            });
        }
        private void setSorter(TableRowSorter<TableModel> sorter){
            this.searchTextField.addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    String text = searchTextField.getText();
                    if (text.trim().length() == 0 || searchTextField.getText().equals(placeholderText)) {
                        sorter.setRowFilter(null);
                    } else {
                        sorter.setRowFilter(RowFilter.regexFilter(text));
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    String text = searchTextField.getText();
                    if (text.trim().length() == 0 || searchTextField.getText().equals(placeholderText)) {
                        sorter.setRowFilter(null);
                    } else {
                        sorter.setRowFilter(RowFilter.regexFilter(text));
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {

                }
            });
        }
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JBTable treeViewTable = new JBTable(treeViewTableModel);
        treeViewTable.addMouseListener(new TreeViewTableMouseListener(treeViewTable, treeViewTableModel));
        treeViewTable.setSelectionMode(SINGLE_SELECTION);
        treeViewTable.setAutoCreateRowSorter(true);
        treeViewTable.getColumnModel().getColumn(TreeTableModel.NAME_COLUMN_INDEX).setPreferredWidth(280);
        treeViewTable.getColumnModel().getColumn(TreeTableModel.LINE_COLUMN_INDEX).setPreferredWidth(5);
        treeViewTable.getColumnModel().getColumn(TreeTableModel.ACTION_COLUMN_INDEX).setPreferredWidth(5);

        JBTable classViewTable = createSubViewTable(
                classTableModel,
                ClassTableModel.NAME_COLUMN_INDEX,
                ClassTableModel.SCORE_COLUMN_INDEX,
                ClassTableModel.RANK_COLUMN_INDEX);
        JBTable methodViewTable = createSubViewTable(
                methodTableModel,
                MethodTableModel.NAME_COLUMN_INDEX,
                MethodTableModel.SCORE_COLUMN_INDEX,
                MethodTableModel.RANK_COLUMN_INDEX);
        JBTable statementViewTable = createSubViewTable(
                statementTableModel,
                StatementTableModel.NAME_COLUMN_INDEX,
                StatementTableModel.SCORE_COLUMN_INDEX,
                StatementTableModel.RANK_COLUMN_INDEX);

        statementViewTable.addMouseListener(new StatementTableMouseListener(statementViewTable, testData));

        JTabbedPane tabsPane = new JTabbedPane();
        tabsPane.addTab(Resources.get("titles", "tree_pane"), createTableScrollPane(treeViewTable));
        tabsPane.addTab(Resources.get("titles", "class_pane"), createTableScrollPane(classViewTable));
        tabsPane.addTab(Resources.get("titles", "method_pane"), createTableScrollPane(methodViewTable));
        tabsPane.addTab(Resources.get("titles", "statement_pane"), createTableScrollPane(statementViewTable));
        tabsPane.setPreferredSize(new Dimension(500, 500));

        pack();
        return tabsPane;
    }

    private JBTable createSubViewTable(TableModel tableModel, int nameColumnIndex, int scoreColumnIndex, int rankColumnIndex) {
        JBTable table = new JBTable(tableModel);
        table.setAutoCreateRowSorter(true);

        table.getColumnModel().getColumn(nameColumnIndex).setPreferredWidth(280);

        if (tableModel.getClass().equals(StatementTableModel.class)) {
            table.getColumnModel().getColumn(StatementTableModel.LINE_COLUMN_INDEX).setPreferredWidth(5);
        }
        table.getColumnModel().getColumn(scoreColumnIndex).setPreferredWidth(5);
        table.getColumnModel().getColumn(rankColumnIndex).setPreferredWidth(5);

        return table;
    }

    private JBScrollPane createTableScrollPane(JBTable table) {
        JPanel mainPanel = createSearchField(table);

        JBScrollPane scrollPane = new JBScrollPane(mainPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        return scrollPane;
    }

    private JPanel createSearchField(JBTable table){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        if(!(table.getModel().getClass().toString()).equals(TreeTableModel.class.toString())){
            final TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
            table.setRowSorter(sorter);

            JPanel headerPanel = new JPanel();
            headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

            String placeholderText = "";

            if((table.getModel().getClass().toString()).equals(ClassTableModel.class.toString())){
                placeholderText = Resources.get("titles", "class_placeholder");
            }else if((table.getModel().getClass().toString()).equals(MethodTableModel.class.toString())){
                placeholderText = Resources.get("titles", "method_placeholder");
            }else if((table.getModel().getClass().toString()).equals(StatementTableModel.class.toString())){
                placeholderText = Resources.get("titles", "statement_placeholder");
            }
            String finalPlaceholderText = placeholderText;

            SearchField searchField = new SearchField(finalPlaceholderText);
            searchField.setSorter(sorter);

            headerPanel.add(searchField.searchTextField);
            headerPanel.add(table.getTableHeader());
            mainPanel.add(headerPanel, BorderLayout.PAGE_START);
        }
        else {
            mainPanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
        }

        mainPanel.add(table, BorderLayout.CENTER);
        return mainPanel;
    }

    private void closeWindow() {
        flService.setViewResultTableDialogOpened(false);
        close(0);
    }

    @Override
    protected @NotNull Action[] createActions() {
        Action close = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeWindow();
            }
        };

        close.putValue(Action.NAME, "Close");
        return new Action[]{close};
    }
}