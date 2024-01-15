package com.kitri.myservletboard.data;

public class Pagination {
    private int page;
    //    private int currentPage; // 조회하고자 하는 현재 페이지가 무엇인가?
    private int maxRecordsPerpage = 10; // 한페이지 들어갈 게시글수(몇개의 레코드를 최대 보여줄것인가)
    private int maxPagesOnScreen = 5; // 한 블럭당 들어갈 페이지 개수(최대 몇개의 페이지번호를 보여줄것인가)
    private int startIndex = 0; // 조회하고자 하는 게시글 리스트는 DB에서 몇번째부터 시작하는가?
    private int totalRecords = 0; // 테이블에 등록된 총 게시글 수(총 레코드는 몇개인가)

    private boolean hasPrev = false; // Previous 활성화, 비활성화
    private boolean hasNext = false; // Next 활성화, 비활성화
    private int startPageOnScrean = 1; // 블럭 페이지 시작 수
    private int endPageOnScrean = this.maxPagesOnScreen; // 블럭 페이지 마지막 수

    public void calcPagination() {
        // 페이지네이션 정보 계산
        // 1,2,3,4,5 -> start = 1, end = ??
        // 6,7,8,9,10 -> start = 6, end = ??
        int totalpages  // 전체 페이지수 ex) 110/10 = 11(올림)
                = (int)(Math.ceil((double) this.totalRecords / this.maxRecordsPerpage));

        this.startPageOnScrean // 블럭 페이지 시작 수 ex) (1-1)*5+1 =1, (2-1)*5+1=6, (3-1)*5+1=10
                = ((int)Math.ceil((double) this.page / this.maxPagesOnScreen) - 1 ) * this.maxPagesOnScreen + 1;

        this.endPageOnScrean // 블럭 페이지 마지막 수
                = this.startPageOnScrean + this.maxPagesOnScreen -1;
        if (this.endPageOnScrean > totalpages) { // 5 , 10 / 1+5-1=5 / 6+5-1=10
            this.endPageOnScrean = totalpages; // 11 = 11
        }

        if (this.startPageOnScrean > this.maxPagesOnScreen) {
            this.hasPrev = true; // Previous 활성화, 비활성화
        }

        if (this.endPageOnScrean < totalpages) {
            this.hasNext = true; // Next 활성화, 비활성화
        }
    }

    public Pagination() {
    }
    public Pagination(int page) {
        this.page = page;
    }
    public Pagination(int page, int maxRecordsPerPage) {
        this.page = page;
        this.maxRecordsPerpage = maxRecordsPerPage;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }

    public int getMaxRecordsPerpage() {
        return maxRecordsPerpage;
    }

    public void setMaxRecordsPerpage(int maxRecordsPerpage) {
        this.maxRecordsPerpage = maxRecordsPerpage;
    }

    public int getMaxPagesOnScreen() {
        return maxPagesOnScreen;
    }

    public void setMaxPagesOnScreen(int maxPagesOnScreen) {
        this.maxPagesOnScreen = maxPagesOnScreen;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPrev() {
        return hasPrev;
    }

    public void setHasPrev(boolean hasPrev) {
        this.hasPrev = hasPrev;
    }

    public int getStartPageOnScrean() {
        return startPageOnScrean;
    }

    public void setStartPageOnScrean(int startPageOnScrean) {
        this.startPageOnScrean = startPageOnScrean;
    }

    public int getEndPageOnScrean() {
        return endPageOnScrean;
    }

    public void setEndPageOnScrean(int endPageOnScrean) {
        this.endPageOnScrean = endPageOnScrean;
    }
}

