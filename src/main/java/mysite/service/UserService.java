package mysite.service;

import mysite.dao.UserDao;
import mysite.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    //    @Autowired
    //    private MailSender mailSender;

    public void join(UserVo userVo) {
        userDao.insert(userVo);
        //        메일 보내기
    }

    //    public Boolean login(UserVo userVo) {
    //        UserVo authUser = userDao.get(userVo);
    //        Boolean ret = false;
    //        if (authUser != null) {
    //            ret = true;
    //        }
    //        return ret;
    //    }

    public UserVo login(UserVo userVo) {
        UserVo authUser = userDao.get(userVo);
        return authUser;
    }

    public UserVo getUser(String email) {
        UserVo userVo = userDao.get(email);
        return userVo;
    }

    public UserVo getUser(Long no) {
        UserVo userVo = userDao.get(no);
        return userVo;
    }

    public void modify(UserVo userVo) {
        userDao.update(userVo);
    }
}
