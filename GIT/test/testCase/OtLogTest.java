package testCase;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import employ.DateString;
import employ.OutLog;

public class OtLogTest {

	/**
	 * C:/test/log/log.txtを削除してからファイルとして作成する
	 */
	
	PrintWriter pw;
	@Before
	public void BEFORE() {


		
		File file=null;
		file = new File("C:\\test\\log\\log.txt");
		//check File existence
		if(file.exists()){
			file.delete();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void UT003_001() {
		//入力値格納量
		String input ="sample：サンプル";
		//出力結値格納用
		String actual = null;
		
		//テストするメソッドの実行
		OutLog.outLogDmp(input);
		//実行結果の取出し
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String expected =sdf.format(date);
		expected =expected +":"+"sample：サンプル";
		File file=null;
		BufferedReader br = null;
		file = new File("C:\\test\\log\\log.txt");
		try {
			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);
			actual = br.readLine();
	
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//エラー時のメッセージ生成
		String falsemsg =expected +":"+actual;
		//比較
		assertEquals(falsemsg,expected,actual);
		
	}
	@Test
	public void UT003_002() {
		//入力値格納量
		String input ="12345";
		//出力結値格納用
		String actual = null;
		
		//テストするメソッドの実行
		OutLog.outLogDmp(input);
		//実行結果の取出し
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String expected =sdf.format(date);
		expected =expected +":"+"12345";
		File file=null;
		BufferedReader br = null;
		file = new File("C:\\test\\log\\log.txt");
		try {
			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);
			actual = br.readLine();
	
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//エラー時のメッセージ生成
		String falsemsg =expected +":"+actual;
		//比較
		assertEquals(falsemsg,expected,actual);
		
	}

}
