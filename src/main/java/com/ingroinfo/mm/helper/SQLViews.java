package com.ingroinfo.mm.helper;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SQLViews {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String MONTHWISE_STOCKS = "CREATE OR REPLACE VIEW DASHBOARD_MONTHWISE_STOCKS AS "
			+ "SELECT 'SPARES' AS TYPE,TO_CHAR( date_created, 'MON' ) AS month_name,"
			+ "EXTRACT( YEAR FROM date_created ) AS year,SUM( quantity ) AS total_quantity FROM "
			+ "MM_INWARD_APPROVED_SPARES WHERE date_created >= SYSDATE - INTERVAL '1' YEAR GROUP BY "
			+ "EXTRACT( YEAR FROM date_created ),TO_CHAR( date_created, 'MON' ) UNION SELECT "
			+ "'MATERIALS' AS TYPE,TO_CHAR( date_created, 'MON' ) AS month_name,"
			+ "EXTRACT( YEAR FROM date_created ) AS year,SUM( quantity ) AS total_quantity FROM "
			+ "MM_INWARD_APPROVED_MATERIALS WHERE date_created >= SYSDATE - INTERVAL '1' YEAR GROUP BY "
			+ "EXTRACT( YEAR FROM date_created ),TO_CHAR( date_created, 'MON' ) UNION SELECT "
			+ "'TOOLS' AS TYPE,TO_CHAR( date_created, 'MON' ) AS month_name,"
			+ "EXTRACT( YEAR FROM date_created ) AS year,SUM( quantity ) AS total_quantity FROM "
			+ "MM_INWARD_APPROVED_TOOLS WHERE date_created >= SYSDATE - INTERVAL '1' YEAR GROUP BY "
			+ "EXTRACT( YEAR FROM date_created ),TO_CHAR( date_created, 'MON' ) ORDER BY year ASC,"
			+ "month_name ASC,TYPE ASC ";

	private static final String MONTHWISE_TOTALSTOCKS = "CREATE OR REPLACE VIEW DASHBOARD_MONTHWISE_TOTALSTOCKS AS "
			+ "SELECT TO_CHAR( date_created, 'Mon' ) AS month_name,TO_CHAR( date_created, 'YYYY' ) AS year,"
			+ "SUM( quantity ) AS total_quantity FROM (SELECT date_created,quantity FROM "
			+ "MM_INWARD_APPROVED_SPARES WHERE date_created >= TRUNC( SYSDATE, 'YEAR' ) "
			+ "AND date_created < ADD_MONTHS( TRUNC( SYSDATE, 'YEAR' ), 12 ) UNION ALL SELECT date_created,"
			+ "quantity FROM MM_INWARD_APPROVED_MATERIALS WHERE date_created >= TRUNC( SYSDATE, 'YEAR' ) "
			+ "AND date_created < ADD_MONTHS( TRUNC( SYSDATE, 'YEAR' ), 12 ) UNION ALL SELECT date_created,"
			+ "quantity FROM MM_INWARD_APPROVED_TOOLS WHERE date_created >= TRUNC( SYSDATE, 'YEAR' ) "
			+ "AND date_created < ADD_MONTHS( TRUNC( SYSDATE, 'YEAR' ), 12 )) all_tables GROUP BY "
			+ "TO_CHAR( date_created, 'Mon' ),TO_CHAR( date_created, 'YYYY' ) ORDER BY "
			+ "TO_DATE( year || month_name || '01', 'YYYYMonDD' ) ASC";

	private static final String OUTWARD_STOCKS = "CREATE OR REPLACE VIEW DASHBOARD_OUTWARD_STOCKS AS SELECT "
			+ "UPPER( TO_CHAR( date_created, 'MON' ) ) AS month_name, "
			+ "SUM( CASE WHEN stock_type = 'ML' THEN final_quantity ELSE 0 END ) AS material_quantity, "
			+ "SUM( CASE WHEN stock_type = 'SP' THEN final_quantity ELSE 0 END ) AS spare_quantity, "
			+ "SUM( CASE WHEN stock_type = 'TE' THEN final_quantity ELSE 0 END ) AS tool_quantity  FROM "
			+ "mm_approved_workorder_items WHERE "
			+ "EXTRACT( YEAR FROM date_created ) = EXTRACT( YEAR FROM SYSDATE )  GROUP BY "
			+ "UPPER( TO_CHAR( date_created, 'MON' ))  ORDER BY TO_DATE( month_name, 'MON' ) ";

	@PostConstruct
	public void createView() {
		jdbcTemplate.execute(MONTHWISE_STOCKS);
		jdbcTemplate.execute(MONTHWISE_TOTALSTOCKS);
		jdbcTemplate.execute(OUTWARD_STOCKS);
	}
}
