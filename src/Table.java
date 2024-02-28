import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Table extends JFrame implements TableModelListener{
	


	 /* DB接続定義 */
	 // private static final String user="testuser";   // Oracle user
	 private static final String user = "postgres"; // PostgreSQL user
	 // private static final String password="testpass";  // Oracle pass
	 private static final String password = "postgrestest"; // PostgreSQL pass
	 // private static final String dbserver="192.168.0.170:1521"; // Oracle Server
	 private static final String dbserver = "jdbc:postgresql://localhost:5432/"; // Postgres Server
	 private static final String dbname = "postgres"; // DataBase name
	 // private static final String driverName = "oracle.jdbc.driver.OracleDriver"; // Oracle Driver name
	 private static final String driverName = "org.postgresql.Driver"; // PostgreSQL Driver name
	 private Connection _conn = null;

	 private PreparedStatement Pstmt_Select;
	 private PreparedStatement Pstmt_Update;
	 private PreparedStatement Pstmt_Insert;
	 private PreparedStatement Pstmt_Delete;
	 private PreparedStatement Pstmt_Search;

	 public void connect() throws Exception {

	  /* ドライバクラスのロード */
	  Class.forName(driverName);

	  /* Connectionの生成 */
	  // _conn=DriverManager.getConnection("jdbc:oracle:thin:@" + dbserver + ":" + dbname, user, password);
	  _conn = DriverManager.getConnection(dbserver + dbname, user, password);

	  Pstmt_Select = _conn.prepareStatement(
	    "SELECT mno, fname, lname, addr,email,age FROM member ORDER BY mno",
	    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
	  Pstmt_Update = _conn.prepareStatement(
	    "UPDATE member SET fname = ?, lname = ?, addr = ?, email = ?, age = ? where mno = ?",
	    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
	  Pstmt_Insert = _conn.prepareStatement(
	    "INSERT INTO member (fname, lname, addr, email, age) values (?, ?, ?, ?, ?)");
	  Pstmt_Delete = _conn.prepareStatement("DELETE FROM member where memberno = ?");
	 }

	 boolean trigger = false;
	 JTable table = null;
	 JPanel panel = new JPanel();
	 JLabel lbl = new JLabel("セルをダブルクリックして変更してください");
	 JButton btn = new JButton("印刷");
	 JButton insertBtn = new JButton("登録");
	 JButton deleteBtn = new JButton("削除");
	 JButton searchBtn = new JButton("検索");

	 String[] columnNames = { "no", "氏名(姓)", "氏名(名)", "郵便番号", "Eメール", "年齢" };
	 // Object[][] data = { { "山田", "太郎", "232-0011", "taro@cal.co.jp", "18" },
	 //   { "天井", "香織", "232-1011", "amai@cal.co.jp", "34" },
	 //   { "村上", "春樹", "232-1234", "murakami@cal.co.jp", "53" },
	 //   { "バク", "ジンウック", "233-0001", "parkjw@cal.co.jp", "33" },
	 //   { "松山", "計上", "235-0011", "matsuyama@cal.co.jp", "40" } };
	 List<String[]> list;
	 Object[][] data;

	 @Override
	 public void tableChanged(TableModelEvent e) {
	  if (!trigger) {
	   int row = e.getFirstRow();
	   int col = e.getColumn();
	   TableModel model = (TableModel) e.getSource();
	   String colName = model.getColumnName(col);
	   Object data1 = model.getValueAt(row, col);
	   try {
	    for(int i=1;i<=5;i++) {
	     Pstmt_Update.setString(i, (String)model.getValueAt(row, i));
	    }
	    Pstmt_Update.setInt(6, Integer.parseInt((String)model.getValueAt(row, 0)));

	   } catch (SQLException e1) {
	    // TODO 自動生成された catch ブロック
	    System.out.println("error occur");
	    e1.printStackTrace();
	   } 
	   String msg = String.format("[%d,%d(%s)]は%s", row, col, colName, (String) data1);
	   lbl.setText(msg);
	  }
	 }

	 public Table() {
	  try {
	   connect();
	  } catch (Exception e) {
	   // TODO 自動生成された catch ブロック
	   e.printStackTrace();
	  }
	  list = new ArrayList<>();
	  ResultSet rset = null;
	  try {
	   rset = Pstmt_Select.executeQuery();
	   while (rset.next()) {
	    String[] temp = { rset.getString("mno"), rset.getString("fname"),
	      rset.getString("lname"), rset.getString("addr"), rset.getString("email"),
	      rset.getString("age") };
	    list.add(temp);
	   }
	  } catch (SQLException e) {
	   // TODO 自動生成された catch ブロック
	   e.printStackTrace();
	  }

	  data = new Object[list.size()][6];
	  for (int i = 0; i < list.size(); i++) {
	   data[i] = (Object[]) list.get(i);
	  }

	  //table = new JTable(data, columnNames);
	  DefaultTableModel dtm = new DefaultTableModel(data, columnNames) {
	   public boolean isCellEditable(int r, int c) {
	    if (c == 0)
	     return false;
	    else
	     return true;
	   }
	  };

	  table = new JTable(dtm);
	  table.setPreferredScrollableViewportSize(new Dimension(500, 70));
	  table.setFillsViewportHeight(true);
	  JScrollPane scrollpane = new JScrollPane(table);
	  table.getModel().addTableModelListener(this);
	  setLayout(new BorderLayout());
	  add(scrollpane, BorderLayout.CENTER);
	  panel.setLayout(new FlowLayout());
	  panel.add(lbl);
	  panel.add(btn);
	  panel.add(insertBtn);
	  panel.add(deleteBtn);
	  panel.add(searchBtn);
	  btn.addActionListener(new ActionListener() {

	   @Override
	   public void actionPerformed(ActionEvent e) {
	    try {
	     table.print();
	    } catch (PrinterException e1) {
	     JOptionPane.showMessageDialog(null, "印刷できません。");
	    }
	   }
	  });
	  insertBtn.addActionListener(new ActionListener() {
	   @Override
	   public void actionPerformed(ActionEvent e) {
	    try {
	     String fname = JOptionPane.showInputDialog("氏名(姓)を入力してください。");
	     String lname = JOptionPane.showInputDialog("氏名(姓)を入力してください。");
	     String pno = JOptionPane.showInputDialog("郵便番号を入力してください。");
	     String email = JOptionPane.showInputDialog("Eメールを入力してください。");
	     String age = JOptionPane.showInputDialog("年齢を入力してください。");

	     Pstmt_Insert.setString(1, fname);
	     Pstmt_Insert.setString(2, lname);
	     Pstmt_Insert.setString(3, pno);
	     Pstmt_Insert.setString(4, email);
	     Pstmt_Insert.setString(5, age);

	     if (Pstmt_Insert.executeUpdate() != 0) {
	      try {
	       ResultSet nrset = Pstmt_Select.executeQuery();
	       list.clear();
	       while (nrset.next()) {
	        String[] temp = { nrset.getString("mno"), nrset.getString("memberfname"),
	          nrset.getString("memberlastname"), nrset.getString("addr"),
	          nrset.getString("memberemail"),
	          nrset.getString("memberage") };
	        list.add(temp);
	       }
	      } catch (SQLException e2) {
	       // TODO 自動生成された catch ブロック
	       e2.printStackTrace();
	      }
	      System.out.println(list.size());
	      data = new Object[list.size()][6];
	      for (int i = 0; i < list.size(); i++) {
	       data[i] = (Object[]) list.get(i);
	      }
	      trigger = true;
	      dtm.setDataVector(data, columnNames);
	      trigger = false;
	      JOptionPane.showMessageDialog(null, "登録しました。");
	     } else
	      throw new Exception();
	    } catch (Exception e1) {
	     e1.printStackTrace();
	     JOptionPane.showMessageDialog(null, "登録できません。");
	    }
	   }
	  });
	  deleteBtn.addActionListener(new ActionListener() {
	   @Override
	   public void actionPerformed(ActionEvent e) {
	    try {
	     int row = Integer.parseInt(JOptionPane.showInputDialog("削除したい行のnoを入力してください。"));
	     Pstmt_Delete.setInt(1, row);
	     if (Pstmt_Delete.executeUpdate() != 0) {
	      try {
	       ResultSet nrset = Pstmt_Select.executeQuery();
	       list.clear();
	       while (nrset.next()) {
	        String[] temp = { nrset.getString("mno"), nrset.getString("memberfname"),
	          nrset.getString("memberlastname"), nrset.getString("addr"),
	          nrset.getString("memberemail"),
	          nrset.getString("memberage") };
	        list.add(temp);
	       }
	      } catch (SQLException e2) {
	       // TODO 自動生成された catch ブロック
	       e2.printStackTrace();
	      }
	      System.out.println(list.size());
	      data = new Object[list.size()][6];
	      for (int i = 0; i < list.size(); i++) {
	       data[i] = (Object[]) list.get(i);
	      }
	      trigger = true;
	      dtm.setDataVector(data, columnNames);
	      trigger = false;
	      JOptionPane.showMessageDialog(null, "削除しました。");
	     } else
	      throw new Exception();
	    } catch (Exception e1) {
	     e1.printStackTrace();
	     JOptionPane.showMessageDialog(null, "削除できません。");
	    }
	   }
	  });
	  add(panel, BorderLayout.SOUTH);

	  setDefaultCloseOperation(EXIT_ON_CLOSE);
	  setTitle("MemberTable");
	  setVisible(true);
	  pack();

	 }

	 public static void main(String[] args) {
	  new Table();
	 }

}