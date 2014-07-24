package unit.user.parts;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;





import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;





import user.parts.RegInfDAO;



public class RegInfDAOTest {
	Connection con = null;
	PreparedStatement pstm = null;
	static MysqlDataSource ds = null;
	
	String col1="001,鈴木太郎,35";
	String col2="002,Tommy,25";
	String col3="003,山田花子,30";
	String col4="004,佐藤路未央,28";
	String col2_002="002,Michael,29";
	/**
	 * Tomcatを使わずにDataSourceを使ってデータベースにアクセスするための設定
	 */
	@BeforeClass
	public static  void  initJndi()  {    
		
	    System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
	    System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
		
	    InitialContext ic;
		try {
			ic = new InitialContext();
		
		ic.createSubcontext("java:");
		ic.createSubcontext("java:comp");
		ic.createSubcontext("java:comp/env");
		ic.createSubcontext("java:comp/env/jdbc"); 
		ds = new MysqlDataSource();
		ds.setUser("root");
		ds.setPassword("password"); 
		ds.setURL("jdbc:mysql://localhost/Task"); 
		ic.bind("java:comp/env/jdbc/Task", ds); 
		} catch (NamingException e) {
			e.printStackTrace();
		} 
		} 
	/**
	 *テーブルがある場合は削除し、既定のテーブルを作成する前処理
	 *各テストの前に毎回実行する
	 */
	@Before
	public void cordinateDataBase() {
		//getConnection


		try {

				String sql=GetProperties.getPro("deleteReg");
				con = FactoryDs.getConnection();
				pstm = con.prepareStatement(sql);
				int result = pstm.executeUpdate();
				System.out.println(result);
				String sql1 =GetProperties.getPro("createTbl");
				
				String sql2 ="INSERT INTO `registrants` VALUES ('001','鈴木太郎','35'),('002','Tommy','25'),('003','山田花子','30');";
				try {
					pstm = con.prepareStatement(sql1);
					pstm.executeUpdate();
					pstm = con.prepareStatement(sql2);
					pstm.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
					
				} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	//特殊な前処理００５専用
	@Ignore
	public void UT002_005_pre() {

		//getConnection

		try {
				//現存するテーブルを削除する
				String sql=GetProperties.getPro("deleteReg");
				con = FactoryDs.getConnection();
				pstm = con.prepareStatement(sql);
				pstm.executeUpdate();
				System.out.println(1);
				//新たに空っぽのテーブルを作成する
				String sql1 =GetProperties.getPro("createTbl");
				pstm = con.prepareStatement(sql1);
				pstm.executeUpdate();
			} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@After
	/**
	 * コネクションの切断　すっかり忘れていたので7/24追記
	 */
	public void closeEtc() {


		if(this.con != null){
			try {
				this.con.close();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		
		if(this.pstm != null){
			try {
				this.pstm.close();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}	
		if(this.ds != null){
			this.ds = null;
		}
	}
	@Test
	public void UT002_001() {

		boolean booleanAssert = true;
		String errorMsg =null;
		RegInfDAO dao =new RegInfDAO();
		//実行値
		ResultSet rs=null;
		//execute 
		dao.insert("004","佐藤路未央","28");
		con =FactoryDs.getConnection();

		try {
			pstm = con.prepareStatement(GetProperties.getPro("selectAll"));
			rs =pstm.executeQuery();
			//セレクトの戻り値格納用
			String actual=null;
			int counter =0;
			while(rs.next()){
				
				int colNum = Integer.parseInt(GetProperties.getPro("reg_col_num"));
				for(int index=0;index<colNum;index++ ){
					actual=rs.getString(GetProperties.getPro("reg1")) +",";
					actual=actual + rs.getString(GetProperties.getPro("reg2")) +",";
					actual=actual + rs.getString(GetProperties.getPro("reg3")) ;
				}
				if(!actual.equals(this.col1)&&counter ==0){
					booleanAssert = false;
					errorMsg=errorMsg+"期待値"+col1+":実行値"+actual;	
					
				}
				if(!actual.equals(this.col2)&&counter ==1){
					booleanAssert = false;
					errorMsg=errorMsg+"期待値"+col2+":実行値"+actual;				}
				if(!actual.equals(this.col3)&&counter ==2){
					booleanAssert = false;
					errorMsg=errorMsg+"期待値"+col3+":実行値"+actual;	
				}
				if(!actual.equals(this.col4)&&counter ==3){
					booleanAssert = false;
					errorMsg=errorMsg+"期待値"+col4+":実行値"+actual;	
				}
				counter++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//assert check
		assertTrue(errorMsg,booleanAssert);
	}
	@Test
	public void UT002_002() {

		boolean booleanAssert = true;
		String errorMsg =null;
		//実行値
		ResultSet rs=null;
		RegInfDAO dao =new RegInfDAO();
		//insert
		dao.update("002","Michael","29");
		//check result
		con =FactoryDs.getConnection();
		try {
			pstm = con.prepareStatement(GetProperties.getPro("selectAll"));
			 rs =pstm.executeQuery();
			//セレクトの戻り値格納用
			String actual=null;
			int counter =0;
			while(rs.next()){
				
				int colNum = Integer.parseInt(GetProperties.getPro("reg_col_num"));
				for(int index=0;index<colNum;index++ ){
					actual=rs.getString(GetProperties.getPro("reg1")) +",";
					actual=actual + rs.getString(GetProperties.getPro("reg2")) +",";
					actual=actual + rs.getString(GetProperties.getPro("reg3")) ;
				}
				if(!actual.equals(this.col1)&&counter ==0){
					booleanAssert = false;
					errorMsg="期待値"+col1+":実行値"+actual;	
					
				}
				if(!actual.equals(this.col2_002)&&counter ==1){
					booleanAssert = false;
					errorMsg="期待値"+col2_002+":実行値"+actual;				}
				if(!actual.equals(this.col3)&&counter ==2){
					booleanAssert = false;
					errorMsg="期待値"+col3+":実行値"+actual;	
				}
				counter++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//assert check
		assertTrue(errorMsg,booleanAssert);
	}
	@Test
	public void UT002_003() {

		boolean booleanAssert = true;
		String errorMsg =null;
		//実行値
		ResultSet rs=null;
		RegInfDAO dao =new RegInfDAO();
		//delete
		dao.delete("001");
		//check result
		con =FactoryDs.getConnection();
		try {
			pstm = con.prepareStatement(GetProperties.getPro("selectAll"));
			 rs =pstm.executeQuery();
			//セレクトの戻り値格納用
			String actual=null;
			int counter =1;
			while(rs.next()){
				
				int colNum = Integer.parseInt(GetProperties.getPro("reg_col_num"));
				for(int index=0;index<colNum;index++ ){
					actual=rs.getString(GetProperties.getPro("reg1")) +",";
					actual=actual + rs.getString(GetProperties.getPro("reg2")) +",";
					actual=actual + rs.getString(GetProperties.getPro("reg3")) ;
				}

				if(!actual.equals(this.col2)&&counter ==1){
					booleanAssert = false;
					errorMsg="期待値"+col2+":実行値"+actual;				}
				if(!actual.equals(this.col3)&&counter ==2){
					booleanAssert = false;
					errorMsg="期待値"+col3+":実行値"+actual;	
				}
				counter++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//assert check
		assertTrue(errorMsg,booleanAssert);
	}
	@Test
	public void UT002_004() {

		boolean booleanAssert = true;
		String errorMsg =null;
		//実行値
		ResultSet rs=null;
		RegInfDAO dao =new RegInfDAO();
		//execute 
		dao.getReglist();
		con =FactoryDs.getConnection();
		try {
			pstm = con.prepareStatement(GetProperties.getPro("selectAll"));
			 rs =pstm.executeQuery();
			//セレクトの戻り値格納用
			String actual=null;
			int counter =0;
			while(rs.next()){
				
				int colNum = Integer.parseInt(GetProperties.getPro("reg_col_num"));
				for(int index=0;index<colNum;index++ ){
					actual=rs.getString(GetProperties.getPro("reg1")) +",";
					actual=actual + rs.getString(GetProperties.getPro("reg2")) +",";
					actual=actual + rs.getString(GetProperties.getPro("reg3")) ;
				}
				if(!actual.equals(this.col1)&&counter ==0){
					booleanAssert = false;
					errorMsg="期待値"+col1+":実行値"+actual;	
					
				}
				if(!actual.equals(this.col2)&&counter ==1){
					booleanAssert = false;
					errorMsg="期待値"+col2+":実行値"+actual;				}
				if(!actual.equals(this.col3)&&counter ==2){
					booleanAssert = false;
					errorMsg="期待値"+col3+":実行値"+actual;	
				}
				counter++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//assert check
		assertTrue(errorMsg,booleanAssert);
	}

	@Test
	public void UT002_005() {
		//前準備処理を呼び出す
		UT002_005_pre();
		String errorMsg =null;
		RegInfDAO dao =new RegInfDAO();
		//期待値の設定
		String expected = GetProperties.getPro("noData");
		//実際の値を格納する変数
		String actual = null;
		//execute 
		actual = dao.getNextId();
		errorMsg=expected+":"+actual;
		//assert check
		assertEquals(errorMsg,expected,actual);
	}
	
	/**
	 *プロパティファイルが文字化けしなければイケてた
	 */
	@Ignore
	public void UT002_001_cool() {

		boolean booleanAssert = true;

		RegInfDAO dao =new RegInfDAO();
		//insert
		dao.insert("004","佐藤路未央","28");
		//check result
		con =FactoryDs.getConnection();
		try {
			pstm = con.prepareStatement(GetProperties.getPro("selectAll"));
			ResultSet rs =pstm.executeQuery();
			Integer unbox =1;
			while(rs.next()){
				String proKye="pattern1_col";
				String actual =null;
				int colNum = Integer.parseInt(GetProperties.getPro("reg_col_num"));
				for(int index=0;index<colNum;index++ ){
					actual=rs.getString(GetProperties.getPro("reg1")) +",";
					actual=actual + rs.getString(GetProperties.getPro("reg2")) +",";
					actual=actual + rs.getString(GetProperties.getPro("reg3")) ;
				}
					proKye =proKye + unbox;
					if(!actual.equals(GetProperties.getPro(proKye))){
						booleanAssert =false;
						System.out.println(GetProperties.getPro(proKye));
						System.out.println(actual);
					}
					unbox++;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//assert check
		assertTrue(booleanAssert);
	}

	
}
