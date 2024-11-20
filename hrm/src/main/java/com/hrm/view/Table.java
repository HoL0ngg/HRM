package com.hrm.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

public class Table extends JPanel {

    private DefaultTableModel model;
    private JTable jTable1;
    private Object[] previousData = null;

    public Table() {
        initComponents();
        configureTable();
    }

    private void initComponents() {
        jTable1 = new JTable();

        setBackground(Color.WHITE);

        // Thiết lập mô hình dữ liệu cho bảng
        model = new DefaultTableModel(new Object[][]{}, new String[]{}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tắt khả năng chỉnh sửa trực tiếp trên bảng
            }
        };
        jTable1.setModel(model);
        JScrollPane jScrollPane1 = new JScrollPane(jTable1, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(14);

        jScrollPane1.setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        add(jScrollPane1, BorderLayout.CENTER);
    }

    private void configureTable() {
        jTable1.setFont(new Font("Arial", Font.PLAIN, 16));
        jTable1.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        jTable1.getTableHeader().setBackground(new Color(255, 204, 153));
        jTable1.getTableHeader().setPreferredSize(new Dimension(jTable1.getTableHeader().getWidth(), 40));

        jTable1.setRowHeight(50);
        // Thiết lập RowSorter để có thể sắp xếp các hàng
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jTable1.setRowSorter(sorter);

        // Cấu hình chọn hàng
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable1.setSelectionBackground(Color.LIGHT_GRAY);

        // Ẩn đường lưới của bảng
        jTable1.setShowGrid(false);

        // Tạo và cấu hình cellRenderer
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER); // Canh giữa nội dung

        // Áp dụng cellRenderer cho tất cả các kiểu dữ liệu
        jTable1.setDefaultRenderer(Object.class, cellRenderer);
        jTable1.setDefaultRenderer(Number.class, cellRenderer);
        jTable1.setDefaultRenderer(Double.class, cellRenderer);
        jTable1.setDefaultRenderer(Float.class, cellRenderer);
        jTable1.setDefaultRenderer(Integer.class, cellRenderer);

        // Áp dụng cellRenderer cho tất cả các cột
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

             // Tô màu hàng lẻ và hàng chẵn
        jTable1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus,
                    int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    if (row % 2 == 0) {
                        cell.setBackground(Color.WHITE); // Hàng chẵn màu trắng
                    } else {
                        cell.setBackground(new Color(230, 230, 230)); // Hàng lẻ màu xám nhạt
                    }
                }
                setHorizontalAlignment(JLabel.CENTER); // Canh giữa nội dung từng ô
                return cell;
            }
        });
    }

    // Thiết lập các tiêu đề cột
    public void setHeaders(String[] headers) {
        model.setColumnIdentifiers(headers);
    }

    public void setHeaders(ArrayList<String> headers) {
        model.setColumnIdentifiers(headers.toArray());
    }

    // Thêm hàng vào bảng
    public <T> void addRow(T[] row) {
        model.addRow(row);
    }

    public <T> void addRow(ArrayList<T> row) {
        model.addRow(row.toArray());
    }

    // Xóa hàng theo giá trị nhận diện trong một cột cụ thể
    public void removeRowByValue(String identifier, int columnIndex) {
        for (int i = 0; i < model.getRowCount(); ++i) {
            if (model.getValueAt(i, columnIndex).toString().equals(identifier)) {
                model.removeRow(i);
                return;
            }
        }
    }

    // Thiết lập giá trị cho một hàng dựa trên giá trị nhận diện trong một cột
    public <T> void setValueRow(String identifier, int identifierColumn, T[] data) {
        for (int i = 0; i < model.getRowCount(); ++i) {
            if (model.getValueAt(i, identifierColumn).toString().equals(identifier)) {
                for (int j = 0; j < data.length; ++j) {
                    model.setValueAt(data[j], i, j);
                }
                return;
            }
        }
    }

    // Lấy giá trị tại một hàng và cột cụ thể
    public Object getValueAt(int row, int column) {
        return model.getValueAt(row, column);
    }

    // Lấy giá trị dựa trên tên cột
    public Object getValueByColumnName(int row, String columnName) {
        int colIndex = getColumnIndexByName(columnName);
        if (colIndex != -1) {
            return model.getValueAt(row, colIndex);
        }
        return null;
    }

    // Tìm chỉ số cột theo tên
    private int getColumnIndexByName(String columnName) {
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            if (jTable1.getColumnName(i).equals(columnName)) {
                return i;
            }
        }
        return -1;
    }

    // Cập nhật giá trị của toàn bộ cột
    public void updateColumnValues(String columnName, Object value) {
        int columnIndex = getColumnIndexByName(columnName);
        if (columnIndex != -1) {
            for (int row = 0; row < model.getRowCount(); row++) {
                model.setValueAt(value, row, columnIndex);
            }
        }
    }

    // Cài đặt lại dữ liệu bảng
    public void clear() {
        model.setRowCount(0);
    }

    // Trả về số hàng của bảng
    public int getRowCount() {
        return model.getRowCount();
    }

    // Đặt chiều cao hàng
    public void setRowHeight(int rowHeight) {
        jTable1.setRowHeight(rowHeight);
    }

    // Đặt chiều rộng cột
    public void setPreferredWidth(int column, int width) {
        TableColumnModel columnModel = jTable1.getColumnModel();
        columnModel.getColumn(column).setPreferredWidth(width);
    }

    // Tự động điều chỉnh kích thước cột dựa trên nội dung
    public void resizeColumnWidth() {
        TableColumnModel columnModel = jTable1.getColumnModel();
        for (int column = 0; column < jTable1.getColumnCount(); column++) {
            int width = 100; // Chiều rộng tối thiểu
            for (int row = 0; row < jTable1.getRowCount(); row++) {
                TableCellRenderer renderer = jTable1.getCellRenderer(row, column);
                Component comp = jTable1.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            width += 20; // Khoảng đệm cho các giá trị lớn
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    // Trả về hàng được chọn
    public int getSelectedRow() {
        return jTable1.getSelectedRow();
    }

    // Trả về JTable
    public JTable getTable() {
        return jTable1;
    }

    // Trả về mô hình bảng
    public DefaultTableModel getModel() {
        return model;
    }

    public TableColumnModel getColumnModel() {
        return jTable1.getColumnModel();
    }

}
