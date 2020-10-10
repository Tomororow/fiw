package com.fourfaith.utils;

import org.apache.commons.lang.math.NumberUtils;

import com.alibaba.druid.util.StringUtils;

/**
 * 对分页进行了封装，方便调用
 * @author wull
 */
public class PageUtil {

	protected static PagingBean pagingBean;

	/**
	 * 公用的分页方法
	 * @param s_start
	 * @param s_limit
	 * @param count
	 */
	public static PagingBean page(String s_start, String s_limit, int count,
			int defaultPageSize) {
		int start = 1;
		int limit = defaultPageSize;
		if (!StringUtils.isEmpty(s_start)) {
			start = NumberUtils.toInt(s_start);
		}
		if (!StringUtils.isEmpty(s_limit)) {
			limit = NumberUtils.toInt(s_limit);
		}
		start = (start - 1) * limit;
		pagingBean = new PagingBean(start, limit);
		pagingBean.setTotalItems(count);// 设置总记录数
		int pageNo = start / limit;
		pagingBean.setPageNo(pageNo + 1);// 当前页数
		pagingBean.setStart(start);
		pagingBean.setLimit(limit);
		int pageNum = 0;
		if (count % limit == 0) {
			pageNum = count / limit;
		} else {
			pageNum = (count / limit) + 1;
		}
		pagingBean.setPageNum(pageNum);
		if ((pageNo + 1) == 1) {
			pagingBean.setHasPrePage(false);
		} else {
			pagingBean.setHasPrePage(true);
		}
		if ((pageNo + 1) == pageNum) {
			pagingBean.setHasNextPage(false);
		} else {
			if (pageNum > 0) {
				pagingBean.setHasNextPage(true);
			} else {
				pagingBean.setHasNextPage(false);
			}

		}
		return pagingBean;
	}
	
}