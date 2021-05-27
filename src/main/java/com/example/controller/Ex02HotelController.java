package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Ex02Hotel;
import com.example.form.Ex02HotelForm;
import com.example.service.Ex02HotelService;

/**
 * ホテル検索の処理を管理するコントローラ.
 *
 * @author shigeki.morishita
 *
 */
@Controller
@RequestMapping("/hotel")
public class Ex02HotelController {
	@Autowired
	private Ex02HotelService service;

	@ModelAttribute
	public Ex02HotelForm setUpForm() {
		return new Ex02HotelForm();
	}

	/**
	 * トップページ情報をビューにフォワードするメソッド.
	 *
	 * @return トップページへフォワード
	 */
	@RequestMapping("/search")
	public String index() {
		return "hotel-search";
	}

	/**
	 * 入力された金額によって呼び出しフォワードを変えるメソッド.
	 *
	 * @param form   Ex02HotelForm
	 * @param result リザルト
	 * @param model  モデル
	 * @return リポジトリの結果によってのリクエストスコープ
	 */
	@RequestMapping("/searched")
	public String search(@Validated Ex02HotelForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return index();
		}

		List<Ex02Hotel> hotelsList = service.searchByLessThanPrice(form.getPrice());
		if (hotelsList.size() != 0) {
			model.addAttribute("hotelsList", hotelsList);
			return "hotel-search";
		}

		model.addAttribute("message", "該当するホテルがありません");
		return "hotel-search";
	}

}
