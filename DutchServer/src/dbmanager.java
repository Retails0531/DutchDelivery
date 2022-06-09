import java.sql.*;

public class dbmanager {

	private Connection con;
	private static final String username = "tnalsa1859";
	private static final String password = "als1tn2!@";
	private static final String url = "jdbc:mysql://database-1.cicqmw0sdjwp.ap-northeast-2.rds.amazonaws.com:3306/dutchdelivery";
	
	public dbmanager() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			System.out.println("Driver roading Sucess");
		} catch(Exception e) {
			System.out.println("Driver roading fail");
			try {
				con.close();
			}catch(SQLException e1) {}
		}
	}
	
	public int login(String id, String pw) { //�α���
		String sql = "select * from users where id = ?"; //�ش� user�� id�� ���� users���̺��� ������ select
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery(); 
			if(rs.next()) {
				if(rs.getString("pw").equals(pw)){ //pwĮ���� ���� �Է��� pw���� ��ġ�Ѵٸ�
					return 1;
					}
					else{
 					return 3;
					}
			}
			else {
				return 2;
			}
		}catch(Exception e) {
			
			System.out.println("Login error");
			return 0;
			
		} 
		
	}
	
	public void Join(String id, String pw, String phonenumber, String reginumber,String email, String name) { //ȸ������
		check(id); //�ߺ�Ȯ�� ���� �� ȸ������ ����. return�� �ִ� ����� ��...
		String sql = "insert into users value(?,?,?,?,?,?,null,null,null)";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id); //id����
			pstmt.setString(2, pw); //pw����
			pstmt.setString(3, phonenumber); //phone number����
			pstmt.setString(4, reginumber); //resi number���� 
			pstmt.setString(5, email); //email ����
			pstmt.setString(6, name); //name ����
			
			pstmt.executeUpdate();
			
		}
		catch(Exception e) {
			System.out.println("join error");
		} finally {
			try {
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();				}
			} catch(Exception e2) {
			}
		}
		
	}
	
	public void check(String id) { //id �ߺ�Ȯ��
		String sql = "select EXISTS (select id from users where id = ? limit 1) as success";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id); 
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("success").equals("1")) { //�������� ��� ���� 1�̸� �ߺ��� id�� ���� 
					System.out.println("exist"); //���ϰ� �ֱ�!!
					
					
				}else { //�׷��� ������ id ��밡��
					System.out.println("can use");
				}
			}
		}catch(Exception e) {
			System.out.println("check error");
		} finally {
			try {
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();				}
			} catch(Exception e2) {
			}
		}
		
	}
	
	public void setProfile(String id, String nickname, String profileURL) { //�����ʼ��� 
		String sql = "update users set nickname = ?, profileURL = ? where id = ?"; //�̹� ������ user������ �����ϱ� ������ update�� ���
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nickname); //nickname ���ڿ�
			pstmt.setString(2, profileURL); //profile URL ���ڿ�
			pstmt.setString(3, id); //use id
			pstmt.executeUpdate(); //DB�� DATA����
			
		}catch(Exception e) {
			System.out.println("profile error");
		} finally {
			try {
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();				}
			} catch(Exception e2) {
			}
		}
		
	}
	
	public void setArea(String x, String y,String id) { //���������� >> ����(?)
		String sql = "update users set cur_coordinate = ST_GeomFromText('point(? ?)') where id = '?'"; //�̹� ������ user������ �����ϱ� ������ update���� ���
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, x);
			pstmt.setString(2, y);
			pstmt.setString(3, id);
			pstmt.executeUpdate(); //DB�� DATA�ݿ�

		}catch(Exception e) {
			System.out.println("setArea error");
		} finally {
			try {
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();				}
			} catch(Exception e2) {
			}
		}	
	}
	
	public void Address(String id, String road, String better, String x, String y) { //���ּ��� �α� 
		String sql = "insert into address value(?,?,?,?,ST_GeomFromText('point(? ?)'))"; //SEQ�� ���� ��� �־���ϳ�?
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(3,road);
			pstmt.setString(4,better);
			pstmt.setString(5,x);
			pstmt.setString(6,y);
			pstmt.executeUpdate(); //DB�� DATA�ݿ�

		}catch(Exception e) {
			System.out.println("setArea error");
		} finally {
			try {
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();				}
			} catch(Exception e2) {
			}
		}
	}
	
	public void inquirePost() { //�ڸ����� ��ȸ�ϱ� (����ȭ��)
		String sql = "select * from post"; 
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println(rs.getString("post title")); 
			}//��� �� return...
		}catch(Exception e) {
			System.out.println("inquirePost error");
		} finally {
			try {
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();				}
			} catch(Exception e2) {
			}
		}
	}
	
	public void post(String postnumber) { //�ڸ����� �󼼺���
		String sql = "select deliveryfee, time, joinPeople, maxPeople, postTitle, postText, pickupAddress from post where post number = ? ";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,postnumber); //���� ������ �������� ��ȣ
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("post title:"+rs.getString("post title")); //������ ����
				System.out.println("post text:"+rs.getString("post text")); //������ ����
				System.out.println("time:"+rs.getString("time")); //�ֹ� �ð���
				System.out.println("join people:"+rs.getString("join people")); //���� �ο���
				System.out.println("max people:"+rs.getString("max people")); //�ִ� �ο���
				System.out.println("delivery fee:"+rs.getString("delivery fee")); //��޺�
				System.out.println("pickup address:"+rs.getString("pickup address")); //������
			} //��� ���� return...
		}catch(Exception e) {
			System.out.println("Post error");
		} finally {
			try {
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();				}
			} catch(Exception e2) {
			}
		}
	}
	
	public void search(String word, Date time) { //�ڰ˻� �� ���͸�
		String sql = "select * from post"; //�ֹ��ð��� && �˻� ���ڿ��� ���缭 select��. 
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				rs.getString("posttitle");
			} //����� return...
			
		}catch(Exception e) {
			System.out.println("Search error");
		} finally {
			try {
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();				}
			} catch(Exception e2) {
			}
		}
	}
	
	public void regiPost(String postnumber, String storenumber, String deliveryfee, String chatnumber, 
			Date time, String posttitle, String posttext, String id, String pickup) { //�ڸ����� ���
		String sql = "insert into post value(?,?,?,?,?,1,4,?,?,?,?,0)"; //post ���̺� ���� insert����
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, postnumber); 
			pstmt.setString(2, storenumber);
			pstmt.setString(3, deliveryfee);
			pstmt.setString(4, chatnumber);
			pstmt.setDate(5, time);
			pstmt.setString(6, posttitle);
			pstmt.setString(7, posttext);
			pstmt.setString(8, id); //host idĮ���� �ڽ��� id�� ����
			pstmt.setString(9, pickup);
			pstmt.executeUpdate(); //DB�� data����
			
		}catch(Exception e) {
			System.out.println("resiPost error");
		} finally {
			try {
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();				}
			} catch(Exception e2) {
			}
		}
	}
	
	public void myPost(String id) { //�ڳ� ������ ��ȸ 
		String sql = "select posttitle from post where id = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				rs.getString("posttitle");
			} //��� ���� return... 
		}catch(Exception e) {
			System.out.println("myPost error");
		} finally {
			try {
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();				}
			} catch(Exception e2) {
			}
		}
	}
	
	public void partPost(String postnumber) { //�ڸ�����, ä�ù� ����
		String sql = "update post set joinpeople = joinpeople + 1 where postnumber = ?"; //�����۰� ä�ù� ���������� ��ȭ�� ���� �ο����ۿ� ���ٰ� ����, �׷��� �ش� �������� ���� �ο����� update����
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,postnumber);
			pstmt.executeUpdate(); //DB�� data����
		}catch(Exception e) {
			System.out.println("partPost error");
		} finally {
			try {
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();				}
			} catch(Exception e2) {
			}
		}
	}
	
	public void profileInfo(String id) { //�ڳ� ������ ����
		String sql = "select nickname, profileURL from users where id = ?"; //�ش� id�� ���õ� ������ �г���, profileURL�� ������ select�Ͽ� ��ȯ���ش�.
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery(); //������ ��� �� ����
		
			if(rs.next()) {
				rs.getString("nickname"); 
				rs.getString("profileURL");
			}//��� ���� return...
			
		}catch(Exception e) {
			System.out.println("profileInfo error");
		} finally {
			try {
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();				}
			} catch(Exception e2) {
			}
		}
	}
		


public void chatList(String id) { //��ä�ù� ���
	String sql = "select * from post where id = ?"; //�ش� id�� ���õ� chatnumber�� select�Ͽ� ��ȯ���ش�.
	PreparedStatement pstmt = null;
	try {
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1,id);
		ResultSet rs = pstmt.executeQuery(); //������ ��� �� ����
	
		if(rs.next()) { 
			rs.getString("chatnumber");
		}//������� return...
		
	}catch(Exception e) {
		System.out.println("profileInfo error");
	} finally {
		try {
			if(pstmt != null && !pstmt.isClosed()) {
				pstmt.close();				}
		} catch(Exception e2) {
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
}
