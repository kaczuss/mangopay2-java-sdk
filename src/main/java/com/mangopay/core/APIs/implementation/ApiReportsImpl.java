package com.mangopay.core.APIs.implementation;

import com.mangopay.MangoPayApi;
import com.mangopay.core.*;
import com.mangopay.core.APIs.ApiBase;
import com.mangopay.core.APIs.ApiReports;
import com.mangopay.core.enumerations.ReportType;
import com.mangopay.entities.ReportRequest;

import java.util.List;

/**
 * API for reports.
 */
public class ApiReportsImpl extends ApiBase implements ApiReports {
    
    /**
     * Instantiates new ApiReportsImpl object.
     * @param root Root/parent instance that holds the OAuthToken and Configuration instance.
     */
    public ApiReportsImpl(MangoPayApi root) { super(root); }

    @Override
    public ReportRequest create(ReportRequest reportRequest) throws Exception {
        if (reportRequest.getReportType() == ReportType.NotSpecified) reportRequest.setReportType(ReportType.TRANSACTIONS);

        return create(null, reportRequest);
    }
    
    @Override
    public ReportRequest create(String idempotencyKey, ReportRequest reportRequest) throws Exception {
        if (reportRequest.getReportType() == ReportType.NotSpecified)  reportRequest.setReportType(ReportType.TRANSACTIONS);

        //ReportRequestTransport reportRequestTransport = ReportRequestTransport.CreateFromBusinessObject(reportRequest);
        //return this.createObject(ReportRequestTransport.class, idempotencyKey, "MethodKey.ReportRequest", reportRequestTransport, reportRequestTransport.ReportType.ToString()).GetBusinessObject();
        return this.createObject(ReportRequest.class, idempotencyKey, "reports_request", reportRequest, reportRequest.getReportType().toString());
    }
    
    @Override
    public ReportRequest get(String reportId) throws Exception {
        return this.getObject(ReportRequest.class, "reports_get", reportId);
    }
    
    @Override
    public List<ReportRequest> getAll(Pagination pagination, FilterReportsList filters, Sorting sort) throws Exception {
        if (filters == null) filters = new FilterReportsList();

        List<ReportRequest> result = this.getList(ReportRequest[].class, ReportRequest.class, "reports_get_all", pagination, null, filters.getValues(), sort);

        return result;
    }
    
    @Override
    public List<ReportRequest> getAll() throws Exception {
        return this.getAll(null, null, null);
    }
}
