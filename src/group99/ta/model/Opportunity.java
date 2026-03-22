package group99.ta.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Opportunity entity created by Module Organiser.
 * Represents TA jobs / activities shown on the job board.
 */
public class Opportunity {
    private String opportunityId;
    private String moduleCode;
    private String moduleName;
    private String roleType;
    private String workingHours;
    private String deadline;
    private String englishRequirement;
    private String requiredSkills;
    private boolean interviewRequired;
    private boolean screeningRequired;
    private String screeningTaskTitle;
    private String screeningTaskDeadline;
    private String description;

    public Opportunity() {
    }

    public Opportunity(String opportunityId, String moduleCode, String moduleName, String roleType,
                       String workingHours, String deadline, String englishRequirement,
                       String requiredSkills, boolean interviewRequired, boolean screeningRequired,
                       String screeningTaskTitle, String screeningTaskDeadline, String description) {
        this.opportunityId = opportunityId;
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.roleType = roleType;
        this.workingHours = workingHours;
        this.deadline = deadline;
        this.englishRequirement = englishRequirement;
        this.requiredSkills = requiredSkills;
        this.interviewRequired = interviewRequired;
        this.screeningRequired = screeningRequired;
        this.screeningTaskTitle = screeningTaskTitle;
        this.screeningTaskDeadline = screeningTaskDeadline;
        this.description = description;
    }

    public List<String> toRow() {
        List<String> row = new ArrayList<>();
        row.add(opportunityId);
        row.add(moduleCode);
        row.add(moduleName);
        row.add(roleType);
        row.add(workingHours);
        row.add(deadline);
        row.add(englishRequirement);
        row.add(requiredSkills);
        row.add(String.valueOf(interviewRequired));
        row.add(String.valueOf(screeningRequired));
        row.add(screeningTaskTitle);
        row.add(screeningTaskDeadline);
        row.add(description);
        return row;
    }

    public static Opportunity fromRow(List<String> row) {
        Opportunity opportunity = new Opportunity();
        opportunity.setOpportunityId(getSafe(row, 0));
        opportunity.setModuleCode(getSafe(row, 1));
        opportunity.setModuleName(getSafe(row, 2));
        opportunity.setRoleType(getSafe(row, 3));
        opportunity.setWorkingHours(getSafe(row, 4));
        opportunity.setDeadline(getSafe(row, 5));
        opportunity.setEnglishRequirement(getSafe(row, 6));
        opportunity.setRequiredSkills(getSafe(row, 7));
        opportunity.setInterviewRequired(Boolean.parseBoolean(getSafe(row, 8)));
        opportunity.setScreeningRequired(Boolean.parseBoolean(getSafe(row, 9)));
        opportunity.setScreeningTaskTitle(getSafe(row, 10));
        opportunity.setScreeningTaskDeadline(getSafe(row, 11));
        opportunity.setDescription(getSafe(row, 12));
        return opportunity;
    }

    private static String getSafe(List<String> row, int index) {
        return index < row.size() ? row.get(index) : "";
    }

    public String getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(String opportunityId) {
        this.opportunityId = opportunityId;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getEnglishRequirement() {
        return englishRequirement;
    }

    public void setEnglishRequirement(String englishRequirement) {
        this.englishRequirement = englishRequirement;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public boolean isInterviewRequired() {
        return interviewRequired;
    }

    public void setInterviewRequired(boolean interviewRequired) {
        this.interviewRequired = interviewRequired;
    }

    public boolean isScreeningRequired() {
        return screeningRequired;
    }

    public void setScreeningRequired(boolean screeningRequired) {
        this.screeningRequired = screeningRequired;
    }

    public String getScreeningTaskTitle() {
        return screeningTaskTitle;
    }

    public void setScreeningTaskTitle(String screeningTaskTitle) {
        this.screeningTaskTitle = screeningTaskTitle;
    }

    public String getScreeningTaskDeadline() {
        return screeningTaskDeadline;
    }

    public void setScreeningTaskDeadline(String screeningTaskDeadline) {
        this.screeningTaskDeadline = screeningTaskDeadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
