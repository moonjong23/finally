package com.acorn.unicorn.dto;

import java.sql.Timestamp;

public class BoardDTO {
	private int idx, parent_idx, hit_cnt;
	private String title, content, del_gb, user_id;
	private Timestamp crea_date;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getParent_idx() {
		return parent_idx;
	}
	public void setParent_idx(int parent_idx) {
		this.parent_idx = parent_idx;
	}
	public int getHit_cnt() {
		return hit_cnt;
	}
	public void setHit_cnt(int hit_cnt) {
		this.hit_cnt = hit_cnt;
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
	public String getDel_gb() {
		return del_gb;
	}
	public void setDel_gb(String del_gb) {
		this.del_gb = del_gb;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Timestamp getCrea_date() {
		return crea_date;
	}
	public void setCrea_date(Timestamp crea_date) {
		this.crea_date = crea_date;
	}
	
	
	
}
