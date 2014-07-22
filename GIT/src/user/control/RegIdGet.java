package user.control;

import java.io.IOException;
import java.io.PrintWriter;

import user.bean.RegistrantInfo;
import user.parts.ReadRegistInfo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.arnx.jsonic.JSON;

/**
 * Servlet implementation class RegistList
 */
public class RegIdGet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegIdGet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {	
			// 現在登録されている登録者を取得
			RegistrantInfo[] result = ReadRegistInfo.getReglist();

			String tempString = JSON.encode(result);
			String resInfo = "{\"regInfo\" : " + tempString+ "}";
			
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(resInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errMsg", "エラーが発生しました。申し訳ありませんがもう一度最初からお願いします。");
			HttpSession session = request.getSession(true);
		    session.invalidate();   
			RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
