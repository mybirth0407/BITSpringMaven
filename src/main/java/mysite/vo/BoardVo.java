package mysite.vo;

public class BoardVo {
    private Long no;
    private String title;
    private String content;
    private String regDate;
    private String userName;
    private Long userNo;
    private Long groupNo;
    private Long orderNo;
    private Long depth;
    private Long viewCount;

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRegDate() {
        return regDate;

    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public Long getUserNo() {
        return userNo;
    }

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    public Long getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(Long groupNo) {
        this.groupNo = groupNo;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Long getDepth() {
        return depth;
    }

    public void setDepth(Long depth) {
        this.depth = depth;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    @Override
    public String toString() {
        return "BoardVo{" +
            "no=" + no +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", regDate='" + regDate + '\'' +
            ", userName='" + userName + '\'' +
            ", userNo=" + userNo +
            ", groupNo=" + groupNo +
            ", orderNo=" + orderNo +
            ", depth=" + depth +
            ", viewCount=" + viewCount +
            '}';
    }
}
