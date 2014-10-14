package com.demo.entities;

import org.apache.ibatis.type.Alias;

@Alias("page")
public class Page extends BaseEntity {

	private static final long serialVersionUID = 3006416759699043377L;

	private final int DEFAULTPAGESIZE = 15; // 默认每页记录数

	private int curPage = 1; // 当前页码
	private int prePage = 1; // 前一页
	private int nextPage = 1; // 下一页
	private int totalPage = 1; // 总页数
	private int totalRec = 0; // 总记录数
	private int pageSize = DEFAULTPAGESIZE; // 每页记录数
	private int[] pageNumbers; // 页码数组
	private boolean openPageNumbers = true;// 是否开启页码数组
	private int pageNumbersLength = 10;// 页码数组长度
	private boolean evem;

	/**
	 * 无参构造函数
	 */
	public Page() {
	}

	public Page(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 开启页码数组构造函数
	 * 
	 * @param openPageNumbers
	 *            是否开启页码数组
	 * @param pageNumbersLength
	 *            页码数组长度
	 */
	public Page openPageNumbers(Boolean openPageNumbers, int pageNumbersLength) {
		this.openPageNumbers = openPageNumbers;
		this.pageNumbersLength = pageNumbersLength;
		this.pageNumbers = new int[pageNumbersLength];
		this.evem = this.pageNumbersLength % 2 == 0 ? true : false;
		return this;
	}

	/**
	 * 更新分页信息
	 * 
	 * @param totalRec
	 */
	public void update(int totalRec) {
		this.totalPage = totalRec % this.pageSize != 0 ? totalRec
				/ this.pageSize + 1 : totalRec / this.pageSize;// 计算总页数
		if (this.curPage > this.totalPage) {
			this.curPage = this.totalPage > 0 ? this.totalPage : 1;
		}
		this.prePage = this.curPage > 1 ? this.curPage - 1 : 1;// 上一页
		this.nextPage = this.curPage < this.totalPage ? this.curPage + 1
				: this.totalPage;// 下一页
		if (openPageNumbers) {// 是否启用页码数组
			if (this.totalPage > this.pageNumbersLength) {
				int j = this.pageNumbersLength / 2;
				if (this.curPage <= j) {
					for (int i = 0; i < this.pageNumbersLength; i++) {
						this.pageNumbers[i] = i + 1;
					}
				} else if (this.curPage + j > this.totalPage) {
					for (int i = this.pageNumbersLength - 1, t = this.totalPage; i >= 0; i--, t--) {
						this.pageNumbers[i] = t;
					}
				} else {
					for (int i = 0, t = this.curPage - j; i < this.pageNumbers.length; i++) {
						this.pageNumbers[i] = t + i;
					}
					if (!evem) {
						this.pageNumbers[j] = this.curPage;
					}
				}
			} else {
				this.pageNumbers = new int[this.totalPage];
				for (int i = 0; i < this.pageNumbers.length; i++) {
					this.pageNumbers[i] = i + 1;
				}
			}
		}
	}

	public Page openPageNumbers(int pageNumbersLength) {
		this.openPageNumbers = true;
		this.pageNumbersLength = pageNumbersLength;
		this.pageNumbers = new int[pageNumbersLength];
		return this;
	}

	public Page closePageNumbers() {
		this.openPageNumbers = false;
		this.pageNumbersLength = 0;
		this.pageNumbers = null;
		return this;
	}

	public Page setCurPage(int curPage) {
		this.curPage = curPage > 0 ? curPage : 1;
		return this;
	}

	public Page setPageSize(int pageSize) {
		this.pageSize = pageSize > 0 ? pageSize : this.pageSize;
		return this;
	}

	public int getCurPage() {
		return curPage;
	}

	public int getPrePage() {
		return prePage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getTotalRec() {
		return totalRec;
	}

	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 1、当没有开启时返回null 2、开启时返回页码数组，数组中不包含第一页与最后一页
	 * 
	 * @return
	 */
	public int[] getPageNumbers() {
		return pageNumbers;
	}

	public boolean isOpenPageNumbers() {
		return openPageNumbers;
	}

	public int getPageNumbersLength() {
		return pageNumbersLength;
	}

	public static void main(String[] args) {
		Page page = new Page();
		int to = 1000;
		for (int k = 1; k <= (to % 15 != 0 ? to / 15 + 1 : to / 15); k++) {
			page.setCurPage(k).openPageNumbers(5).update(to);
			System.out.println(page.curPage + "-" + page.totalPage);
			for (int i = 0; i < page.getPageNumbers().length; i++) {
				System.out.print(page.getPageNumbers()[i] + " ");
			}
			System.out.println();
			System.out.println("------------------------");
		}
	}
}
