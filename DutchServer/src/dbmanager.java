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
	
	public int login(String id, String pw) { //로그인
		String sql = "select * from users where id = ?"; //해당 user의 id를 통해 users테이블에서 정보를 select
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery(); 
			if(rs.next()) {
				if(rs.getString("pw").equals(pw)){ //pw칼럼의 값과 입력한 pw값이 일치한다면
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
	
	public void Join(String id, String pw, String phonenumber, String reginumber,String email, String name) { //회원가입
		check(id); //중복확인 성공 시 회원가입 가능. return값 주는 방법을 모름...
		String sql = "insert into users value(?,?,?,?,?,?,null,null,null)";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id); //id삽입
			pstmt.setString(2, pw); //pw삽입
			pstmt.setString(3, phonenumber); //phone number삽입
			pstmt.setString(4, reginumber); //resi number삽입 
			pstmt.setString(5, email); //email 삽입
			pstmt.setString(6, name); //name 삽입
			
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
	
	public void check(String id) { //id 중복확인
		String sql = "select EXISTS (select id from users where id = ? limit 1) as success";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id); 
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("success").equals("1")) { //쿼리문의 결과 값이 1이면 중복된 id가 존재 
					System.out.println("exist"); //리턴값 주기!!
					
					
				}else { //그렇지 않으면 id 사용가능
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
	
	public void setProfile(String id, String nickname, String profileURL) { //프로필설정 
		String sql = "update users set nickname = ?, profileURL = ? where id = ?"; //이미 기존의 user정보가 존재하기 때문에 update를 사용
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nickname); //nickname 문자열
			pstmt.setString(2, profileURL); //profile URL 문자열
			pstmt.setString(3, id); //use id
			pstmt.executeUpdate(); //DB에 DATA삽입
			
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
	
	public void setArea(String x, String y,String id) { //★지역설정 >> 오류(?)
		String sql = "update users set cur_coordinate = ST_GeomFromText('point(? ?)') where id = '?'"; //이미 기존의 user정보가 존재하기 때문에 update문을 사용
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, x);
			pstmt.setString(2, y);
			pstmt.setString(3, id);
			pstmt.executeUpdate(); //DB에 DATA반영

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
	
	public void Address(String id, String road, String better, String x, String y) { //★주소지 로깅 
		String sql = "insert into address value(?,?,?,?,ST_GeomFromText('point(? ?)'))"; //SEQ는 값을 어떻게 넣어야하나?
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(3,road);
			pstmt.setString(4,better);
			pstmt.setString(5,x);
			pstmt.setString(6,y);
			pstmt.executeUpdate(); //DB에 DATA반영

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
	
	public void inquirePost() { //★모집글 조회하기 (메인화면)
		String sql = "select * from post"; 
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println(rs.getString("post title")); 
			}//결과 값 return...
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
	
	public void post(String postnumber) { //★모집글 상세보기
		String sql = "select deliveryfee, time, joinPeople, maxPeople, postTitle, postText, pickupAddress from post where post number = ? ";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,postnumber); //내가 선택한 모집글의 번호
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("post title:"+rs.getString("post title")); //모집글 제목
				System.out.println("post text:"+rs.getString("post text")); //모집글 내용
				System.out.println("time:"+rs.getString("time")); //주문 시간대
				System.out.println("join people:"+rs.getString("join people")); //참여 인원수
				System.out.println("max people:"+rs.getString("max people")); //최대 인원수
				System.out.println("delivery fee:"+rs.getString("delivery fee")); //배달비
				System.out.println("pickup address:"+rs.getString("pickup address")); //수령지
			} //결과 값을 return...
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
	
	public void search(String word, Date time) { //★검색 및 필터링
		String sql = "select * from post"; //주문시간대 && 검색 문자열에 맞춰서 select함. 
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				rs.getString("posttitle");
			} //결과값 return...
			
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
			Date time, String posttitle, String posttext, String id, String pickup) { //★모집글 등록
		String sql = "insert into post value(?,?,?,?,?,1,4,?,?,?,?,0)"; //post 테이블에 값을 insert해줌
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
			pstmt.setString(8, id); //host id칼럼에 자신의 id를 삽입
			pstmt.setString(9, pickup);
			pstmt.executeUpdate(); //DB에 data삽입
			
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
	
	public void myPost(String id) { //★내 모집글 조회 
		String sql = "select posttitle from post where id = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				rs.getString("posttitle");
			} //결과 값을 return... 
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
	
	public void partPost(String postnumber) { //★모집글, 채팅방 참여
		String sql = "update post set joinpeople = joinpeople + 1 where postnumber = ?"; //모집글과 채팅방 참여에서의 변화는 참여 인원수밖에 없다고 생각, 그래서 해당 모집글의 참여 인원수만 update해줌
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,postnumber);
			pstmt.executeUpdate(); //DB에 data삽입
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
	
	public void profileInfo(String id) { //★내 프로필 정보
		String sql = "select nickname, profileURL from users where id = ?"; //해당 id와 관련된 유저의 닉네임, profileURL의 정보를 select하여 반환해준다.
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery(); //쿼리문 결과 값 저장
		
			if(rs.next()) {
				rs.getString("nickname"); 
				rs.getString("profileURL");
			}//결과 값을 return...
			
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
		


public void chatList(String id) { //★채팅방 목록
	String sql = "select * from post where id = ?"; //해당 id에 관련된 chatnumber를 select하여 반환해준다.
	PreparedStatement pstmt = null;
	try {
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1,id);
		ResultSet rs = pstmt.executeQuery(); //쿼리문 결과 값 저장
	
		if(rs.next()) { 
			rs.getString("chatnumber");
		}//결과값을 return...
		
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
