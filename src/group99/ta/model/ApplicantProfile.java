package group99.ta.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Applicant profile entity.
 * Used for TA-side profile creation and editing.
 */
public class ApplicantProfile {
    private String applicantId;
    private String fullName;
    private String studentId;
    private String email;
    private String programme;
    private String yearOfStudy;

    // English qualification
    private String englishType;
    private String englishScore;
    private String englishDate;

    // Supporting information
    private String cvFileName;
    private String availability;
    private String skills;

    // Simple progress indicator used by prototype
    private int completionPercent;

    public ApplicantProfile() {
    }

    public ApplicantProfile(String applicantId, String fullName, String studentId, String email,
                            String programme, String yearOfStudy, String englishType,
                            String englishScore, String englishDate, String cvFileName,
                            String availability, String skills, int completionPercent) {
        this.applicantId = applicantId;
        this.fullName = fullName;
        this.studentId = studentId;
        this.email = email;
        this.programme = programme;
        this.yearOfStudy = yearOfStudy;
        this.englishType = englishType;
        this.englishScore = englishScore;
        this.englishDate = englishDate;
        this.cvFileName = cvFileName;
        this.availability = availability;
        this.skills = skills;
        this.completionPercent = completionPercent;
    }

    public List<String> toRow() {
        List<String> row = new ArrayList<>();
        row.add(applicantId);
        row.add(fullName);
        row.add(studentId);
        row.add(email);
        row.add(programme);
        row.add(yearOfStudy);
        row.add(englishType);
        row.add(englishScore);
        row.add(englishDate);
        row.add(cvFileName);
        row.add(availability);
        row.add(skills);
        row.add(String.valueOf(completionPercent));
        return row;
    }

    public static ApplicantProfile fromRow(List<String> row) {
        ApplicantProfile profile = new ApplicantProfile();
        profile.setApplicantId(getSafe(row, 0));
        profile.setFullName(getSafe(row, 1));
        profile.setStudentId(getSafe(row, 2));
        profile.setEmail(getSafe(row, 3));
        profile.setProgramme(getSafe(row, 4));
        profile.setYearOfStudy(getSafe(row, 5));
        profile.setEnglishType(getSafe(row, 6));
        profile.setEnglishScore(getSafe(row, 7));
        profile.setEnglishDate(getSafe(row, 8));
        profile.setCvFileName(getSafe(row, 9));
        profile.setAvailability(getSafe(row, 10));
        profile.setSkills(getSafe(row, 11));

        String percent = getSafe(row, 12);
        try {
            profile.setCompletionPercent(Integer.parseInt(percent));
        } catch (NumberFormatException e) {
            profile.setCompletionPercent(0);
        }
        return profile;
    }

    private static String getSafe(List<String> row, int index) {
        return index < row.size() ? row.get(index) : "";
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public String getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(String yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public String getEnglishType() {
        return englishType;
    }

    public void setEnglishType(String englishType) {
        this.englishType = englishType;
    }

    public String getEnglishScore() {
        return englishScore;
    }

    public void setEnglishScore(String englishScore) {
        this.englishScore = englishScore;
    }

    public String getEnglishDate() {
        return englishDate;
    }

    public void setEnglishDate(String englishDate) {
        this.englishDate = englishDate;
    }

    public String getCvFileName() {
        return cvFileName;
    }

    public void setCvFileName(String cvFileName) {
        this.cvFileName = cvFileName;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public int getCompletionPercent() {
        return completionPercent;
    }

    public void setCompletionPercent(int completionPercent) {
        this.completionPercent = completionPercent;
    }
}
