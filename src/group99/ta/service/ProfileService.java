package group99.ta.service;

import group99.ta.model.ApplicantProfile;
import group99.ta.storage.DataStore;

import java.util.List;

/**
 * Handles applicant profile creation, validation and progress calculation.
 * Maps to backlog items:
 * - TA-01 Create Applicant Profile
 * - TA-02 Record English Qualification
 * - TA-03 Upload CV
 * - TA-04 Declare Availability
 */
public class ProfileService {
    private final DataStore dataStore;

    public ProfileService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public void saveProfile(ApplicantProfile profile) {
        validateRequiredFields(profile);
        profile.setCompletionPercent(calculateCompletionPercent(profile));

        List<ApplicantProfile> allProfiles = dataStore.loadApplicants();
        boolean updated = false;

        for (int i = 0; i < allProfiles.size(); i++) {
            if (allProfiles.get(i).getApplicantId().equals(profile.getApplicantId())) {
                allProfiles.set(i, profile);
                updated = true;
                break;
            }
        }

        if (!updated) {
            allProfiles.add(profile);
        }

        dataStore.saveApplicants(allProfiles);
    }

    public ApplicantProfile findByApplicantId(String applicantId) {
        List<ApplicantProfile> profiles = dataStore.loadApplicants();
        for (ApplicantProfile profile : profiles) {
            if (profile.getApplicantId().equals(applicantId)) {
                return profile;
            }
        }
        return null;
    }

    public List<ApplicantProfile> getAllProfiles() {
        return dataStore.loadApplicants();
    }

    private void validateRequiredFields(ApplicantProfile profile) {
        if (isBlank(profile.getApplicantId())) {
            throw new IllegalArgumentException("Applicant ID is required.");
        }
        if (isBlank(profile.getFullName())) {
            throw new IllegalArgumentException("Full name is required.");
        }
        if (isBlank(profile.getStudentId())) {
            throw new IllegalArgumentException("Student ID is required.");
        }
        if (isBlank(profile.getEmail())) {
            throw new IllegalArgumentException("Email is required.");
        }
        if (isBlank(profile.getProgramme())) {
            throw new IllegalArgumentException("Programme is required.");
        }
        if (isBlank(profile.getYearOfStudy())) {
            throw new IllegalArgumentException("Year of study is required.");
        }
    }

    /**
     * Prototype shows a profile completion bar, so this method gives a simple percentage.
     */
    private int calculateCompletionPercent(ApplicantProfile profile) {
        int totalFields = 9;
        int completed = 0;

        if (!isBlank(profile.getFullName())) completed++;
        if (!isBlank(profile.getStudentId())) completed++;
        if (!isBlank(profile.getEmail())) completed++;
        if (!isBlank(profile.getProgramme())) completed++;
        if (!isBlank(profile.getYearOfStudy())) completed++;
        if (!isBlank(profile.getEnglishType())) completed++;
        if (!isBlank(profile.getEnglishScore())) completed++;
        if (!isBlank(profile.getCvFileName())) completed++;
        if (!isBlank(profile.getAvailability())) completed++;

        return (int) Math.round((completed * 100.0) / totalFields);
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}