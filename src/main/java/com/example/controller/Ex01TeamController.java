package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Ex01Team;
import com.example.service.Ex01TeamService;

/**
 * チーム情報の検索処理をするコントローラ.
 *
 * @author shigeki.morishita
 *
 */
@Controller
@RequestMapping("/ex01")
public class Ex01TeamController {
	@Autowired
	private Ex01TeamService service;

	/**
	 * チーム一覧情報をビューに渡すメソッド.
	 *
	 * @param model モデル
	 * @return 各野球チームの情報
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Ex01Team> teamList = service.showList();

		model.addAttribute("teamList", teamList);

		return "team-list";
	}

	/**
	 * チーム詳細画面をビューに渡すメソッド.
	 *
	 * @param id    チームid
	 * @param model モデル
	 * @return 詳細画面へフォワード
	 */
	@RequestMapping("/showDetail")
	public String showDetail(Integer id, Model model) {
		Ex01Team team = service.showDetail(id);

		model.addAttribute("team", team);

		return "team-detail";
	}
}
