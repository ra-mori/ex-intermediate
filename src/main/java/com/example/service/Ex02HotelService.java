package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Ex02Hotel;
import com.example.repository.Ex02HotelRepository;

/**
 * ホテル検索の業務処理を行うサービスクラス.
 *
 * @author shigeki.morishita
 *
 */
@Service
@Transactional
public class Ex02HotelService {
	@Autowired
	private Ex02HotelRepository repository;

	/**
	 * priceの値によって処理を変えるメソッド.
	 * 
	 * priceが空文字ならfindAll、 priceが空文字でないならfindOfを呼び出す。
	 *
	 * @param price 入力された金額
	 * @return Ex02Hotel 全検索結果またはprice以下のホテルまたはサイズ0のリストを返す。
	 */
	public List<Ex02Hotel> searchByLessThanPrice(String price) {
		if (price.isEmpty()) {
			return repository.findAll();
		} else {
			Integer intPrice = Integer.parseInt(price);
			return repository.findOf(intPrice);
		}
	}

}
