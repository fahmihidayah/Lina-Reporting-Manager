/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fahmi.test;

import com.fahmi.controller.ReportController;
import com.fahmi.gui.FrameReport;
import com.fahmi.reportmanager.LinaReportingManager;

/**
 *
 * @author fahmi
 */
public class LinasReportManager {
    public static void main(String args[]){
        FrameReport frm = new FrameReport();
        LinaReportingManager lnRm = new LinaReportingManager();
        ReportController rpm = new ReportController(frm, lnRm);
    }
}
