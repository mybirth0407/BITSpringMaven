package mysite.dao;

import mysite.db.DBConnection;
import mysite.vo.GuestBookVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GuestBookDao {
    @Autowired
    private DBConnection dbConnection;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private SqlSession sqlSession;

    public GuestBookVo get(Long no) {
        GuestBookVo guestBookVo = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
//            conn = dbConnection.getConnection();
            conn = dataSource.getConnection();
            String sql = "select * " +
                "from guestbook " +
                "where no = ?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setLong(1, no);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                guestBookVo = new GuestBookVo();
                guestBookVo.setNo(rs.getLong(1));
                guestBookVo.setName(rs.getString(2));
                guestBookVo.setRegDate(rs.getString(3));
                guestBookVo.setMessage(rs.getString(4));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }

                if (rs != null) {
                    rs.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return guestBookVo;
    }

    public Long insert(GuestBookVo guestBookVo) {
        int count = sqlSession.insert("guestbook.insert", guestBookVo);
        System.out.println(count + ": " + guestBookVo.getNo());
        return guestBookVo.getNo();
    }

    public void delete(Long no) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
//            conn = dbConnection.getConnection();
            conn = dataSource.getConnection();
            String sql = "delete from guestbook " + "where no = ? ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, no);

            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int delete(Long no, String passwd) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("no", no);
        map.put("passwd", passwd);

        int count = sqlSession.delete("guestbook.delete", map);
        return count;
    }

    public List<GuestBookVo> getList() {
        List<GuestBookVo> list = sqlSession.selectList("guestbook.selectList");
        return list;
    }

    public List<GuestBookVo> getList(Long page) {
        List<GuestBookVo> list = new ArrayList<GuestBookVo>();
        Connection conn = null;
        Statement stmt = null;  // 여기서는 preparestatement 써도 됨.
        ResultSet rs = null;

        try {
//            conn = dbConnection.getConnection();
            conn = dataSource.getConnection();
            stmt = conn.createStatement();

            String sql = "select * " +
                "from guestbook " +
                "order by no desc " +
                "limit " + ((page - 1) * 5) + ", 5";

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Long no = rs.getLong(1);
                String name = rs.getString(2);
                String regDate = rs.getString(3);
                String message = rs.getString(4);

                GuestBookVo vo = new GuestBookVo();

                vo.setNo(no);
                vo.setName(name);
                vo.setRegDate(regDate);
                vo.setMessage(message);

                list.add(vo);
            }
        }
        catch (SQLException e) {
            System.out.println(e);
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }
}
