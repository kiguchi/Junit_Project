package unit.user.parts;

import static org.junit.Assert.*;

import org.junit.Test;

import user.parts.RegInfCheck;

public class RegInfCheckTest {
	//プロパティファイルに分離するのは文字化けするため断念
	String input002 ="あいうえおかきくけこ";
	String input004 ="あいうえおかきくけこさ";
	String input009 = "１６";
	String input010 = "あいうえお";
	String input011 = "16あいうえお";
	
	String msg003_004 ="名前は10桁以内で入力してください。<br />";
	String msg007_008 ="年齢は(16-60)の範囲で入力してください。<br />";
	String msg009 = "年齢は数値(半角)で入力してください。<br />";
	String msg010_011 ="年齢は数値(半角)で入力してください。<br />年齢は(16-60)の範囲で入力してください。<br />";
	String msg013 ="登録可能なID（999）を超えています。管理者に問い合わせてください。<br />";
	
	public RegInfCheckTest(){
		
	}
	@Test
	public void UT001_001() {
		RegInfCheck source =new RegInfCheck();
		source.checkName("1234567890");
		String asert = source.getErrMsg();
		assertEquals(asert, "");
	}
	@Test
	public void UT001_002() {
		RegInfCheck source =new RegInfCheck();
		source.checkName(input002);
		String asert = source.getErrMsg();
		assertEquals(asert, "");
	}
	@Test
	public void UT001_003() {
		RegInfCheck source =new RegInfCheck();
		source.checkName("01234567890");
		String asert = source.getErrMsg();
		assertEquals(asert, msg003_004);
	}
	@Test
	public void UT001_004() {
		RegInfCheck source =new RegInfCheck();
		source.checkName(input004);
		String asert = source.getErrMsg();
		assertEquals(asert,msg003_004);
	}
	@Test
	public void UT001_005() {
		RegInfCheck source =new RegInfCheck();
		source.checkAge("16");
		String asert = source.getErrMsg();
		assertEquals(asert, "");
	}
	@Test
	public void UT001_006() {
		RegInfCheck source =new RegInfCheck();
		source.checkAge("60");
		String asert = source.getErrMsg();
		assertEquals(asert, "");
	}
	@Test
	public void UT001_007() {
		RegInfCheck source =new RegInfCheck();
		source.checkAge("15");
		String asert = source.getErrMsg();
		assertEquals(asert, msg007_008);
	}
	@Test
	public void UT001_008() {
		RegInfCheck source =new RegInfCheck();
		source.checkAge("61");
		String asert = source.getErrMsg();
		assertEquals(asert, msg007_008);
	}
	@Test
	public void UT001_009() {
		RegInfCheck source =new RegInfCheck();
		source.checkAge(input009);
		String asert = source.getErrMsg();
		assertEquals(asert, msg009);
	}
	@Test
	public void UT001_010() {
		RegInfCheck source =new RegInfCheck();
		source.checkAge(input010);
		String asert = source.getErrMsg();
		assertEquals(asert, msg010_011);
	}
	@Test
	public void UT001_011() {
		RegInfCheck source =new RegInfCheck();
		source.checkAge(input011);
		String asert = source.getErrMsg();
		assertEquals(asert,msg010_011);
	}
	@Test
	public void UT001_012() {
		RegInfCheck source =new RegInfCheck();
		source.checkId("999");
		String asert = source.getErrMsg();
		assertEquals(asert, "");
	}
	@Test
	public void UT001_013() {
		RegInfCheck source =new RegInfCheck();
		source.checkId("1000");
		String asert = source.getErrMsg();
		assertEquals(asert, msg013);
	}
}
