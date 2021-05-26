package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Ex01Team;
import com.example.repository.Ex01TeamRepository;

/**
 * 野球チーム詳細検索の処理を行うサービスクラス.
 *
 * @author shigeki.morishita
 *
 */
@Service
public class Ex01TeamService {
	@Autowired
	private Ex01TeamRepository repository;

	/**
	 * チーム情報を全件取得の処理を行う.
	 * 
	 * @return 全野球チームの各情報。
	 */
	public List<Ex01Team> showList() {
		return repository.findAll();
	}

	/**
	 * チームの主キー検索の処理を行う.
	 *
	 * @param id 主キー
	 * @return 野球チームの詳細情報
	 */
	public Ex01Team showDetail(Integer id) {
		return repository.load(id);
	}
}
