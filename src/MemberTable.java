import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class MemberTable extends JFrame implements TableModelListener {
	JTable table = null;

	JPanel panel = new JPanel();
	JPanel panel2 = new JPanel();
	JLabel lbl = new JLabel("セルをダブルクリックして変更してください。");
	JButton btn = new JButton("印刷");
	JButton btnInsert = new JButton("登録");
	JButton btnUpdate = new JButton("修正");
	JButton btnDelete = new JButton("削除");
	JButton btnSearch = new JButton("検索");
	JLabel lblFname = new JLabel("氏名(姓)");
	JLabel lblLname = new JLabel("氏名(名)");
	JLabel lblAddr = new JLabel("郵便番号");
	JLabel lblEmail = new JLabel("Eメール");
	JLabel lblAge = new JLabel("年齢");
	JTextField tfFname = new JTextField(10);
	JTextField tfLname = new JTextField(10);
	JTextField tfAddr = new JTextField(10);
	JTextField tfEmail = new JTextField(20);
	JTextField tfAge = new JTextField(3);

	String[] colomNames = { "No", "氏名(姓)", "氏名(名)", "郵便番号", "Eメール", "年齢" };
	//	Object[][] data = {{"山田", "太郎", "232-0011","taro@cal.co.jp" ,"18"},
	//			{"天井", "香織", "232-1011","amai@cal.co.jp" ,"34"},
	//			{"神尾", "次郎", "232-1111","kamio@cal.co.jp" ,"21"},
	//			{"井上", "三郎", "122-3313","inoue@cal.co.jp" ,"33"},
	//			{"神谷", "五郎", "123-4567","kamiya@cal.co.jp" ,"20"},
	//	};
	Object[][] data = null;
	MemberDAO dao = new MemberDAO();
	MemberDTO member = new MemberDTO();

	@Override
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
		int col = e.getColumn();
		TableModel model = (TableModel) e.getSource();
		String colName = model.getColumnName(col);
		Object data = model.getValueAt(row, col);
		String msg = String.format("[%d,%d(%S)]は%s", row, col, colName, (String) data);
		System.out.println(col);
		System.out.println(col);
		lbl.setText(msg);

	}
	public void memberselect(){
		try {
			dao.connect();
			List<MemberDTO> members = dao.selectAllMember();
			data = new Object[members.size()][members.size()];
			for (int i = 0; i < members.size(); i++) {
				MemberDTO member = members.get(i);
				data[i][0] = member.getMno();
				data[i][1] = member.getLname();
				data[i][2] = member.getFname();
				data[i][3] = member.getAddr();
				data[i][4] = member.getEmail();
				data[i][5] = member.getAge();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dao != null) {
				dao.disConnect();
			}
		}
	}
	public MemberTable() throws Exception {
		memberselect();
		table = new JTable(data, colomNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 100));
		table.setFillsViewportHeight(true);

		JScrollPane scrollpane = new JScrollPane(table);
		table.getModel().addTableModelListener(this);
		setLayout(new BorderLayout());
		add(scrollpane, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout());
		panel.add(lbl);
		panel.add(btn);
		panel.add(btnUpdate);
		panel.add(btnDelete);

		panel2.add(lblFname);
		panel2.add(tfFname);
		panel2.add(lblLname);
		panel2.add(tfLname);
		panel2.add(lblAddr);
		panel2.add(tfAddr);
		panel2.add(lblEmail);
		panel2.add(tfEmail);
		panel2.add(lblAge);
		panel2.add(tfAge);
		panel2.add(btnInsert);
		panel.add(btnSearch);

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

		btnInsert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					dao.connect();
					member.setFname((String) tfFname.getText());
					member.setLname((String) tfFname.getText());
					member.setAddr((String) tfAddr.getText());
					member.setEmail((String) tfEmail.getText());
					member.setAge(Integer.parseInt((String) tfAge.getText()));
					dao.insertMember(member);
					memberselect();
					
				} catch (Exception e2) {
					// TODO: handle exception
				} finally {
					if (dao != null) {
						dao.disConnect();
					}
				}
			}
		});

		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					dao.connect();
					// 선택된 행의 인덱스를 가져온다.
					int selectedRow = table.getSelectedRow();

					// 선택된 행의 데이터를 가져와서 MemberDTO 객체에 설정한다.
					member.setMno((int) table.getValueAt(selectedRow, 0));
					member.setLname((String) table.getValueAt(selectedRow, 1));
					member.setFname((String) table.getValueAt(selectedRow, 2));
					member.setAddr((String) table.getValueAt(selectedRow, 3));
					member.setEmail((String) table.getValueAt(selectedRow, 4));
					member.setAge((int) table.getValueAt(selectedRow, 5));

					// MemberDAO를 생성하고 updateMember 메서드를 호출하여 데이터를 수정한다.
					dao.updateMember(member);
					memberselect();
				} catch (Exception e1) {
					e1.printStackTrace();
				} finally {
					if (dao != null) {
						dao.disConnect();
					}
				}
			}
		});

		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					dao.connect();
					int selectedRow = table.getSelectedRow();

					// 선택된 행의 데이터를 가져와서 MemberDTO 객체에 설정한다.
					member.setMno((int) table.getValueAt(selectedRow, 0));
					dao.deleteMember(member);
					memberselect();
				} catch (Exception e1) {
					e1.printStackTrace();
				} finally {
					if (dao != null) {
						dao.disConnect();
					}
				}
			}
		});
		add(panel, BorderLayout.CENTER);
		add(panel2, BorderLayout.SOUTH);
		// ウィンドウを閉じたらプログラムを終了する。
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("MemberTable");
		setVisible(true);
		setSize(1000, 300);
	}

	public static void main(String[] args) throws Exception {
		new MemberTable();
	}
}
