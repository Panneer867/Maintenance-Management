package com.ingroinfo.mm.service;

import java.text.ParseException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

public interface MisReportService {

	JasperPrint generateAssetsReport(String from, String to) throws ParseException, JRException;
}
