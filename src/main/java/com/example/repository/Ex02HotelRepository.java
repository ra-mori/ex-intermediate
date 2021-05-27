package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Ex02Hotel;

/**
 * hotelsテーブルを操作するリポジトリ.
 *
 * @author shigeki.morishita
 *
 */
@Repository
public class Ex02HotelRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
	private static final String TABLE_NAME = "hotels";

	/**
	 * hotelsオブジェクトを生成するローマッパー.
	 * 
	 */
	private static final RowMapper<Ex02Hotel> HOTEL_ROW_MAPPER = (rs, i) -> {
		Ex02Hotel hotel = new Ex02Hotel();

		hotel.setId(rs.getInt("id"));
		hotel.setAreaName(rs.getString("area_name"));
		hotel.setHotelName(rs.getString("hotel_name"));
		hotel.setAddress(rs.getString("address"));
		hotel.setNearestStation(rs.getString("nearest_station"));
		hotel.setPrice(rs.getInt("price"));
		hotel.setParking(rs.getString("parking"));

		return hotel;
	};

	/**
	 * ホテル情報を値段の降順で取得する.
	 * 
	 * @return ホテル全件検索、ホテル一覧 が存在しない場合はサイズ0件のホテル一覧を返す
	 */
	public List<Ex02Hotel> findAll() {
		String sql = "SELECT id,area_name,hotel_name,address,nearest_station,price,parking FROM " + TABLE_NAME
				+ " ORDER BY price DESC;";

		List<Ex02Hotel> hotelList = template.query(sql, HOTEL_ROW_MAPPER);

		return hotelList;
	}

	/**
	 * 入力された値段以下のホテル情報を取得する.
	 *
	 * @param price 入力された金額
	 * @return 入力されタ金額以下のホテル情報
	 */
	public List<Ex02Hotel> findOf(int price) {
		String sql = "SELECT id,area_name,hotel_name,address,nearest_station,price,parking FROM " + TABLE_NAME
				+ " WHERE price <= :price ORDER BY price DESC;";

		SqlParameterSource param = new MapSqlParameterSource().addValue("price", price);
		List<Ex02Hotel> hotelList = template.query(sql.toString(), param, HOTEL_ROW_MAPPER);
		return hotelList;
	}

}
