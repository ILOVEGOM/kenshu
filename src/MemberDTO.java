
public class MemberDTO {
	private int mno;
	private String fname;
	private String lname;
	private String addr;
	private String email;
	private int age;
	public MemberDTO() {
		// TODO 自動生成されたコンストラクター・スタブ
	}
	public MemberDTO(int mno, String fname, String lname, String addr, String email, int age) {
		super();
		this.mno = mno;
		this.fname = fname;
		this.lname = lname;
		this.addr = addr;
		this.email = email;
		this.age = age;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
}
