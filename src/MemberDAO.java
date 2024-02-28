import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

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
	
	
	private static final String sqlInsertMember = "insert into member values (nextval('member_seq'),?, ?, ?, ?, ?)";
	private static final String sqlSelectAllMember = "select * from member";
	private static final String sqlSelectMember = "select * from member where mno = ?";
	private static final String sqlUpdateMember = "update member set fname = ?, lname = ?, addr = ?, email = ?, age = ? where mno = ?";
	private static final String sqlDeleteMember = "delete from member where mno = ?";
	

	private PreparedStatement pstmtInsert;
	private PreparedStatement pstmtSelect;
	private PreparedStatement pstmtSelectAll;
	private PreparedStatement pstmtUpdate;
	private PreparedStatement pstmtDelete;
	private Connection con = null;

	public void connect() throws Exception {
		try {
			Class.forName(driverName);

			con = DriverManager.getConnection(dbserver + dbname, user, password);
			System.out.println(con);
			pstmtInsert = con.prepareStatement(sqlInsertMember);
			pstmtSelect = con.prepareStatement(sqlSelectMember, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			pstmtUpdate = con.prepareStatement(sqlUpdateMember, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			pstmtSelectAll = con.prepareStatement(sqlSelectAllMember);
			pstmtDelete = con.prepareStatement(sqlDeleteMember);
			System.out.println("db connect");

		} catch (Exception e) {
			System.out.println("error");
		}
	}

	public void disConnect() {

		try {
			pstmtSelect.close();
			pstmtSelectAll.close();
			pstmtUpdate.close();
			con.close();
		} catch (Exception e) {

		}

	}

	public List<MemberDTO> selectAllMember() throws Exception {
		List<MemberDTO> members = new ArrayList<MemberDTO>();
		ResultSet rs = null;
		MemberDTO member = null;

		try {
			rs = pstmtSelectAll.executeQuery();
			while (rs.next()) {
				member = new MemberDTO();
				member.setMno(rs.getInt("mno"));
				member.setFname(rs.getString("fname"));
				member.setLname(rs.getString("lname"));
				member.setAddr(rs.getString("addr"));
				member.setEmail(rs.getString("email"));
				member.setAge(rs.getInt("age"));

				members.add(member);
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			}
		return members;

	}
	
	public List<MemberDTO> selectMember() throws Exception {
		List<MemberDTO> members = new ArrayList<MemberDTO>();
		ResultSet rs = null;
		MemberDTO member = null;

		try {
			pstmtSelect.setString(1, member.getFname());
			rs = pstmtSelect.executeQuery();
			while (rs.next()) {
				member = new MemberDTO();
				member.setMno(rs.getInt("mno"));
				member.setFname(rs.getString("fname"));
				member.setLname(rs.getString("lname"));
				member.setAddr(rs.getString("addr"));
				member.setEmail(rs.getString("email"));
				member.setAge(rs.getInt("age"));

				members.add(member);
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
		return members;

	}
	
	public void insertMember(MemberDTO member) {
		long retUpdateCnt = 0;
		
		try {
			pstmtInsert.setString(1, member.getFname());
			pstmtInsert.setString(2, member.getLname());
			pstmtInsert.setString(3, member.getAddr());
			pstmtInsert.setString(4, member.getEmail());
			pstmtInsert.setInt(5, member.getAge());
			
			retUpdateCnt = pstmtInsert.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void updateMember(MemberDTO member) {
		long retUpdateCnt = 0;
		try {
			pstmtUpdate.setString(1, member.getFname());
			System.out.println(member.getFname());
			System.out.println(member.getLname());
			System.out.println(member.getAddr());
			System.out.println(member.getEmail());
			System.out.println(member.getAge());
			System.out.println(member.getMno());
			pstmtUpdate.setString(2, member.getLname());
			pstmtUpdate.setString(3, member.getAddr());
			pstmtUpdate.setString(4, member.getEmail());
			pstmtUpdate.setInt(5, member.getAge());
			pstmtUpdate.setInt(6, member.getMno());

			retUpdateCnt = pstmtUpdate.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void deleteMember(MemberDTO member) {
		long retUpdateCnt = 0;
		try {
			pstmtDelete.setInt(1, member.getMno());
			
			retUpdateCnt = pstmtDelete.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
