package mysite.dao;

import mysite.db.DBConnection;
import mysite.vo.GuestBookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GuestBookDao {
    @Autowired
    private DBConnection dbConnection;

    public GuestBookVo get(Long no) {
        GuestBookVo guestBookVo = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dbConnection.getConnection();
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
        Long no = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = dbConnection.getConnection();
            String sql = "insert into guestbook " + "values(null, ?, now(), " +
                "?, password(?))";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, guestBookVo.getName());
            pstmt.setString(2, guestBookVo.getMessage());
            pstmt.setString(3, guestBookVo.getPasswd());

            pstmt.executeUpdate();

            stmt = conn.createStatement();
            rs = stmt.executeQuery("select last_insert_id()");

            if (rs.next()) {
                no = rs.getLong(1);
            }
            return no;
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

                if (stmt != null) {
                    stmt.close();
                }

                if (rs != null) {
                    rs.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            return no;
        }
    }

    public void delete(Long no) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dbConnection.getConnection();
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
        Connection conn = null;
        PreparedStatement pstmt = null;
        int ret = 0;

        try {
            conn = dbConnection.getConnection();
            String sql = "delete from guestbook " +
                "where no = ? " +
                "and passwd = password(?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, no);
            pstmt.setString(2, passwd);

            ret = pstmt.executeUpdate();

            //            System.out.println(no + " " + passwd + " " + ret);
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
        return ret;
    }

    public List<GuestBookVo> getList() {
        List<GuestBookVo> list = new ArrayList<GuestBookVo>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = dbConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "select * " +
                "from guestbook " +
                "order by reg_date desc";
            rs = stmt.executeQuery(sql);

            while (true) {
                if (rs.next() == false) {
                    break;
                }

                Long no = rs.getLong(1);
                String name = rs.getString(2);
                String regDate = rs.getString(3);
                String message = rs.getString(4);
                String passwd = rs.getString(5);

                GuestBookVo guestBookVo = new GuestBookVo();
                guestBookVo.setNo(no);
                guestBookVo.setName(name);
                guestBookVo.setRegDate(regDate);
                guestBookVo.setMessage(message);
                guestBookVo.setPasswd(passwd);

                list.add(guestBookVo);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
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
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public List<GuestBookVo> getList(Long page) {
        List<GuestBookVo> list = new ArrayList<GuestBookVo>();
        Connection conn = null;
        Statement stmt = null;  // 여기서는 preparestatement 써도 됨.
        ResultSet rs = null;

        try {
            conn = dbConnection.getConnection();
            stmt = conn.createStatement();

            String sql = "select *" +
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
