package group99.ta.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Application entity.
 * Links one applicant to one opportunity.
 */
public class ApplicationRecord {
    private String applicationId;
    private String applicantId;
    private String opportunityId;
    private String motivation;
    private String status;
    private String screeningContent;
    private String submittedAt;

    // screening task submission
    private String screeningFileName;
    private String screeningSubmittedAt;

    public ApplicationRecord() {
    }

    public ApplicationRecord(String applicationId, String applicantId, String opportunityId,
                             String motivation, String status, String submittedAt,
                             String screeningFileName, String screeningSubmittedAt) {
        this.applicationId = applicationId;
        this.applicantId = applicantId;
        this.opportunityId = opportunityId;
        this.motivation = motivation;
        this.status = status;
        this.submittedAt = submittedAt;
        this.screeningFileName = screeningFileName;
        this.screeningSubmittedAt = screeningSubmittedAt;
    }

    public List<String> toRow() {
        List<String> row = new ArrayList<>();
        row.add(applicationId);
        row.add(applicantId);
        row.add(opportunityId);
        row.add(motivation);
        row.add(status);
        row.add(submittedAt);
        row.add(screeningFileName);
        row.add(screeningSubmittedAt);
        return row;
    }

    public static ApplicationRecord fromRow(List<String> row) {
        ApplicationRecord application = new ApplicationRecord();
        application.setApplicationId(getSafe(row, 0));
        application.setApplicantId(getSafe(row, 1));
        application.setOpportunityId(getSafe(row, 2));
        application.setMotivation(getSafe(row, 3));
        application.setStatus(getSafe(row, 4));
        application.setSubmittedAt(getSafe(row, 5));
        application.setScreeningFileName(getSafe(row, 6));
        application.setScreeningSubmittedAt(getSafe(row, 7));
        return application;
    }

    private static String getSafe(List<String> row, int index) {
        return index < row.size() ? row.get(index) : "";
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(String opportunityId) {
        this.opportunityId = opportunityId;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(String submittedAt) {
        this.submittedAt = submittedAt;
    }

    public String getScreeningFileName() {
        return screeningFileName;
    }

    public void setScreeningFileName(String screeningFileName) {
        this.screeningFileName = screeningFileName;
    }

    public String getScreeningSubmittedAt() {
        return screeningSubmittedAt;
    }

    public void setScreeningSubmittedAt(String screeningSubmittedAt) {
        this.screeningSubmittedAt = screeningSubmittedAt;
    }
    
    public String getScreeningContent() {
        return screeningContent;
    }

    public void setScreeningContent(String screeningContent) {
        this.screeningContent = screeningContent;
    }
}
