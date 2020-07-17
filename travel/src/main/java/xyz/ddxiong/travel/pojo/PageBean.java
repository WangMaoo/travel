package xyz.ddxiong.travel.pojo;

import java.util.List;

/**
 * 采用java的封装思想,将浏览器需要的数据信息封装到一个对象中
 * 封装浏览器需要的数据信息
 */
public class PageBean<T> {
    private List<T> data; //本页需要展示的数据信息
    private int pageNumber; // 当前页页码
    private int pageSize; // 每页显示条数
    private int totalCount; // 总条数
    private int totalPage; // 总页数

    private int start; // 开始页
    private int end; // 终止页

    public PageBean(int pageSize, int totalCount) {
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "data=" + data +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                ", totalPage=" + getTotalPage() +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 计算总页数
     * @return
     */
    public int getTotalPage() {
        return (int)Math.ceil(totalCount * 1.0 / pageSize);
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
