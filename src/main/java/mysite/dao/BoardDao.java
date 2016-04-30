package mysite.dao;

import mysite.config.Config;
import mysite.db.DBConnection;
import mysite.vo.BoardVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardDao {
    @Autowired
    private SqlSession sqlSession;

    public BoardVo get(Long no) {
        BoardVo boardVo = sqlSession.selectOne("board.selectByNo", no);
        return boardVo;
    }

    public void refreshOrder(Long groupNo, Long orderNo) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("groupNo", groupNo);
        map.put("orderNo", orderNo);
        List<Map<String, Object>> list = sqlSession.selectList(
            "board.refreshOrderSelect", map);
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            sqlSession.update("board.refreshOrderUpdate", list.get(i));
        }
    }

    public void viewCountIncrease(Long no) {
        sqlSession.update("board.viewCountIncrease", no);
    }

    public void write(BoardVo boardVo) {
        sqlSession.insert("board.write", boardVo);
    }

    public void delete(Long no) {
        sqlSession.delete("board.delete", no);
    }

    public void modify(BoardVo boardVo) {
        sqlSession.update("borad.modify", boardVo);
    }

    public void reply(BoardVo boardVo) {
        refreshOrder(boardVo.getGroupNo(), boardVo.getOrderNo());
        sqlSession.insert("board.reply", boardVo);
    }

    public List<BoardVo> getList() {
        List<BoardVo> list = sqlSession.selectList("board.getList");
        return list;
    }

    public List<BoardVo> pageGetList(String keyword, long page) {
        Long p = (page - 1) * Config.N_LIST;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("keyword", keyword);
        map.put("page", p);
        map.put("N_LIST", Config.N_LIST);

        List<BoardVo> list;
        if ("".equals(keyword) || keyword == null) {
            list = sqlSession.selectList("pageGetListKeywordNotExist", map);
        }
        else {
            list = sqlSession.selectList("pageGetListKeywordExist", map);
        }
        return list;
    }

    public Long searchCount(String keyword) {
        Long count;
        if ("".equals(keyword) || keyword == null) {
            count = sqlSession.selectOne(
                "searchCountKeywordNotExist", keyword);
        }
        else {
            count = sqlSession.selectOne(
                "searchCountKeywordExist");
        }
        return count;
    }
}
