package com.ingroinfo.mm.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/graph/stocks")
public class StocksDashboardController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("/lastthreemonths")
	public @ResponseBody String getStocksThree() {

		List<Map<String, Object>> stocksData = new ArrayList<>();
		try (Connection con = jdbcTemplate.getDataSource().getConnection();
				CallableStatement cs = con.prepareCall("{? = call stockslastthreemonths()}");) {
			cs.registerOutParameter(1, Types.REF_CURSOR);
			cs.execute();
			ResultSet rs = (ResultSet) cs.getObject(1);
			while (rs.next()) {
				Map<String, Object> row = new HashMap<>();
				row.put("type", rs.getString("type"));
				row.put("month_name", rs.getString("month_name"));
				row.put("month_number", rs.getInt("month_number"));
				row.put("year", rs.getInt("year"));
				row.put("total_quantity", rs.getInt("total_quantity"));
				stocksData.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(stocksData);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}

	}

	@GetMapping("/allmonths")
	public @ResponseBody List<Map<String, Object>> getMonthlyTotalQuantity() {
		
		String query = "SELECT TO_CHAR(date_created, 'Mon') AS month_name, " + "TO_CHAR(date_created, 'YYYY') AS year, "
				+ "SUM(quantity) AS total_quantity "
				+ "FROM (SELECT date_created, quantity FROM MM_INWARD_APPROVED_SPARES "
				+ "WHERE date_created >= TRUNC(SYSDATE, 'YEAR') AND date_created < ADD_MONTHS(TRUNC(SYSDATE, 'YEAR'), 12) "
				+ "UNION ALL SELECT date_created, quantity FROM MM_INWARD_APPROVED_MATERIALS "
				+ "WHERE date_created >= TRUNC(SYSDATE, 'YEAR') AND date_created < ADD_MONTHS(TRUNC(SYSDATE, 'YEAR'), 12) "
				+ "UNION ALL SELECT date_created, quantity FROM MM_INWARD_APPROVED_TOOLS "
				+ "WHERE date_created >= TRUNC(SYSDATE, 'YEAR') AND date_created < ADD_MONTHS(TRUNC(SYSDATE, 'YEAR'), 12)) all_tables "
				+ "GROUP BY TO_CHAR(date_created, 'Mon'), TO_CHAR(date_created, 'YYYY') ORDER BY TO_DATE(year || month_name || '01', 'YYYYMonDD') ASC";
		
		return jdbcTemplate.queryForList(query);
	}
}
