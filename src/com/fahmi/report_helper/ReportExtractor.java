/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fahmi.report_helper;

import jxl.Sheet;

/**
 *
 * @author fahmi
 */
public interface ReportExtractor<T> {
    public T getData(Sheet sheet, int iRow);
}
