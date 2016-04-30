package mysite.service;

import mysite.config.Config;
import mysite.dao.BoardDao;
import mysite.vo.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {
    @Autowired
    private BoardDao boardDao;

    public List<BoardVo> getList() {
        return boardDao.getList();
    }

    public List<BoardVo> pageGetList(Long page, String keyword) {
        if (page == null) {
            page = 1L;
        }
        List<BoardVo> list = boardDao.pageGetList(keyword, page);
        return list;
    }

    public Map<String, Long> pageMap(Long page, String keyword) {
        long left = 1, right = 1;
        long startPage, lastPage;
        long searchResult = boardDao.searchCount(keyword);
        long maxPage = searchResult / Config.N_LIST;

        if (searchResult % Config.N_LIST != 0) {
            maxPage++;
        }

        if (page < 1 || page > maxPage) {
            page = 1L;
        }

        long maxPageGroup = maxPage / Config.N_PAGE;

        if (maxPage % Config.N_PAGE != 0) {
            maxPageGroup++;
        }

        long selectedPageGroup = page / Config.N_PAGE;

        if (page % Config.N_PAGE != 0) {
            selectedPageGroup++;
        }

        if (selectedPageGroup == 1) {
            left = 0;
        }

        if (selectedPageGroup == maxPageGroup) {
            right = 0;
        }

        startPage = (selectedPageGroup - 1) * Config.N_PAGE + 1;
        lastPage = (selectedPageGroup) * Config.N_PAGE;

        if (lastPage > maxPage) {
            lastPage = maxPage;
        }

        Map<String, Long> map = new HashMap<String, Long>();
        map.put("left", left);
        map.put("right", right);
        map.put("startPage", startPage);
        map.put("lastPage", lastPage);
        map.put("page", page);
        map.put("total", searchResult);
        map.put("N_PAGE", Config.N_PAGE);
        map.put("N_LIST", Config.N_LIST);
        return map;
    }

    public void write(BoardVo boardVo) {
        boardDao.write(boardVo);
    }

    public BoardVo get(Long no) {
        return boardDao.get(no);
    }

    public void viewCountIncrease(Long no) {
        boardDao.viewCountIncrease(no);
    }

    public void reply(BoardVo boardVo) {
        boardDao.reply(boardVo);
    }

    public void delete(Long no) {
        boardDao.delete(no);
    }

    public void modify(BoardVo boardVo) {
        boardDao.modify(boardVo);
    }
}
