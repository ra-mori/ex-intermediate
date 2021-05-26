package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Ex01Team;

/**
 * teamsテーブルを操作するリポジトリ.
 *
 * @author shigeki.morishita
 *
 */
@Repository
public class Ex01TeamRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
	private static final String TABLE_NAME = "teams";

	/**
	 * Teamsオブジェクトを生成するローマッパー.
	 * 
	 */
	private static final RowMapper<Ex01Team> TEAM_ROW_MAPPER = (rs, i) -> {
		Ex01Team team = new Ex01Team();

		team.setId(rs.getInt("id"));
		team.setLeagueName(rs.getString("league_name"));
		team.setTeamName(rs.getString("team_name"));
		team.setHeadQuarters(rs.getString("headquarters"));
		team.setInauguration(rs.getString("inauguration"));
		team.setHistory(rs.getString("history"));
		return team;
	};

	/**
	 * 野球チーム一覧情報を発足日順で取得する.
	 * 
	 * @return 全野球チーム一覧 チームが存在しない場合はサイズ0件のチーム一覧を返す
	 */
	public List<Ex01Team> findAll() {
		String sql = "SELECT id,league_name,team_name,headquarters,inauguration,history FROM " + TABLE_NAME
				+ " ORDER BY inauguration;";

		List<Ex01Team> teamList = template.query(sql, TEAM_ROW_MAPPER);

		return teamList;
	}

	/**
	 * 主キー検索を行います.
	 * 
	 * @param id 検索したい主キーの値
	 * @return 野球チーム情報(検索されなかった場合は非検査例外が発生します)
	 */
	public Ex01Team load(Integer id) {
		String sql = "SELECT id,league_name,team_name,headquarters,inauguration,history FROM " + TABLE_NAME
				+ " WHERE id = :id;";
		System.out.println(sql);
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		Ex01Team team = template.queryForObject(sql, param, TEAM_ROW_MAPPER);

		return team;
	}

}
